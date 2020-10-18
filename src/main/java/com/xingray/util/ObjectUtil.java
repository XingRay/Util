package com.xingray.util;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectUtil {

    public static <T> T mapToObject(Map<String, Object> map, Class<T> cls) {
        if (map == null || map.isEmpty()) {
            return null;
        }

        T instance = null;
        try {
            instance = cls.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        if (instance == null) {
            return null;
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            ReflectUtil.set(instance, key, value);
        }

        return instance;
    }

    public static <T> Map<String, Object> objectToMap(T t) {
        if (t == null) {
            return null;
        }
        Field[] fields = t.getClass().getDeclaredFields();
        if (fields.length == 0) {
            return null;
        }
        int size = fields.length;
        Map<String, Object> map = new HashMap<>(size);
        List<String> fieldNames = new ArrayList<>(size);
        for (Field field : fields) {
            fieldNames.add(field.getName());
        }

        for (String fieldName : fieldNames) {
            map.put(fieldName, ReflectUtil.get(t, fieldName));
        }

        return map;
    }

    public static Object ensureMatchesType(Object value, Class<?> type) {
        if (value == null) {
            return null;
        }

        if (type.isInstance(value) || type.isAssignableFrom(value.getClass())) {
            return value;
        }

        if (type == String.class) {
            return StringUtil.toString(value);
        } else if (type == Long.class) {
            return NumberUtil.toLong(value);
        } else if (type == long.class) {
            return NumberUtil.toLongValue(value);
        } else if (type == Integer.class) {
            return NumberUtil.toInteger(value);
        } else if (type == int.class) {
            return NumberUtil.toInt(value);
        } else if (type == Double.class) {
            return NumberUtil.toDouble(value);
        } else if (type == double.class) {
            return NumberUtil.toDoubleValue(value);
        } else if (type == Float.class) {
            return NumberUtil.toFloat(value);
        } else if (type == float.class) {
            return NumberUtil.toFloatValue(value);
        } else if (type == Boolean.class) {
            return NumberUtil.toBoolean(value);
        } else if (type == boolean.class) {
            return NumberUtil.toBooleanValue(value);
        } else if (type == Byte.class) {
            return NumberUtil.toByte(value);
        } else if (type == byte.class) {
            return NumberUtil.toByteValue(value);
        } else if (type == Short.class) {
            return NumberUtil.toShort(value);
        } else if (type == short.class) {
            return NumberUtil.toShortValue(value);
        } else if (type == Character.class) {
            return NumberUtil.toCharacter(value);
        } else if (type == char.class) {
            return NumberUtil.toChar(value);
        }

        return null;
    }
}
