package com.homework.firstweek;

import com.homework.firstweek.classLoader.HelloClassLoader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
public class MainClass {
    public static void main(String[] args) {
        String path = "file:d/";
        String methodName = "hello";
        String className = "Hello.xlass";
        HelloClassLoader helloClassLoader = new HelloClassLoader();


        try {
            URLClassLoader loader = new URLClassLoader(
                    new URL[]{new URL(path)}, helloClassLoader);
            Class helloClass = loader.loadClass(className);
            Object hello = helloClass.newInstance();
            Method helloMethod = helloClass.getMethod(methodName);
            helloMethod.invoke(hello);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
