package com.natera.test.graph.model;

import com.natera.test.graph.exception.VertexNotFoundException;
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

    /**
     * Checks if edge contains vertex.
     *
     * @param vertex {@link Vertex}
     * @return true if contains
     */
    public boolean containsVertex(Vertex vertex) {
        return Objects.equals(this.firstVertex, vertex) || Objects.equals(this.secondVertex, vertex);
    }

    /**
     * Checks if vertex is start vertex.
     *
     * @param vertex {@link Vertex}
     * @return true if yes
     */
    public boolean isStartVertex(Vertex vertex) {
        return Objects.equals(this.firstVertex, vertex);
    }

    /**
     * Gets adjacent vertex for specified vertex.
     *
     * @param vertex {@link Vertex}
     * @return adjacent vertex
     */
    public Vertex getAdjacentVertex(Vertex vertex) {
        if (!this.containsVertex(vertex)) {
            throw new VertexNotFoundException(vertex);
        }
        return this.isStartVertex(vertex) ? this.secondVertex : this.firstVertex;
    }
}
