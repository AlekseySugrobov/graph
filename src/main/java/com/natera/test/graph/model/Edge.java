package com.natera.test.graph.model;

import com.natera.test.graph.exception.VertexNullException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

/**
 * Describes edge without direction.
 */
@Getter
@ToString
@EqualsAndHashCode
public class Edge {
    private Vertex firstVertex;
    private Vertex secondVertex;

    /**
     * Creates edge from values.
     *
     * @param firstVertex  first vertex value
     * @param secondVertex second vertex value
     */
    public Edge(Vertex firstVertex, Vertex secondVertex) {
        if (Objects.isNull(firstVertex) || Objects.isNull(secondVertex)) {
            throw new VertexNullException();
        }
        this.firstVertex = firstVertex;
        this.secondVertex = secondVertex;
    }
}
