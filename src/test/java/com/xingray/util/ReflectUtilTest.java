package com.xingray.util;

import org.junit.Test;

public class ReflectUtilTest {

    @Test
    public void getterSetterTest() {
        String[] boolNames = new String[]{
                "isApple",
                "cat",
                "isred",
                "is",
                "is1"
        };

        String[] booleanNames = new String[]{
                "isApp",
                "dog",
                "isyellow",
                "is",
                "is1"
        };

        for (String s : boolNames) {
            System.out.println(s + " " + ReflectUtil.getGetterName(s, boolean.class) + " " + ReflectUtil.getSetterName(s, boolean.class));
        }

        for (String s : booleanNames) {
            System.out.println(s + " " + ReflectUtil.getGetterName(s, Boolean.class) + " " + ReflectUtil.getSetterName(s, Boolean.class));
        }
    }
}
