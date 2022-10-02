package com.sample.utilities.config;

import java.util.HashMap;
import java.util.Map;

public enum Environment {
    LOCAL("local"),
    DOCKER("docker.container");

    private final String env;

    Environment(String env) {
        this.env = env;
    }

    private static Map<String, Environment> envMap = new HashMap<>();

    static {
        for (Environment type : values()) {
            envMap.put(type.env, type);
        }
    }

    public static Environment getEnvironment(String name) {
        return envMap.get(name);
    }

}
