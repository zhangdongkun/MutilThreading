package cn.zdk.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class MyServer {

    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private final int PORT=6667;

    /**
     * 通过构造方法来初始化各个参数
     */
    public MyServer() {

        try {

            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
            serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    /**
     * @description:主要监听方法
     */
    public void listen(){

        try {

            while (true){

                int select = selector.select();
                if(select>0){

                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()){

                        SelectionKey selectionKey = iterator.next();
                        if(selectionKey.isAcceptable()){

                            SocketChannel socketChannel = serverSocketChannel.accept();
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector,SelectionKey.OP_READ);
                            System.out.println(socketChannel.getRemoteAddress()+"上线了");
                        }
                        if(selectionKey.isReadable()){

                            readData(selectionKey);
                        }
                        iterator.remove();
                    }
                }else {

                    System.out.println("系统正在等待人上线....");
                }
            }
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

        }
    }

    /**
     * @description:读取客户端发送的消息，并向其他客户端转发消息
     * @param selectionKey
     */
    private void readData(SelectionKey selectionKey){

        SocketChannel socketChannel = null;
        try {

            socketChannel = (SocketChannel)selectionKey.channel();
            socketChannel.configureBlocking(false);
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int read = socketChannel.read(byteBuffer);
            if(read>0){

                String msg = new String(byteBuffer.array());
                System.out.println("from客户端"+msg);
                // 向其他客户端转发消息
                sendInfoToOtherClient(msg,socketChannel);
            }
        } catch (IOException e) {

            try {

                System.out.println(socketChannel.getRemoteAddress()+"离线了");
                // 取消注册
                selectionKey.cancel();
                socketChannel.close();
            } catch (IOException ex) {

                ex.printStackTrace();
            }
        }

    }

    /**
     * @description: 向其他客户端转发消息
     * @param msg
     * @param socketChannel
     */
    private void sendInfoToOtherClient(String msg, SocketChannel socketChannel){

        System.out.println("服务器转发消息中");
        // 遍历所有注册到selector的所有channel,排除自己
        for (SelectionKey selectionKey : selector.keys()) {

            Channel taGetChannel = selectionKey.channel();
            if(taGetChannel instanceof SocketChannel && taGetChannel!=socketChannel){

                SocketChannel socketChannel1 = (SocketChannel)taGetChannel;
                ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());
                try {

                    socketChannel1.write(byteBuffer);
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {

        MyServer MyServer = new MyServer();
        MyServer.listen();
    }

}
