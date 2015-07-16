package sql.usc.XMLAdjacencyGraph;

import sql.usc.Color.ARGB;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by mianwan on 7/14/15.
 */
public class XMLAdjacencyNode {
    private int id;
    private static int nextId = 0;
    private String component;
    private Set<XMLAdjacencyNode> successors = new HashSet<XMLAdjacencyNode>();
    private Set<XMLAdjacencyNode> predecessors = new HashSet<XMLAdjacencyNode>();

    private Set<ARGB> backgroundColors = new HashSet<ARGB>();
    private Set<ARGB> textColors = new HashSet<ARGB>();

    public XMLAdjacencyNode(String component) {
        this.component = component;
        this.id = nextId;
        nextId++;
    }

    public Set<XMLAdjacencyNode> getSuccessors() {
        return successors;
    }

    public Set<XMLAdjacencyNode> getPredecessors() {
        return predecessors;
    }

    public Set<ARGB> getBackgroundColors() {
        return backgroundColors;
    }

    public Set<ARGB> getTextColors() {
        return textColors;
    }

    public void setSuccessors(Set<XMLAdjacencyNode> successors) {
        this.successors = successors;
    }

    public void setPredecessors(Set<XMLAdjacencyNode> predecessors) {
        this.predecessors = predecessors;
    }

    public void setBackgroundColors(Set<ARGB> backgroundColors) {
        this.backgroundColors = backgroundColors;
    }

    public void setTextColors(Set<ARGB> textColors) {
        this.textColors = textColors;
    }

    public int getId() {
        return id;
    }

    public String getComponent() {
        return component;
    }

    public String toDot() {
        return id + " [label=\"" + component + "\"];";
    }

    @Override
    public String toString() {
        return component;
    }
}
