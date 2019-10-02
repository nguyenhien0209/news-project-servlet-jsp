package com.programmingjavaweb.controller.web;

import com.programmingjavaweb.model.AbstractModel;
import com.programmingjavaweb.model.UserModel;
import com.programmingjavaweb.security.AuthenticationFilter;
import com.programmingjavaweb.service.ICategoryService;
import com.programmingjavaweb.service.INewsService;
import com.programmingjavaweb.service.IUserService;
import com.programmingjavaweb.utils.FormUtil;
import com.programmingjavaweb.utils.SessionUtil;
import com.programmingjavaweb.utils.message.MessageUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

@WebServlet(urlPatterns = {"/trang-chu","/dang-nhap", "/thoat"})
public class HomController extends HttpServlet {

	@Inject
	private ICategoryService categoryService;

	@Inject
	private IUserService userService;

	ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

	protected void doGet (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null && action.equals("login")) {
            UserModel model = FormUtil.toModel(UserModel.class, request);
		    if(model.getMessage() != null && model.getAlert() != null) {
                MessageUtil.of(resourceBundle.getString(model.getMessage()), model.getAlert()).buildMessage(request);
            }
			RequestDispatcher rd = request.getRequestDispatcher("/views/login.jsp");
			rd.forward(request, response);
		} else if (action != null && action.equals("logout")) {
			SessionUtil.getInstance().removeValue(request, "USERMODEL");
			response.sendRedirect(request.getContextPath() + "/trang-chu");
		} else {
			request.setAttribute("categories", categoryService.findAll());
			RequestDispatcher rd = request.getRequestDispatcher("/views/web/home.jsp");
			rd.forward(request, response);
		}
	}
	
	protected void doPost (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null && action.equals("login")) {
			UserModel model = FormUtil.toModel(UserModel.class, request);
			String url = AuthenticationFilter.of(model.getUserName(), model.getPassword()).urlRedirect(request);
			response.sendRedirect(request.getContextPath() + url);

//			model = userService.findByUserNameAndPasswordAndStatus(model.getUserName(), model.getPassword(), 1);
//			if(model != null) {
//				SessionUtil.getInstance().putValue(request, "USERMODEL",model);
//				if(model.getRole().getCode().equals("USER")) {
//					response.sendRedirect(request.getContextPath() + "/trang-chu");
//				} else if (model.getRole().getCode().equals("ADMIN")) {
//					response.sendRedirect(request.getContextPath() + "/admin-home");
//				}
//			} else {
//				response.sendRedirect(request.getContextPath() + "/dang-nhap?action=login&message=username_password_invalid&alert=danger");
//			}
		}
	}
}
