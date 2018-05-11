<map version="1.0.1">
<!-- To view this file, download free mind mapping software FreeMind from http://freemind.sourceforge.net -->
<node COLOR="#990000" CREATED="1519785838736" ID="ID_1894290678" LINK="../../Users/liujin/Desktop/Work%20Agenda.mm" MODIFIED="1523511130606" TEXT="ethereum">
<font NAME="SansSerif" SIZE="16"/>
<node COLOR="#111111" CREATED="1519785868710" ID="ID_1318619135" LINK="https://github.com/ethereum/wiki/wiki/%5B%E4%B8%AD%E6%96%87%5D-%E4%BB%A5%E5%A4%AA%E5%9D%8A%E7%99%BD%E7%9A%AE%E4%B9%A6" MODIFIED="1520404947636" POSITION="left" TEXT="whitepaper">
<font NAME="SansSerif" SIZE="16"/>
<attribute_layout NAME_WIDTH="122" VALUE_WIDTH="198"/>
<attribute NAME="merkel tree" VALUE="download TX data partially root/leaf"/>
<attribute NAME="mine" VALUE="SHA256/POW/&lt; 2^190"/>
<attribute NAME="APPLY(S,TX)" VALUE="state transfer coin"/>
<attribute NAME="bitcoin" VALUE="value/blockchain-blindness"/>
<attribute NAME="ethcoin" VALUE="value/blockchain-awareness"/>
<attribute NAME="ether" VALUE="STARTGAS/GASPRICE"/>
<node CREATED="1522226995829" ID="ID_1062701533" LINK="https://github.com/ethereum/wiki/wiki/White-Paper" MODIFIED="1522227012700" TEXT="English whitepaper"/>
</node>
<node CREATED="1523153569699" ID="ID_513383478" LINK="https://medium.com/@collin.cusce/why-business-needs-ethereum-plasma-now-scaling-problems-pt-1-8d6186438b5" MODIFIED="1523153596594" POSITION="left" TEXT="scale"/>
<node CREATED="1522287623729" ID="ID_490832524" LINK="https://github.com/ethereum/wiki/wiki/Ethereum-Development-Tutorial" MODIFIED="1522287696968" POSITION="left" TEXT="Ethereum Development Tutorial">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    The purpose of this page is to serve as an introduction to the basics of Ethereum that you will need to understand from a development standpoint, in order to produce contracts and decentralized applications. For a general introduction to Ethereum, see

    <p>
      <a href="https://github.com/ethereum/wiki/wiki/White-Paper">the white paper</a>, and for a full technical spec see the <a href="http://gavwood.com/Paper.pdf" rel="nofollow">yellow</a>&#160; papers
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1522287849602" ID="ID_998953890" LINK="https://github.com/ethereum/wiki/wiki/Ethereum-Virtual-Machine-(EVM)-Awesome-List" MODIFIED="1523153576526" POSITION="left" TEXT="Ethereum Virtual Machine">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <ul>
      <li>
        <a href="https://github.com/ethereum/go-ethereum">go-ethereum</a>

        <ul>
          <li>
            A popular Ethereum client with its own EVM implementation (<a href="https://github.com/ethereum/go-ethereum/tree/master/core/vm">core/vm</a>&#160; directory)
          </li>
        </ul>
      </li>
      <li>
        <a href="https://github.com/paritytech/parity">Parity</a>&#160;in Rust

        <ul>
          <li>
            Another popular Ethereum client with its own EVM implementation (<a href="https://github.com/paritytech/parity/tree/master/ethcore">ethcore</a>&#160; directory)
          </li>
        </ul>
      </li>
      <li>
        <a href="https://github.com/ethereum/cpp-ethereum">cpp-ethereum</a>

        <ul>
          <li>
            An Ethereum client that generates the consensus test suite (<a href="https://github.com/ethereum/cpp-ethereum/blob/develop/libevm/VM.cpp">libevm/VM.cpp</a>)
          </li>
        </ul>
      </li>
      <li>
        <a href="https://github.com/ethereum/pyethereum">Pyethereum</a>&#160;in Python

        <ul>
          <li>
            Another client with probably the best readable EVM implementation (<a href="https://github.com/ethereum/pyethereum/blob/develop/ethereum/vm.py">ethereum/vm.py</a>)
          </li>
        </ul>
      </li>
      <li>
        <a href="https://github.com/pipermerriam/py-evm">Py-EVM</a>&#160;in Python

        <ul>
          <li>
            An alternate Python implementation designed to be highly configurable and modular.
          </li>
        </ul>
      </li>
      <li>
        <a href="https://github.com/ethereum/ethereumj">EthereumJ</a>&#160;in Java

        <ul>
          <li>
            A client with its own EVM implementation
          </li>
        </ul>
      </li>
    </ul>
  </body>
</html></richcontent>
<node CREATED="1522288437752" ID="ID_64308067" LINK="https://blog.qtum.org/diving-into-the-ethereum-vm-6e8d5d2f3c30" MODIFIED="1522288464760" TEXT="contract with solidity"/>
<node CREATED="1523172931611" ID="ID_1656920844" LINK="https://myetherwallet.github.io/knowledge-base/gas/what-is-gas-ethereum.html" MODIFIED="1523172950263" TEXT="gas"/>
</node>
<node COLOR="#111111" CREATED="1519885045803" ID="ID_1778209390" LINK="https://github.com/ethereum/wiki/wiki/Sharding-FAQ" MODIFIED="1520558715680" POSITION="left" TEXT="sharding">
<font NAME="SansSerif" SIZE="16"/>
<attribute_layout NAME_WIDTH="54" VALUE_WIDTH="342"/>
<attribute NAME="challenge" VALUE="scalability,txs 3-7 for bitcoin, 7-15 for ethcoin"/>
<attribute NAME="solution1" VALUE="use more altcoin(&#x4ee3;&#x5e01;), more security risk"/>
<attribute NAME="solution2" VALUE="increase block size, need supercomputer leading centralization"/>
<attribute NAME="solution3" VALUE="merge mining, a simple form of block size increasing"/>
<node COLOR="#111111" CREATED="1519885167362" ID="ID_743662414" LINK="https://github.com/ethereum/sharding/blob/develop/docs/doc.md" MODIFIED="1520404947636" TEXT="design">
<font NAME="SansSerif" SIZE="16"/>
</node>
</node>
<node CREATED="1520562519870" ID="ID_1528041180" LINK="https://github.com/ethereum/wiki/wiki/RLP" MODIFIED="1520562605332" POSITION="left" TEXT="RLP">
<attribute_layout NAME_WIDTH="47" VALUE_WIDTH="209"/>
<attribute NAME="purpose" VALUE="encode and serialize ethereum object"/>
</node>
<node CREATED="1521700213522" ID="ID_971630779" LINK="https://github.com/ethereum/wiki/wiki/Patricia-Tree" MODIFIED="1521700260057" POSITION="left" TEXT="Merkle Patricia Trie">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    Merkle Patricia tries provide a cryptographically authenticated data structure that can be used to store all (key, value) bindings
  </body>
</html></richcontent>
</node>
<node COLOR="#111111" CREATED="1519885101157" ID="ID_948042273" LINK="https://github.com/ethereum/research/wiki/A-note-on-data-availability-and-erasure-coding" MODIFIED="1520404947636" POSITION="left" TEXT="data availability">
<font NAME="SansSerif" SIZE="16"/>
</node>
<node COLOR="#111111" CREATED="1520302102172" ID="ID_797128013" LINK="https://www.myetherwallet.com/" MODIFIED="1520404947635" POSITION="left" TEXT="myetherwallet">
<font NAME="SansSerif" SIZE="16"/>
</node>
<node COLOR="#111111" CREATED="1520303200128" ID="ID_402439978" LINK="https://etherscan.io" MODIFIED="1520404947635" POSITION="left" TEXT="etherscan">
<font NAME="SansSerif" SIZE="16"/>
</node>
<node CREATED="1520404864210" ID="ID_917423830" MODIFIED="1523169228089" POSITION="right" TEXT="ethereumJ">
<font NAME="SansSerif" SIZE="16"/>
<node CREATED="1521599743224" ID="ID_1757556837" MODIFIED="1521599786453" TEXT="Sample">
<node CREATED="1521599754807" ID="ID_1042877527" MODIFIED="1521599951287" TEXT="BasicSample">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#160;*&#160;&#160;The base sample class which creates EthereumJ instance, tracks and report all the stages
    </p>
    <p>
      &#160;*&#160;&#160;of starting up like discovering nodes, connecting, syncing
    </p>
    <p>
      &#160;*
    </p>
    <p>
      &#160;*&#160;&#160;The class can be started as a standalone sample it should just run until full blockchain
    </p>
    <p>
      &#160;*&#160;&#160;sync and then just hanging, listening for new blocks and importing them into a DB
    </p>
    <p>
      &#160;*
    </p>
    <p>
      &#160;*&#160;&#160;This class is a Spring Component which makes it convenient to easily get access (autowire) to
    </p>
    <p>
      &#160;*&#160;&#160;all components created within EthereumJ. However almost all this could be done without dealing
    </p>
    <p>
      &#160;*&#160;&#160;with the Spring machinery from within a simple main method
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1521708187844" ID="ID_686165538" MODIFIED="1521708188969" TEXT="general">
<node CREATED="1521708197427" ID="ID_1108009354" MODIFIED="1521708210994" TEXT="WorldManager">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      WorldManager is a singleton containing references to different parts of the system
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1521707759197" ID="ID_850886646" MODIFIED="1521707921900" TEXT="discover">
<node CREATED="1521707773225" ID="ID_1610626300" MODIFIED="1521707863745" TEXT="NodeManager">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      The central class for Peer Discovery machinery
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1521707796727" ID="ID_1604970246" MODIFIED="1521707797586" TEXT="UDPListener"/>
<node CREATED="1521708000900" ID="ID_97420312" MODIFIED="1521708021283" TEXT="Node">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      byte[] id;
    </p>
    <p>
      String host;
    </p>
    <p>
      int port;
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1520406221328" ID="ID_950921087" LINK="https://github.com/ethereumj/ethereumj/blob/master/ethereumj-core/src/main/java/org/ethereum/net/client/PeerClient.java" MODIFIED="1522028187489" TEXT="PeerClient">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      This class creates the connection to an remote address using the Netty framework
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1521708279772" ID="ID_140272519" MODIFIED="1521708281990" TEXT="sync">
<node CREATED="1521602329555" ID="ID_1476637021" MODIFIED="1521708306396" TEXT="SyncManager">
<node CREATED="1521602340353" ID="ID_1225852316" MODIFIED="1521602341197" TEXT="BlockDownloader"/>
</node>
</node>
<node CREATED="1521708687704" ID="ID_614412727" MODIFIED="1521708689126" TEXT="net">
<node CREATED="1521708690199" ID="ID_181901873" MODIFIED="1523263382575" TEXT="ChannelManager">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      <font size="3">Channel central controller which deals with incoming connections that are processed by PeerServer </font>
    </p>
    <p>
      
    </p>
    <p>
      <font size="3">sendNewBlock(Block block, Channel receivedFrom) &#24191;&#25773;&#25910;&#21040;&#26032;block&#32473;3&#25104;&#30340;peers</font>
    </p>
    <p>
      <font size="3">* Propagates the new block message across active peers with exclusion of 'receivedFrom' peer. </font>
    </p>
    <p>
      <font size="3">* Distributes full block to 30% of peers and only its hash to remains </font>
    </p>
    <p>
      
    </p>
    <p>
      <font size="3">&#33258;&#25366;&#30340;block&#24191;&#25773;&#21040;&#20840;&#22495;&#30340;peers </font>
    </p>
    <p>
      <font size="3">sendNewBlock(Block block)</font>
    </p>
    <p>
      
    </p>
    <p>
      
    </p>
    <table border="0" style="width: 80%; border-top-style: solid; border-right-style: solid; border-bottom-style: solid; border-left-style: solid; border-top-width: 0; border-right-width: 0; border-bottom-width: 0; border-left-width: 0">
      <tr>
        <td valign="top" style="width: 50%; border-top-style: solid; border-right-style: solid; border-bottom-style: solid; border-left-style: solid; border-top-width: 1; border-right-width: 1; border-bottom-width: 1; border-left-width: 1">
          <p style="margin-top: 1; margin-right: 1; margin-bottom: 1; margin-left: 1">
            <font size="3">validateAndAddNewBlock (SyncManager)</font>
          </p>
        </td>
        <td valign="top" style="width: 50%; border-top-style: solid; border-right-style: solid; border-bottom-style: solid; border-left-style: solid; border-top-width: 1; border-right-width: 1; border-bottom-width: 1; border-left-width: 1">
          <p style="margin-top: 1; margin-right: 1; margin-bottom: 1; margin-left: 1">
            <font size="3">Adds NEW block to the queue </font>
          </p>
        </td>
      </tr>
      <tr>
        <td valign="top" style="width: 50%; border-top-style: solid; border-right-style: solid; border-bottom-style: solid; border-left-style: solid; border-top-width: 1; border-right-width: 1; border-bottom-width: 1; border-left-width: 1">
          <p style="margin-top: 1; margin-right: 1; margin-bottom: 1; margin-left: 1">
            <font size="3">sendTransaction </font>
          </p>
        </td>
        <td valign="top" style="width: 50%; border-top-style: solid; border-right-style: solid; border-bottom-style: solid; border-left-style: solid; border-top-width: 1; border-right-width: 1; border-bottom-width: 1; border-left-width: 1">
          <p style="margin-top: 1; margin-right: 1; margin-bottom: 1; margin-left: 1">
            <font size="3">Propagates the transactions message across active peers with exclusion of&#160;'receivedFrom' peer. </font>
          </p>
        </td>
      </tr>
      <tr>
        <td valign="top" style="width: 50%; border-top-style: solid; border-right-style: solid; border-bottom-style: solid; border-left-style: solid; border-top-width: 1; border-right-width: 1; border-bottom-width: 1; border-left-width: 1">
          <p style="margin-top: 1; margin-right: 1; margin-bottom: 1; margin-left: 1">
            <font size="3">sendNewBlock </font>
          </p>
        </td>
        <td valign="top" style="width: 50%; border-top-style: solid; border-right-style: solid; border-bottom-style: solid; border-left-style: solid; border-top-width: 1; border-right-width: 1; border-bottom-width: 1; border-left-width: 1">
          <p style="margin-top: 1; margin-right: 1; margin-bottom: 1; margin-left: 1">
            <font size="3">Propagates the new block message across active peers Suitable only for self-mined blocks </font>
          </p>
        </td>
      </tr>
    </table>
  </body>
</html></richcontent>
<attribute_layout NAME_WIDTH="141" VALUE_WIDTH="171"/>
</node>
<node CREATED="1521708691879" ID="ID_435380565" MODIFIED="1521708730788" TEXT="PeerServer">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      This class establishes a listener for incoming connections
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1522029433094" ID="ID_820626456" MODIFIED="1523262477377" TEXT="EthHandler">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      <font size="3">&#160;* Process the messages between peers with 'eth' capability on the network&lt;br&gt; </font>
    </p>
    <p>
      <font size="3">&#160;* Contains common logic to all supported versions </font>
    </p>
    <p>
      <font size="3">&#160;* delegating version specific stuff to its descendants </font>
    </p>
    <p>
      
    </p>
    <p>
      <font size="3">core method is channelRead0(final ChannelHandlerContext ctx, EthMessage msg) , dealing with all messages related ETH protocol among peers in network </font>
    </p>
  </body>
</html></richcontent>
<node CREATED="1522029443019" ID="ID_1206421369" MODIFIED="1523262446419" TEXT="Eth62">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      <font size="3">remote peer -&gt; Eth62.processGetBlockHeaders -&gt; blockchain.getListOfHeadersStartFrom -&gt; EthHandler.sendMessage -&gt; MessageQueue.sendMessage </font>
    </p>
    <p>
      
    </p>
    <p>
      <font size="3">Eth62.processTransactions -&gt; TransactionExecutor.instance.submitTransaction(TransactionTask) -&gt; channelManager.sendTransaction </font>
    </p>
    <p>
      
    </p>
    <p>
      <font size="3">override EthHandle's channelRead0(final ChannelHandlerContext ctx, EthMessage msg), </font>
    </p>
    <p>
      
    </p>
    <p>
      <font size="3">switch (msg.getCommand()) { </font>
    </p>
    <p>
      <font size="3">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;case STATUS: </font>
    </p>
    <p>
      <font size="3">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;processStatus((StatusMessage) msg, ctx); </font>
    </p>
    <p>
      <font size="3">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;break; </font>
    </p>
    <p>
      <font size="3">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;case NEW_BLOCK_HASHES: </font>
    </p>
    <p>
      <font size="3">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;processNewBlockHashes((NewBlockHashesMessage) msg); </font>
    </p>
    <p>
      <font size="3">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;break; </font>
    </p>
    <p>
      <font size="3">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;case TRANSACTIONS: </font>
    </p>
    <p>
      <font size="3">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;processTransactions((TransactionsMessage) msg); </font>
    </p>
    <p>
      <font size="3">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;break; </font>
    </p>
    <p>
      <font size="3">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;case GET_BLOCK_HEADERS: </font>
    </p>
    <p>
      <font size="3">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;processGetBlockHeaders((GetBlockHeadersMessage) msg); </font>
    </p>
    <p>
      <font size="3">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;break; </font>
    </p>
    <p>
      <font size="3">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;case BLOCK_HEADERS: </font>
    </p>
    <p>
      <font size="3">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;processBlockHeaders((BlockHeadersMessage) msg); </font>
    </p>
    <p>
      <font size="3">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;break; </font>
    </p>
    <p>
      <font size="3">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;case GET_BLOCK_BODIES: </font>
    </p>
    <p>
      <font size="3">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;processGetBlockBodies((GetBlockBodiesMessage) msg); </font>
    </p>
    <p>
      <font size="3">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;break; </font>
    </p>
    <p>
      <font size="3">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;case BLOCK_BODIES: </font>
    </p>
    <p>
      <font size="3">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;processBlockBodies((BlockBodiesMessage) msg); </font>
    </p>
    <p>
      <font size="3">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;break; </font>
    </p>
    <p>
      <font size="3">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;case NEW_BLOCK: </font>
    </p>
    <p>
      <font size="3">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;processNewBlock((NewBlockMessage) msg); </font>
    </p>
    <p>
      <font size="3">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;break; </font>
    </p>
    <p>
      <font size="3">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;default: </font>
    </p>
    <p>
      <font size="3">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;break; </font>
    </p>
    <p>
      <font size="3">}</font>
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1522030566297" ID="ID_1155004861" MODIFIED="1522033980934" TEXT="MessageQueue">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      <font size="4">&#160;* This class contains the logic for sending messages in a queue </font>
    </p>
    <p>
      <font size="4">&#160;* </font>
    </p>
    <p>
      <font size="4">&#160;* Messages open by send and answered by receive of appropriate message </font>
    </p>
    <p>
      <font size="4">&#160;*&#160;&#160;&#160;&#160;&#160;&#160;PING by PONG </font>
    </p>
    <p>
      <font size="4">&#160;*&#160;&#160;&#160;&#160;&#160;&#160;GET_PEERS by PEERS </font>
    </p>
    <p>
      <font size="4">&#160;*&#160;&#160;&#160;&#160;&#160;&#160;GET_TRANSACTIONS by TRANSACTIONS </font>
    </p>
    <p>
      <font size="4">&#160;*&#160;&#160;&#160;&#160;&#160;&#160;GET_BLOCK_HASHES by BLOCK_HASHES </font>
    </p>
    <p>
      <font size="4">&#160;*&#160;&#160;&#160;&#160;&#160;&#160;GET_BLOCKS by BLOCKS </font>
    </p>
    <p>
      <font size="4">&#160;* </font>
    </p>
    <p>
      <font size="4">&#160;* The following messages will not be answered: </font>
    </p>
    <p>
      <font size="4">&#160;*&#160;&#160;&#160;&#160;&#160;&#160;PONG, PEERS, HELLO, STATUS, TRANSACTIONS, BLOCKS</font>
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1522044623358" ID="ID_505009263" MODIFIED="1522044625327" TEXT="mine">
<node CREATED="1520406027721" ID="ID_1649087125" MODIFIED="1522287192669" TEXT="BlockMiner">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Manages embedded CPU mining and allows to use external miners.
    </p>
    <p>
      
    </p>
    <p>
      self-mine flow:
    </p>
    <p>
      <font size="3">O -&gt; BlockMiner.restartMining -&gt; BlockMiner.getNewBlockForMining -&gt; BlockchainImpl.createNewBlock -&gt; BlockMiner.blockMined -&gt; EthereumImpl.addNewMinedBlock -&gt; ChannelManager.sendNewBlock -&gt; remote active peers</font>
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1520405539930" ID="ID_471569449" LINK="https://github.com/ethereumj/ethereumj/blob/master/ethereumj-core/src/main/java/org/ethereum/facade/Blockchain.java" MODIFIED="1522226399283" TEXT="Blockchain">
<node CREATED="1521185483914" ID="ID_867265956" LINK="https://github.com/ethereumj/ethereumj/blob/master/ethereumj-core/src/main/java/org/ethereum/core/BlockchainImpl.java" MODIFIED="1521708312006" TEXT="BlockchainImpl">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      <font size="4">The main difference between Ethereum and Bitcoin with regard to the blockchain architecture is that, </font>
    </p>
    <p>
      <font size="4">* unlike Bitcoin, Ethereum blocks contain a copy of both the transaction list and the most recent state. </font>
    </p>
    <p>
      <font size="4">* Aside from that, two other values, the block number and the difficulty, are also stored in the block.</font>
    </p>
  </body>
</html></richcontent>
<attribute_layout NAME_WIDTH="96" VALUE_WIDTH="96"/>
<attribute NAME="TransactionStore" VALUE=""/>
<attribute NAME="BlockStore" VALUE=""/>
<node CREATED="1521448959753" ID="ID_279761961" MODIFIED="1521448991661" TEXT="TransactionStore">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#160;* Storage (tx hash) =&gt; List of (block idx, tx idx, TransactionReceipt)
    </p>
    <p>
      &#160;*
    </p>
    <p>
      &#160;* Since a transaction could be included into blocks from different forks and
    </p>
    <p>
      &#160;* have different receipts the class stores all of them (the same manner fork blocks are stored)
    </p>
  </body>
</html></richcontent>
<node CREATED="1521449221142" ID="ID_969221664" MODIFIED="1521515222762" TEXT="TransactionInfo">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#160;* Contains Transaction execution info:
    </p>
    <p>
      &#160;* its receipt and execution context
    </p>
    <p>
      &#160;* If the transaction is still in pending state the context is the
    </p>
    <p>
      &#160;* hash of the parent block on top of which the transaction was executed
    </p>
    <p>
      &#160;* If the transaction is already mined into a block the context
    </p>
    <p>
      &#160;* is the containing block and the index of the transaction in that block
    </p>
  </body>
</html></richcontent>
<attribute_layout NAME_WIDTH="107" VALUE_WIDTH="107"/>
<attribute NAME="TransactionReceipt" VALUE=""/>
<attribute NAME="blockHash" VALUE=""/>
<attribute NAME="parentBlockHash" VALUE=""/>
<attribute NAME="index" VALUE=""/>
</node>
<node CREATED="1521515267304" ID="ID_125277422" MODIFIED="1521515320647" TEXT="TransactionReceipt">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#160;* The transaction receipt is a tuple of three items
    </p>
    <p>
      &#160;* comprising the transaction, together with the post-transaction state,
    </p>
    <p>
      &#160;* and the cumulative gas used in the block containing the transaction receipt
    </p>
  </body>
</html></richcontent>
</node>
</node>
</node>
<node CREATED="1521597109997" ID="ID_103322692" MODIFIED="1521597111981" TEXT="EasyBlockchain">
<node CREATED="1521597123259" ID="ID_19323866" MODIFIED="1521597125134" TEXT="LocalBlockchain">
<node CREATED="1521597160697" ID="ID_1123028574" MODIFIED="1521597161666" TEXT="StandaloneBlockchain"/>
</node>
</node>
<node CREATED="1521598685794" ID="ID_92362436" MODIFIED="1521598689116" TEXT="config">
<node CREATED="1521598699119" ID="ID_461640139" MODIFIED="1521598700838" TEXT="BlockchainConfig">
<node CREATED="1521598819701" ID="ID_1447093029" MODIFIED="1521598822046" TEXT="Eip150HFConfig"/>
<node CREATED="1521598830546" ID="ID_78076378" MODIFIED="1521598831656" TEXT="Eip160HFConfig"/>
<node CREATED="1521598839444" ID="ID_991142023" MODIFIED="1521598840569" TEXT="ByzantiumConfig"/>
</node>
<node CREATED="1521598770008" ID="ID_1439535778" MODIFIED="1521598784451" TEXT="BlockchainNetConfig">
<node CREATED="1521598897599" ID="ID_1243534318" MODIFIED="1521598898239" TEXT="BaseNetConfig"/>
</node>
</node>
<node CREATED="1521599210693" ID="ID_1083558250" MODIFIED="1521599255072" TEXT="JournalSource">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#160;* The JournalSource records all the changes which were made before each commitUpdate
    </p>
    <p>
      &#160;* Unlike 'put' deletes are not propagated to the backing Source immediately but are
    </p>
    <p>
      &#160;* delayed until 'persistUpdate' is called for the corresponding hash.
    </p>
    <p>
      &#160;* Also 'revertUpdate' might be called for a hash, in this case all inserts are removed
    </p>
    <p>
      &#160;* from the database.
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1521599334842" ID="ID_1991147108" MODIFIED="1521599335404" TEXT="PruneManager"/>
</node>
<node CREATED="1522286566082" FOLDED="true" ID="ID_1379421117" MODIFIED="1523171184691" TEXT="VM">
<node CREATED="1523169239801" ID="ID_1855076525" MODIFIED="1523169279046" TEXT="VM">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      <font size="3">&#160;* The Ethereum Virtual Machine (EVM) is responsible for initialization </font>
    </p>
    <p>
      <font size="3">&#160;* and executing a transaction on a contract. </font>
    </p>
    <p>
      <font size="3">&#160;* </font>
    </p>
    <p>
      <font size="3">&#160;* It is a quasi-Turing-complete machine; the quasi qualification </font>
    </p>
    <p>
      <font size="3">&#160;* comes from the fact that the computation is intrinsically bounded </font>
    </p>
    <p>
      <font size="3">&#160;* through a parameter, gas, which limits the total amount of computation done. </font>
    </p>
    <p>
      <font size="3">&#160;* </font>
    </p>
    <p>
      <font size="3">&#160;* The EVM is a simple stack-based architecture. The word size of the machine </font>
    </p>
    <p>
      <font size="3">&#160;* (and thus size of stack item) is 256-bit. This was chosen to facilitate </font>
    </p>
    <p>
      <font size="3">&#160;* the SHA3-256 hash scheme and&#160;&#160;elliptic-curve computations. The memory model </font>
    </p>
    <p>
      <font size="3">&#160;* is a simple word-addressed byte array. The stack has an unlimited size. </font>
    </p>
    <p>
      <font size="3">&#160;* The machine also has an independent storage model; this is similar in concept </font>
    </p>
    <p>
      <font size="3">&#160;* to the memory but rather than a byte array, it is a word-addressable word array. </font>
    </p>
    <p>
      <font size="3">&#160;* </font>
    </p>
    <p>
      <font size="3">&#160;* Unlike memory, which is volatile, storage is non volatile and is </font>
    </p>
    <p>
      <font size="3">&#160;* maintained as part of the system state. All locations in both storage </font>
    </p>
    <p>
      <font size="3">&#160;* and memory are well-defined initially as zero. </font>
    </p>
    <p>
      <font size="3">&#160;* </font>
    </p>
    <p>
      <font size="3">&#160;* The machine does not follow the standard von Neumann architecture. </font>
    </p>
    <p>
      <font size="3">&#160;* Rather than storing program code in generally-accessible memory or storage, </font>
    </p>
    <p>
      <font size="3">&#160;* it is stored separately in a virtual ROM interactable only though </font>
    </p>
    <p>
      <font size="3">&#160;* a specialised instruction. </font>
    </p>
    <p>
      <font size="3">&#160;* </font>
    </p>
    <p>
      <font size="3">&#160;* The machine can have exceptional execution for several reasons, </font>
    </p>
    <p>
      <font size="3">&#160;* including stack underflows and invalid instructions. These unambiguously </font>
    </p>
    <p>
      <font size="3">&#160;* and validly result in immediate halting of the machine with all state changes </font>
    </p>
    <p>
      <font size="3">&#160;* left intact. The one piece of exceptional execution that does not leave </font>
    </p>
    <p>
      <font size="3">&#160;* state changes intact is the out-of-gas (OOG) exception. </font>
    </p>
    <p>
      <font size="3">&#160;* </font>
    </p>
    <p>
      <font size="3">&#160;* Here, the machine halts immediately and reports the issue to </font>
    </p>
    <p>
      <font size="3">&#160;* the execution agent (either the transaction processor or, recursively, </font>
    </p>
    <p>
      <font size="3">&#160;* the spawning execution environment) and which will deal with it separately.</font>
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1523169245305" ID="ID_792443362" MODIFIED="1523170864904" TEXT="Program">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      <font size="3">&#160;/** </font>
    </p>
    <p>
      <font size="3">&#160;&#160;&#160;&#160;&#160;* This attribute defines the number of recursive calls allowed in the EVM </font>
    </p>
    <p>
      <font size="3">&#160;&#160;&#160;&#160;&#160;* Note: For the JVM to reach this level without a StackOverflow exception, </font>
    </p>
    <p>
      <font size="3">&#160;&#160;&#160;&#160;&#160;* ethereumj may need to be started with a JVM argument to increase </font>
    </p>
    <p>
      <font size="3">&#160;&#160;&#160;&#160;&#160;* the stack size. For example: -Xss10m </font>
    </p>
    <p>
      <font size="3">&#160;&#160;&#160;&#160;&#160;*/ </font>
    </p>
    <p>
      <font size="3">&#160;&#160;&#160;&#160;private static final int MAX_DEPTH = 1024;</font>
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1522286573544" ID="ID_1003179085" MODIFIED="1522286584429" TEXT="ProgramInvokeFactoryImpl"/>
<node CREATED="1522286611504" ID="ID_696450371" MODIFIED="1522286612284" TEXT="ProgramInvokeImpl"/>
</node>
<node CREATED="1520404894446" FOLDED="true" ID="ID_567234089" LINK="https://github.com/ethereumj/ethereumj/blob/master/ethereumj-core/src/main/java/org/ethereum/facade/Repository.java" MODIFIED="1523171188218" TEXT="Repository">
<font NAME="SansSerif" SIZE="16"/>
<node CREATED="1521537200163" ID="ID_624512765" MODIFIED="1521537231203" TEXT="RepositoryImpl">
<attribute_layout NAME_WIDTH="84" VALUE_WIDTH="84"/>
<attribute NAME="createAccount" VALUE=""/>
</node>
<node CREATED="1521595074044" ID="ID_397428066" MODIFIED="1521595261957" TEXT="AccountState">
<attribute NAME="BigInteger" VALUE="nonce"/>
<attribute NAME="BigInteger" VALUE="balance"/>
<attribute NAME="byte[]" VALUE="stateRoot"/>
</node>
</node>
<node CREATED="1520405090238" ID="ID_1956800188" LINK="https://github.com/ethereumj/ethereumj/blob/master/ethereumj-core/src/main/java/org/ethereum/facade/Ethereum.java" MODIFIED="1520405172575" TEXT="Ethereum">
<node CREATED="1521600912662" ID="ID_1318432016" MODIFIED="1521600916135" TEXT="EthereumImpl"/>
<node CREATED="1521597808726" ID="ID_1526767566" MODIFIED="1521597809883" TEXT="EthereumListener">
<node CREATED="1521597847443" ID="ID_468499897" MODIFIED="1521597848412" TEXT="CompositeEthereumListener"/>
</node>
</node>
<node CREATED="1521596685628" ID="ID_1551290717" MODIFIED="1521596686659" TEXT="Contract">
<node CREATED="1521596767607" ID="ID_345314616" MODIFIED="1521596768763" TEXT="SolidityContract">
<node CREATED="1521596745447" ID="ID_740922279" MODIFIED="1521596746182" TEXT="SolidityContractImpl"/>
</node>
</node>
<node CREATED="1521596326446" ID="ID_1806060178" MODIFIED="1521596328727" TEXT="ContractDetails">
<node CREATED="1521596402883" ID="ID_1300828544" MODIFIED="1521596434493" TEXT="ContractDetailsImpl">
<attribute NAME="byte[]" VALUE="address"/>
</node>
</node>
<node CREATED="1520405657680" ID="ID_1345568268" LINK="https://github.com/ethereumj/ethereumj/blob/master/ethereumj-core/src/main/java/org/ethereum/core/Block.java" MODIFIED="1520405711254" TEXT="Block">
<node CREATED="1520405768375" ID="ID_1407155466" LINK="https://github.com/ethereumj/ethereumj/blob/master/ethereumj-core/src/main/java/org/ethereum/core/BlockHeader.java" MODIFIED="1520405790956" TEXT="BlockHeader"/>
<node CREATED="1523262853372" ID="ID_411894769" MODIFIED="1523263031301" TEXT="BlockIdentifier">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      <font size="3">* Block identifier holds block hash and number &lt;br&gt; </font>
    </p>
    <p>
      <font size="3">&#160;* This tuple is used in some places of the core </font>
    </p>
    <p>
      
    </p>
    <p>
      <font size="3">get block number and compare with current block, the bigger number is latest block in chain</font>
    </p>
    <p>
      
    </p>
    <p>
      
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1520405929437" ID="ID_654105876" LINK="https://github.com/ethereumj/ethereumj/blob/master/ethereumj-core/src/main/java/org/ethereum/core/Transaction.java" MODIFIED="1521448600983" TEXT="Transaction">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#160;* A transaction (formally, T) is a single cryptographically signed instruction sent by an actor external to Ethereum.
    </p>
    <p>
      &#160;* An external actor can be a person (via a mobile device or desktop computer) or could be from a piece of automated software running on a server.
    </p>
    <p>
      &#160;* There are two types of transactions:
    </p>
    <p>
      &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;those which result in message calls
    </p>
    <p>
      &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;those which result in the creation of new contracts.
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1521597372631" ID="ID_1284056504" MODIFIED="1521597416661" TEXT="Genesis">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      The genesis block is the first block in the chain and has fixed values according to
    </p>
    <p>
      the protocol specification
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1521511028743" ID="ID_1286990669" MODIFIED="1521511104973" TEXT="RLP">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Recursive Length Prefix (RLP) encoding.
    </p>
    <p>
      
    </p>
    <p>
      The purpose of RLP is to encode arbitrarily nested arrays of binary data, and
    </p>
    <p>
      RLP is the main encoding method used to serialize objects in Ethereum. The
    </p>
    <p>
      only purpose of RLP is to encode structure; encoding specific atomic data
    </p>
    <p>
      types (eg. strings, integers, floats) is left up to higher-order protocols; in
    </p>
    <p>
      Ethereum the standard is that integers are represented in big endian binary
    </p>
    <p>
      form. If one wishes to use RLP to encode a dictionary, the two suggested
    </p>
    <p>
      canonical forms are to either use [[k1,v1],[k2,v2]...] with keys in
    </p>
    <p>
      lexicographic order or to use the higher-level Patricia Tree encoding as
    </p>
    <p>
      Ethereum does.
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1521598150764" ID="ID_807385509" MODIFIED="1521598177018" TEXT="ECKey">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#160;* &lt;p&gt;Represents an elliptic curve public and (optionally) private key, usable for digital signatures but not encryption.
    </p>
    <p>
      &#160;* Creating a new ECKey with the empty constructor will generate a new random keypair. Other static methods can be used
    </p>
    <p>
      &#160;* when you already have the public or private parts. If you create a key with only the public part, you can check
    </p>
    <p>
      &#160;* signatures but not create them.&lt;/p&gt;
    </p>
    <p>
      &#160;*
    </p>
    <p>
      &#160;* &lt;p&gt;The ECDSA algorithm supports &lt;i&gt;key recovery&lt;/i&gt; in which a signature plus a couple of discriminator bits can
    </p>
    <p>
      &#160;* be reversed to find the public key used to calculate it. This can be convenient when you have a message and a
    </p>
    <p>
      &#160;* signature and want to find out who signed it, rather than requiring the user to provide the expected identity.&lt;/p&gt;
    </p>
    <p>
      &#160;*
    </p>
    <p>
      &#160;* This code is borrowed from the bitcoinj project and altered to fit Ethereum.&lt;br&gt;
    </p>
    <p>
      &#160;* See &lt;a href=&quot;https://github.com/bitcoinj/bitcoinj/blob/master/core/src/main/java/com/google/bitcoin/core/ECKey.java&quot;&gt;
    </p>
    <p>
      &#160;* bitcoinj on GitHub&lt;/a&gt;.
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1520498604413" FOLDED="true" ID="ID_735199492" MODIFIED="1526026364286" POSITION="right" TEXT="go-ethereum">
<node CREATED="1520498635165" ID="ID_930890117" LINK="https://github.com/ethereum/go-ethereum/blob/master/core/types/block.go" MODIFIED="1521181830353" TEXT="block">
<attribute_layout NAME_WIDTH="73" VALUE_WIDTH="110"/>
<attribute NAME="header" VALUE="block_header"/>
<attribute NAME="header[]" VALUE="uncles_header"/>
<attribute NAME="transaction[]" VALUE="transactions"/>
<attribute NAME="body" VALUE="transactions&amp;uncles"/>
<node CREATED="1521180006069" ID="ID_999331010" LINK="https://github.com/ethereum/go-ethereum/blob/master/core/types/transaction.go" MODIFIED="1521180014202" TEXT="transaction"/>
</node>
<node CREATED="1521187898856" ID="ID_56127015" LINK="https://github.com/ethereum/go-ethereum/blob/master/core/blockchain.go" MODIFIED="1521187910624" TEXT="blockchain"/>
</node>
</node>
</map>
