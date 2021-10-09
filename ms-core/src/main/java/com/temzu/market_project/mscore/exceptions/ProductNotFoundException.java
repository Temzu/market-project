package com.temzu.market_project.mscore.exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String msg) {
        super("Product not found:" + msg);
    }
}
