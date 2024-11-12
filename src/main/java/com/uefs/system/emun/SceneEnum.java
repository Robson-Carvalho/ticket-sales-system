package com.uefs.system.emun;

public enum SceneEnum {
    DASHBOARD("dashboard"),
    SIGNUP("signup"),
    SIGNIN("signin");

    private final String value;

    SceneEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}