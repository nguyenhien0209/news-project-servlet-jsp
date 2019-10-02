package com.programmingjavaweb.mapper;

import java.sql.ResultSet;

public interface IRowMapper<T> {
    //De gan du lieu tu ResultSet sang Model va nguoc lai
//    <T> T mapRow(ResultSet resultSet);
    T mapRow(ResultSet resultSet);
}
