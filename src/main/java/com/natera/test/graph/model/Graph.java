package com.natera.test.graph.model;

import com.natera.test.graph.exception.IllegalGraphTypeException;
import com.natera.test.graph.exception.UnableGetPathException;
import com.natera.test.graph.exception.VertexNotFoundException;
import com.natera.test.graph.helper.PathSearchHelper;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Graph {
    private List<Vertex> vertices;
    private List<Edge> edges;
    private Type type;

    public Graph(Type type) {
        if (Objects.isNull(type)) {
            throw new IllegalGraphTypeException("Graph type can't be null");
        }
        this.vertices = new LinkedList<>();
        this.edges = new LinkedList<>();
        this.type = type;
    }

    /**
     * Adds vertex.
     *
     * @param vertex see {@link Vertex}
     */
    public void addVertex(Vertex vertex) {
        if (this.vertices.contains(vertex)) {
            return;
        }
        this.vertices.add(vertex);
    }

    /**
     * Adds edge between vertices.
     *
     * @param firstVertex  firs vertex
     * @param secondVertex second vertex
     */
    public void addEdge(Vertex firstVertex, Vertex secondVertex) {
        this.checkVertexExisting(firstVertex);
        this.checkVertexExisting(secondVertex);
        this.edges.add(new Edge(firstVertex, secondVertex));
    }

    /**
     * Ges path from start vertex to end vertex.
     *
     * @param startVertex start vertex
     * @param endVertex   end vertex
     * @return edge list
     */
    public List<Edge> getPath(Vertex startVertex, Vertex endVertex) {
        if (this.vertices.size() <= 1) {
            throw new UnableGetPathException("Graph have 0 or 1 vertices");
        }
        if (this.edges.isEmpty()) {
            throw new UnableGetPathException("Graph have no edges");
        }
        if (Objects.isNull(startVertex) || Objects.isNull(endVertex)) {
            throw new UnableGetPathException("Passed vertices must not be null");
        }
        this.checkVertexExisting(startVertex);
        this.checkVertexExisting(endVertex);
        PathSearchHelper pathSearchHelper = new PathSearchHelper(startVertex, endVertex, this.edges, this.type);
        return pathSearchHelper.findEdgePath();
    }

    public int getVerticesCount() {
        if (Objects.isNull(this.vertices) || this.vertices.isEmpty()) {
            return 0;
        }
        return this.vertices.size();
    }

    public int getEdgesCount() {
        if (Objects.isNull(this.edges) || this.edges.isEmpty()) {
            return 0;
        }
        return this.edges.size();
    }


    /**
     * Checks if vertex existing.
     *
     * @param vertex {@link Vertex}
     */
    private void checkVertexExisting(Vertex vertex) {
        if (!this.vertices.contains(vertex)) {
            throw new VertexNotFoundException(vertex);
        }
    }

}
