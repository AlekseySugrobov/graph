package com.natera.test.graph.helper;

import com.natera.test.graph.exception.IllegalGraphTypeException;
import com.natera.test.graph.model.Edge;
import com.natera.test.graph.model.Type;
import com.natera.test.graph.model.Vertex;
import org.apache.commons.lang3.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

public class PathSearchHelper {
    private final Vertex startVertex;
    private final Vertex endVertex;
    private final List<Edge> edgeList;
    private final Type graphType;
    private Set<Vertex> visited = new HashSet<>();
    private List<Vertex> path = new ArrayList<>();
    private List<Edge> edgesPath = new ArrayList<>();

    /**
     * Initialize path search helper.
     *
     * @param startVertex start vertex
     * @param endVertex   end vertex
     * @param edgeList    edges
     * @param graphType   graph type
     */
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
        this.findVertexPath();
        this.convertToEdgesPath();
        return this.edgesPath;
    }

    /**
     * Map list of vertices to list of edges.
     */
    private void convertToEdgesPath() {
        if (ObjectUtils.isEmpty(this.path)) {
            return;
        }
        for (int index = 0; index < this.path.size() - 1; index++) {
            Vertex currentVertex = this.path.get(index);
            Vertex nextVertex = this.path.get(index + 1);
            this.edgesPath.add(new Edge(currentVertex, nextVertex));
        }
    }


    /**
     * Search vertex path.
     */
    private void findVertexPath() {
        this.path.add(this.startVertex);
        boolean isPathExists = this.findVertexPathRecursive(this.startVertex, this.endVertex);
        if (!isPathExists) {
            this.path = Collections.emptyList();
        }
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
    private List<Vertex> getAdjacentVertices(Vertex vertex, List<Edge> edges) {
        switch (this.graphType) {
            case DIRECTED:
                return edges.stream()
                        .filter(edge -> edge.isStartVertex(vertex))
                        .map(Edge::getSecondVertex)
                        .collect(Collectors.toList());
            case UNDIRECTED:
                return edges.stream()
                        .filter(edge -> edge.containsVertex(vertex))
                        .map(edge -> edge.getAdjacentVertex(vertex))
                        .collect(Collectors.toList());
            default:
                throw new IllegalGraphTypeException("Unknown graph type");
        }
    }
}
