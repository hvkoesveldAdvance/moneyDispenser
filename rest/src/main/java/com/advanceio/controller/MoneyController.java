package com.advanceio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.advanceio.dto.MoneyDTO;
import com.advanceio.dto.WithdrawRequestDTO;
import com.advanceio.entity.Money;
import com.advanceio.service.MoneyService;

@RestController
@RequestMapping("money")
public class MoneyController {

    @Autowired
    private MoneyService moneyService;

    @Autowired
    private MoneyMapper moneyMapper;

    @GetMapping()
    public List<MoneyDTO> findAll() {
        List<Money> funds = moneyService.findAll();
        return moneyMapper.toDTOsList(funds);
    }

    @PostMapping("withdraw")
    public List<MoneyDTO> withdrawFunds(@RequestBody WithdrawRequestDTO request) {
        List<Money> withdrawnFunds = moneyService.withdraw(request.getChange());
        return moneyMapper.toDTOsList(withdrawnFunds);
    }
}
