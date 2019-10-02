package com.programmingjavaweb.service.impl;

import com.programmingjavaweb.dao.IUserDAO;
import com.programmingjavaweb.dao.impl.UserDAO;
import com.programmingjavaweb.model.UserModel;
import com.programmingjavaweb.service.IUserService;

public class UserService implements IUserService {

    private IUserDAO userDAO;

    public UserService() {
        userDAO = new UserDAO();
    }

    @Override
    public UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status) {
        return userDAO.findByUserNameAndPasswordAndStatus(userName, password, status);
    }
}
