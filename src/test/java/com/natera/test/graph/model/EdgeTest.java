package com.natera.test.graph.model;

import com.natera.test.graph.exception.VertexNullException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Edge tests")
class EdgeTest {

    private static final Vertex<Integer> FIRST_VERTEX = new Vertex<>(1);
    private static final Vertex<String> SECOND_VERTEX = new Vertex<>("1");

    @Test
    @DisplayName("Edge creation test")
    void edgeCreation() {
        Edge edge = new Edge(FIRST_VERTEX, SECOND_VERTEX);

        assertThat(edge.getFirstVertex()).isEqualTo(FIRST_VERTEX);
        assertThat(edge.getSecondVertex()).isEqualTo(SECOND_VERTEX);
    }

    @Test
    @DisplayName("Edge creation test with first vertex null value should throw VertexNullException")
    void edgeCreationWithFirstVertexNullValueShouldThrowVertexNullException() {
        assertThatThrownBy(() -> new Edge(null, SECOND_VERTEX))
                .isInstanceOf(VertexNullException.class);
    }

    @Test
    @DisplayName("Edge creation test with second vertex null value should throw VertexNullException")
    void edgeCreationWithSecondVertexNullValueShouldThrowVertexNullException() {
        assertThatThrownBy(() -> new Edge(FIRST_VERTEX, null))
                .isInstanceOf(VertexNullException.class);
    }

    @Test
    @DisplayName("Edge creation test with first and second vertex null value. Should throw VertexNullException")
    void edgeCreationWithFirstAndSecondVertexNullValueShouldThrowVertexNullException() {
        assertThatThrownBy(() -> new Edge(null, null))
                .isInstanceOf(VertexNullException.class);
    }

    @Test
    @DisplayName("Edge creation test with first and second vertex null value. Should throw VertexNullException")
    void edgeCreationWithFirstAndSecondVertexNullValueShouldThrowVertexNullExceptionWithExpectedMessage() {
        String expectedMessage = "Vertex can't be null";

        assertThatThrownBy(() -> new Edge(null, null))
                .isInstanceOf(VertexNullException.class)
                .hasMessage(expectedMessage);
    }
}