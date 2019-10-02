package com.programmingjavaweb.security;

import com.programmingjavaweb.model.UserModel;
import com.programmingjavaweb.service.IUserService;
import com.programmingjavaweb.service.impl.UserService;
import com.programmingjavaweb.utils.SessionUtil;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

public class AuthenticationImpl implements AuthenticationFilter {

    private IUserService userService;

    private String userName;
    private String password;

    public AuthenticationImpl(String userName, String password) {
        this.userName = userName;
        this.password = password;
        userService = new UserService();
    }

    @Override
    public String urlRedirect(HttpServletRequest request) {
        UserModel model = userService.findByUserNameAndPasswordAndStatus(this.userName, this.password, 1);
        if(model != null) {
            SessionUtil.getInstance().putValue(request, "USERMODEL",model);
            if(model.getRole().getCode().equals("USER")) {
                return "/trang-chu";
            } else if (model.getRole().getCode().equals("ADMIN")) {
                return "/admin-home";
            }
        } else {
            return "/dang-nhap?action=login&message=username_password_invalid&alert=danger";
        }
        return null;
    }
}
