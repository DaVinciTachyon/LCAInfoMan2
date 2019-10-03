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
        assertNull(root.getLeft());
        assertNull(root.getRight());
        root.setLeft(new Node(2));
        assertEquals(2, root.getLeft().getData());
        assertNull(root.getLeft().getLeft());
        assertNull(root.getLeft().getRight());
        root.setRight(new Node(3));
        assertEquals(3, root.getRight().getData());
        assertNull(root.getRight().getLeft());
        assertNull(root.getRight().getRight());
        root.getLeft().setLeft(new Node(4));
        assertEquals(4, root.getLeft().getLeft().getData());
        assertNull(root.getLeft().getLeft().getLeft());
        assertNull(root.getLeft().getLeft().getRight());
        root.getLeft().setRight(new Node(5));
        assertEquals(5, root.getLeft().getRight().getData());
        assertNull(root.getLeft().getRight().getLeft());
        assertNull(root.getLeft().getRight().getRight());
        root.getRight().setLeft(new Node(6));
        assertEquals(6, root.getRight().getLeft().getData());
        assertNull(root.getRight().getLeft().getLeft());
        assertNull(root.getRight().getLeft().getRight());
        root.getRight().setRight(new Node(7));
        assertEquals(7, root.getRight().getRight().getData());
        assertNull(root.getRight().getRight().getLeft());
        assertNull(root.getRight().getRight().getRight());
    }
}