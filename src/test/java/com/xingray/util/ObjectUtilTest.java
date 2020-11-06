package com.xingray.util;

import org.junit.Test;

public class ObjectUtilTest {

    @Test
    public void test01() {
        Object o = new Object();

        System.out.println(ObjectUtil.isNoneNull(o, o));
        System.out.println(ObjectUtil.isNoneNull(null, o));
        System.out.println(ObjectUtil.isNoneNull(null, null));

        System.out.println(ObjectUtil.isAllNull(o, o));
        System.out.println(ObjectUtil.isAllNull(null, o));
        System.out.println(ObjectUtil.isAllNull(null, null));

        System.out.println(ObjectUtil.isAtLeastOneIsNotNull(o, o));
        System.out.println(ObjectUtil.isAtLeastOneIsNotNull(null, o));
        System.out.println(ObjectUtil.isAtLeastOneIsNotNull(null, null));

        System.out.println(ObjectUtil.isAtLeastOneIsNull(o, o));
        System.out.println(ObjectUtil.isAtLeastOneIsNull(null, o));
        System.out.println(ObjectUtil.isAtLeastOneIsNull(null, null));
    }
}
