package com.service.mart.util;

import java.util.Random;

/**
 * Index and Constraints
 */
public class KeyUtil {

    /**
     * Generate unique primary keys.
     * Format: time + random number
     * @return
     */
    public static synchronized String genUniqueKey(){
        Random random = new Random();

        // 6-digit number
        int num = random.nextInt(90000) + 10000;

        return (System.currentTimeMillis() + String.valueOf(num));
    }

}
