public class IntList {
    private IntNode head;
    private IntNode tail;
    private IntNode cursor;

    public IntList() {
        head = null;
        tail = null;
        cursor = null;
    }

    public void addNewHead(int element) {
        IntNode newNode = new IntNode(element);
        newNode.setLink(head);
        head = newNode;
        if(tail == null) tail = head;
        cursor = head;
    }

    public void addIntAfter(int element) {
        IntNode newNode = new IntNode(element);
        if(cursor == null) {
            head = newNode;
            tail = newNode;
            cursor = newNode;
        } else {
            newNode.setLink(cursor.getLink());
            cursor.setLink(newNode);
            cursor = newNode;
            if(cursor.getLink() == null) {
                tail = cursor;
            }
        }
    }

    public void removeIntAfter(int element) {
        if(cursor != tail) {
            cursor.setLink(cursor.getLink().getLink());
            if(cursor.getLink() == null) {
                tail = cursor;
            }
        }
    }

    public void removeHead() {
        if(head != null) {
            head = head.getLink();
        }
        if(head == null) {
            tail = null
        }
        cursor = head;
    }

    public boolean advanceCursor() {
        if(cursor != tail) {
            cursor = cursor.getLink();
            return true;
        }
        return false;
    }

    public void resetCursor() {
        cursor = head;
    }

    public boolean isEmpty() {
        return cursor == null;
    }

    public int listLength() {
        IntNode ptr = head;
        int cnt = 0;
        while(ptr != null) {
            cnt++;
            ptr = ptr.getLink();
        }
        return cnt;
    }

    public boolean listSearch(int target) {
        IntNode ptr = head;
        while(ptr != null) {
            if(ptr.getData() == target) {
                cursor = ptr;
                return true;
            }
            ptr = ptr.getLink();
        }
        return false;
    }

    public boolean listPosition(int pos) {
        if(position <= 0) {
            // throw something
        }
        IntNode ptr = head;
        int i = 1;
        while(i < pos && ptr != null) {
            ptr = ptr.getLink();
            i++;
        }
        if(ptr != null) cursor = ptr;
        return ptr != null;
    }

    public boolean remove(int target) {
        IntNode prev, ptr;
        ptr = head;
        if(target == ptr.getData()) {
            removeHead();
            return true;
        } else {
            while(ptr != null && ptr.getData() != target) {
                prev = ptr;
                ptr = ptr.getLink();
            }
            if(ptr != null) {
                prev.setLink(ptr.getLink());
                if(prev.getLink() == null) {
                    tail = prev;
                }
                return true;
            }
            return false;
        }
    }

    public int getNodeData() {
        if(cursor == null) {

        } else {
            return cursor.getData();
        }
    }

    public void setNodeData(int x) {
        if(cursor == null) {

        } else {
            cursor.setData(x);
        }
    }
}
