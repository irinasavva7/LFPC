import java.util.*;

class Node {
    private int index;
    private List<Relation> relations;
    private boolean fin;

    Node(int index, boolean fin) {
        this.fin = fin;
        this.index = index;
    }

    public List<Relation> getRelations() {
        return relations;
    }

    public void setRelations(List<Relation> relations) {
        this.relations = relations;
    }

    public boolean isFin() {
        return fin;
    }
}


