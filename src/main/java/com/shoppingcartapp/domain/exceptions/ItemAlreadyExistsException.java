package com.shoppingcartapp.domain.exceptions;

public class ItemAlreadyExistsException extends RuntimeException {
    public ItemAlreadyExistsException() {
    }

    public ItemAlreadyExistsException(String message) {
        super(message);
    }
}
