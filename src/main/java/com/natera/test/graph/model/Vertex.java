package com.natera.test.graph.model;

import com.natera.test.graph.exception.VertexNullContentException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString
@EqualsAndHashCode
public class Vertex<T> {
    private final T content;

    public Vertex(T content) {
        if (Objects.isNull(content)) {
            throw new VertexNullContentException();
        }
        this.content = content;
    }
}
