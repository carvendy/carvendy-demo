package com.carvendy.base.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class HelloClient { 
    public static void main(String args[]){ 
        try { 
            //在RMI服务注册表中查找名称为RHello的对象，并调用其上的方法 
            IHello rhello =(IHello) Naming.lookup("rmi://192.168.20.164:8888/RHello"); 
            System.out.println(rhello.helloWorld()); 
            System.out.println(rhello.sayHelloToSomeBody("熔岩")); 
        } catch (NotBoundException e) { 
            e.printStackTrace(); 
        } catch (MalformedURLException e) { 
            e.printStackTrace(); 
        } catch (RemoteException e) { 
            e.printStackTrace();   
        } 
    } 
}
