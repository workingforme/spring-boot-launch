package com.cdut.springbootlaunch.service;

import com.cdut.springbootlaunch.dao.ArticleJDBCDAO;
import com.cdut.springbootlaunch.model.Article;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleRestJDBCServiceImpl implements ArticleRestService{

    @Resource
    private ArticleJDBCDAO articleJDBCDAO;

    @Resource
    JdbcTemplate primaryJdbcTemplate;

    @Resource
    JdbcTemplate secondaryJdbcTemplate;

    @Override
    @Transactional
//    @Transactional 事务的管理   saveArticle方法一旦有异常，所有的数据库操作就回滚
    public Article saveArticle(Article article) {

        articleJDBCDAO.save(article,primaryJdbcTemplate);

        articleJDBCDAO.save(article,secondaryJdbcTemplate);

        return article;
    }

    @Override
    public void deleteArticle(Long id) {
        articleJDBCDAO.deleteById(id,primaryJdbcTemplate);
    }

    @Override
    public void updateArticle(Article article) {
        articleJDBCDAO.updateById(article,primaryJdbcTemplate);
    }

    @Override
    public Article getArticle(Long id) {
        return articleJDBCDAO.findById(id,primaryJdbcTemplate);
    }

    @Override
    public List<Article> getAll() {
        return articleJDBCDAO.findAll(primaryJdbcTemplate);
    }
}
