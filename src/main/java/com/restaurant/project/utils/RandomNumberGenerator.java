package com.restaurant.project.utils;

import java.util.Random;

public class RandomNumberGenerator {
    public static String generateRandomNumber(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("长度必须大于 0");
        }

        // 可以根据需求修改数字范围
        long min = (long) Math.pow(10, length - 1);
        long max = (long) Math.pow(10, length) - 1;

        Random random = new Random();
        long range = max - min + 1;
        long randomNum = min + (long) (random.nextDouble() * range);
        return String.valueOf(randomNum);
    }
}
