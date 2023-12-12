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
                    swap(data, j, j - 1);
                }
            }
            print(data);
            if(!swapped) {
                return;
            }
        }
    }

    public static void merge(int[] data, int first, int n1, int n2) {
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

    public static void mergeSort(int[] data, int first, int n) {
        if(n > 1) {
            int n1 = n / 2;
            int n2 = n - n1;
            mergeSort(data, first, n1);
            mergeSort(data, first + n1, n2);
            merge(data, first, n1, n2);
        }
    }

    public static int partition(int[] data, int first, int n) {
        int pivot = data[first];
        int i = first + 1;
        int j = first + n - 1;
        while(true) {
            while(data[i] < pivot && i <= j) i++;
            while(data[j] > pivot && i <= j) j--;
            if(i >= j) break;
            swap(data, i, j);
            i++;
            j--;
        }
        swap(data, first, i - 1);
        return i - 1;
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
                if(data[pos] >= data[child]) break;
                swap(data, pos, child);
                pos = child;
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
        // int[] data = new int[]{6, 4, 9, 5, 1, 8, 2, 7, 3};
        // int[] data = new int[]{75, 20, 1000, 7, 9, 17};
        int[] data = new int[]{54, 26, 93, 17, 77, 31, 44, 55, 20};
        heapSort(data);
        // quickSort(data, 0, data.length);
        // qsort(data, 0, data.length - 1);
        // data = new int[]{1, 1, 1, 2, 3, 4, 5, 5, 5, 6, 6, 7, 8, 9, 9, 9};
        // countSort(data, 10);
        print(data);
    }
}
