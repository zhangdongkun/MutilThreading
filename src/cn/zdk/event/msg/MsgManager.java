package cn.zdk.event.msg;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MsgManager {
    static List<MsgListener> msgListeners = new ArrayList<>();

    public static void addListener(MsgListener listener) {
        msgListeners.add(listener);
    }
    public static void sendMsg(MsgEvent event) throws Exception {
        notifyListeners(event);
    }
    public static void notifyListeners(MsgEvent event) throws Exception {
        for (MsgListener listener : msgListeners) {
            listener.handleMsg(event);
        }
    }
   @Test
    public  void test() {
        try {
            //监听器A
            MsgManager.addListener(new MsgListener() {
                @Override
                public void handleMsg(MsgEvent event) throws Exception {
                    System.out.println("MsgListenerA," + event.getStatus());
                }
            });
            //监听器B
            MsgManager.addListener(new MsgListener() {
                @Override
                public void handleMsg(MsgEvent event) throws Exception {
                    System.out.println("MsgListenerB," + event.getStatus());
                }
            });
            MsgEvent msg1 = new MsgEvent(this, 11);
            sendMsg(msg1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
