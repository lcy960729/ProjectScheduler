package com.lcy.ps.core.enums;

public enum SecurityHeaders {
    AUTHORIZATION("Authorization");

    private final String name;

    SecurityHeaders(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
