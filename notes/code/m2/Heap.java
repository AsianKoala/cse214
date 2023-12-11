public class Heap {
    private int[] data;
    private int heapSize;
    private int maxSize;

    public Heap(int maxSize) {
        this.maxSize = maxSize;
        data = new int[maxSize];
        heapSize = 0;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public boolean isFull() {
        return heapSize == maxSize;
    }

    public void swap(int i, int j) {
        int a = data[i];
        int b = data[j];
        data[i] = b;
        data[j] = a;
    }

    public void insert(int item) {
        int pos;
        if(isFull()) {
            // throw exception
        }
        heapSize++;
        pos = heapSize - 1;
        data[pos] = item;
        while(pos > 0 && data[pos] > data[(pos - 1) / 2]) {
            swap(pos, (pos - 1) / 2);
            pos = (pos - 1) / 2;
        }
    }

    public int remove() {
        if(heapSize == 0) {
            // throw exception
        }
        int result = data[0];
        data[0] = data[heapSize - 1];
        heapSize--;
        fixHeap();
        return result;
    }

    public void fixHeap() {
        int position = 0;
        int childpos;
        while(position * 2 + 1 < heapSize) {
            childpos = position * 2 + 1;
            if(childpos < heapSize - 1 && data[childpos + 1] > data[childpos]) {
                childpos++;
            }
            if(data[position] >= data[childpos]) {
                return;
            }
            swap(position, childpos);
            position = childpos;
        }
    }

    public static void makeHeap(int[] arr) {
        int n = arr.length;
        for(int i = (n - 2) / 2; i >= 0; i--) {
            int pos = i;
            while(pos * 2 + 1 < n) {
                int child = pos * 2 + 1;
                if(child + 1 < n && arr[child + 1] > arr[child]) {
                    child++;
                }
                if(arr[child] > arr[pos]) {
                    int tmp = arr[child];
                    arr[child] = arr[pos];
                    arr[pos] = tmp;
                    pos = child;
                } else {
                    break;
                }
            }
        }
    }
}
