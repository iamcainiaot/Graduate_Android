package lib_utils;

import android.content.Context;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

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
    public static void showShort(Context context, CharSequence message, boolean flag) {
        try {
            toast = flag ? Toasty.success(context, message, Toast.LENGTH_SHORT)
                    : Toasty.error(context, message, Toast.LENGTH_SHORT);
            toast.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    // public static void showError(Context context, CharSequence message) {
//      try {
//          toast = flag ? Toasty.success(context, message, Toast.LENGTH_SHORT)
//                  : Toasty.error(context, message, Toast.LENGTH_SHORT);
//          toast.setText(message);
//          toast.show();
//      } catch (Exception ex) {
//          ex.printStackTrace();
//      }
//  }

    /**
     * Hide the toast, if any.
     */
    public static void hideToast() {
        if (null != toast) {
            toast.cancel();
        }
    }
}
