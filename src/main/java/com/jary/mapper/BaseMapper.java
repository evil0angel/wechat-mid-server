package com.jary.mapper;

import java.util.List;

/**
 * Created by GF on 2017/6/12.
 */
public interface BaseMapper<T> {
    void insert(T obj);

    List<T> getAll();

    T getOne(Object id);

    void update(T obj);

    void delete(Object id);
}
