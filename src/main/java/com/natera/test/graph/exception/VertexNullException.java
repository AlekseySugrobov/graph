package com.natera.test.graph.exception;

public class VertexNullException extends RuntimeException {
    private static final long serialVersionUID = 1343440476548359790L;
    private static final String VERTEX_CANT_BE_NULL =
            "Vertex can't be null";

    public VertexNullException() {
        super(VERTEX_CANT_BE_NULL);
    }
}
