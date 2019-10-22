package queue;

import java.util.Scanner;

/**
 * 数组实现一个队列
 */
public class QueueDemo {
    private Scanner scanner = new Scanner(System.in);
    private MyQueue queue;

    public static void main(String[] args) {
        QueueDemo queueDemo = new QueueDemo(100);
        for (int i = 0; i < 100; i++) {
            queueDemo.queue.addEle(i);
            System.out.println("有效个数为:" + queueDemo.queue.size());
        }
        for (int i = 0; i < 99; i++) {
            System.out.println("移除的头部元素: " + queueDemo.queue.remove());
            System.out.println("有效个数为:" + queueDemo.queue.size());
        }
    }

    public QueueDemo(int num) {
        queue = new MyQueue(num);
    }

    private void run() {
        while (true) {
            System.out.println("a,添加");
            System.out.println("g,移除");
            System.out.println("s,显示");
            System.out.println("h,返回队列头部");
            System.out.println("输入:");
            String s = scanner.nextLine();
            switch (s) {
                case "a":
                    add();
                    break;
                case "g":
                    System.out.println("移除的头部元素: " + queue.remove());
                    break;
                case "s":
                    queue.show();
                    break;
                case "h":
                    System.out.println("头部元素为: " + queue.getHead());
                    break;
                default:
                    return;
            }
        }
    }

    private void add() {
        System.out.println("请输入要添加的元素:");
        String s = scanner.nextLine();
        int num = Integer.parseInt(s);
        queue.addEle(num);
        queue.show();
    }
}


/**
 * 队列,先进先出原则
 */
class MyQueue {
    //数组最大长度
    private int maxSize;
    //指向队列头部
    private int front;
    //指向队列尾部后一位
    private int rear;
    //数组
    private int[] arr;

    public MyQueue(int maxArrSize) {
        maxSize = maxArrSize;
        arr = new int[maxSize];
    }

    /**
     * 队列是否满了
     */
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    /**
     * 队列是否为空
     */
    public boolean isEmpty() {
        return rear == front;
    }

    /**
     * 添加元素
     */
    public void addEle(int ele) {
        if (isFull()) {
            System.out.println("队列满了,无法添加");
            return;
        }
        arr[rear] = ele;
        rear = (rear + 1) % maxSize;
    }

    /**
     * 移除元素
     */
    public int remove() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空,无法移除元素");
        }
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    /**
     * 返回头部
     */
    public int getHead() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空,无法移除元素");
        }
        return arr[front];
    }

    public void show() {
        for (int i = front; i < front + size(); i++) {
            System.out.println(i % maxSize + ":  " + arr[i % maxSize]);
        }
    }

    public int size() {
        if (front == rear) {
            return 0;
        } else if (front < rear) {
            return rear - front;
        } else {
            return maxSize + rear - front;
        }
    }
}