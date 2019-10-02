package com.programmingjavaweb.dao.impl;

import com.programmingjavaweb.dao.IUserDAO;
import com.programmingjavaweb.mapper.UserMapper;
import com.programmingjavaweb.model.UserModel;

import java.util.List;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO {
    @Override
    public UserModel findByUserNameAndPasswordAndStatus(String userName, String password, int status) {
        StringBuilder sql = new StringBuilder("SELECT * FROM user AS u ");
        sql.append(" INNER JOIN role AS r ON r.id = u.roleid");
        sql.append(" WHERE u.username = ? AND u.password = ? AND u.status = ?");
        List<UserModel> users = query(sql.toString(), new UserMapper(), userName, password, status);
        return users.isEmpty() ? null : users.get(0);
    }
}
