package com.xingray.util.base;

public interface Mapper<T, V> {
    V map(T t);
}