package com.devsu.clientes.enums;

public enum Estado {

    ACTIVO(true), INACTIVO(false);

    private final boolean value;


    Estado(boolean value) {
        this.value = value;
    }

    public boolean getValue()
    {
        return value;
    }
}
