package com.example.boot.util;

import java.security.SecureRandom;
import java.util.Random;

public class HashUtils {

    public static String generateRandomKey(int keySize) {
        Random oRandom = new SecureRandom();
        StringBuilder key = new StringBuilder();

        while (key.length() < keySize) {
            int iKey = Math.abs(oRandom.nextInt()%74)+48;

            if ((iKey >= 48 && iKey <=57) || (iKey >= 65 && iKey <=90) || (iKey >= 97 && iKey <=122)) {
                key.append((char) (iKey));
            }
        }
        return key.toString();
    }

}
