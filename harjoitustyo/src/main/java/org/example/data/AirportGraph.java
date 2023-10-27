package org.example.data;

import java.util.ArrayList;

/**
 * Airport graph data holder
 */
public class AirportGraph {
    private final ArrayList<AirportDistance>[] graph;

    /**
     * @param graph The airport graph
     */
    public AirportGraph(ArrayList<AirportDistance>[] graph) {
        this.graph = graph;
    }

    /**
     * @return graph
     */
    public ArrayList<AirportDistance>[] getGraph() {
        return graph;
    }
}
