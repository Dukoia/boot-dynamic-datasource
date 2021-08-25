package com.dukoia.boot;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * @Description: Algorithm
 * @Author: jiangze.He
 * @Date: 2021-08-06
 * @Version: v1.0
 */
public class Algorithm {

    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal("22.10");
        BigDecimal bigDecimal1 = new BigDecimal("10.301");

        BigDecimal subtract = bigDecimal1.subtract(bigDecimal);
        System.out.println(subtract.toString());
//        scannerIn();
    }

    private static void scannerIn() {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()) {
            int input = in.nextInt();
            String s = reverseInt(input);
            System.out.println(s);
        }
    }

    public static String reverseInt(int a) {
        String str = Integer.toString(a);
        StringBuilder builder = new StringBuilder();
        for (int i = str.length(); i > 0 ; i--) {
            builder.append(str.charAt(i));
        }
        return builder.toString();
    }


    public static int CountOne(int a) {
        int count = 0;
        String str = Integer.toBinaryString(a);
//        count = str.length() - str.replace("1", "").length();
        char[] chars = str.toCharArray();
        System.out.println(chars);
        for (char achar : chars) {
            System.out.println(achar & 1);
            count += (achar & 1);
        }
        return count;
    }

}
