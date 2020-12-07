package com.xingray.util;

import org.junit.Test;

public class StringUtilTest {

    @Test
    public void getIntStringTest() {
        String intString = StringUtil.getIntString(9, (int) Math.floor(Math.log10(999)) + 1);
        System.out.println(intString);
    }
}
