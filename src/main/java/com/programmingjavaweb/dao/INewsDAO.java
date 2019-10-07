package com.programmingjavaweb.dao;

import com.programmingjavaweb.builder.NewsBuilder;
import com.programmingjavaweb.model.NewsModel;
import com.programmingjavaweb.paging.Pageble;

import java.util.List;

public interface INewsDAO extends GenericDAO<NewsModel> {
    NewsModel findOne(Long id);
    List<NewsModel> findByCategoryId(Long categoryId);
    Long save(NewsModel newsModel);
    void update(NewsModel newNews);
    void delete(Long id);
    List<NewsModel> findAll(NewsBuilder newsBuilder, Pageble pageble);
    int getTotalItem(NewsBuilder newsBuilder);
}
