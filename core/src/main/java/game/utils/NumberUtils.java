package game.utils;

public final class NumberUtils {
    private NumberUtils() {
        throw new UnsupportedOperationException();
    }

    public static int tryParseInt(String str, int defaultValue) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
