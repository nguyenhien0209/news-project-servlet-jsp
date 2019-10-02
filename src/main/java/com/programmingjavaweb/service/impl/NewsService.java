package com.programmingjavaweb.service.impl;

import com.programmingjavaweb.builder.NewsBuilder;
import com.programmingjavaweb.dao.ICategoryDAO;
import com.programmingjavaweb.dao.INewsDAO;
import com.programmingjavaweb.model.CategoryModel;
import com.programmingjavaweb.model.NewsModel;
import com.programmingjavaweb.paging.Pageble;
import com.programmingjavaweb.service.INewsService;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;

public class NewsService implements INewsService {

    @Inject
    private INewsDAO newsDao;

    @Inject
    private ICategoryDAO categoryDAO;

    @Override
    public List<NewsModel> findByCategoryId(Long categoryId) {
        return newsDao.findByCategoryId(categoryId);
    }

    @Override
    public NewsModel save(NewsModel newsModel) {
        newsModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        CategoryModel categoryModel = categoryDAO.findOneByCode(newsModel.getCategoryCode());
        newsModel.setCategoryId(categoryModel.getId());
        Long newId = newsDao.save(newsModel);
        return newsDao.findOne(newId);
    }

    @Override
    public NewsModel update(NewsModel newNews) {
        NewsModel oldNews = newsDao.findOne(newNews.getId());
        newNews.setCreatedDate(oldNews.getCreatedDate());
        newNews.setCreatedBy(oldNews.getCreatedBy());
        newNews.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        CategoryModel categoryModel = categoryDAO.findOneByCode(newNews.getCategoryCode());
        newNews.setCategoryId(categoryModel.getId());
        newsDao.update(newNews);
        return newsDao.findOne(newNews.getId());
    }

    @Override
    public void delete(Long[] ids) {
        for(Long id : ids) {
            //delete comment before (foreign key to comment table)
            //delete news after
            newsDao.delete(id);
        }
    }

    @Override
    public List<NewsModel> findAll(NewsBuilder newsBuilder,Pageble pageble) {
        return newsDao.findAll(newsBuilder, pageble);
    }

    @Override
    public int getTotalItem() {
        return newsDao.getTotalItem();
    }

    @Override
    public NewsModel findOne(Long id) {
        NewsModel newsModel= newsDao.findOne(id);
        CategoryModel categoryModel = categoryDAO.findOne(newsModel.getCategoryId());
        newsModel.setCategoryCode(categoryModel.getCode());
        return newsModel;
    }
}
