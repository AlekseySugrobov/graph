package com.natera.test.graph.exception;

public class UnableGetPathException extends RuntimeException {
    private static final long serialVersionUID = 7410605569598047114L;
    private static final String PREFIX =
            "Unable get path: ";

    public UnableGetPathException(String message) {
        super(PREFIX + message);
    }
}
