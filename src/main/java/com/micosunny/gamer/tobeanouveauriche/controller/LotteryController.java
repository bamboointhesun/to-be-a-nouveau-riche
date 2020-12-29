package com.micosunny.gamer.tobeanouveauriche.controller;

import com.alibaba.fastjson.JSON;
import com.micosunny.gamer.tobeanouveauriche.service.LotteryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @ author: Carmine
 * @ date: 2020/12/25-11:08
 */
@RestController
@Slf4j
@RequestMapping("/monopoly")
public class LotteryController {

    @Autowired
    private LotteryService lotteryService;
    @GetMapping("/lottery")
    public String lotteryTicket(){
        List<Integer> user = Arrays.asList(new Integer[]{12,2,3,7,11,4,1});
        Map<String, BigDecimal> stringIntegerMap = lotteryService.checkPrizeLevel(user);
        return JSON.toJSONString(stringIntegerMap);
    }

}
