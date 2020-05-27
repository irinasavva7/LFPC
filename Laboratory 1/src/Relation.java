import java.util.*;

class Relation {
    private int to;
    private String value;

    Relation(int to, String value) {
        this.to = to;
        this.value = value;
    }

    public int getTo() {
        return to;
    }

    public String getValue() {
        return value;
    }
}

