package com.haha.Misc;

import java.util.HashMap;
import java.util.Map;

public class MapMap {
    public static void main(String[] args) {
        Map<String, Long> haha = new HashMap<>();
        haha.put("jsdkjs",10l);
        haha.forEach((k,v)-> {
            System.out.println(k+v);
        });
    }
}
