package com.advanceio.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.advanceio.dao.ArticleDAO;
import com.advanceio.entity.Article;
import com.advanceio.exception.BadRequestException;
import com.advanceio.exception.NotFoundException;
import com.advanceio.service.ArticleService;

@RunWith(MockitoJUnitRunner.class)
public class ArticleControllerTest {

    @Mock
    private ArticleDAO articleDAO;

    @InjectMocks
    private ArticleService articleService;

    private Article mockCorrectArticle() {
        Article article = new Article();
        article.setContent("This is stub content");
        return article;
    }

    private Article mockNullContentArticle() {
        Article article = new Article();
        return article;
    }

    @Test
    public void shouldFailOnNullContent() {
        Article article = mockNullContentArticle();
        try {
            articleService.save(article);
            fail("Should not be able to save null articleContent;");
        } catch (BadRequestException notFoundException) {
            assertTrue(Boolean.TRUE);
        }
    }

    @Test
    public void shouldSucceedOnCorrectArticle() {
        Article article = mockCorrectArticle();
        Mockito.when(articleDAO.create(Mockito.any())).thenReturn(mockCorrectArticle());
        assertNotNull(articleService.save(article));
    }

}
