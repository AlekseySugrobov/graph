package com.natera.test.graph.exception;

import com.natera.test.graph.model.Vertex;

public class VertexNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -2830343280424147703L;
    private static final String VERTEX_NOT_FOUND =
            "Vertex %s not found";

    public VertexNotFoundException(Vertex notFoundVertex) {
        super(String.format(VERTEX_NOT_FOUND, notFoundVertex));
    }
}
