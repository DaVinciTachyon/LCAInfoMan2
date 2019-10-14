import java.util.ArrayList;
import java.util.List;

public class LCA {

    Node root;
    private List<Integer> path1;
    private List<Integer> path2;

    LCA() {
        path1 = new ArrayList<>();
        path2 = new ArrayList<>();
        root = null;
    }

    int findLCA(int n1, int n2) {
        path1.clear();
        path2.clear();
        return findLCAInternal(root, n1, n2);
    }

    private int findLCAInternal(Node root, int n1, int n2) {

        if (!findPath(root, n1, path1) || !findPath(root, n2, path2)) {
            System.out.println((path1.size() > 0) ? "n1 is present" : "n1 is missing");
            System.out.println((path2.size() > 0) ? "n2 is present" : "n2 is missing");
            return -1;
        }

        int i;
        for (i = 0; i < path1.size() && i < path2.size(); i++)
            if (!path1.get(i).equals(path2.get(i)))
                break;

        return path1.get(i - 1);
    }

    private boolean findPath(Node root, int n, List<Integer> path) {
        if (root == null)
            return false;

        path.add(root.getData());

        if (root.getData() == n)
            return true;

        int i = 0;

        while(i < root.getNodes().size()) {
            if (root.getNode(i) != null && findPath(root.getNode(i), n, path))
                return true;
            i++;
        }

        path.remove(path.size() - 1);

        return false;
    }
}
