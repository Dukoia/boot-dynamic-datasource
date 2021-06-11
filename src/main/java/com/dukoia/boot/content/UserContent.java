package com.dukoia.boot.content;

public class UserContent {

    private static ThreadLocal<String> map = new ThreadLocal<>();

    public static void put(String userid){
        map.set(userid);
    }

    public static String get(){
        return map.get();
    }

    public static void clean(){
        map.remove();
    }
}
