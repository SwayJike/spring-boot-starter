package cn.frame888.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class OrderNumberGenerator {

    private static final String DATE_FORMAT = "yyyyMMddHHmmss";
    private static final int RANDOM_BOUND = 1000; // 随机数范围
    private static final Random RANDOM = new Random();

    public static String generateOrderNumber(String prefix) {
        String PREFIX = "";
        if (prefix != null && !"".equals(prefix)){
            PREFIX = prefix + "_";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        String timestamp = dateFormat.format(new Date());

        // 生成4位随机数
        int randomValue = RANDOM.nextInt(RANDOM_BOUND);

        // 将时间戳和随机数拼接成订单号
        return PREFIX + timestamp + String.format("_%04d", randomValue);
    }

}
