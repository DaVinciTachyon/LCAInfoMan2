import java.util.ArrayList;

public class Node {
    private int data;
    private ArrayList<Node> nodes;

    Node(int value) {
        setData(value);
        setNodes(new ArrayList<>());
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    public void setNode(Node node) {
        nodes.add(node);
    }

    public Node getNode(int index) {
        return nodes.get(index);
    }
}