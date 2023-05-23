package com.multitone.resources.utils;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class URL {

    public static String decodeParam(String s){
        return URLDecoder.decode(s, StandardCharsets.UTF_8);
    }

    public static List<Long> decodeIntList(String s){
         String [] vet = s.split(",");
         List<Long> list = new ArrayList<>();
        for (int i = 0; i < vet.length; i++) {
            list.add(Long.parseLong(vet[i]));
        }
        return list;
 }
}
