package lib_utils.db.entity;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

import java.util.Objects;

/**
 * @author taozhu5
 * @date 2019/3/13 18:53
 * @description 描述
 */
@Entity
public class UserInfoTable implements Parcelable {
    /**
     * userId
     */
    @Property(nameInDb = "userId")
    private String userId;
    /**
     * 用户名
     */
    @Property(nameInDb = "userName")
    private String name;
    /**
     * 性别
     */
    @Property(nameInDb = "sex")
    private String sex;
    /**
     * 个性标签
     */
    @Property(nameInDb = "label")
    private String label;
    /**
     * 用户头像
     */
    @Property(nameInDb = "imageUrl")
    private String imageUrl;

    @Generated(hash = 157910945)
    public UserInfoTable(String userId, String name, String sex, String label,
                         String imageUrl) {
        this.userId = userId;
        this.name = name;
        this.sex = sex;
        this.label = label;
        this.imageUrl = imageUrl;
    }

    @Generated(hash = 1354492153)
    public UserInfoTable() {
    }

    protected UserInfoTable(Parcel in) {
        userId = in.readString();
        name = in.readString();
        sex = in.readString();
        label = in.readString();
        imageUrl = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userId);
        dest.writeString(name);
        dest.writeString(sex);
        dest.writeString(label);
        dest.writeString(imageUrl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserInfoTable> CREATOR = new Creator<UserInfoTable>() {
        @Override
        public UserInfoTable createFromParcel(Parcel in) {
            return new UserInfoTable(in);
        }

        @Override
        public UserInfoTable[] newArray(int size) {
            return new UserInfoTable[size];
        }
    };

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserInfoTable{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", label='" + label + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfoTable that = (UserInfoTable) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(sex, that.sex) &&
                Objects.equals(label, that.label) &&
                Objects.equals(imageUrl, that.imageUrl);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, name, sex, label, imageUrl);
    }


}