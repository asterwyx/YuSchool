package com.yuschool.utils;

import java.util.Random;
import java.util.UUID;

public class CodeUtil {

    /**
     * 生成唯一的激活码
     * @return 激活码
     */
    public static String generateUniqueCode() {
        return CodeUtil.generateRawUUID().replaceAll("-", "");
    }

    /**
     * 生成UUID
     * @return UUID
     */
    public static String generateRawUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取几位随机数
     * @author 郑明亮
     * @param number 验证码的位数
     * @return 数字验证码的字符串形式
     */
    public static String getRandomNumCode(int number){
        StringBuilder codeNum = new StringBuilder();
        int [] numbers = {0,1,2,3,4,5,6,7,8,9};
        Random random = new Random();
        for (int i = 0; i < number; i++) {
            int next = random.nextInt(10000);//目的是产生足够随机的数，避免产生的数字重复率高的问题
            codeNum.append(numbers[next % 10]);
        }
        System.out.println("--------");
        System.out.println(codeNum);

        return codeNum.toString();
    }


}
