package com.xingray.util.base;

public interface Mapper2<T1, T2, V> {
    V map(T1 t1, T2 t2);
}