package com.sample.utilities.config;

import java.util.HashMap;
import java.util.Map;

public enum Browser {
    CHROME("chrome"),
    FIREFOX("firefox");

    private final String name;

    Browser(String name) {
        this.name = name;
    }

    private static Map<String, Browser> browserMap = new HashMap<>();

    static {
        for (Browser type : values()) {
            browserMap.put(type.name, type);
        }
    }

    public static Browser getBrowser(String label) {
        return browserMap.get(label);
    }

}
