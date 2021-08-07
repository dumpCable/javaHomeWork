package com.homework.firstweek.classLoader;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractClassLoader extends ClassLoader{


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] bytes = convertByte(name);
            name = name.replace(".xlass", "");
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException();
        }
    }

    public byte[] convertByte(String filePath) throws FileNotFoundException, IOException {
        BufferedInputStream io = new BufferedInputStream(
                new FileInputStream(filePath));
        List<Byte> bytes = new ArrayList<Byte>(1024);
        byte[] a = new byte[1024];
        int len;
        while ((len = io.read(a, 0, 1024)) != -1) {
            for (int i = 0; i < len; i++) {
                bytes.add((byte)(255 - a[i]));
            }
        }
        a = new byte[bytes.size()];
        int i = 0;
        for (Byte aByte : bytes) {
            a[i++] = aByte;
        }
        return a;
    }

}
