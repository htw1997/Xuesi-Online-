package com.xuesi.utils;

import java.text.DecimalFormat;

public class StrTimeUtils {
    public static String intToStrTime(float num) {
        StringBuilder sb = new StringBuilder();
        DecimalFormat df = new DecimalFormat("00");
        float ss = num % 60;
        num = num / 60;
        float mm =  num % 60;
        float hh = num / 60;
        sb.append( df.format(hh));
        sb.append(":").append(df.format(mm));
        sb.append(":").append(df.format(ss));
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(intToStrTime(7300));
    }
}
