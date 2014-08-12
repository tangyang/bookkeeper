package com.weibo.bookkeeper.util;

/**
 * Created by TangYang on 14-8-12.
 */
public class MixAll {

    public static final Double toDoubleOrNull(String str) {
        try {
            return Double.parseDouble(str);
        } catch (Exception e) {
            return null;
        }
    }
}
