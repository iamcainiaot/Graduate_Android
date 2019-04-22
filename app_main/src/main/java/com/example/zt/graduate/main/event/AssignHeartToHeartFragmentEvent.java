package com.example.zt.graduate.main.event;

/**
 * @author taozhu5
 * @date 2019/4/16 14:32
 * @description 发送心声->心声墙的事件通知对象
 */
public class AssignHeartToHeartFragmentEvent {
    String message;

    public AssignHeartToHeartFragmentEvent() {

    }

    public AssignHeartToHeartFragmentEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
