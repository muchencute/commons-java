package com.muchencute.commons.regexp;

/**
 * 包含常用的正则表达式
 * <p>
 * 参看: http://www.mkyong.com/regular-expressions/10-java-regular-expression-examples-you-should-know/
 */
public final class RegexpUtils {

    public static final String USERNAME = "^[a-z0-9_-]{3,15}$";

    public static final String PASSWORD = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

    public static final String PASSWORD2 = "((?=.*\\d)(?=.*[a-zA-Z]).{6,20})";

    public static final String HEXADECIMAL_COLOR_CODE = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";

    public static final String PHONE = "^\\+?\\d+[\\d\\-]*\\d$";

    public static final String EMAIL = "(?i)^[-a-z0-9~!$%^&*_=+}{\\'?]+(\\.[-a-z0-9~!$%^&*_=+}{\\'?]+)*@([a-z0-9_][-a-z0-9_]*(\\.[-a-z0-9_]+)*\\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,5})?$";

    public static final String IMAGE_FILE = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)";

    public static final String IP = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    public static final String TIME_12 = "(1[012]|[1-9]):[0-5][0-9](\\s)?(?i)(am|pm)";

    public static final String TIME_24 = "([01]?[0-9]|2[0-3]):[0-5][0-9]";

    public static final String DATE = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";

    public static final String HTML_TAG = "<(\"[^\"]*\"|'[^']*'|[^'\">])*>";

    public static final String HTML_A_TAG = "(?i)<a([^>]+)>(.+?)</a>";

    public static final String EXTRACT_HTML_LINK = "\\s*(?i)href\\s*=\\s*(\\\"([^\"]*\\\")|'[^']*'|([^'\">\\s]+))";

    /**
     * 因为 / 和 $ 在做替换字符串时是有意义的，所以要进行再次转义，不然会报错
     */
    public static String normalizeReplacement(final String replacement) {

        return replacement.replaceAll("\\\\", "\\\\")
                .replaceAll("\\$", "\\\\\\$");
    }
}
