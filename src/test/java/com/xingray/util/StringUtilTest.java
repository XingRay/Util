package com.xingray.util;

import org.junit.Test;

public class StringUtilTest {

    @Test
    public void getIntStringTest() {
        String intString = StringUtil.getIntString(9, (int) Math.floor(Math.log10(999)) + 1);
        System.out.println(intString);
    }

    @Test
    public void doubleStringTest() {
        System.out.println(StringUtil.toString(0.0000012));
        System.out.println(StringUtil.toString(124578963.1235689));
        System.out.println(StringUtil.toString(3.1235689));
    }
}
