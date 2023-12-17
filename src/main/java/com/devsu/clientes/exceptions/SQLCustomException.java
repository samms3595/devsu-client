package com.devsu.clientes.exceptions;

public class SQLCustomException extends RuntimeException{

    public SQLCustomException(String message, Throwable code) {
        super(message, code);
    }
}
