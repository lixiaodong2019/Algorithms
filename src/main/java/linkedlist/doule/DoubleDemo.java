package linkedlist.doule;

import linkedlist.Hero;
import linkedlist.HeroNode;

public class DoubleDemo {

    public static void main(String[] args) {
        DoubleLinkedList list = new DoubleLinkedList();
        Hero hero1 = new Hero(1, "刘备", "玄德");
        Hero hero2 = new Hero(2, "诸葛亮", "孔明");
        Hero hero3 = new Hero(3, "关羽", "云长");
        Hero hero4 = new Hero(4, "张飞", "翼德");
        Hero hero5 = new Hero(5, "赵云", "子龙");
        Hero hero6 = new Hero(6, "曹操", "孟德");
        Hero hero7 = new Hero(7, "孙权", "仲谋");
        list.addByOrder(hero2);
        list.addByOrder(hero5);
        list.addByOrder(hero2);
        list.addByOrder(hero7);
        list.addByOrder(hero6);
        list.addByOrder(hero1);
        list.addByOrder(hero3);
        list.addByOrder(hero4);
        list.printListDesc();
        list.update(new Hero(3, "小羽", "云长~"));
        System.out.println("---------");
        list.printListDesc();
        System.out.println("---------");
        list.printListAsc();
        System.out.println("---------");
        list.delete(3);
        list.printListDesc();
        reverse(list);
        list.printListDesc();
    }

    /**
     * 链表反转
     * 采用 新节点的方式,从老链表中每次取出一个数据,就将它插入到新链表的头结点之后
     */
    public static void reverse(DoubleLinkedList linkedList) {
        HeroNode head = linkedList.getHead();

        if (head.next == null || head.next.next == null) {
            return;
        }
        HeroNode newHead = new HeroNode(new Hero(0, "", ""));
        HeroNode cur = head.next;
        HeroNode next;
        while (cur != null) {
            next = cur.next; //将cur下一个节点保存下来
            cur.next = newHead.next; //将新节点的后续赋值给当前节点
            newHead.next = cur;
            cur = next;
        }
        head.next = newHead.next;
    }
}
