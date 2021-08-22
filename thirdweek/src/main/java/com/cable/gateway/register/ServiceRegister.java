package com.cable.gateway.register;

import com.cable.gateway.service.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//todo 增加观察者模式更新数据
public abstract class ServiceRegister {
    private static Map<String, Service> services;

    public abstract void register();

    public void addService(Service service){
        if (service == null) {
            return ;
        }
        if (services == null) {
            services = new HashMap<String, Service>();
        }
        services.put(service.getServiceName(), service);
    }


    public void removeService(Service service){
        if (service == null) {
            return ;
        }
        if (services == null) {
            //throw 异常提醒
           return;
        }
        services.remove(service.getServiceName());
    }

    public void addServiceAddress(String serviceName, String address) {
        if (services == null) {
            services = new HashMap<String, Service>();
        }
        Service service = services.get(serviceName);
        if (service == null) {
            service = new Service();
            service.setServiceName(serviceName);
            service.setServerAddresses(new ArrayList<String>());
            services.put(serviceName, service);
        }
        if (!service.getServerAddresses().contains(address)) {
            service.getServerAddresses().add(address);
        }


    }

    public Map<String, Service> getServices() {
        return services;
    }

}
