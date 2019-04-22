package lib_utils.net.tencent;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author taozhu5
 * @date 2019/4/10 21:00
 * @description 腾讯用户信息
 */
public class TencentUserInfoModel implements Parcelable {
/**
 * "ret":0, 返回码
 * "msg":"", 如果ret<0，会有相应的错误信息提示，返回数据全部用UTF-8编码。
 * "nickname":"Peter", 用户在QQ空间的昵称。
 * "figureurl_2":"http://qzapp.qlogo.cn/qzapp/111111/942FEA70050EEAFBD4DCE2C1FC775E56/100",   用户头像可以用这个
 * "gender":"男", 性别。 如果获取不到则默认返回"男"
 * "is_yellow_vip":"1",
 * "vip":"1",
 * "yellow_vip_level":"7",
 * "level":"7",
 * "is_yellow_year_vip":"1"
 * "province":"安徽",
 * "city":"合肥",
 * "year":"1997"
 */
    /**
     * 返回码
     */
    private int ret;
    /**
     * 错误信息
     */
    private String msg;
    /**
     * 用户名
     */
    private String nickname;
    /**
     * 用户头像Url
     */
    private String figureurl_2;
    /**
     * 性别
     */
    private String gender;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 出生年份
     */
    private String year;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFigureurl_2() {
        return figureurl_2;
    }

    public void setFigureurl_2(String figureurl_2) {
        this.figureurl_2 = figureurl_2;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "TencentUserInfoModel{" +
                "ret='" + ret + '\'' +
                ", msg='" + msg + '\'' +
                ", nickname='" + nickname + '\'' +
                ", figureurl_2='" + figureurl_2 + '\'' +
                ", gender='" + gender + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", year='" + year + '\'' +
                '}';
    }

    public TencentUserInfoModel() {
    }

    /**
     * 实现Parcel接口
     */
    protected TencentUserInfoModel(Parcel in) {
        ret = in.readInt();
        msg = in.readString();
        nickname = in.readString();
        figureurl_2 = in.readString();
        gender = in.readString();
        province = in.readString();
        city = in.readString();
        year = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ret);
        dest.writeString(msg);
        dest.writeString(nickname);
        dest.writeString(figureurl_2);
        dest.writeString(gender);
        dest.writeString(province);
        dest.writeString(city);
        dest.writeString(year);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TencentUserInfoModel> CREATOR = new Creator<TencentUserInfoModel>() {
        @Override
        public TencentUserInfoModel createFromParcel(Parcel in) {
            return new TencentUserInfoModel(in);
        }

        @Override
        public TencentUserInfoModel[] newArray(int size) {
            return new TencentUserInfoModel[size];
        }
    };

}