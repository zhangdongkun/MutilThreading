package cn.zdk.event.msg;


import java.util.EventObject;

public class MsgEvent extends EventObject {
    private static final long serialVersionUID = 1L;

    //也可以用source来传递值 这里用status来传递值
    private int status;

    public MsgEvent(Object source) {
        super(source);
    }

    public MsgEvent(Object source, int status) {
        super(source);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
