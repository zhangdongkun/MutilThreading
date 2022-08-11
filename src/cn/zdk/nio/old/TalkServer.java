package cn.zdk.nio.old;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 支持多线程,支持心跳包
 * <p>
 * 1、TCP协议是面向连接的、可靠的、有序的、以字节流的方式发送数据，通过三次握手方式建立连接，形成传输数据的通道，在连接中进行大量数据的传输，效率会稍低
 * 2、Java中基于TCP协议实现网络通信的类
 * 客户端的Socket类
 * 服务器端的ServerSocket类
 * 3、Socket通信的步骤
 * ① 创建ServerSocket和Socket
 * ② 打开连接到Socket的输入/输出流
 * ③ 按照协议对Socket进行读/写操作
 * ④ 关闭输入输出流、关闭Socket
 * 4、服务器端：
 * ① 创建ServerSocket对象，绑定监听端口
 * ② 通过accept()方法监听客户端请求
 * ③ 连接建立后，通过输入流读取客户端发送的请求信息
 * ④ 通过输出流向客户端发送响应信息
 * ⑤ 关闭相关资源
 */

public class TalkServer {
    //客户端连接池

    public static void main(String args[]) throws IOException {

        ExecutorService pool = Executors.newFixedThreadPool(50);
        ServerSocket server = null;
        try {
            server = new ServerSocket(4701);

            Socket socket = null;

            while (true) {
                //使用accept()堵塞等待客户请求，有客户
                socket = server.accept();
                System.out.println("客户端上线");

                pool.execute(new Handler(socket));

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (server!=null) {
                server.close();

            }
        }

    }


}

class Handler implements Runnable {
    Socket socket;

    Handler(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {
        BufferedReader reader = null;
        PrintWriter writer = null;
        try {
            InputStream inputStream = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            OutputStream os = socket.getOutputStream();

            String line ;
            while ((line = reader.readLine() )!= null) {
                System.out.println("接受到客户端消息:" + line);

            }
            writer = new PrintWriter(os);
            writer.write("消息已收到");
            writer.flush();

        } catch (Exception e) {

        } finally {
            writer.close();
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
