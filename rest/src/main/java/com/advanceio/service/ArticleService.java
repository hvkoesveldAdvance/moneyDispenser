package com.advanceio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceio.dao.ArticleDAO;
import com.advanceio.dao.SingletonFundRepository;
import com.advanceio.entity.Article;
import com.advanceio.exception.BadRequestException;
import com.google.common.base.Strings;

@Service
public class ArticleService {

    @Autowired
    private ArticleDAO articleDAO;
    
    @Autowired
    private SingletonFundRepository singletonFundRepository;

    public Article find(Long id) {
        return articleDAO.find(id);
    }

    public List<Article> findAll() {
        return articleDAO.findAll();
    }

    public Article save(Article article) {
        if (Strings.isNullOrEmpty(article.getContent())) {
            throw new BadRequestException("Content must be provided for article");
        }
        return articleDAO.create(article);
    }

    public Article update(Article article) {
        return articleDAO.update(article);
    }

    public Article delete(Long id) {
        Article entityToDelete = find(id);
        articleDAO.delete(entityToDelete);
        return entityToDelete;
    }
}
