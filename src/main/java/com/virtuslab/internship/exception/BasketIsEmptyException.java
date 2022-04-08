package com.virtuslab.internship.exception;

import lombok.Getter;

@Getter
public class BasketIsEmptyException extends RuntimeException {
    private final String message;

    public BasketIsEmptyException() {
        this.message = "Basket is empty";
    }
}
