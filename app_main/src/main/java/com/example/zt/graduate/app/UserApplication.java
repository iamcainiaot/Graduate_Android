package com.example.zt.graduate.app;

import android.support.multidex.BuildConfig;
import android.support.multidex.MultiDexApplication;

import com.example.zt.graduate.db.greendaogen.DaoSession;
import com.example.zt.graduate.db.greendaogen.UserInfoTableDao;
import com.example.zt.graduate.main.chat.ChatMainActivity;
import com.example.zt.graduate.main.chat.chat_message.ChatModel;
import com.mob.MobSDK;

import java.lang.reflect.Method;
import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.event.NotificationClickEvent;
import lib_utils.CommonUtils;
import lib_utils.MyLogUtil;
import lib_utils.ToastUtil;
import lib_utils.db.entity.UserInfoTable;
import lib_utils.db.manager.DbManager;
import lib_utils.disk_cache.DiskCacheManager;

/**
 * @author taozhu5
 * @date 2019/3/13 19:47
 * @description UserApplication类
 */
public class UserApplication extends MultiDexApplication {
    private static final String TAG = "UserApplication";

    private static UserApplication sInstance;

    public static UserApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        initDiskCacheManager();
        showDebugDBAddress();
        initTable();
        // 短信验证码服务
        MobSDK.init(this);
        // 极光推送
        JiGuang();


    }

    private void JiGuang() {
        // 极光推送
        JMessageClient.setDebugMode(true);
        JMessageClient.init(this);

    }

    // TODO 点击通知栏出现的消息通知做的事儿
    public void onEvent(NotificationClickEvent event) {
        ChatMainActivity.start(this);
    }

    /**
     * 初始化用户表
     */
    public void initTable() {
        daoSession = DbManager.getDaoSession(this);
        //  userInfoTable = daoSession.getUserInfoTableDao().;
        MyLogUtil.d(TAG, "GreeDao初始化！");
    }

    /**
     * 数据库操作对象
     */
    private static DaoSession daoSession;

    /**
     * 获取数据库对象
     */
    public DaoSession getDaoSession() {
        return daoSession;
    }

    /**
     * 显示DebugDB的地址，只有在debug编译时会打印
     */
    private void showDebugDBAddress() {
        if (BuildConfig.DEBUG) {
            try {
                Class<?> debugDB = Class.forName("com.amitshekhar.DebugDB");
                Method getAddressLog = debugDB.getMethod("getAddressLog");
                Object value = getAddressLog.invoke(null);
                MyLogUtil.d(TAG, "DebugDB-->" + value);
            } catch (Exception ignore) {

            }
        }
    }


    /**
     * 初始化磁盘缓存工具类
     */
    private void initDiskCacheManager() {

        DiskCacheManager.init(this, new DiskCacheManager.OnGetUserIdListener() {
            @Override
            public String getUserId() {
                return userInfoTable == null ? null : getCurrentUserId();
            }
        });

        // DiskCacheManager.registerClassCacheFileRule(HitWorkListResponse.class,
        //         new CacheFileRule(DiskCacheConst.HitWorkListData.FILE_NAME,
        //                 DiskCacheConst.HitWorkListData.DIR_NAME,
        //                 true));
    }

    private UserInfoTable userInfoTable;

    public String getCurrentUserId() {
        userInfoTable = getUserInfoTable();
        if (getUserInfoTable() != null) {
            return userInfoTable.getUserId();
        }
        MyLogUtil.d(TAG, "出错啦");
        return null;
    }

    public UserInfoTable getUserInfoTable() {
        UserInfoTableDao userInfoTableDao = getDaoSession().getUserInfoTableDao();
        List<UserInfoTable> list = userInfoTableDao.queryBuilder().list();
        if (!CommonUtils.isEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    private static UserInfoTable mUserInfoTable;

    /**
     * 得到现在的用户Id
     *
     * @return 用户id
     */
    public static String getCurrentId() {
        UserInfoTableDao userInfoTableDao = daoSession.getUserInfoTableDao();
        List<UserInfoTable> list = userInfoTableDao.queryBuilder().list();
        if (!CommonUtils.isEmpty(list)) {
            mUserInfoTable = list.get(0);
            return mUserInfoTable.getUserId();
        }
        return null;
    }
}