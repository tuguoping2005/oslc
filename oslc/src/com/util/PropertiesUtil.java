package com.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by tuguoping
 */

public class PropertiesUtil {
	private static Logger log = LoggerFactory.getLogger(PropertiesUtil.class); 
    private static Properties props;

    static {
        String fileName = "user.properties";
        props = new Properties();
        try {
        	props.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName),"UTF-8"));
        } catch (IOException e) {
            log.error("Configuration file read exception",e);
        }
    }

    public static String getProperty(String key){
        String value = props.getProperty(key.trim());
        if(StringUtils.isBlank(value)){
            return null;
        }
        return value.trim();
    }

    public static String getProperty(String key,String defaultValue){
        String value = props.getProperty(key.trim());
        if(StringUtils.isBlank(value)){
            value = defaultValue;
        }
        return value.trim();
    }
}
