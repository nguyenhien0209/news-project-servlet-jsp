package com.programmingjavaweb.service;

import com.programmingjavaweb.builder.NewsBuilder;
import com.programmingjavaweb.model.NewsModel;
import com.programmingjavaweb.paging.Pageble;

import java.util.List;

public interface INewsService {
    List<NewsModel> findByCategoryId(Long categoryId);
    NewsModel save (NewsModel newsModel);
    NewsModel update (NewsModel newsModel);
    void delete(Long[] ids);
    List<NewsModel> findAll(NewsBuilder newsBuilder, Pageble pageble);
    int getTotalItem();
    NewsModel findOne(Long id);
}
