package game.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class TimeUtils {
    private TimeUtils() { }

    public static String getFormattedTime(String pattern) {
        return new SimpleDateFormat(pattern).format(new Date());
    }
}
