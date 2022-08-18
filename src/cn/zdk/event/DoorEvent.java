package cn.zdk.event;

import java.util.EventObject;

/**
 * 继承EventObject 定义事件
 */
public class DoorEvent extends EventObject {
    String stat = "";
    public DoorEvent(Object source, String stat) {
        super(source);
        this.stat = stat;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }
}
