package stack;

/**
 * 利用数组实现一个栈
 */
public class ArrayStack<E> implements Stack<E> {

    private int maxSize;
    private E[] arr;
    private int top;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        arr = (E[]) new Object[maxSize];
        top = -1;
    }


    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }


    /**
     * 添加
     */
    public void push(E value) {
        if (isFull()) {
            System.out.println("栈满~");
            return;
        }
        arr[++top] = value;
    }


    /**
     * 出栈
     */
    public E pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        return arr[top--];
    }

    @Override
    public E peek() {
        return arr[top];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = top; i >= 0; i--) {
            sb.append(arr[i]).append("\n");
        }
        return sb.toString();
    }
}
