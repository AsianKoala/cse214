public class IntStackArray {
    public final int CAPACITY = 100;
    private int[] data;
    private int top;

    public IntStackArray() {
        top = -1;
        data = new int[CAPACITY];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(int item) {
        if(top == CAPACITY - 1) {
            // THROW STACK EXCEPTION
        }
        top++;
        data[top] = item;
    }

    public int pop() {
        int answer;
        if(top == -1) {
            // THROW EMPTY STACK EXCEPTION
        }
        answer = data[top];
        top--;
        return answer;
    }

    public int peek() {
        if(top == -1) {
            // THROW EMPTY STACK EXCEPTION
        }
        return data[top];
    }

    public int size() {
        return top + 1;
    }
}