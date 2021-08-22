package com.cable.gateway.register;

import com.cable.gateway.service.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public final class LocalFileServiceRegister extends ServiceRegister {

    @Override
    public void register() {
        try {
            System.getProperties().load(new BufferedInputStream(
                    LocalFileServiceRegister.class.getClassLoader().getResourceAsStream("service.properties")
            ));
            int i = 1;
            String propertyValue;
            while ((propertyValue = System.getProperty("service" + i)) != null) {
                String[] serviceAddresses = propertyValue.split(",");
                Service service = new Service();
                service.setServiceName("service" + i);
                List<String> list = new ArrayList<String>();
                for (String serviceAddress : serviceAddresses) {
                    list.add(serviceAddress);
                }
                service.setServerAddresses(list);
                this.addService(service);
                i++;
            }

        } catch (IOException e) {
            System.out.println("本地配置注册异常" + e);
        }
    }
}
