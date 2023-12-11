public class IntStackList {
    private IntNode top;
    
    public IntStackList() {
        top = null;
    }

    public boolean isEmpty() {
        return (top == null);
    }

    public void push(int item) {
        IntNode newNode = new IntNode(item);
        newNode.setLink(top);
        top = newNode;
    }

    public int pop() {
        int answer;
        if(top == null) {
            // throw emptystackException
        }
        answer = top.getData();
        top = top.getLink();
        return answer;
    }

    public int peek() {
        if(top == null) {
            // throw EmptyStackException
        }
        return top.getData();
    }
}