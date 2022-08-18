package cn.zdk.event;

import java.util.EventListener;
//事件监听器注册在事件源上，当事件源的属性或状态改变的时候，调用相应监听器内的回调方法。
public interface DoorListener extends EventListener {

      void listen(DoorEvent doorEvent);



}
