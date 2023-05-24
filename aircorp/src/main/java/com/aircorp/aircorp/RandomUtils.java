package com.aircorp.aircorp;

import java.security.SecureRandom;

public class RandomUtils {
    static private final String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static private final SecureRandom rnd = new SecureRandom();

    static public String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for(int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }
}
