package com.programmingjavaweb.dao.impl;

import com.programmingjavaweb.dao.INewsDAO;
import com.programmingjavaweb.mapper.NewsMapper;
import com.programmingjavaweb.model.NewsModel;
import com.programmingjavaweb.paging.Pageble;

import java.util.List;

public class NewsDAO extends AbstractDAO<NewsModel> implements INewsDAO {

    @Override
    public NewsModel findOne(Long id) {
        String sql = "SELECT * FROM news WHERE id = ?";
        List<NewsModel> news = query(sql, new NewsMapper(), id);
        return news.isEmpty() ? null : news.get(0);
    }

    @Override
    public List<NewsModel> findByCategoryId(Long categoryId) {
        String sql = "SELECT * FROM news WHERE categoryid = ?";
        return query(sql, new NewsMapper(), categoryId);
    }

    @Override
    public Long save(NewsModel newsModel) {
//        String sql = "INSERT INTO news(title, content, categoryid, ) VALUES(?, ?, ?)";
        StringBuilder sql = new StringBuilder("INSERT INTO news (title, content, categoryid, ");
        sql.append(" thumbnail, shortdescription, ");
        sql.append(" createddate, createdby )");
        sql.append( " VALUES (?,?,?,?,?,?,?)");
        return insert(sql.toString(), newsModel.getTitle(), newsModel.getContent(), newsModel.getCategoryId(),
                newsModel.getThumbnail(), newsModel.getShortDescription(),
                newsModel.getCreatedDate(), newsModel.getCreatedBy());
    }

    @Override
    public void update(NewsModel newNews) {
        StringBuilder sql = new StringBuilder("UPDATE news SET title = ?, thumbnail = ?, ");
        sql.append(" shortdescription = ?, content = ?, categoryid = ?, ");
        sql.append(" createddate = ?, createdby = ?, modifieddate = ?, modifiedby = ? ");
        sql.append(" WHERE id = ?");
        update(sql.toString(), newNews.getTitle(), newNews.getThumbnail(), newNews.getShortDescription(),
                newNews.getContent(), newNews.getCategoryId(),
                newNews.getCreatedDate(), newNews.getCreatedBy(), newNews.getModifiedDate(), newNews.getModifiedBy(),
                newNews.getId());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM news WHERE id = ?";
        update(sql, id);
    }

    @Override
    public List<NewsModel> findAll(Pageble pageble) {
        StringBuilder sql = new StringBuilder("SELECT * FROM news ");
        if(pageble.getSorter() != null) {
            sql.append(" ORDER BY " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy() + " ");
        }
        if(pageble.getOffset() != null && pageble.getLimit() != null) {
            sql.append(" LIMIT " + pageble.getOffset() + "," + pageble.getLimit() + " ");
        }
        return query(sql.toString(), new NewsMapper());

    }

    @Override
    public int getTotalItem() {
        String sql = "SELECT count(*) FROM news";
        return count(sql);
    }


}
