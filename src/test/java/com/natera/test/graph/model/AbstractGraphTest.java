package com.natera.test.graph.model;

import com.natera.test.graph.exception.VertexNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

abstract class AbstractGraphTest {
    private Graph graph;

    @BeforeEach
    void createTestGraph() {
        this.graph = this.createGraphInstance();
        this.graph.addVertex(new Vertex<>(1));
        this.graph.addVertex(new Vertex<>(2));
        this.graph.addVertex(new Vertex<>(3));
        this.graph.addVertex(new Vertex<>(4));
        this.graph.addVertex(new Vertex<>(5));
        this.graph.addVertex(new Vertex<>(6));
        this.graph.addVertex(new Vertex<>(7));
        this.graph.addVertex(new Vertex<>(8));
        this.graph.addEdge(new Vertex<>(1), new Vertex<>(2));
        this.graph.addEdge(new Vertex<>(1), new Vertex<>(3));
        this.graph.addEdge(new Vertex<>(2), new Vertex<>(6));
        this.graph.addEdge(new Vertex<>(2), new Vertex<>(3));
        this.graph.addEdge(new Vertex<>(3), new Vertex<>(4));
        this.graph.addEdge(new Vertex<>(6), new Vertex<>(7));
        this.graph.addEdge(new Vertex<>(7), new Vertex<>(5));
    }

    abstract Graph createGraphInstance();

    @Test
    @DisplayName("Adding vertex test")
    void addVertex() {
        int expectedVerticesCount = this.graph.getVerticesCount() + 1;

        this.graph.addVertex(new Vertex<>(Integer.MAX_VALUE));
        int actualVerticesCount = this.graph.getVerticesCount();

        assertThat(actualVerticesCount).isEqualTo(expectedVerticesCount);
    }

    @Test
    @DisplayName("Adding vertex. Vertex already exists")
    void addVertexVertexExists() {
        int expectedVerticesCount = this.graph.getVerticesCount();

        this.graph.addVertex(new Vertex<>(1));
        this.graph.addVertex(new Vertex<>(1));
        int actualVerticesCount = this.graph.getVerticesCount();

        assertThat(actualVerticesCount).isEqualTo(expectedVerticesCount);
    }

    @Test
    @DisplayName("Adding edge")
    void addEdge() {
        int expectedEdgeCount = this.graph.getEdgesCount() + 1;
        int expectedVerticesCount = this.graph.getVerticesCount();

        this.graph.addEdge(new Vertex<>(5), new Vertex<>(7));
        int actualEdgesCount = this.graph.getEdgesCount();
        int actualVerticesCount = this.graph.getVerticesCount();

        assertThat(actualEdgesCount).isEqualTo(expectedEdgeCount);
        assertThat(actualVerticesCount).isEqualTo(expectedVerticesCount);
    }

    @Test
    @DisplayName("Adding edge. First vertex doesn't exists")
    void addEdgeFirstVertexNotExists() {
        assertThatThrownBy(() -> this.graph.addEdge(new Vertex<>(10), new Vertex<>(7)))
                .isInstanceOf(VertexNotFoundException.class);
    }

    void testExistingPath(Vertex startVertex, Vertex endVertex) {
        assertNotEquals(startVertex, endVertex);

        List<Edge> path = this.graph.getPath(startVertex, endVertex);

        assertEquals(startVertex, path.get(0).getFirstVertex());
        assertEquals(endVertex, path.get(path.size() - 1).getSecondVertex());
    }

    void testNonExitingPath(Vertex startVertex, Vertex endVertex) {
        List<Edge> path = this.graph.getPath(startVertex, endVertex);

        assertTrue(path.isEmpty());
    }
}
