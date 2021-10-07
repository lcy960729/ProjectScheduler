package com.lcy.projectscheduler.security;

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
