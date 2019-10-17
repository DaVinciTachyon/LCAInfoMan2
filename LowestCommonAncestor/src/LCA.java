import java.util.ArrayList;
import java.util.List;

public class LCA {

    Node root;
    private List<List<Integer>> path1;
    private List<List<Integer>> path2;
    int t;

    LCA() {
        path1 = new ArrayList<>();
        path2 = new ArrayList<>();
        root = null;
        t = 0;
    }

    int findLCA(int n1, int n2) {
        path1.clear();
        path2.clear();
        return findLCAInternal(root, n1, n2);
    }

    private int findLCAInternal(Node root, int n1, int n2) {
        int m = 0;
        while(true) {
            t = m;
            path1.add(new ArrayList<>());
            if (!findPath(root, n1, path1.get(m), 0)) {
                if(path1.size() > 0)
                    path1.remove(m);
                break;
            }
            m++;
        }

        if(path1.size() == 0)
            return -1;

        m = 0;

        while(true) {
            t = m;
            path2.add(new ArrayList<>());
            if (!findPath(root, n2, path2.get(m), 0)) {
                if(path2.size() > 0)
                    path2.remove(m);
                break;
            }
            m++;
        }

        if(path2.size() == 0)
            return -1;

        for(int i = 0; i < path1.size(); i++)
            for(int j = 0; j < path1.get(i).size(); j++)
                if(path1.get(i).get(j) == n2)
                    return n2;

        for(int i = 0; i < path2.size(); i++)
            for(int j = 0; j < path2.get(i).size(); j++)
                if(path2.get(i).get(j) == n1)
                    return n1;

        int minLength = Integer.MIN_VALUE, minValue = -1;
        for(int i = 0; i < path1.size(); i++)
            for(int j = 0; j < path2.size(); j++)
                for(int k = 0; k < path1.get(i).size() && k < path2.get(j).size(); k++)
                    if (!path1.get(i).get(k).equals(path2.get(j).get(k))) {
                        if (k > minLength) {
                            minLength = k;
                            minValue = path1.get(i).get(k - 1);
                        }
                        break;
                    }

        return minValue;
    }

    private boolean findPath(Node root, int n, List<Integer> path, int p) {
        if(root == null)
            return false;

        path.add(root.getData());

        if(root.getData() == n && t == 0 && p == 0 || root.getData() == n && p != 0 )
            return true;

        for(int i = 0; i < root.getNodes().size(); i++) {
            if(root.getNode(i) != null && findPath(root.getNode(i), n, path, p + 1)) {
                if(t == 0)
                    return true;

                t--;
                while(path.size() > p + 1)
                    path.remove(path.size() - 1);
            }
        }

        path.remove(path.size() - 1);

        return false;
    }
}
