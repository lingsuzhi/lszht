package com.lszpro.application;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Created by relic5 on 2017/5/26.
 */
@Component
public class SpringCtxHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    private static Environment env;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringCtxHolder.applicationContext = applicationContext;
        env = applicationContext.getEnvironment();
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Environment getEnv() {
        return env;
    }

    public static void setEnv(Environment env) {
        SpringCtxHolder.env = env;
    }

    public static String getProperty(String key){
        if(StringUtils.isEmpty(key)){
            return "";
        }
        return env.getProperty(key);
    }

    /*通过class获取Bean*/
    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }
}
