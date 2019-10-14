import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(JUnit4.class)
public class LCATest {
    @Test
    public void testNode() {
        Node root = new Node(1);
        assertEquals(1, root.getData());
        assertEquals(0, root.getNodes().size());
        root.setNode(new Node(2));
        assertEquals(2, root.getNode(0).getData());
        assertEquals(0, root.getNode(0).getNodes().size());
        root.setNode(new Node(3));
        assertEquals(3, root.getNode(1).getData());
        assertEquals(0, root.getNode(1).getNodes().size());
        root.getNode(0).setNode(new Node(4));
        assertEquals(4, root.getNode(0).getNode(0).getData());
        assertEquals(0, root.getNode(0).getNode(0).getNodes().size());
        root.getNode(0).setNode(new Node(5));
        assertEquals(5, root.getNode(0).getNode(1).getData());
        assertEquals(0, root.getNode(0).getNode(1).getNodes().size());
        root.getNode(1).setNode(new Node(6));
        assertEquals(6, root.getNode(1).getNode(0).getData());
        assertEquals(0, root.getNode(1).getNode(0).getNodes().size());
        root.getNode(1).setNode(new Node(7));
        assertEquals(7, root.getNode(1).getNode(1).getData());
        assertEquals(0, root.getNode(1).getNode(1).getNodes().size());


        assertEquals(1, root.getData());
        assertEquals(2, root.getNode(0).getData());
        assertEquals(3, root.getNode(1).getData());
        assertEquals(4, root.getNode(0).getNode(0).getData());
        assertEquals(5, root.getNode(0).getNode(1).getData());
        assertEquals(6, root.getNode(1).getNode(0).getData());
        assertEquals(7, root.getNode(1).getNode(1).getData());
    }

    @Test
    public void testLCA() {
        LCA tree = new LCA();
        assertEquals(-1, tree.findLCA(0, 0));

        tree.root = new Node(1);
        tree.root.setNode(new Node(2));
        tree.root.setNode(new Node(3));
        tree.root.getNode(0).setNode(new Node(4));
        tree.root.getNode(0).setNode(new Node(5));
        tree.root.getNode(1).setNode(new Node(6));
        tree.root.getNode(1).setNode(new Node(7));


        assertEquals(-1, tree.findLCA(10, 11));
        assertEquals(-1, tree.findLCA(2, 11));
        assertEquals(-1, tree.findLCA(10, 2));
        assertEquals(2, tree.findLCA(4, 5));
        assertEquals(1, tree.findLCA(4, 6));
        assertEquals(1, tree.findLCA(3, 4));
        assertEquals(2, tree.findLCA(2, 4));
    }
}