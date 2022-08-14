package cn.zdk.nio;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class WriteTest {

    @Test
    public void  write() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("write.txt");
        fileOutputStream.write("123".getBytes());
        fileOutputStream.flush();
        fileOutputStream.close();
    }


    @Test
    public void  write2() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("write2.txt");
        //2.得到字节输出流对应的通道Channel
        FileChannel channel = fileOutputStream.getChannel();
        //3.分配缓存区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("我的一个buffer 写".getBytes());
        /**
         //3.flip() 为 将缓存区的界限设置为当前位置，并将当前位置设置为 0 可读模式

         */
        buffer.flip();
        channel.write(buffer);

        fileOutputStream.write("123".getBytes());
        channel.close();
    }
}
