package com.muchencute.commons.synonym;

@SuppressWarnings("ALL")
public class Synonym {

    /**
     * 将 null 替换成空字符串
     *
     * @param value 待转换字符串对象
     * @return 如果 {@code value} 为 null 则返回空字符串，否则返回 value 本身
     */
    public static String replaceNullWithEmptyString(String value) {
        return value == null ? "" : value;
    }
}
