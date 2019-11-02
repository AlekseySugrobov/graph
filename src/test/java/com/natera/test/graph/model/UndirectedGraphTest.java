package com.natera.test.graph.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Undirected graph tests")
class UndirectedGraphTest extends AbstractGraphTest {
    @Override
    Graph createGraphInstance() {
        return new Graph(Type.UNDIRECTED);
    }

    @ParameterizedTest
    @CsvSource({"1, 4", "1, 3"})
    @DisplayName("Test get path method. Path exists")
    void getPathPathExisting(Integer startVertexValue, Integer endVertexValue) {
        super.testExistingPath(new Vertex<>(startVertexValue), new Vertex<>(endVertexValue));
    }

    @ParameterizedTest
    @CsvSource({"1, 8", "8, 3"})
    @DisplayName("Test get path method. Path not exists")
    void getPathPathNotExisting(Integer startVertexValue, Integer endVertexValue) {
        super.testNonExitingPath(new Vertex<>(startVertexValue), new Vertex<>(endVertexValue));
    }

}