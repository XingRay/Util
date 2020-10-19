package com.xingray.util;

import com.xingray.util.base.Mapper;

public class StringUtil {

    public static boolean isEmpty(String s) {
        return s == null || s.isBlank() || s.trim().isEmpty();
    }

    public static String toString(Object[] array) {
        return toString(array, ",");
    }

    public static String toString(Object[] array, String sep) {
        if (array == null || array.length == 0) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        boolean isFirst = true;
        for (Object o : array) {
            String s = o == null ? null : o.toString();
            if (isEmpty(s)) {
                continue;
            }
            if (!isFirst) {
                builder.append(sep);
            }
            isFirst = false;
            builder.append(s);
        }

        return builder.toString();
    }

    public static String toString(Iterable<?> iterable) {
        return toString(iterable, ",");
    }

    public static String toString(Iterable<?> iterable, String sep) {
        if (iterable == null) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        boolean isFirst = true;
        for (Object o : iterable) {
            String s = o == null ? null : o.toString();
            if (isEmpty(s)) {
                continue;
            }
            if (!isFirst) {
                builder.append(sep);
            }
            isFirst = false;
            builder.append(s);
        }

        return builder.toString();
    }

    public static <T> String toString(Iterable<T> iterable, String sep, Mapper<T, String> mapper) {
        if (iterable == null) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        boolean isFirst = true;
        for (T t : iterable) {
            if (!isFirst) {
                builder.append(sep);
            }
            isFirst = false;

            builder.append(mapper.map(t));
        }

        return builder.toString();
    }

    public static String toString(Object o) {
        return toString(o, null);
    }

    public static String toString(Object value, String defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        if (value instanceof String) {
            return (String) value;
        }
        return value.toString();
    }


    public static Long[] toLongs(String s, String sep) {
        if (isEmpty(s)) {
            return null;
        }
        String[] split = s.split(sep);
        int length = split.length;
        Long[] longs = new Long[length];

        for (int i = 0; i < length; i++) {
            longs[i] = NumberUtil.toLong(split[i]);
        }
        return longs;
    }

    public static Integer[] toIntegers(String s, String sep) {
        if (isEmpty(s)) {
            return null;
        }
        String[] split = s.split(sep);
        int length = split.length;
        Integer[] integers = new Integer[length];

        for (int i = 0; i < length; i++) {
            integers[i] = NumberUtil.toInteger(split[i]);
        }
        return integers;
    }

    public static long[] toLongValues(String s, String sep) {
        String[] split = s.split(sep);
        int length = split.length;
        long[] longs = new long[length];

        for (int i = 0; i < length; i++) {
            longs[i] = NumberUtil.toLongValue(split[i]);
        }
        return longs;
    }

    public static int[] toInts(String s, String sep) {
        String[] split = s.split(sep);
        int length = split.length;
        int[] ints = new int[length];

        for (int i = 0; i < length; i++) {
            ints[i] = NumberUtil.toInt(split[i], 0);
        }
        return ints;
    }

    public static String cutIfStartWith(String raw, String start) {
        if (raw.startsWith(start)) {
            raw = raw.substring(start.length());
        }
        return raw;
    }

    public static String cutIfEndWith(String raw, String end) {
        if (raw.endsWith(end)) {
            raw = raw.substring(0, raw.length() - end.length());
        }
        return raw;
    }

    public static String cutBefore(String raw, String content) {
        int index = raw.indexOf(content);
        if (index >= 0) {
            raw = raw.substring(0, index);
        }
        return raw;
    }

    public static String cutAfter(String raw, String content) {
        int index = raw.indexOf(content);
        if (index >= 0) {
            raw = raw.substring(index + content.length());
        }
        return raw;
    }

    public static String captain(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }

}
