package com.natera.test.graph.model;

import com.natera.test.graph.exception.VertexNullContentException;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class VertexTest {

    @Test
    @DisplayName("Creation vertex test")
    void creationVertexTest() {
        Vertex<String> vertex = new Vertex<>(StringUtils.EMPTY);
        assertThat(vertex).isNotNull();
    }

    @Test
    @DisplayName("Creation vertex with two digit value test")
    void creationVertexWithTwoDigitValue() {
        Vertex<Integer> vertex = new Vertex<>(2);
        assertThat(vertex.getContent()).isEqualTo(2);
    }


    @MethodSource("creationVertexSource")
    @DisplayName("Creation vertex with specified value test")
    @ParameterizedTest(name = "{index} creation vertex from {0} value")
    void creationVertexWithSpecifiedValue(Object specifiedValue) {
        Class<?> expectedContentClass = specifiedValue.getClass();

        Vertex<Object> vertex = new Vertex<>(specifiedValue);

        assertThat(vertex.getContent()).isEqualTo(specifiedValue);
        assertThat(vertex.getContent()).isInstanceOf(expectedContentClass);
    }

    @Test
    @DisplayName("Creation vertex from null test")
    void creationVertexFromNullShouldThrowVertexNullContentException() {
        assertThatThrownBy(() -> new Vertex<>(null))
                .isInstanceOf(VertexNullContentException.class);
    }

    @Test
    @DisplayName("Creation vertex from null value has expected message")
    void creationVertexFromNullShouldThrowVertexNullContentExceptionWithExpectedText() {
        String expectedExceptionMessage = "Unable create vertex from null";

        assertThatThrownBy(() -> new Vertex<>(null))
                .isInstanceOf(VertexNullContentException.class)
                .hasMessage(expectedExceptionMessage);
    }

    static Stream<Arguments> creationVertexSource() {
        return Stream.of(
                Arguments.of(RandomStringUtils.random(3)),
                Arguments.of(1),
                Arguments.of(2L)
        );
    }
}