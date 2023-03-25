package com.shoppingcartapp.domain.exceptions;

public class ItemDoesNotExistException extends RuntimeException {

    public ItemDoesNotExistException() {
    }

    public ItemDoesNotExistException(String message) {
        super(message);
    }
}
