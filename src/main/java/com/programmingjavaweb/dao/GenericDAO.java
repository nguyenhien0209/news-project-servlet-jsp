package com.programmingjavaweb.dao;

import com.programmingjavaweb.mapper.IRowMapper;

import java.util.List;

public interface GenericDAO <T> {
    <T> List<T> query(String sql, IRowMapper<T> rowMapper, Object... parameters);
    void update(String sql, Object... parameters);
    Long insert(String sql, Object... parameters);
    int count(String sql, Object... parameters);
}
