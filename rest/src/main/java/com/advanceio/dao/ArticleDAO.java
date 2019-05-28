package com.advanceio.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.advanceio.entity.Article;

@Repository
public class ArticleDAO extends AbstractDAO<Article, Long> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    protected Class<Article> getEntityType() {
        return Article.class;
    }
}
