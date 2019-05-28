package com.advanceio.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;

import com.advanceio.dto.ArticleDTO;
import com.advanceio.entity.Article;

@Mapper
public abstract class ArticleMapper {

    abstract Article toEntity(ArticleDTO articleDTO);

    abstract ArticleDTO toDTO(Article find);

    List<ArticleDTO> toDTOsList(List<Article> articles) {
        return articles
                .stream()
                .map(a -> toDTO(a))
                .collect(Collectors.toList());
    }
}