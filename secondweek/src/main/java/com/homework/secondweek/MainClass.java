package com.homework.secondweek;

import com.homework.secondweek.util.HttpUtils;

import java.io.IOException;

public class MainClass {
    public static void main(String[] args)throws IOException, RuntimeException{
        String result = HttpUtils.getToObject("http://localhost:8088/api/hello", String.class);
        System.out.println(result);
    }
}
