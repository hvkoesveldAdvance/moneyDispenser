package com.advanceio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.advanceio.dto.WithdrawRequestDTO;
import com.advanceio.entity.Money;
import com.advanceio.service.MoneyService;

@RestController
@RequestMapping("api/money")
public class MoneyController {

    @Autowired
    private MoneyService moneyService;

    @GetMapping()
    public List<Money> findAll() {
        return moneyService.findAll();
    }

    @PutMapping("withdraw")
    public List<Money> withdrawFunds(WithdrawRequestDTO request) {
        return moneyService.withdraw(request.getChange());
    }

}
