public class IntNode {
    private int val;
    private IntNode next;

    public IntNode() {}

    public IntNode(int val) {
        this.val = val;
    }

    public IntNode(int val, IntNode next) {
        this.val = val;
        this.next = next;
    }

    public int getData() {
        return val;
    }

    public IntNode getLink() {
        return next;
    }

    public void setData(int val) {
        this.val = val;
    }

    public void setLink(IntNode next) {
        this.next = next;
    }
}