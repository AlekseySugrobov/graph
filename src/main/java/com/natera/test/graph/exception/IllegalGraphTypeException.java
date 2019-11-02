package com.natera.test.graph.exception;

public class IllegalGraphTypeException extends RuntimeException {
    private static final long serialVersionUID = -3218540326274952467L;

    public IllegalGraphTypeException(String message) {
        super(message);
    }
}
