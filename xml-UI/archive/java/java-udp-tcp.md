***

## Ways to packet processing

### 用DatagramSocket发送udp报文
```java 
try (DatagramSocket datagramSocket = new DatagramSocket()) {
    // 3 bytes udp packet
    byte[] buffer = {10, 23, 12};
    byte[] IP = {10, 117, 4, 117};
    InetAddress address = InetAddress.getByAddress(IP);
    DatagramPacket packet = new DatagramPacket(
        buffer, buffer.length, address, 6831
    );
    datagramSocket.send(packet);
} catch (IOException e) {
    e.printStackTrace();
}
``` 