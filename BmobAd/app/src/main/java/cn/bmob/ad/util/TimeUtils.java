package cn.bmob.ad.util;

/**
 * @author zhangchaozhou
 */
public class TimeUtils {

    private static final int SECOND = 60;
    private static final int MINUTE = 60;
    private static final int DECIMAL = 10;

    public static String formatLongToTimeStr(Long l) {
        int hour = 0;
        int minute = 0;
        int second;

        second = l.intValue() / 1000;

        if (second > SECOND) {
            minute = second / 60;
            second = second % 60;
        }
        if (minute > MINUTE) {
            hour = minute / 60;
            minute = minute % 60;
        }
        String hourText = getTwoLength(hour);
        if (hourText.equals("00")) {
            return (getTwoLength(minute) + ":" + getTwoLength(second));
        } else {
            return (getTwoLength(hour) + ":" + getTwoLength(minute) + ":" + getTwoLength(second));
        }
    }


    private static String getTwoLength(final int data) {
        if (data < DECIMAL) {
            return "0" + data;
        } else {
            return "" + data;
        }
    }
}
