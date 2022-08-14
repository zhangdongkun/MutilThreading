package cn.zdk.nio;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * Buffer中的重要概念：
 *
 * 容量（capacity)：作为一个内存块，Buffer具有一定的固定大小，也称为”容量”，缓冲区容量不能为负，并且 创建后不能更改。
 * 限制（limit)：表示缓冲区中可以操作数据的大小（limit后数据不能进行读写）。缓冲区的限制不能为负，并且不能大于其容量。写入模式，限制等于buffer的容量。读取模式下，limit等于写入的数据量。
 * 位置‘(position)：下一个要读取或写入的数据的索引。缓’中区的位置不能为负，并且不能大于其限制
 * 标记（mark）与重置（reset)：标记是一个索弓l，通过Buffer中的mark(）方法指定Buffer中一个特定的 position，之后可以通过调用reset(）方法恢复到这个position。标记、位置、限制、容量遵守以T不变式：0<=mark<=position<=limit<=capacity
 * 图示
 *
 */


/**
 * 写入数据到Buffer
 *
 * 调用flip()方法，转换为读取模式
 *
 * 从Buffer中读取数据
 * 调用buffer.clear()方法或则buffer.compact()方法清除缓存区
 */
public class BufferTest {
    @Test
    public void test1(){
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put("a".getBytes());
        System.out.println(buffer.capacity());

        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println("-----------------------");

        //3.flip() 为 将缓存区的界限设置为当前位置，并将当前位置设置为 0 可读模式
        buffer.flip();
        System.out.println(buffer.position());//0
        System.out.println(buffer.limit());//7
        System.out.println(buffer.capacity());//10
        System.out.println("-----------------------");


        //4. get数据的读取
        char ch = (char) buffer.get();
        System.out.println(ch);//i
        System.out.println(buffer.position());//1
        System.out.println(buffer.limit());//7
        System.out.println(buffer.capacity());//10



    }


    @Test
    public void test03(){
        //创建一个非直接内存的缓存区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //buffer.isDirect()用于判断是否为直接内存
        System.out.println(buffer.isDirect());
        System.out.println("----------------");
        //创建一个直接内存的缓存区
        ByteBuffer buffer2 = ByteBuffer.allocateDirect(1024);
        System.out.println(buffer2.isDirect());
    }

}
