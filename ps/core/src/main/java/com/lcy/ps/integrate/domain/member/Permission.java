package com.lcy.ps.integrate.domain.member;


public enum Permission {
    READ, CREATE, UPDATE, DELETE, INVITE, GRANT;

    @Override
    public String toString() {
        return name();
    }
}
