public class BinarySearchTree {
    private BTNode root;

    public BinarySearchTree() {
        root = null;
    }

    public void insert(int item) {
        BTNode newNode = new BTNode(item);
        BTNode cursor;
        if(root == null) {
            root = newNode;
        } else {
            cursor = root;
            boolean done = false;
            while(!done) {
                if(item < cursor.getData()) {
                    if(cursor.getLeft() == null) {
                        cursor.setLeft(newNode);
                        done = true;
                    } else {
                        cursor = cursor.getLeft();
                    }
                } else if(item > cursor.getData()) {
                    if(cursor.getRight() == null) {
                        cursor.setRight(newNode);
                    } else {
                        cursor = cursor.getRight();
                    }
                } else {
                    done = true;
                }
            }
        }
    }

    public boolean remove(int item) {
        BTNode cursor = root;
        BTNode parent = null;
        while(cursor != null && cursor.getData() != item) {
            parent = cursor;
            if(item < cursor.getData()) {
                cursor = cursor.getLeft();
            } else {
                cursor = cursor.getRight();
            }
        }

        // 1. not in tree
        if(cursor == null) {
            return false;
        } else {
            // case 2: root, no left
            if(cursor == root && cursor.getLeft() == null) {
                root = root.getRight();
            } else if(cursor != root && cursor.getLeft() == null) {
                // case 3: non-root, no left
                if(cursor == parent.getLeft()) {
                    parent.setLeft(cursor.getRight());
                } else {
                    parent.setRight(cursor.getRight());
                }
            } else {
                cursor.setData(cursor.getLeft().getRightmostData());
                cursor.setLeft(cursor.getLeft().removeRightMost());
            }
            return true;
        }
    }
}
