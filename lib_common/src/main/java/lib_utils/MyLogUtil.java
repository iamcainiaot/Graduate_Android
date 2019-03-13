package lib_utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * @author taozhu5
 * @date 2019/3/13 16:57
 * @description 日志工具类
 */
public class MyLogUtil {
    /**
     * 默认日志tag <br/>
     * created at 2015/12/27 14:46
     */
    private static final String TAG = "MyLogUtil";

    public static String tagPrefix = "";
    public static boolean showV = true;
    public static boolean showD = true;
    public static boolean showI = true;
    public static boolean showW = true;
    public static boolean showE = true;
    public static boolean showWTF = true;

    /**
     * 初始化
     *
     * @param context
     */
    public static void init(Context context, final boolean isEnableAndroidLog) {
        setAndroidLog(isEnableAndroidLog);
    }

    /**
     * 设置打印日志到Android控制台（Console）
     *
     * @param isEnableAndroidLog 是否开启
     */
    private static void setAndroidLog(final boolean isEnableAndroidLog) {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(0)         // (Optional) How many method line to show. Default 2
                .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
//                    .logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
                .tag(TAG)   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return isEnableAndroidLog;
            }
        });
    }

    /**
     * 得到tag（所在类.方法（L:行））
     *
     * @return
     */
    private static String generateTag() {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[4];
        String callerClazzName = stackTraceElement.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        String tag = "%s.%s(L:%d)";
        tag = String.format(tag, new Object[]{callerClazzName, stackTraceElement.getMethodName(), Integer.valueOf(stackTraceElement.getLineNumber())});
        //给tag设置前缀
        tag = TextUtils.isEmpty(tagPrefix) ? tag : tagPrefix + ":" + tag;
        return tag;
    }

    public static void v(String msg) {
        if (showV) {
            String tag = generateTag();
            Log.v(tag, msg);
        }
    }

    public static void v(String msg, Throwable tr) {
        if (showV) {
            String tag = generateTag();
            Log.v(tag, msg, tr);
        }
    }

    public static void d(String msg) {
        Log.d(TAG, msg);
    }

    public static void d(String msg1, String msg2) {
        Log.d(TAG, msg1 + msg2);
    }

    public static void d(String msg, Throwable tr) {
        Log.d(TAG, msg, tr);
    }

    public static void d(String tag, String msg, Throwable tr) {
        Log.d(TAG, msg, tr);
    }

    public static void i(String msg) {
        Log.i(TAG, msg);
    }

    public static void i(String msg, Throwable tr) {
        Log.i(TAG, msg, tr);
    }

    public static void w(String msg) {
        Log.w(TAG, msg);
    }

    public static void w(String msg, Throwable tr) {
        Log.w(TAG, msg, tr);
    }

    public static void e(String msg) {
        Log.e(TAG, msg);
    }

    public static void e(String msg, Throwable tr) {
        Log.e(TAG, msg, tr);
    }

    public static void e(String tag, String msg, Throwable tr) {
        Log.e(TAG, msg, tr);
    }

    public static void wtf(String msg) {
        Log.wtf(TAG, msg);
    }

    public static void wtf(String msg, Throwable tr) {
        Log.wtf(TAG, msg, tr);
    }
}