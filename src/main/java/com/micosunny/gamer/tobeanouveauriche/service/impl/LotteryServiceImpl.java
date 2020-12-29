package com.micosunny.gamer.tobeanouveauriche.service.impl;

import com.alibaba.fastjson.JSON;
import com.micosunny.gamer.tobeanouveauriche.service.LotteryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @ author: Carmine
 * @ date: 2020/12/25-10:59
 */
@Service
@Slf4j
public class LotteryServiceImpl implements LotteryService {


    /**
     * 解析中奖等级和奖金金额
     */
    @Override
    public Map<String, BigDecimal> checkPrizeLevel(List<Integer> userNumbers) {
        Map<String,Integer> resultMap = checkPrizeNums(userNumbers);
        Map<String,BigDecimal> noteMoneyMap = new HashMap<>();
        log.info("您的参与号码是:" + JSON.toJSONString(userNumbers));
        noteMoneyMap.put("没有中奖",new BigDecimal(0));
        Integer first = resultMap.get("first");
        Integer second = resultMap.get("second");
        if (first == 5 && second == 2) {
            noteMoneyMap.clear();
            noteMoneyMap.put("一等奖",new BigDecimal(300000));
        }
        if (first == 5 && second == 1) {
            noteMoneyMap.clear();
            noteMoneyMap.put("二等奖",new BigDecimal(200000));
        }
        if (first == 5 && second == 0) {
            noteMoneyMap.clear();
            noteMoneyMap.put("三等奖",new BigDecimal(100000));
        }
        if (first == 4 && second == 2) {
            noteMoneyMap.clear();
            noteMoneyMap.put("四等奖",new BigDecimal(3000));
        }
        if (first == 4 && second == 1) {
            noteMoneyMap.clear();
            noteMoneyMap.put("五等奖",new BigDecimal(300));
        }
        if (first == 3 && second == 2) {
            noteMoneyMap.clear();
            noteMoneyMap.put("六等奖",new BigDecimal(200));
        }
        if (first == 4 && second == 0) {
            noteMoneyMap.clear();
            noteMoneyMap.put("七等奖",new BigDecimal(100));
        }
        if (first == 3 && second == 1) {
            noteMoneyMap.clear();
            noteMoneyMap.put("八等奖",new BigDecimal(15));
        }
        if (first == 2 && second == 2) {
            noteMoneyMap.clear();
            noteMoneyMap.put("八等奖",new BigDecimal(15));
        }
        if (first == 3 && second == 0) {
            noteMoneyMap.clear();
            noteMoneyMap.put("九等奖",new BigDecimal(5));
        }
        if (first == 2 && second == 1) {
            noteMoneyMap.clear();
            noteMoneyMap.put("九等奖",new BigDecimal(5));
        }
        if (first == 1 && second == 2) {
            noteMoneyMap.clear();
            noteMoneyMap.put("九等奖",new BigDecimal(5));
        }
        if (first == 0 && second == 2) {
            noteMoneyMap.clear();
            noteMoneyMap.put("九等奖",new BigDecimal(5));
        }
        return noteMoneyMap;
    }

    /**
     * 开奖
     * 第一组 35选5 [1,35]
     * 第二组 12选2 [1,12]
     */
    private  Map<String, List<Integer>> rewardNumbers() {
        ThreadLocalRandom current = ThreadLocalRandom.current();
        List<Integer> firstGroupNums = new ArrayList<>();
        List<Integer> secondGroupNums = new ArrayList<>();
        Map<String, List<Integer>> resultMap = new HashMap<>();
        log.info("第一组 35选5 准备开奖");
        int i = 1;
        while (firstGroupNums.size() < 5) {
            // 生成 [1,35]的整数 5个
            int num = current.nextInt(1, 36);
            if (!firstGroupNums.contains(num)) {
                firstGroupNums.add(num);
                log.info("第一组 第" + i + "个中奖号码是:" + num);
                i++;
            }
        }
        log.info("第一组的中奖号码有:" + JSON.toJSONString(firstGroupNums));
        log.info("第二组 12选2 准备开奖");
        while (secondGroupNums.size() < 2) {
            // 生成 [1,12]的整数 2个
            int num = current.nextInt(1, 13);
            if (!secondGroupNums.contains(num)) {
                secondGroupNums.add(num);
                log.info("第二组 第" + i + "个中奖号码是:" + num);
                i++;
            }
        }
        log.info("第二组的中奖号码有:" + JSON.toJSONString(secondGroupNums));
        firstGroupNums.addAll(secondGroupNums);
        log.info("本期中奖号码有:" + JSON.toJSONString(firstGroupNums));
        resultMap.put("first", firstGroupNums);
        resultMap.put("second", secondGroupNums);
        return resultMap;
    }

    /**
     * 验证中奖号码个数
     */
    private  Map<String, Integer> checkPrizeNums(List<Integer> userNumbers) {
        int firstGroup = 0;
        int secondGroup = 0;
        Map<String, List<Integer>> rewardNumbersListMap = rewardNumbers();
        List<Integer> rewardFirstGroup = rewardNumbersListMap.get("first");
        List<Integer> rewardSecondGroup = rewardNumbersListMap.get("second");
        Map<String, Integer> resultMap = new HashMap<>();
        for (int i = 0; i < userNumbers.size() - 2; i++) {
            if (rewardFirstGroup.contains(userNumbers.get(i))) {
                firstGroup++;
            }
        }
        for (int i = userNumbers.size() - 2; i < userNumbers.size(); i++) {
            if (rewardSecondGroup.contains(userNumbers.get(i))) {
                secondGroup++;
            }
        }
        resultMap.put("first", firstGroup);
        resultMap.put("second", secondGroup);
        return resultMap;
    }
}
