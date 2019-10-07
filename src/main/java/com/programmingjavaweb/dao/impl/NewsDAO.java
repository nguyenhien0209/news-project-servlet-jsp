package com.programmingjavaweb.dao.impl;

import com.programmingjavaweb.builder.NewsBuilder;
import com.programmingjavaweb.dao.INewsDAO;
import com.programmingjavaweb.mapper.NewsMapper;
import com.programmingjavaweb.model.NewsModel;
import com.programmingjavaweb.paging.Pageble;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
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
    public List<NewsModel> findAll(NewsBuilder newsBuilder, Pageble pageble) {
        StringBuilder sql = new StringBuilder("SELECT * FROM news AS n ");
        if(StringUtils.isNotBlank(newsBuilder.getCode())) {
            sql.append(" INNER JOIN category AS c ON n.categoryid = c.id");
        }
        sql.append(" WHERE 1=1 ");
        sql = buildNewsQuery(sql, newsBuilder);
//        if(StringUtils.isNotBlank(newsBuilder.getTitle())) {
//            sql.append(" AND LOWER(title) LIKE '%" + newsBuilder.getTitle().toLowerCase() +"%'");
//        }
//        if(StringUtils.isNotBlank(newsBuilder.getCode())) {
//            sql.append(" AND c.code LIKE '%" + newsBuilder.getCode() +"%'");
//        }
        if(StringUtils.isNotBlank(pageble.getSorter().getSortBy()) && StringUtils.isNotBlank(pageble.getSorter().getSortName())) {
            sql.append(" ORDER BY " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy() + " ");
        }
        if(pageble.getOffset() != null && pageble.getLimit() != null) {
            sql.append(" LIMIT " + pageble.getOffset() + "," + pageble.getLimit() + " ");
        }
        return query(sql.toString(), new NewsMapper());

    }

    private StringBuilder buildNewsQuery(StringBuilder sql, NewsBuilder newsBuilder) {
        Field[] fields = NewsBuilder.class.getDeclaredFields();
        for(Field field : fields) {
            String fieldType = field.getType().getName();
            //String: java.lang.String
        }
        return null;
    }

    @Override
    public int getTotalItem(NewsBuilder newsBuilder) {
        StringBuilder sql = new StringBuilder("SELECT count(*) FROM news");
        if(StringUtils.isNotBlank(newsBuilder.getCode())) {
            sql.append(" INNER JOIN category AS c ON n.categoryid = c.id");
        }
        sql.append(" WHERE 1=1 ");
        if(StringUtils.isNotBlank(newsBuilder.getTitle())) {
            sql.append(" AND LOWER(title) LIKE '%" + newsBuilder.getTitle().toLowerCase() +"%'");
        }
        if(StringUtils.isNotBlank(newsBuilder.getCode())) {
            sql.append(" AND c.code LIKE '%" + newsBuilder.getCode() +"%'");
        }
        return count(sql.toString());
    }


}
