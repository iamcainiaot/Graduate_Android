package lib_utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.CountDownTimer;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.util.ArrayMap;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lib_common.R;
import com.github.rahatarmanahmed.cpv.CircularProgressView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.TreeMap;

/**
 * Created by ts_liang on 2018/3/19.
 */

public class CommonUtils {

    private static final String TAG = CommonUtils.class.getName();

    private CommonUtils() {
        throw new RuntimeException("CommonUtils不能被构造");
    }

    private static long lastClickTime = 0;//上次点击时间

    /**
     * 网络相关
     */

    //判断是否有网络
    public static boolean isNetWorkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable() && mNetworkInfo.isConnected();
            }
        }
        return false;
    }

    // 延时5s
    public static void delay5S(final Activity activity) {
    }

    //倒计时
    public static void CountDown(final Activity activity, final TextView view) {
        new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (activity.isFinishing()) {
                    this.cancel();
                } else {
                    view.setClickable(false);
                    view.setText(millisUntilFinished / 1000 + "秒");
                }
            }

            @Override
            public void onFinish() {
                if (activity.isFinishing()) {
                    this.cancel();
                } else {
                    view.setText("获取验证码");
                    view.setClickable(true);
                }
            }
        }.start();
    }

    /**
     * 判断是否快速点击，避免重复点击
     *
     * @return
     */
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (timeD >= 0 && timeD <= 300) {//两次间隔时间太短就认为是快速点击
            return true;
        } else {
            lastClickTime = time;
            return false;
        }
    }

    /**
     * 获取数组的大小
     *
     * @param array
     * @param <T>
     * @return
     */
    public static <T> int size(T[] array) {
        return array == null ? 0 : array.length;
    }

    /**
     * 获取集合的大小
     *
     * @param array
     * @param <T>
     * @return
     */
    public static <T> int size(Collection<T> array) {
        return array == null ? 0 : array.size();
    }

    /**
     * 判断数组是否为空或者null
     *
     * @param array
     * @return
     */
    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 判断集合是否为空或者null
     *
     * @param array
     * @return
     */
    public static <T> boolean isEmpty(Collection<T> array) {
        return array == null || array.isEmpty();
    }

    /**
     * 判断集合是否为空或者null
     *
     * @param map
     * @return
     */
    public static boolean isEmpty(Map map) {
        return map == null || map.isEmpty();
    }

    /**
     * 判断集合是否为空或者null
     *
     * @param array
     * @return
     */
    public static <T> boolean isListEmpty(Collection<T> array) {
        return array == null;
    }

    /**
     * 比较两个集合是否相等（即两个集合里面的元素都一样）请注意请务必实现集合元素的equals方法(集合里面元素顺序也要一样)
     *
     * @param left
     * @param right
     * @param <T>
     * @return
     */
    public static <T> boolean isListEqual(Collection<T> left, Collection<T> right) {
        if (left == null) return right == null;
        if (right == null) return left == null;
        if (left.isEmpty()) return right.isEmpty();
        int leftSize = left.size();
        int rightSize = right.size();
        if (leftSize != rightSize) {
            return false;
        }
        Iterator<T> leftIterator = left.iterator();
        Iterator<T> rightIterator = right.iterator();
        while (leftIterator.hasNext()) {
            if (!Objects.equals(leftIterator.next(), rightIterator.next())) {
                return false;
            }
        }
        return true;
    }

    /**
     * 比较两个集合是否相等（即两个集合里面的元素都一样）请注意请务必实现集合元素的equals方法(集合里面元素顺序也要一样) 忽略空集合，即null和空数组认为是相等的
     *
     * @param left
     * @param right
     * @param <T>
     * @return
     */
    public static <T> boolean isListEqualIgnoreEmpty(Collection<T> left, Collection<T> right) {
        if (isEmpty(left)) return isEmpty(right);
        return isListEqual(left, right);
    }

    /**
     * 比较两个集合是否相等（即两个集合里面的元素都一样）请注意请务必实现集合元素的equals方法(集合里面元素顺序可以不一样) 忽略空集合，即null和空数组认为是相等的
     *
     * @param left
     * @param right
     * @param <T>
     * @return
     */
    public static <T> boolean isListEqualIgnoreEmptyAndOrder(Collection<T> left, Collection<T> right) {
        return isListEqualIgnoreEmpty(left, right) || isListEqualIgnoreOrder(left, right);
    }

    /**
     * 比较两个集合是否相等（即两个集合里面的元素都一样）请注意请务必实现集合元素的equals方法(集合里面元素顺序可以不一样)
     *
     * @param left
     * @param right
     * @param <T>
     * @return
     */
    public static <T> boolean isListEqualIgnoreOrder(Collection<T> left, Collection<T> right) {
        if (left == null) return right == null;
        if (right == null) return left == null;
        if (left.isEmpty()) return right.isEmpty();
        int leftSize = left.size();
        int rightSize = right.size();
        if (leftSize != rightSize) {
            return false;
        }
        Iterator<T> leftIterator = left.iterator();
        while (leftIterator.hasNext()) {
            if (!right.contains(leftIterator.next())) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断数组中是否包含某个元素
     *
     * @param array 数组
     * @param data  某个元素
     * @param <T>
     * @return
     */
    public static <T> boolean contains(T[] array, T data) {
        if (isEmpty(array)) return false;
        int length = array.length;
        for (int i = 0; i < length; i++) {
            T t = array[i];
            if (t.equals(data)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 对象深拷贝
     *
     * @param input
     * @param <T>
     * @return
     */
    public static <T> T copy(Parcelable input) {
        Parcel parcel = null;

        try {
            parcel = Parcel.obtain();
            parcel.writeParcelable(input, 0);

            parcel.setDataPosition(0);
            return parcel.readParcelable(input.getClass().getClassLoader());
        } finally {
            parcel.recycle();
        }
    }

    /**
     * 判断集合中是否包含某个元素
     *
     * @param collection 集合
     * @param data       某个元素
     * @param <T>
     * @return
     */
    public static <T> boolean contains(Collection<T> collection, T data) {
        if (isEmpty(collection)) return false;
        return collection.contains(data);
    }

    /**
     * 时间转换
     *
     * @param tm
     * @return
     */
    public static String getTransforTime(long tm) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日 HH:mm");
        return sdf.format(new Date(tm));
    }


    /**
     * 计算文件大小
     */
    public static long getTotalSizeOfFilesInDir(final File file) {
        if (file.isFile())
            return file.length();
        final File[] children = file.listFiles();
        long total = 0;
        if (children != null)
            try {
                for (final File child : children) {
                    total += getFolderSize(child);
                }
            } catch (Exception e) {
                Log.d("CALCULATE_CAHCE", "getTotalSizeOfFilesInDir方法计算失败");
            }
        return total;
    }

    /**
     * 获取指定文件夹内所有文件大小的和
     *
     * @param file file
     * @return size
     * @throws Exception
     */
    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            if (file.isDirectory()) {
                File[] fileList = file.listFiles();
                if (fileList != null) {
                    for (File aFileList : fileList) {
                        if (aFileList.isDirectory()) {
                            size += getFolderSize(aFileList);
                        } else {
                            size += aFileList.length();
                        }
                    }
                }
            } else {
                size += file.length();
            }
        } catch (Exception e) {
            MyLogUtil.e(TAG, e);
        }
        return size;
    }

    /**
     * 获取小数点后maxcount位，并且四舍五入(如果已经是maxcount位则不处理)
     *
     * @param number   数字
     * @param maxcount 小数点后maxcount位数
     * @return
     */
    public static float getNumber(float number, int maxcount) {
        float resultnumber = number;

        try {
            BigDecimal b = BigDecimal.valueOf(number);
            int count = b.scale();
            if (count <= maxcount) return resultnumber;

            b = b.setScale(maxcount, BigDecimal.ROUND_HALF_UP);
            resultnumber = b.floatValue();

        } catch (Exception ex) {
            MyLogUtil.e(TAG, ex);
        }

        return resultnumber;
    }

    /**
     * 获取小数点后maxcount位，并且四舍五入(如果已经是maxcount位则不处理)
     *
     * @param number   数字
     * @param maxcount 小数点后maxcount位数
     * @return
     */
    public static double getNumber(double number, int maxcount) {
        double resultnumber = number;

        try {
            BigDecimal b = BigDecimal.valueOf(number);
            int count = b.scale();
            if (count <= maxcount) return resultnumber;

            b = b.setScale(maxcount, BigDecimal.ROUND_HALF_UP);
            resultnumber = b.doubleValue();

        } catch (Exception ex) {
            MyLogUtil.e(TAG, ex);
        }

        return resultnumber;
    }


    /**
     * 获取小数点后maxcount位，并且四舍五入(如果已经是maxcount位则不处理)
     *
     * @param number   数字
     * @param maxcount 小数点后maxcount位数
     * @return
     */
    public static long getNumber(long number, int maxcount) {
        long resultnumber = number;

        try {
            BigDecimal b = BigDecimal.valueOf(number);
            int count = b.scale();
            if (count <= maxcount) return resultnumber;

            b = b.setScale(maxcount, BigDecimal.ROUND_HALF_UP);
            resultnumber = b.longValue();

        } catch (Exception ex) {
            MyLogUtil.e(TAG, ex);
        }

        return resultnumber;
    }


    /**
     * 获取url完整路径
     *
     * @param url 服务端返回的url
     * @return
     */
    public static String getFsUrl(String url) {
        if (!TextUtils.isEmpty(url) && !url.startsWith("http")) {
            if (url.startsWith("/")) {
                url = "http://fs.yixuexiao.cn" + url;
            } else {
                url = "http://fs.yixuexiao.cn/" + url;
            }
        }
        return url;
    }

    /**
     * 获取屏幕的宽度
     *
     * @return
     */
    public static int getScreenWidth(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        //取得窗口属性
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        //窗口宽度
        return dm.widthPixels;
    }

    /**
     * 获取屏幕的高度
     *
     * @return
     */
    public static int getScreenHeight(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        //取得窗口属性
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        //窗口高度
        return dm.heightPixels;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 设置View的背景，设置背景时会清空padding值，所以要额外处理下，保证显示正常
     *
     * @param view  要设置背景的View
     * @param resId 背景资源id
     */
    public static void setViewBackground(View view, @DrawableRes int resId) {
        setViewBackground(view, resId, false);
    }

    /**
     * 设置View的背景，设置背景时会清空padding值，所以要额外处理下，保证显示正常
     *
     * @param view         要设置背景的View
     * @param resId        背景资源id
     * @param needCatchOOM 是否需要捕获OOM异常错误 一般设置大图的时候 需要将此值设置为true
     */
    public static void setViewBackground(View view, @DrawableRes int resId, boolean needCatchOOM) {
        //先将padding值保存下来
        int paddingLeft = view.getPaddingLeft();
        int paddingTop = view.getPaddingTop();
        int paddingRight = view.getPaddingRight();
        int paddingBottom = view.getPaddingBottom();
        //设置背景
        if (!needCatchOOM) {
            view.setBackgroundResource(resId);
        } else {
            int inSampleSize = 1;//缩放比例
            boolean isSuccess = false;//设置背景是否成功
            while (!isSuccess) {
                try {
                    if (inSampleSize == 1) {//代表不缩放，直接设置背景
                        view.setBackgroundResource(resId);
                    } else {//代表缩放
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize = inSampleSize;
                        Bitmap bitmap = BitmapFactory.decodeResource(view.getResources(), resId, options);
                        view.setBackgroundDrawable(new BitmapDrawable(bitmap));
                    }
                    isSuccess = true;
                } catch (OutOfMemoryError outOfMemoryError) {
                    MyLogUtil.e(TAG, outOfMemoryError);
                    inSampleSize *= 2;//发现OOM了，就将缩放比例乘以2
                }
            }
        }
        //恢复padding值
        view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

    /**
     * 给ImageView的图片设置tint
     *
     * @param imageView 要设置的ImageView控件
     * @param resId     图片资源id
     * @param tintColor tint颜色值
     */
    public static void setImageViewResourceWithTint(ImageView imageView, @DrawableRes int resId, @ColorInt int tintColor) {
        //1:通过图片资源文件生成Drawable实例
        Drawable drawable = imageView.getResources().getDrawable(resId).mutate();
        //2:先调用DrawableCompat的wrap方法
        drawable = DrawableCompat.wrap(drawable);
        //3:再调用DrawableCompat的setTint方法，为Drawable实例进行着色
        DrawableCompat.setTint(drawable, tintColor);
        //4:最后将着色的Drawable设置给ImageView
        imageView.setImageDrawable(drawable);
    }

    /**
     * 判断时间是否在今年
     *
     * @param time
     * @return
     */
    public static boolean isInCurrentYear(long time) {
        long current = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(current);
        int currentyear = calendar.get(Calendar.YEAR);

        calendar.setTimeInMillis(time);
        int year = calendar.get(Calendar.YEAR);
        return (currentyear == year);
    }

    /**
     * 获取今天的最小时间戳 即0时0分0秒
     *
     * @return
     */
    public static long getTodayMinTime() {
        long current = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(current);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(year, month, day, 0, 0, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取昨天的最小时间戳
     *
     * @return
     */
    public static long getYesterdayMinTime() {
        return (getTodayMinTime() - TIME_DAY);
    }

    /**
     * 获取明天的最大时间戳
     *
     * @return
     */
    public static long getTomorrowMaxTime() {
        return (getTodayMinTime() + 2 * TIME_DAY);
    }

    public static final long TIME_DAY = 24 * 60 * 60 * 1000L;

    /**
     * 判断当前设备是手机还是平板，代码来自 Google I/O App for Android
     *
     * @param context
     * @return 平板返回 True，手机返回 False
     */
    public static boolean isPad(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    /**
     * 判断是否为是手机
     *
     * @return
     */
    public static boolean isPhone(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        // 屏幕宽度
        float screenWidth = display.getWidth();
        // 屏幕高度
        float screenHeight = display.getHeight();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        double x = Math.pow(dm.widthPixels / dm.xdpi, 2);
        double y = Math.pow(dm.heightPixels / dm.ydpi, 2);
        // 屏幕尺寸
        double screenInches = Math.sqrt(x + y);
        // 大于6尺寸则为Pad
        if (screenInches >= 6.0) {
            return false;
        }
        return true;
    }

    /**
     * 使用Map按key进行排序
     *
     * @param map 待排序的map
     * @return
     */
    public static <T> Map<Integer, T> sortMapByKey(Map<Integer, T> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<Integer, T> sortMap = new TreeMap<>(new MapKeyComparator());
        sortMap.putAll(map);
        return sortMap;
    }


    /**
     * 比较器类
     */
    private static class MapKeyComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer left, Integer right) {
            return left.compareTo(right);
        }
    }

    /**
     * 获取指定TextView的文本宽度
     *
     * @param textView
     * @return
     */
    public static float getTextWidth(final TextView textView) {
        TextPaint textPaint = textView.getPaint();
        return textPaint.measureText(textView.getText().toString());
    }

    /**
     * 返回urlEncode后的字符串
     *
     * @param str 原字符串
     * @return
     */
    public static String urlEncode(String str) {
        try {
            return URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            MyLogUtil.e(TAG, e);
        }
        return str;
    }

    /**
     * 判断设备是否支持闪光灯
     *
     * @return boolean
     */
    public static boolean isSupportFlash(Context context) {
        PackageManager pm = context.getPackageManager();
        FeatureInfo[] features = pm.getSystemAvailableFeatures();
        for (FeatureInfo f : features) {
            // 判断设备是否支持闪光灯
            if (PackageManager.FEATURE_CAMERA_FLASH.equals(f.name)) {
                return true;
            }
        }
        return false;
    }

    private static final Map<Integer, String> INDEX_TO_CHINESE_MAP;

    static {
        INDEX_TO_CHINESE_MAP = new ArrayMap<>();
        INDEX_TO_CHINESE_MAP.put(1, "一");
        INDEX_TO_CHINESE_MAP.put(2, "二");
        INDEX_TO_CHINESE_MAP.put(3, "三");
    }

    /**
     * 根据下标索引返回对应的中文字符串 eg:"1"返回"一"
     *
     * @param index 从1开始
     * @return
     */
    public static String getChineseIndexStr(int index) {
        return INDEX_TO_CHINESE_MAP.get(index);
    }

    /**
     * 获取当前应用渠道号(application中用meta-data标记的友盟渠道号)
     *
     * @param context
     * @return
     */
    public static String getVendor(Context context) {
        String vendor = null;
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(),
                    PackageManager.GET_META_DATA);
            vendor = appInfo.metaData.getString("UMENG_CHANNEL");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            MyLogUtil.e(TAG, e);
        }
        return vendor;
    }

    /**
     * 从assets中获取文件输入流
     *
     * @param context
     * @param filePath 文件路径（相对assets）
     * @return
     */
    public static InputStream getInputStreamFromAssets(Context context, String filePath) throws IOException {
        if (context == null) {
            return null;
        }
        if (TextUtils.isEmpty(filePath)) {
            return null;
        }
        return context.getResources().getAssets().open(filePath);
    }

    public static boolean isExcellent(float score) {
        return Math.round(score * 20) >= 85;
    }

    static Dialog dia;

    /**
     * 点击图片展示大图
     *
     * @return
     */
    public static void imagePreview(Context context, String url) {
        ImageView imageView;

        // 初始化图片预览Dialog
        dia = new Dialog(context, R.style.edit_AlertDialog_style);
        dia.setContentView(R.layout.activity_start_dialog);
        imageView = (ImageView) dia.findViewById(R.id.start_img);
        //选择true的话点击其他地方可以使dialog消失，为false的话不会消失
        dia.setCanceledOnTouchOutside(true);
        Window w = dia.getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.x = 0;
        lp.y = 40;
        dia.onWindowAttributesChanged(lp);

        dia.show();
        Glide.with(context).load(url).into(imageView);
        imageView.setOnClickListener(
                view -> dia.dismiss());
    }

    /**
     * 进度条
     *
     * @return
     */
    public static void progressBar(Dialog dia) {
        CircularProgressView circularProgressView;

        circularProgressView = dia.findViewById(R.id.start_img);
        //选择true的话点击其他地方可以使dialog消失，为false的话不会消失
        dia.setCanceledOnTouchOutside(false);
        Window w = dia.getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.x = 0;
        lp.y = 40;
        dia.onWindowAttributesChanged(lp);
        dia.show();
        circularProgressView.setOnClickListener(
                view -> dia.dismiss());
    }

    public static void progressBarStop(Dialog dia) {
        dia.dismiss();
    }

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};

    public static void verifyStoragePermissions(Activity activity) {
        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取随机图片
     */
    public static String getRandomImgUrl() {
        String[] array = {
                "https://ss2.baidu.com/-vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=d8" +
                        "d48aa8a151f3dedcb2bf64a4eff0ec/4610b912c8fcc3ce863f8b519c45d688d53f20d0.jpg"

                , "https://ss2.baidu.com/-vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/s" +
                "ign=f888027cebdde711f8d245f697eecef4/71cf3bc79f3df8dcfcea3de8c311728b461028f7.jpg"

                , "https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/im" +
                "age/h%3D300/sign=3a82cecba7014c08063b2ea53a7a025b/359b033b5bb5c9eac6684081db39b6003af3b33d.jpg"

                , "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoW" +
                "K1HF6hhy/it/u=2092436646,1722038551&fm=26&gp=0.jpg"

                , "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/" +
                "it/u=2092436646,1722038551&fm=26&gp=0.jpg"

                , "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2068613769,2319387558&fm=26&gp=0.jpg"};
        int size = array.length;
        int s = new Random().nextInt(size);
        if (s == 0) {
            return array[s];
        } else {
            return array[s - 1];
        }
    }
}