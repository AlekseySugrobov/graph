package com.natera.test.graph.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EdgeTest {

    @Test
    @DisplayName("Edge creation test")
    void edgeCreation() {
        Edge edge = new Edge(1, "1");

        assertThat(edge.getFirstVertex()).isEqualTo(new Vertex<>(1));
        assertThat(edge.getSecondVertex()).isEqualTo(new Vertex<>("1"));
    }
}