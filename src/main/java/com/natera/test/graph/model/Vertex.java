package com.natera.test.graph.model;

import com.natera.test.graph.exception.VertexNullContentException;
import lombok.Data;

import java.util.Objects;

@Data
public class Vertex<T> {
    private final T content;

    public Vertex(T content) {
        if (Objects.isNull(content)) {
            throw new VertexNullContentException();
        }
        this.content = content;
    }
}
