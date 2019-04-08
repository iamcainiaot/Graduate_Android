package lib_utils.db.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.zt.graduate.db.greendaogen.DaoMaster;

import org.greenrobot.greendao.database.Database;

import lib_utils.MyLogUtil;

/**
 * @author shyu
 * @date 2019/3/13 20:35
 * @description 数据库帮助类
 */
public class DBOpenHelper extends DaoMaster.OpenHelper {

    private static final String TAG = DBOpenHelper.class.getName();

    private Context mContext;

    public DBOpenHelper(Context context, String name) {
        super(context, name);
        this.mContext = context;
    }

    public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
        this.mContext = context;
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        MyLogUtil.d(TAG, "Upgrading schema from version " + oldVersion + " to " + newVersion);
    }
}
