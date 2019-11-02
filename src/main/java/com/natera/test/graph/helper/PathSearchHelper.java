package com.natera.test.graph.helper;

import com.natera.test.graph.exception.IllegalGraphTypeException;
import com.natera.test.graph.model.Edge;
import com.natera.test.graph.model.Type;
import com.natera.test.graph.model.Vertex;
import com.natera.test.graph.utils.EdgeUtils;

import java.util.*;
import java.util.stream.Collectors;

public class PathSearchHelper {
    private final Vertex startVertex;
    private final Vertex endVertex;
    private final List<Edge> edgeList;
    private final Type graphType;
    private Set<Vertex> visited = new HashSet<>();
    private List<Vertex> path = new ArrayList<>();

    public PathSearchHelper(Vertex startVertex, Vertex endVertex, List<Edge> edgeList, Type graphType) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.edgeList = edgeList;
        this.graphType = graphType;
    }

    /**
     * Searches edge path.
     *
     * @return edge path.
     */
    public List<Edge> findEdgePath() {
        List<Vertex> vertexPath = this.findVertexPath();
        return EdgeUtils.convertToEdgesPath(vertexPath);
    }

    /**
     * Search vertex path.
     *
     * @return vertex path
     */
    private List<Vertex> findVertexPath() {
        this.path.add(this.startVertex);
        boolean isPathExists = this.findVertexPathRecursive(this.startVertex, this.endVertex);
        if (isPathExists) {
            return this.path;
        }
        return Collections.emptyList();
    }

    /**
     * Searches vertex path recursively.
     *
     * @param current current vertex
     * @param end     end vertex
     * @return true if path exists
     */
    private boolean findVertexPathRecursive(Vertex current, Vertex end) {
        this.visited.add(current);
        if (current.equals(end)) {
            return true;
        }
        for (Vertex adjacentVertex : this.getAdjacentVertices(current, this.edgeList)) {
            if (!this.visited.contains(adjacentVertex)) {
                this.path.add(adjacentVertex);
                boolean result = this.findVertexPathRecursive(adjacentVertex, end);
                if (result) {
                    return true;
                }
                this.path.remove(adjacentVertex);
            }
        }
        return false;
    }

    /**
     * Returns adjacent vertices for specified vertex.
     *
     * @param vertex vertex
     * @return list with adjacent vertices
     */
    protected List<Vertex> getAdjacentVertices(Vertex vertex, List<Edge> edges) {
        switch (this.graphType) {
            case DIRECTED:
                return edges.stream()
                        .filter(e -> e.getFirstVertex().equals(vertex))
                        .map(Edge::getSecondVertex)
                        .collect(Collectors.toList());
            case UNDIRECTED:
                return edges.stream()
                        .filter(edge -> vertex.equals(edge.getFirstVertex()) || vertex.equals(edge.getSecondVertex()))
                        .map(edge -> vertex.equals(edge.getFirstVertex()) ? edge.getSecondVertex() : edge.getFirstVertex())
                        .collect(Collectors.toList());
            default:
                throw new IllegalGraphTypeException("Unknown graph type");
        }
    }
}
