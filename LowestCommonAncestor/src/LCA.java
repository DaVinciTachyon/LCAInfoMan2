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
            path2.add(new ArrayList<>());
            if (!findPath(root, n1, path1.get(t), t) || !findPath(root, n2, path2.get(t), t)) {
                System.out.println((path1.size() > 0) ? "n1 is present" : "n1 is missing");
                System.out.println((path2.size() > 0) ? "n2 is present" : "n2 is missing");
                path1.remove(t);
                path2.remove(t);
                break;
            }
            t++;
        }

        if(t == 0)
            return -1;

        int minLength = Integer.MAX_VALUE, minValue = -1;
        int k = 0;
        for(int i = 0; i < path1.size(); i++)
            for(int j = 0; j < path2.size(); j++)
                for(k = 0; k < path1.get(i).size() && k < path2.get(j).size(); k++)
                    if (!path1.get(i).get(k).equals(path2.get(j).get(k))) {
                        if (minLength > k) {
                            minLength = k;
                            minValue = path1.get(i).get(k - 1);
                        }
                        break;
                    }

        if(minValue == -1)
            return path1.get(0).get(k - 1); //fix the zero and you're done
        else
            return minValue;
    }

    private boolean findPath(Node root, int n, List<Integer> path, int t) {
        if(root == null)
            return false;

        path.add(root.getData());

        if(root.getData() == n)
            return true;

        for(int i = 0; i < root.getNodes().size(); i++) {
            if(root.getNode(i) != null && findPath(root.getNode(i), n, path, t)) {
                if(t == 0)
                    return true;
                else
                    t--;
            }
        }

        path.remove(path.size() - 1);

        return false;
    }
}
