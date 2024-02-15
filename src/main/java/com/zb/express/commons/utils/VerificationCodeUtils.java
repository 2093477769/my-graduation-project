package com.zb.express.commons.utils;

import java.util.Random;

public class VerificationCodeUtils {

    public static String verification() {
        String list = "0123456789";
        StringBuilder sb = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
            int index = r.nextInt(list.length());
            sb.append(list.charAt(index));
        }
        return sb.toString();
    }

}

