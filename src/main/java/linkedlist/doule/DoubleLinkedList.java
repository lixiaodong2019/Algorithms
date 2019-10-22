package linkedlist.doule;

import linkedlist.Hero;
import linkedlist.HeroNode;


public class DoubleLinkedList {

    private HeroNode head; //head不能动
    private HeroNode tail; //head不能动

    public DoubleLinkedList() {
        head = new HeroNode(new Hero(0, "", ""));
        tail = head;
    }

    /**
     * 添加
     */
    public void add(Hero hero) {
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        HeroNode next = new HeroNode(hero);
        temp.next = next;  //将节点加到链表尾部
        next.pre = temp; //上一个节点指向前一个节点
        tail = next;
    }

    /**
     * 按顺序添加,通过比较找到添加的位置,判断依据是是否重复
     */
    public void addByOrder(Hero hero) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) { //到最后还没找到重复的,添加到末尾
                break;
            }
            if (temp.next.getHero().no > hero.no) {
                break;
            } else if (temp.next.getHero().no == hero.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.println(hero.no + "已经存在,无法添加");
            // 插入操作
        } else {
            HeroNode newNode = new HeroNode(hero);
            newNode.next = temp.next;
            if (temp.next != null) {  //中间插入
                temp.next.pre = newNode;
            } else { //末尾插入
                tail = newNode;
            }
            temp.next = newNode;
            newNode.pre = temp;
        }
    }

    /**
     * 修改链表
     */
    public void update(Hero hero) {
        HeroNode temp = head.next;
        if (temp == null) {
            System.out.println("链表为空");
            return;
        }
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.getHero().no == hero.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.setHero(hero);
        } else {
            System.out.printf("未找到要修改的节点%d\n", hero.no);
        }
    }

    /**
     * 删除节点
     */

    public HeroNode delete(int no) {
        HeroNode temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {  //每个节点都遍历
                break;
            }
            if (temp.getHero().no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            //执行删除操作
            temp.pre.next = temp.next;
            if (temp.next != null) {  //如果temp是最后一个节点,不做下面的处理
                temp.next.pre = temp.pre;
            }
            return temp;
        } else {
            System.out.println("未找到该节点");
            return null;
        }
    }


    /**
     * 遍历链表,打印
     */
    public void printListDesc() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    public void printListAsc() {
        if (tail == head) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = this.tail;
        while (temp != head) { //逆序打印
            System.out.println(temp);
            temp = temp.pre;
        }
    }


    public HeroNode getHead() {
        return head;
    }

    public HeroNode getTail() {
        return tail;
    }

}