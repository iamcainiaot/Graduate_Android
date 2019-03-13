package lib_utils;

import android.support.annotation.NonNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Author: Wang Chenlong
 * <p>Created On: 2018/6/27 15:37</p>
 * <p>Description: 时间工具类，部分代码来自开源库 AndroidUtilCode</p>
 *
 * @see <a/>开源项目<a href="https://github.com/Blankj/AndroidUtilCode">AndroidUtilCode</a>工具类<a href="https://github.com/Blankj/AndroidUtilCode/blob/master/utilcode/src/main/java/com/blankj/utilcode/util/TimeUtils.java">TimeUtils.java</a>
 */
public final class TimeUtils {
    private static final DateFormat DEFAULT_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private TimeUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * Milliseconds to the formatted time string.
     * <p>The pattern is {@code yyyy-MM-dd HH:mm:ss}.</p>
     *
     * @param millis The milliseconds.
     * @return the formatted time string
     */
    public static String millis2String(final long millis) {
        return millis2String(millis, DEFAULT_FORMAT);
    }

    /**
     * Milliseconds to the formatted time string.
     *
     * @param millis The milliseconds.
     * @param format The format.
     * @return the formatted time string
     */
    public static String millis2String(final long millis, @NonNull final DateFormat format) {
        return format.format(new Date(millis));
    }

    /**
     * Date to the formatted time string.
     * <p>The pattern is {@code yyyy-MM-dd HH:mm:ss}.</p>
     *
     * @param date The date.
     * @return the formatted time string
     */
    public static String date2String(final Date date) {
        return date2String(date, DEFAULT_FORMAT);
    }

    /**
     * Date to the formatted time string.
     *
     * @param date   The date.
     * @param format The format.
     * @return the formatted time string
     */
    public static String date2String(final Date date, @NonNull final DateFormat format) {
        return format.format(date);
    }

    /**
     * Return whether it is today.
     *
     * @param millis The milliseconds.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isToday(final long millis) {
        long wee = getWeeOfToday();
        return millis >= wee && millis < wee + TimeConstants.DAY;
    }

    /**
     * Return whether it is yesterday.
     *
     * @param millis The milliseconds.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isYesterday(final long millis) {
        long wee = getWeeOfToday() - TimeConstants.DAY;
        return millis >= wee && millis < wee + TimeConstants.DAY;
    }

    /**
     * Return whether it is tomorrow.
     *
     * @param millis The milliseconds.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isTomorrow(final long millis) {
        long wee = getWeeOfToday() + TimeConstants.DAY;
        return millis >= wee && millis < wee + TimeConstants.DAY;
    }

    /**
     * Return whether it is this year.
     *
     * @param millis The milliseconds.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isThisYear(final long millis) {
        Calendar now = Calendar.getInstance();
        return isYear(millis, now.get(Calendar.YEAR));
    }

    /**
     * Return whether it is the year.
     *
     * @param millis The milliseconds.
     * @param year   Which year
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isYear(final long millis, final int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.get(Calendar.YEAR) == year;
    }

    /**
     * Return today wee milliseconds.
     */
    public static long getWeeOfToday() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }
}
