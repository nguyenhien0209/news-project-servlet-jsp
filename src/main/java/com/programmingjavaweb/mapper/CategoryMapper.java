package com.programmingjavaweb.mapper;

import com.programmingjavaweb.model.CategoryModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper implements IRowMapper<CategoryModel> {
    @Override
    public CategoryModel mapRow(ResultSet resultSet) {
        try {
            CategoryModel category = new CategoryModel();
            category.setId(resultSet.getLong("id"));
            category.setName(resultSet.getString("name"));
            category.setCode(resultSet.getString("code"));
            return category;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
