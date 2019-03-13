package lib_utils.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author taozhu5
 * @date 2019/3/13 18:53
 * @description 描述
 */
@Entity
public class UserInfoTable {
    @Id(autoincrement = true)
    private long id;
    @Property(nameInDb = "userId")
    private String userId;
    @Property(nameInDb = "userName")
    private String name;
    @Property(nameInDb = "password")
    private int password;

    @Generated(hash = 961719127)
    public UserInfoTable(long id, String userId, String name, int password) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.password = password;
    }

    @Generated(hash = 1354492153)
    public UserInfoTable() {
    }

    public String getUserId() {
        return userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }
}
