package com.muchencute.commons.config;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;

public class Config {

    private static Config instance;

    private Configuration config;

    private Config(String configFilename) {

        try {
            Configurations configs = new Configurations();
            config = configs.properties(Config.class.getResource(configFilename));
        } catch (Exception e) {
            System.err.println("Cannot read config file");
            e.printStackTrace();
        }
    }

    public static <T> T get(Class<T> type, String key) {

        return instance.config.get(type, key);
    }

    public static void initConfig(String configFilename) {
        instance = new Config(configFilename);
    }
}
