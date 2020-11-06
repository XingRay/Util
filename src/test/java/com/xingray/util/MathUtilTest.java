package com.xingray.util;

import org.junit.Test;

public class MathUtilTest {
    @Test
    public void test01() {
        System.out.println(MathUtil.reserveValidNumber(954785, 3));
        System.out.println(MathUtil.reserveValidNumber(0.0154545145, 3));
        System.out.println(MathUtil.reserveValidNumber(0, 3));
        System.out.println(MathUtil.reserveValidNumber(-132345, 3));
    }

    @Test
    public void test02() {
        System.out.println(MathUtil.getSplitRange(52, 1458, 10));
        System.out.println(MathUtil.getSplitRange(12, 458, 10));
        System.out.println(MathUtil.getSplitRange(0.04, 10.23, 10));
        System.out.println(MathUtil.getSplitRange(0.01, 0.21, 10));
    }
}
