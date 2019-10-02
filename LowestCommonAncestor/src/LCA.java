public class LCA {
    LCA() {

    }

    class Node {
        int data;
        Node left, right; //make arraylist

        Node(int value) {
            setData(value);
            setLeft(null);
            setRight(null);
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}
