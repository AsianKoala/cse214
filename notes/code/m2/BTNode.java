public class BTNode {
    private int data;
    private BTNode left;
    private BTNode right;

    public BTNode(int data) {
        this.data = data;
        left = null;
        right = null;
    }

    public int getData() {
        return data;
    }

    public BTNode getLeft() {
        return left;
    }

    public BTNode getRight() {
        return right;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setLeft(BTNode left) {
        this.left = left;
    }

    public void setRight(BTNode right) {
        this.right = right;
    }

    public void preorder() {
        System.out.println(data);
        if(left != null) left.preorder();
        if(right != null) right.preorder();
    }

    public void inorder() {
        if(left != null) left.inorder();
        System.out.println(data);
        if(right != null) right.inorder();
    }

    public void postorder() {
        if(left != null) left.postorder();
        if(right != null) right.postorder();
        System.out.println(data);
    }

    public int getRightmostData() {
        if(right == null) {
            return data;
        } else {
            return right.getRightmostData();
        }
    }

    public BTNode removeRightMost() {
        if(right == null) {
            return left;
        } else {
            right = right.removeRightMost();
            return this;
        }
    }
}
