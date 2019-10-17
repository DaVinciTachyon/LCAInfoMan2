import java.util.ArrayList;
import java.util.List;

public class LCA {

    Node root;
    private List<List<Integer>> path1;
    private List<List<Integer>> path2;

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
        int t = 0;
        while(true) {
            path1.add(new ArrayList<>());
            if (!findPath(root, n1, path1.get(t), t, 0)) {
                if(path1.size() > 0)
                    path1.remove(t);
                break;
            }
            t++;
        }
        System.out.println(n1 + " " + n2 + " -- " + path1.size() + " - " + path2.size());

        if(path1.size() == 0)
            return -1;

        t = 0;

        while(true) {
            path2.add(new ArrayList<>());
            if (!findPath(root, n2, path2.get(t), t, 0)) {
                if(path2.size() > 0)
                    path2.remove(t);
                break;
            }
            t++;
        }
        System.out.println(n1 + " " + n2 + " -- " + path1.size() + " - " + path2.size());

        if(path2.size() == 0)
            return -1;

        int minLength = Integer.MAX_VALUE, minValue = -1;
        for(int i = 0; i < path1.size(); i++)
            for(int j = 0; j < path2.size(); j++)
                for(int k = 0; k < path1.get(i).size() && k < path2.get(j).size(); k++)
                    if (!path1.get(i).get(k).equals(path2.get(j).get(k))) {
                        if (minLength > k) {
                            minLength = k;
                            minValue = path1.get(i).get(k - 1);
                        }
                        break;
                    }

        if(minValue == -1) {
            for(int i = 0; i < path1.size(); i++)
                for(int j = 0; j < path1.get(i).size(); j++)
                    if(path1.get(i).get(j) == n2)
                        return n2;
            return n1;
        }

        return minValue;
    }

    private boolean findPath(Node root, int n, List<Integer> path, int t, int p) {
        if(root == null)
            return false;

        path.add(root.getData());

        if(root.getData() == n && t == 0)
            return true;

        for(int i = 0; i < root.getNodes().size(); i++) {
            if(root.getNode(i) != null && findPath(root.getNode(i), n, path, t, p + 1)) {
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
