package com.cdut.springboot.service;

import com.cdut.springboot.dao.Article;
import com.cdut.springboot.dao.ArticleRepository;
import com.cdut.springboot.model.ArticleVO;
import com.cdut.springboot.utils.DozerUtils;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ArticleRestJPAServiceImpl implements ArticleRestService{


    //articleRepository JPA提供的操作对象，其中包含了很多对于数据的操作方法
    @Resource
    private ArticleRepository articleRepository;

    @Resource
    private Mapper dozerMapper;

    @Override
    public ArticleVO saveArticle(ArticleVO article) {

/*
  Article articlePO = dozerMapper.map(article,Article.class);;
    将页面传过来的VO 转化成PO 存到数据库

   */
        Article articlePO = dozerMapper.map(article,Article.class);;
        articleRepository.save(articlePO);

        return article;
    }

    @Override
    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }

    @Override
    public void updateArticle(ArticleVO article) {
        Article articlePO = dozerMapper.map(article,Article.class);;
        articleRepository.save(articlePO);
    }

    @Override
    public ArticleVO getArticle(Long id) {

        Optional<Article> article = articleRepository.findById(id);

        ArticleVO articleVO = dozerMapper.map(article.get(),ArticleVO.class);

        //把读者查出来
//        articleVO.setReader();

        return articleVO;
    }

    @Override
    public List<ArticleVO> getAll() {


        List<Article> articleLis = articleRepository.findAll();

        return DozerUtils.mapList(articleLis,ArticleVO.class);
    }
}
