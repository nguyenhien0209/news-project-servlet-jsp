package com.programmingjavaweb.controller.admin.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.programmingjavaweb.model.NewsModel;
import com.programmingjavaweb.model.UserModel;
import com.programmingjavaweb.service.INewsService;
import com.programmingjavaweb.utils.HttpUtil;
import com.programmingjavaweb.utils.SessionUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api-admin-news"})
public class NewsAPI extends HttpServlet {

    @Inject
    private INewsService newsService;

    protected void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        //De du lieu tieng viet tu clien gui ve server khong bi loi font
        request.setCharacterEncoding("UTF-8");
        //Dinh nghia kieu du lieu JSON tu server tra ve cho client
        response.setContentType("application/json");
        NewsModel newsModel = HttpUtil.of(request.getReader()).toModel(NewsModel.class);
        newsModel.setCreatedBy(((UserModel)SessionUtil.getInstance().getValue(request,"USERMODEL")).getUserName());
        newsModel = newsService.save(newsModel);
        mapper.writeValue(response.getOutputStream(),newsModel);
    }

    protected void doPut (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        NewsModel newNews = HttpUtil.of(request.getReader()).toModel(NewsModel.class);
        newNews.setModifiedBy(((UserModel)SessionUtil.getInstance().getValue(request,"USERMODEL")).getUserName());
        newNews = newsService.update(newNews);
        mapper.writeValue(response.getOutputStream(), newNews);
    }

    protected void doDelete (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        NewsModel newsModel = HttpUtil.of(request.getReader()).toModel(NewsModel.class);
        newsService.delete(newsModel.getIds());
        mapper.writeValue(response.getOutputStream(), "{}");
    }

}
