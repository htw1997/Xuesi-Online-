package com.xuesi.utils;

import java.util.Properties;

public class InfoUtils {
    static Properties properties = null;
    static{
        try{
            properties = new Properties();
            properties.load(InfoUtils.class.getClassLoader().getResourceAsStream("Info.properties"));
            //System.out.println(properties.getProperty("IMG_PATH"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static String getProperties(String name){
        return properties.getProperty(name);
    }


    public static void main(String[] args) {

        System.out.println(getProperties("IMG_PATH"));
    }
}
