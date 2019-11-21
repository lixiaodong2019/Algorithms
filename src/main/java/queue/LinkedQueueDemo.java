package queue;


import java.util.Scanner;

/**
 * 采用单链表实现队列
 */
public class LinkedQueueDemo {

    private Scanner scanner = new Scanner(System.in);
    private LinkedListQueue queue = new LinkedListQueue();

    public static void main(String[] args) {

        new LinkedQueueDemo().run();
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
                    System.out.println("移除的头部元素: " + queue.poll());
                    break;
                case "s":
                    queue.show();
                    break;
                case "h":
                    System.out.println("头部元素为: " + queue.peek());
                    break;
                default:
                    break;
            }
        }
    }

    private void add() {
        System.out.println("请输入要添加的元素:");
        String s = scanner.nextLine();
        int num = Integer.parseInt(s);
        queue.add(new Node(num));
        queue.show();
    }
}


class LinkedListQueue {
    private Node head;

    public LinkedListQueue() {
        head = new Node(0);
    }


    public boolean isEmpty() {
        return head.next == null;
    }


    public void add(Node node) {
        if (isEmpty()) {
            head.next = node;
            return;
        }
        Node cur = head.next;
        while (cur != null) {
            if (cur.next == null) {
                cur.next = node; //加到链表的末尾处
                return;
            }
            cur = cur.next;
        }
    }

    /**
     * 每次移除掉队列头部的元素
     *
     * @return
     */
    public Node poll() {
        if (isEmpty()) {
            System.out.println("队列为空,无法移除");
            return null;
        }
        Node temp = head.next;
        head.next = temp.next;
        return temp; //轻松
    }

    public Node peek() {
        return head.next;
    }

    public void show() {
        Node temp = head;
        if (temp.next == null) {
            System.out.println("链表为空");
            return;
        }
        while (true) {
            if (temp == null) { //如果当前列表为空,则退出循环
                break;
            }
            System.out.println(temp);
            temp = temp.next; //节点后移
        }
    }
}

class Node {
    private int value;
    Node next;

    @Override
    public String toString() {
        return value + "";
    }

    public Node(int value) {
        this.value = value;
    }
}
