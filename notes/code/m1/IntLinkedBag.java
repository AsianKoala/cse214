public class IntLinkedBag {
    private IntList data;
    private int manyItems;

    public IntLinkedBag() {
        manyItems = 0;
        data = new IntList();
    }

    public int getCapacity() {
        return Integer.MAX_VALUE;
    }

    public int size() {
        return manyItems;
    }

    public void add(int x) {
        data.addNewHead(x);
        manyItems++;
    }
}
