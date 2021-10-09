package com.temzu.market_project.mscore.exceptions;

public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException(String msg) {
        super("Invalid token:" + msg);
    }
}
