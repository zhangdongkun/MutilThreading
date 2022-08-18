package cn.zdk.event;

public class DoorListenerImplA implements DoorListener {
    @Override
    public void listen(DoorEvent doorEvent) {
        System.out.println( doorEvent.stat);
    }
}
