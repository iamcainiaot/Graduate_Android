package com.example.zt.graduate.main.chat.chat_message;

/**
 * @author taozhu5
 * @date 2019/4/19 22:19
 * @description 描述
 */
public class ChatModel {
    public ChatModel() {
    }

    public ChatModel(boolean isLeft, String msg) {
        this.isLeft = isLeft;
        this.msg = msg;
    }

    public ChatModel(boolean isLeft, String msg, String imageUrl) {
        this.isLeft = isLeft;
        this.msg = msg;
        this.imageUrl = imageUrl;
    }

    private boolean isLeft;
    private String msg;
    private String imageUrl;

    public boolean isLeft() {
        return isLeft;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setLeft(boolean left) {
        isLeft = left;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
