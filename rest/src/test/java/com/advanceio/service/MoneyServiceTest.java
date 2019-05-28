package com.advanceio.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MoneyServiceTest {
/*
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
*/
    @Test
    public void shouldSucceedTest() {
        assertTrue(Boolean.TRUE);
    }
}
