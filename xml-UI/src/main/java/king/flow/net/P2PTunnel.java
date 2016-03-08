package king.flow.net;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import king.flow.common.CommonConstants;
import static king.flow.data.TLSResult.PRS_CODE;
import king.flow.design.TLSProcessor;
import king.flow.test.net.MockClientReq;

/**
 *
 * @author Administrator
 */
public class P2PTunnel implements Tunnel {

    private final String host;
    private final int port;
    private final int timeout;
    private String responseMSG = null;

    public P2PTunnel(String hostName, int portNumber, int channelTimeout) {
        this.host = hostName;
        this.port = portNumber;
        this.timeout = channelTimeout;
    }

    @Override
    public String connect() {
        connectAndSend(null, null);
        return this.responseMSG;
    }

    @Override
    public String connect(int commmand, String message) {
        connectAndSend(commmand, message);
        return this.responseMSG;
    }

    private void connectAndSend(Integer command, String message) {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap(); // (1)
            b.group(workerGroup); // (2)
            b.channel(NioSocketChannel.class); // (3)
            b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
            b.option(ChannelOption.MAX_MESSAGES_PER_READ,
                    CommonConstants.MAX_MESSAGES_PER_READ); // (4)
            if (command == null || message == null) {
                b.handler(new ChannelInitializerImpl());
            } else {
                b.handler(new ChannelInitializerImpl(command, message));
            }

            // Start the client.
            ChannelFuture f = b.connect(host, port).sync(); // (5)
            // Wait until the connection is closed.
            //f.channel().closeFuture().sync();
            boolean completed = f.channel().closeFuture().await(timeout, TimeUnit.SECONDS); // (6)
            if (!completed) {
                String PRSCODE_PREFIX = "<" + PRS_CODE + ">";
                String PRSCODE_AFFIX = "</" + PRS_CODE + ">";

                String prsCode = "[Unkonwn prscode]";
                if (message == null) {
                } else {
                    int start = message.indexOf(PRSCODE_PREFIX);
                    int end = message.indexOf(PRSCODE_AFFIX) + PRSCODE_AFFIX.length();
                    prsCode = (start == -1 || end == -1) ? prsCode : message.substring(start, end);
                }
                Logger.getLogger(P2PTunnel.class.getName()).log(Level.WARNING,
                        "[{0}] operation exceeds {1}seconds and is timeout, channel is forcily closed",
                        new Object[]{prsCode, timeout});
                //forcily close channel to avoid connection leak
                //acutally if no forcible channel close calling, connection still will be closed.
                //but for comprehensive consideration, I call channel close again
                f.channel().close().sync();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(P2PTunnel.class.getName()).log(Level.SEVERE,
                    "channel management hits a problem, due to\n{0}", ex);
        } finally {
            workerGroup.shutdownGracefully();
        }
    }

    private class MessageClientHandler extends ChannelInboundHandlerAdapter {

        private String message = null;
        private StringBuilder sb = null;

        public MessageClientHandler() {
        }

        public MessageClientHandler(String msg) {
            this.message = msg;
            this.sb = new StringBuilder();
        }

        public MessageClientHandler setMessage(String message) {
            this.message = message;
            return this;
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            ctx.writeAndFlush(this.message);
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) {
            Logger.getLogger(MessageClientHandler.class.getName()).log(Level.CONFIG, msg.toString());
            sb.append(msg);
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            P2PTunnel.this.responseMSG = sb.toString();
            ctx.disconnect();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            Logger.getLogger(MessageClientHandler.class.getName()).log(Level.SEVERE, cause.getMessage(), cause);
            ctx.close();
        }
    }

    public static void main(String... args) throws JAXBException {
        // Parse options.
        String host = "127.0.0.1";
        int port = 8080;
        int code = 1;
        String msg = new TLSProcessor().init().createRegistryMsg("1", "sanlogin", "token");
        if (args != null && args.length >= 2) {
            host = args[0];
            port = Integer.parseInt(args[1]);
            if (args.length >= 4) {
                code = Integer.parseInt(args[2]);
                msg = args[3];
            }
        }

        Tunnel tunnel = TunnelBuilder.getTunnelBuilder().setHostName(host).setPortNumber(port).createTunnel();
        tunnel.connect(code, msg);
        System.out.println("0000000000000000 : " + ((P2PTunnel) tunnel).responseMSG);
        tunnel.connect(300, MockClientReq.mockBasicReq());
//        System.out.println("33333333330000000 : " + ((P2PTunnel)tunnel).responseMSG);
        tunnel.connect(400, MockClientReq.mockBasicReq());
    }

    private class ChannelInitializerImpl extends ChannelInitializer<SocketChannel> {

        private ChannelHandler[] handler_list = null;

        public ChannelInitializerImpl() {
            this.handler_list = new ChannelHandler[]{new CMDFieldPrepender(1), new LengthFieldPrepender(4),
                new StringEncoder(CommonConstants.UTF8), new StringDecoder(CommonConstants.UTF8),
                new MessageClientHandler("This is echo message")};
        }

        public ChannelInitializerImpl(int command, String message) {
            this.handler_list = new ChannelHandler[]{new CMDFieldPrepender(command), new LengthFieldPrepender(4),
                new StringEncoder(CommonConstants.UTF8), new StringDecoder(CommonConstants.UTF8),
                new MessageClientHandler(message)};//new DelimiterBasedFrameDecoder(2048, Delimiters.lineDelimiter()), 
        }

        public ChannelInitializerImpl(ChannelHandler... ch) {
            this.handler_list = ch;
        }

        @Override
        public void initChannel(SocketChannel ch) throws Exception {
            ch.pipeline().addLast(handler_list);
        }
    }

    private class CMDFieldPrepender extends MessageToByteEncoder<ByteBuf> {

        private int command = -1;

        public CMDFieldPrepender(int commandCode) {
            this.command = commandCode;
        }

        @Override
        protected void encode(ChannelHandlerContext ctx, ByteBuf msg, ByteBuf out) throws Exception {
            out.writeInt(this.command);
            out.writeBytes(msg, msg.readerIndex(), msg.readableBytes());
        }
    }
}
