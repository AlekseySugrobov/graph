package com.natera.test.graph.utils;

import com.natera.test.graph.model.Edge;
import com.natera.test.graph.model.Vertex;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EdgeUtils {

    public static List<Edge> convertToEdgesPath(List<Vertex> verticesPath) {
        if (verticesPath == null || verticesPath.isEmpty()) {
            return Collections.emptyList();
        }

        int len = verticesPath.size() - 1;
        Stream<Edge> edgeStream = StreamUtils.zip(
                verticesPath.stream().limit(len),
                verticesPath.stream().skip(1),
                Edge::new);

        return edgeStream.collect(Collectors.toList());
    }

    private EdgeUtils() {

    }
}
