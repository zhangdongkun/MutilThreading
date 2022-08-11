package cn.zdk.nio.old;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 5、客户端：
 * <p>
 * ① 创建Socket对象，指明需要连接的服务器的地址和端口号
 * ② 连接建立后，通过输出流想服务器端发送请求信息
 * ③ 通过输入流获取服务器响应的信息
 * ④ 关闭响应资源
 */
public class TalkClient {

    public static void main(String args[]) throws IOException {
        BufferedReader sin = null;
        PrintWriter writer = null;
        BufferedReader reader = null;
        Socket socket = null;
        try {

            socket = new Socket("127.0.0.1", 4701);

            //向本机的4700port发出客户请求
            sin = new BufferedReader(new InputStreamReader(System.in));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //
            Thread.sleep(2000);
            writer = new PrintWriter(socket.getOutputStream());
            String msg = "";
            //1、在网络上，readLine()是阻塞模式，也就是说如果readLine()读取不到数据的话，会一直阻塞，而不是返回null，所以如果你想要在while循环后执形相关操作是不可能的，因为while()里面是一个死循环，一旦读不到数据，它又开始阻塞，因此永远也无法执形while()循环外面的操作，所以应该把操作放在while循环里面。（在我做的即时通讯里，为了能够不断获取服务器返回的消息，就是用这种方法，不断去服务器获取消息，一旦有就返回。）
            while (!(msg = sin.readLine()).equals("ok")) {

                System.out.println("发生消息:" + msg);
                writer.println(msg);
                writer.write(msg);
                writer.flush();

            }
            //使用socket.shutdownOutput();来通知服务端发送完成
           /* 调用Socket.shutdownOutput()方法后，客户端输出的数据都将被发送，并加上 TCP 的正常连接终止序列（-1，也就是服务端终止循环的判断条件)，这样服务端读取数据时就不会被阻塞了。*/
            socket.shutdownOutput();
            System.out.println("接受到客户端的反馈:" + reader.readLine());


        } catch (Exception e) {

            System.out.println("Error" + e); //出错，则打印出错信息

        } finally {
            sin.close();

            writer.close(); //关闭Socket输出流
            reader.close(); //关闭Socket输入流

            socket.close(); //关闭Socket
        }

    }
}
