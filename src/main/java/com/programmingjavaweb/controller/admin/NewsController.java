package com.programmingjavaweb.controller.admin;

import com.programmingjavaweb.constant.SystemConstant;
import com.programmingjavaweb.model.NewsModel;
import com.programmingjavaweb.paging.PageRequest;
import com.programmingjavaweb.paging.Pageble;
import com.programmingjavaweb.service.ICategoryService;
import com.programmingjavaweb.service.INewsService;
import com.programmingjavaweb.sort.Sorter;
import com.programmingjavaweb.utils.FormUtil;
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

@WebServlet(urlPatterns = {"/admin-news"})
public class NewsController extends HttpServlet {

    @Inject
    private INewsService newsService;

    @Inject
    private ICategoryService categoryService;

    ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

    protected void doGet (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        NewsModel model = FormUtil.toModel(NewsModel.class, request);
        String view = "";
        if(model.getType().equals(SystemConstant.LIST)) {
            Pageble pageable = new PageRequest(model.getPage(), model.getMaxPageItem(), new Sorter(model.getSortName(), model.getSortBy()));
            model.setListResult(newsService.findAll(pageable));
            model.setTotalItem(newsService.getTotalItem());
            model.setTotalPages((int) Math.ceil(model.getTotalItem() / model.getMaxPageItem()));
            view = "/views/admin/news/list.jsp";
        } else if (model.getType().equals(SystemConstant.EDIT)) {
            if(model.getId() != null) {
                model = newsService.findOne(model.getId());
            }
            view = "/views/admin/news/edit.jsp";
        }
        if(model.getMessage() != null && model.getAlert() != null) {
            MessageUtil.of(resourceBundle.getString(model.getMessage()), model.getAlert()).buildMessage(request);
        }
        request.setAttribute("categories", categoryService.findAll());
        request.setAttribute(SystemConstant.MODEL, model);
        RequestDispatcher rd = request.getRequestDispatcher(view);
        rd.forward(request, response);
    }

    protected void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
