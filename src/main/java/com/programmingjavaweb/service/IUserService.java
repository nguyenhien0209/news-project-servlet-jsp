package com.programmingjavaweb.service;

import com.programmingjavaweb.model.UserModel;

public interface IUserService {
    UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status);

}
