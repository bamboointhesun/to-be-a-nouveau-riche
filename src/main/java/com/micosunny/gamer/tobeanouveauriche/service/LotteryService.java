package com.micosunny.gamer.tobeanouveauriche.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @ author: Carmine
 * @ date: 2020/12/25-10:58
 */
public interface LotteryService {

    Map<String, BigDecimal> checkPrizeLevel(List<Integer> userNumbers);

}
