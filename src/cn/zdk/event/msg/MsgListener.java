package cn.zdk.event.msg;

import java.util.EventListener;

public interface MsgListener extends EventListener {
    void handleMsg(MsgEvent event) throws Exception;
}
