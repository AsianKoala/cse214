public class IntQueue {
    public final int CAPACITY = 100;
    private int[] data;
    private int front;
    private int rear;

    public IntQueue() {
        front = -1;
        rear = -1;
        data = new int[CAPACITY];
    }

    public boolean isEmpty() {
        return (front == -1);
    }

    public void enqueue(int item) {
        if((rear + 1) % CAPACITY == front) {
            // throw new FullQueueException
        }
        if(front == -1) {
            front = 0;
            rear = 0;
        } else {
            rear = (rear + 1) % CAPACITY;
        }
        data[rear] = item;
    }

    public int dequeue() {
        int answer;
        if(front == -1) {
            // throw EmptyQueueException
        }
        answer = data[front];
        if(front == rear) {
            front = -1;
            rear = -1;
        } else {
            front = (front + 1) % CAPACITY;
        }
        return answer;
    }
}
