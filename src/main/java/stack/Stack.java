package stack;

public interface Stack<E> {
    boolean isEmpty(); //判断是否为空

    void push(E value); //压栈

    E pop(); //出栈

    E peek(); //返回栈顶元素
}
