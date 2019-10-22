package linkedlist.single;


import linkedlist.Hero;
import linkedlist.HeroNode;

import java.util.Stack;

/**
 * 单链表的增删改查
 * 2019年9月19日13:54:48
 * 5道面试题
 * 2019年9月19日16:15:12
 *
 /**
 * 基本概念:
 * 1.链表以节点的方式来存储,是链式存储`
 * 2.每个字节包含data域, next域:指向下一个节点
 * 3.链表的每一个节点不一定是连续存储
 * 4.链表分带头结点和不带头结点的,根据实际需求来确定
 */

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        SingleLinkedList list = new SingleLinkedList();
        SingleLinkedList list2 = new SingleLinkedList();
        Hero hero1 = new Hero(1, "刘备", "玄德");
        Hero hero2 = new Hero(2, "诸葛亮", "孔明");
        Hero hero3 = new Hero(3, "关羽", "云长");
        Hero hero4 = new Hero(4, "张飞", "翼德");
        Hero hero5 = new Hero(5, "赵云", "子龙");
        Hero hero6 = new Hero(6, "曹操", "孟德");
        Hero hero7 = new Hero(7, "孙权", "仲谋");
        list.add(hero1).add(hero4).add(hero5);
        list2.add(hero2).add(hero3).add(hero6).add(hero7);
        list.list();
        list2.list();
        System.out.println("--------");
        reverse(list.getHead());
        list.list();
        System.out.println(getLength(list.getHead()));
        System.out.println(findNodeByIndex(1, list.getHead()));
        System.out.println("--------");
        printList(list.getHead());
        System.out.println("--------");
        SingleLinkedList merge = merge(list.getHead(), list2.getHead());
        merge.list();
    }

    /**
     * 1链表反转
     * 思路:创建一个新连表
     * 从之前的链表中不断的移出元素,然后插入到新链表的头结点之后,
     */
    public static void reverse(HeroNode head) {
        //如果链表为空或者,链表的长度为1,无需反转
        if (head.next == null || head.next.next == null) {
            return;
        }
        HeroNode newHead = new HeroNode(new Hero(0, "", ""));
        HeroNode cur = head.next;
        HeroNode next;

        while (cur != null) {
            next = cur.next;     //得到下一个节点
            cur.next = newHead.next;
            newHead.next = cur; // 将当前节点插入到新链表的头结点之后的位置
            cur = next; //指针后移
        }
        head.next = newHead.next; //最后将新链表给旧链表,完成反转
    }

    /**
     * 2求链表有效节点的个数
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        int count = 0;
        HeroNode cur = head.next;
        while (cur != null) {
            count++;
            cur = cur.next; //向后移动一个节点
        }
        return count;
    }

    /**
     * 3查找单链表中倒数第index个节点
     */
    public static HeroNode findNodeByIndex(int index, HeroNode head) {
        if (index <= 0 || index > getLength(head)) {
            System.out.println("index值不对");
            return null;
        }

        //要得到倒数第index个节点,那么就是得到链表长度减去index位置的节点
        int k = getLength(head) - index;
        HeroNode cur = head.next;
        while (k-- > 0) {
            cur = cur.next;
        }
        return cur;
    }

    /**
     * 4.从尾到头打印单链表 ,采用栈实现
     */
    public static void printList(HeroNode head) {
        Stack<HeroNode> stack = new Stack<>();
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode cur = head.next; //赋初始值
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (!stack.empty()) {
            System.out.println(stack.pop());
        }
    }

    /**
     * 合并两个有序链表,合并之后仍然有序
     * temp.pre.next= temp.next; temp.next.pre = temp.pre;
     * 提示:
     * 模仿反转链表的新建链表的方式
     */

    public static SingleLinkedList merge(HeroNode head1, HeroNode head2) {
        SingleLinkedList newList = new SingleLinkedList();
        addByOrder(head1, newList);
        addByOrder(head2, newList);
        return newList;
    }

    private static void addByOrder(HeroNode head1, SingleLinkedList newList) {
        if (head1.next != null) {
            HeroNode cur = head1.next;
            while (cur != null) {
                newList.addByOrder(cur.getHero());
                cur = cur.next;
            }
        }
    }
}






