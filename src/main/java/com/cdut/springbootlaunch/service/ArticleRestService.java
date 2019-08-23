package com.cdut.springbootlaunch.service;

import com.cdut.springbootlaunch.model.Article;

import java.util.List;

public interface ArticleRestService {

    public Article saveArticle(Article article);

    public void deleteArticle(Long id);

    public void updateArticle(Article article);

    public Article getArticle(Long id);

    public List<Article> getAll();
}
