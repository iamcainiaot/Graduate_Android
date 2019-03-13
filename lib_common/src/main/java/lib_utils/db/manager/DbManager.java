package lib_utils.db.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import lib_utils.db.greendaogen.DaoMaster;
import lib_utils.db.greendaogen.DaoSession;

/**
 * 数据库管理类
 * Created by shenhua_yu on 2018/3/9.
 */
public class DbManager {
    /**
     * 数据库名字
     */
    private static final String DB_NAME = "zt.graduate.db";

    private static volatile DbManager sInstance = null;
    private static DBOpenHelper mDbOpenHelper;
    private static volatile DaoMaster mDaoMaster;
    private static volatile DaoSession mDaoSession;

    private DbManager(Context context) {
        // 初始化数据库信息
        mDbOpenHelper = new DBOpenHelper(context, DB_NAME);
    }

    private static DbManager getInstance(Context context) {
        if (sInstance == null) {
            synchronized (DbManager.class) {
                if (sInstance == null) {
                    sInstance = new DbManager(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    /**
     * 获取可写入的数据库
     *
     * @param context
     * @return
     */
    private static SQLiteDatabase getWritableDatabase(Context context) {
        if (null == mDbOpenHelper) {
            getInstance(context);
        }
        return mDbOpenHelper.getWritableDatabase();
    }

    /**
     * 获取DaoMaster
     *
     * @param context
     * @return
     */
    private static DaoMaster getDaoMaster(Context context) {
        if (null == mDaoMaster) {
            synchronized (DbManager.class) {
                if (null == mDaoMaster) {
                    mDaoMaster = new DaoMaster(getWritableDatabase(context));
                }
            }
        }
        return mDaoMaster;
    }

    /**
     * 获取DaoSession 对外暴露这个方法就可以了
     *
     * @param context
     * @return
     */
    public static DaoSession getDaoSession(Context context) {
        if (null == mDaoSession) {
            synchronized (DbManager.class) {
                if (null == mDaoSession) {
                    mDaoSession = getDaoMaster(context).newSession();
                }
            }
        }
        return mDaoSession;
    }
}
