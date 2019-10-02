package com.programmingjavaweb.dao;

import com.programmingjavaweb.model.UserModel;

public interface IUserDAO extends GenericDAO<UserModel> {
    UserModel findByUserNameAndPasswordAndStatus(String userName, String password, int status);
}
