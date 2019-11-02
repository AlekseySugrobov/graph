package com.natera.test.graph.exception;

public class VertexNullContentException extends RuntimeException {

    private static final long serialVersionUID = -4578795397354529091L;
    private static final String UNABLE_CREATE_VERTEX_FROM_NULL =
            "Unable create vertex from null";

    public VertexNullContentException() {
        super(UNABLE_CREATE_VERTEX_FROM_NULL);
    }
}
