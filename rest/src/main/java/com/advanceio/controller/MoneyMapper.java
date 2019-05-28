package com.advanceio.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;

import com.advanceio.dto.MoneyDTO;
import com.advanceio.entity.Money;

@Mapper
public abstract class MoneyMapper {

    abstract Money toEntity(MoneyDTO articleDTO);

    abstract MoneyDTO toDTO(Money find);

    List<MoneyDTO> toDTOsList(List<Money> articles) {
        return articles
                .stream()
                .map(a -> toDTO(a))
                .collect(Collectors.toList());
    }
}