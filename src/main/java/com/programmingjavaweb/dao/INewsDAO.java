package com.programmingjavaweb.dao;

import com.programmingjavaweb.model.NewsModel;
import com.programmingjavaweb.paging.Pageble;

import java.util.List;

public interface INewsDAO extends GenericDAO<NewsModel> {
    NewsModel findOne(Long id);
    List<NewsModel> findByCategoryId(Long categoryId);
    Long save(NewsModel newsModel);
    void update(NewsModel newNews);
    void delete(Long id);
    List<NewsModel> findAll(Pageble pageble);
    int getTotalItem();
}