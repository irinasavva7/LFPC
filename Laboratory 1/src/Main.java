import java.util.*;

public class Main {

    public static boolean checkPath(Graph graph) {
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();
        List<String> letters = Arrays.asList(word.split(""));
        int letterIndex = 0, relation = 0, node = 0;
        NODE:while (node != graph.getNodes().size()) {
            List<String> values = new ArrayList<>();
            for (Relation tempRel:graph.getNodes().get(node).getRelations()) {
                values.add(tempRel.getValue());
            }
            if (values.contains(letters.get(letterIndex))) {
                while (relation < graph.getNodes().get(node).getRelations().size()) {
                    Relation current = graph.getNodes().get(node).getRelations().get(relation);
                    if (current.getValue().equals(letters.get(letterIndex))) {
                        node = current.getTo();
                        letterIndex++;
                        relation = 0;
                        if (letterIndex>=letters.size()) {
                            break NODE;
                        }
                    } else {
                        relation++;
                    }
                }
            } else {
                letterIndex=-1;
                break NODE;
            }
        }
        return letterIndex>=letters.size()-1 && graph.getNodes().get(node).isFin();
    }

    public static void main(String[] args) {
        Graph graph = new Graph();

        Node q1 = new Node(0, false);
        Relation q1q1a = new Relation(0, "a");
        Relation q1q1b = new Relation(0, "b");
        Relation q1q2c = new Relation(1, "c");
        q1.setRelations(Arrays.asList(q1q1a, q1q1b, q1q2c));

        Node q2 = new Node(1, true);
        Relation q2q2a = new Relation(1, "a");
        Relation q2q2b = new Relation(1, "b");
        Relation q2q3c = new Relation(2, "c");
        q2.setRelations(Arrays.asList(q2q2a, q2q2b, q2q3c));

        Node q3 = new Node(2, true);
        Relation q3q3b = new Relation(2, "b");
        q3.setRelations(Collections.singletonList(q3q3b));

        graph.setNodes(Arrays.asList(q1, q2, q3));

        System.out.println(checkPath(graph));
    }
}