package com.natera.test.graph.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class Edge {
    private Vertex firstVertex;
    private Vertex secondVertex;

    public Edge(Object firstVertexValue, Object secondVertexValue) {
        this.firstVertex = new Vertex<>(firstVertexValue);
        this.secondVertex = new Vertex<>(secondVertexValue);
    }
}
