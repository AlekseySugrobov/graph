package com.natera.test.graph.model;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Directed graph tests")
class DirectedGraphTest extends AbstractGraphTest {
    @Override
    Graph createGraphInstance() {
        return new Graph(Type.DIRECTED);
    }

    @ParameterizedTest
    @CsvSource({"1, 4", "1, 5"})
    @DisplayName("Test get path method. Path exists")
    void getPathPathExisting(Integer startVertexValue, Integer endVertexValue) {
        super.testExistingPath(new Vertex<>(startVertexValue), new Vertex<>(endVertexValue));
    }

    @ParameterizedTest
    @CsvSource({"4, 1", "5, 3"})
    @DisplayName("Test get path method. Path not exists")
    void getPathPathNotExisting(Integer startVertexValue, Integer endVertexValue) {
        super.testNonExitingPath(new Vertex<>(startVertexValue), new Vertex<>(endVertexValue));
    }
}