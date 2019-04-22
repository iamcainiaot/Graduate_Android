package com.example.zt.graduate.main.fragment.searching.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import mvp.BaseResponse;

/**
 * @author taozhu5
 * @date 2019年4月9日 14:18:11
 * @description 匹配好友响应数据
 */
public class SearchingResponse extends BaseResponse implements Parcelable {
    /**
     * 心声id
     */
    private String heartId;

    /**
     * 图片
     */
    private List<String> heartImageUrl;

    /**
     * 内容
     */
    private String content;

    /**
     * 用户Id
     */
    private String userId;
    /**
     * 用户头像
     */
    private String imageUrl;
    /**
     * 用户性别
     */
    private String sex;
    /**
     * 个性标签
     */
    private String label;

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHeartId() {
        return heartId;
    }

    public void setHeartId(String heartId) {
        this.heartId = heartId;
    }

    public List<String> getHeartImageUrl() {
        return heartImageUrl;
    }

    public void setHeartImageUrl(List<String> heartImageUrl) {
        this.heartImageUrl = heartImageUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "HeartResponse{" +
                "heartId='" + heartId + '\'' +
                ", heartImageUrl=" + heartImageUrl +
                ", content='" + content + '\'' +
                ", userId='" + userId + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", sex='" + sex + '\'' +
                ", label='" + label + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }

    /**
     * 实现Parcel 接口
     */
    protected SearchingResponse(Parcel in) {
        heartId = in.readString();
        heartImageUrl = in.createStringArrayList();
        content = in.readString();
        userId = in.readString();
        imageUrl = in.readString();
        sex = in.readString();
        label = in.readString();
        userName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(heartId);
        dest.writeStringList(heartImageUrl);
        dest.writeString(content);
        dest.writeString(userId);
        dest.writeString(imageUrl);
        dest.writeString(sex);
        dest.writeString(label);
        dest.writeString(userName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SearchingResponse> CREATOR = new Creator<SearchingResponse>() {
        @Override
        public SearchingResponse createFromParcel(Parcel in) {
            return new SearchingResponse(in);
        }

        @Override
        public SearchingResponse[] newArray(int size) {
            return new SearchingResponse[size];
        }
    };
}