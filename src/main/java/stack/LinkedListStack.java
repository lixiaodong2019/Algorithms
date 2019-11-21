package stack;


public class LinkedListStack<E> implements Stack<E> {
    private Node head;

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void push(E value) {
        if (head == null) {
            head = new Node<>(value);
            return;
        }
        Node temp = head;
        while (true) {
            if (temp.next == null) {
                temp.next = new Node<>(value);
                break;
            }
            temp = temp.next;
        }
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        Node temp = head; //找到栈顶的元素
        if (temp.next == null) { //如果只有一个元素
            head = null;
            return (E) temp.getValue();
        } else {
            while (temp.next.next != null) { //找到了栈顶的上一个元素
                temp = temp.next;
            }
            Node popValue = temp.next;
            temp.next = popValue.next;
            return (E) popValue.getValue();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node temp = head;
        while (temp != null) {
            sb.append(temp.value).append("\n");
            temp = temp.next;
        }
        return sb.toString();
    }

    /**
     * 获得栈顶的元素
     *
     * @return
     */
    public E peek() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        Node temp = head; //找到栈顶的元素
        if (temp.next == null) { //如果只有一个元素
            return (E) temp.getValue();
        } else {
            while (temp.next.next != null) { //找到了栈顶的上一个元素
                temp = temp.next;
            }
            return (E) temp.next.getValue();
        }
    }


    private static class Node<E> {
        private E value;
        private Node next;

        public Node(E value) {
            this.value = value;
        }

        public E getValue() {
            return value;
        }
    }

}
