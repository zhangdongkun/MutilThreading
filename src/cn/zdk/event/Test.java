package cn.zdk.event;


public class Test {
    @org.junit.Test
    public  void test() {
        DoorEvent doorEvent = new DoorEvent(this,"open");
        DoorListenerImplA doorListenerImplA = new DoorListenerImplA();
        doorListenerImplA.listen(doorEvent);
        doorEvent.setStat("123123");

        System.out.println(doorEvent.getClass().getEnclosingClass());


    }

}
