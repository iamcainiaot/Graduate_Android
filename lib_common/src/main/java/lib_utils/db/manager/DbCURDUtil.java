package lib_utils.db.manager;

import lib_utils.db.entity.UserInfoTable;
import lib_utils.db.greendaogen.DaoSession;

/**
 * @author taozhu5
 * @date 2019/3/13 19:43
 * @description 对数据库进行增删改查等等
 */
public class DbCURDUtil {
    /**
     * 增加一条数据
     *
     * @param userInfoTable 用户表对象
     * @param daoSession    org.greenrobot.greendao.AbstractDaoSession
     */
    public void insert(UserInfoTable userInfoTable, DaoSession daoSession) {
        daoSession.insertOrReplace(userInfoTable);
    }

    /**
     * 删除
     *
     * @param userInfoTable 用户表对象
     * @param daoSession    org.greenrobot.greendao.AbstractDaoSession
     */
    public void delete(UserInfoTable userInfoTable, DaoSession daoSession) {
        daoSession.delete(userInfoTable);
    }

    /**
     * 更新
     *
     * @param userInfoTable 用户表对象
     * @param daoSession    org.greenrobot.greendao.AbstractDaoSession
     */
    public void updataData(UserInfoTable userInfoTable, DaoSession daoSession) {
        daoSession.update(userInfoTable);
    }

}
