package lib_utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast统一管理类
 *
 * @author way
 */
public class ToastUtil {
    private final static String TAG = "ToastUtil";

    private static Toast toast;

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, CharSequence message) {
        try {
            if (null == toast) {
                //  toast = Toasty.normal(context, message);
                toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            } else {
                toast.setText(message);
            }
            toast.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, CharSequence message) {
        try {
            if (null == toast) {
                toast = Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_LONG);
                // toast.setGravity(Gravity.CENTER, 0, 0);
            } else {
                toast.setText(message);
            }
            toast.show();
        } catch (Exception ex) {
            MyLogUtil.e(TAG, ex);
        }
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void show(Context context, CharSequence message, int duration) {
        try {
            if (null == toast) {
                toast = Toast.makeText(context.getApplicationContext(), message, duration);
                // toast.setGravity(Gravity.CENTER, 0, 0);
            } else {
                toast.setText(message);
            }
            toast.show();
        } catch (Exception ex) {
            MyLogUtil.e(TAG, ex);
        }
    }

    /**
     * Hide the toast, if any.
     */
    public static void hideToast() {
        if (null != toast) {
            toast.cancel();
        }
    }
}
