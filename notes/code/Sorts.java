public class Sorts {
    public static void print(int[] data) {
        for(int i = 0; i < data.length; i++) {
            System.out.print(data[i] + (i == data.length - 1 ? "\n" : " "));
        }
    }

    public static void swap(int data[], int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    public static void selectionSort(int[] data) {
        int n = data.length;
        for(int i = 0; i < n - 1; i++) {
            int min = i;
            int j;
            for(j = i + 1; j < n; j++) {
                if(data[j] < data[min]) {
                    min = j;
                }
            }
            swap(data, i, j);
        }
    }

    public static void insertionSort(int[] data) {
        int n = data.length;
        for(int i = 1; i < n; i++) {
            int j = i;
            int item = data[i];
            while(j > 0 && item < data[j - 1]) {
                data[j] = data[j - 1];
                j--;
            }
            data[j] = item;
        }
    }

    public static void bubbleSort(int[] data) {
        int n = data.length;
        int i, j;
        for(i = 0; i <= n - 2; i++) {
            boolean swapped = false;
            for(j = n - 1; j > i; j--) {
                if(data[j] < data[j - 1]) {
                    swapped = true;
                    swap(data, i, j);
                }
            }
            if(!swapped) {
                return;
            }
        }
    }

    // p1: [left .. mid], p2: [mid + 1 .. right]
    public static void merge(int data[], int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] p1 = new int[n1];
        int[] p2 = new int[n2];
        for(int i = 0; i < n1; i++) {
            p1[i] = data[i + left];
        }
        for(int i = 0; i < n2; i++) {
            p2[i] = data[mid + i + 1];
        }
        int i = 0, j = 0, k = left;
        while(i < n1 && j < n2) {
            if(p1[i] < p2[j]) {
                data[k] = p1[i];
                i++;
            } else {
                data[k] = p2[j];
                j++;
            }
            k++;
        }
        while(i < n1) {
            data[k] = p1[i];
            i++;
            k++;
        }
        while(j < n2) {
            data[k] = p2[j];
            j++;
            k++;
        }
    }

    public static void mergeSort(int[] data, int l, int r) {
        if(l < r) {
            int m = l + (r - l) / 2;
            mergeSort(data, l, m);
            mergeSort(data, m + 1, r);
            merge(data, l, m, r);
        }
    }

    // p1: [first .. n1), p2: [n1 .. n2)
    public static void merge2(int[] data, int first, int n1, int n2) {
        System.out.println("Merging " + first + " " + n1 + " " + n2);
        int[] p1 = new int[n1];
        int[] p2 = new int[n2];
        for(int i = 0; i < n1; i++) {
            p1[i] = data[first + i];
        }
        for(int i = 0; i < n2; i++) {
            p2[i] = data[first + n1 + i];
        }
        int i = 0, j = 0, k = first;
        while(i < n1 && j < n2) {
            if(p1[i] < p2[j]) {
                data[k] = p1[i];
                i++;
            } else {
                data[k] = p2[j];
                j++;
            }
            k++;
        }
        while(i < n1) {
            data[k] = p1[i];
            i++;
            k++;
        }
        while(j < n2) {
            data[k] = p2[j];
            j++;
            k++;
        }
    }

    public static void mergeSort2(int[] data, int first, int n) {
        if(n > 1) {
            int n1 = n / 2;
            int n2 = n - n1;
            mergeSort2(data, first, n1);
            mergeSort2(data, first + n1, n2);
            merge2(data, first, n1, n2);
        }
    }

    public static int partition(int[] data, int first, int n) {
        int pivot = first;
        int last = first + n - 1;
        for(int i = last; i > first; i--) {
            if(data[i] > data[pivot]) {
                swap(data, i, last);
                last--;
            }
        }
        swap(data, last, first);
        return last;
    }

    public static int partition2(int[] data, int first, int n) {
        int pivot = data[first];
        int lo = first, hi = first + n - 1;
        while(lo + 1 < hi) {
            if(data[lo] <= pivot) {
                lo++;
            } else if(data[hi] >= pivot) {
                hi--;
            } else {
                swap(data, lo, hi);
                lo++;
                hi--;
            }
        }
        swap(data, lo, first);
        return lo;
    }

    public static void quickSort(int[] data, int first, int n) {
        if(n > 1) {
            int pivotIndex = partition(data, first, n);
            int n1 = pivotIndex - first;
            int n2 = n - n1 - 1;
            quickSort(data, first, n1);
            quickSort(data, pivotIndex + 1, n2);
        }
    }

    public static void heapSort(int[] data) {
        Heap.makeHeap(data);
        for(int i = data.length - 1; i > 0; i--) {
            swap(data, 0, i);
            int pos = 0;
            while(pos * 2 + 1 < i) {
                int child = pos * 2 + 1;
                if(child + 1 < i && data[child + 1] > data[child]) {
                    child++;
                }
                if(data[child] > data[pos]) {
                    int tmp = data[child];
                    data[child] = data[pos];
                    data[pos] = tmp;
                    pos = child;
                }
            }
        }
    }

    public static void countSort(int[] data, int k) {
        int n = data.length;
        int[] count = new int[k], start = new int[k];
        int[] result = new int[n];
        for(int i = 0; i < k; i++) {
            count[i] = 0;
        }
        for(int i = 0; i < n; i++) {
            count[data[i]]++;
        }
        start[0] = 0;
        for(int i = 1; i < k; i++) {
            start[i] = start[i - 1] + count[i - 1];
        }
        for(int i = 0; i < n; i++) {
            int pos = start[data[i]];
            result[pos] = data[i];
            start[data[i]]++;
        }
        for(int i = 0; i < n; i++) data[i] = result[i];
    }

    public static void main(String[] args) {
        int[] data = new int[]{6, 4, 9, 5, 1, 8, 2, 7, 3};
        heapSort(data);
        // quickSort(data, 0, data.length);
        // data = new int[]{1, 1, 1, 2, 3, 4, 5, 5, 5, 6, 6, 7, 8, 9, 9, 9};
        // countSort(data, 10);
        print(data);
    }
}
