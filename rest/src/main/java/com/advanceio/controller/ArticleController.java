package com.advanceio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.advanceio.dto.ArticleDTO;
import com.advanceio.entity.Article;
import com.advanceio.service.ArticleService;

//@Controller
@RestController
@RequestMapping("api/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    
    @Autowired
    private ArticleMapper articleMapper;

    @GetMapping("{id}")
    public ArticleDTO findArticleById(@PathVariable("id") Long id) {
        return articleMapper.toDTO(articleService.find(id));
    }

    @GetMapping()
    public List<ArticleDTO> findAllArticles() {
        return articleMapper.toDTOsList(articleService.findAll());
    }

    @PostMapping()
    public ArticleDTO addArticle(@RequestBody Article article) {
        return articleMapper.toDTO(articleService.save(article));
    }

    @PutMapping()
    public ArticleDTO updateArticle(@RequestBody Article article) {
        return articleMapper.toDTO(articleService.update(article));
    }

    @DeleteMapping("article/{id}")
    public ArticleDTO deleteArticle(@PathVariable("id") Long id) {
        return articleMapper.toDTO(articleService.delete(id));
    }
}