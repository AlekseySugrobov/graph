package com.natera.test.graph.exception;

public class VertexNullContentException extends RuntimeException {

    private static final String UNABLE_CREATE_VERTEX_FROM_NULL =
            "Unable create vertex from null";

    public VertexNullContentException() {
        super(UNABLE_CREATE_VERTEX_FROM_NULL);
    }
}
