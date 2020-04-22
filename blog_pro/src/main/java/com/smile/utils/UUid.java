package com.smile.utils;

import java.util.UUID;

/**
 * @author: Smile
 * @description: id
 * @create: 2020-03-25 08:40
 */
public class UUid {
    public static void main(String[] args) {
        System.out.println(getId());
    }
    public static String getId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}