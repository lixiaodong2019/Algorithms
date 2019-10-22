package linkedlist.single;

import linkedlist.Hero;
import linkedlist.HeroNode;

public class SingleLinkedList {

    private HeroNode head; //head不能动

    public SingleLinkedList() {
        head = new HeroNode(new Hero(0, "", ""));
    }

    /**
     * 添加
     */
    public SingleLinkedList add(Hero hero) {
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                //找到了最后一个节点
                break;
            }
            temp = temp.next;  //否则将temp后移动一位
        }
        temp.next = new HeroNode(hero); //将新节点添加到最后一个节点的后面
        return this;
    }

    /**
     * 按顺序添加
     */
    public SingleLinkedList addByOrder(Hero hero) {
        HeroNode temp = head;
        boolean flag = false; //判断是否重复的标志
        while (true) {
            if (temp.next == null) {
                break;
            }
            //如果不是最后一个节点
            if (temp.next.hero.no > hero.no) { //该节点的后一个节点编号大于当前,说明找到了该节点
                break;
            } else if (temp.next.hero.no == hero.no) { //出现重复了
                flag = true;
                break;
            }
            temp = temp.next; //都不满足,节点后移
        }
        //根据flag判断
        if (flag) {
            System.out.printf("%d号英雄存在,添加失败\n", hero.no);
        } else {
            //执行插入操作,将新节点的后一个节点指向当前节点的后一个节点,再将当前的节点的后一个节点指向新节点
            HeroNode newNode = new HeroNode(hero);
            newNode.next = temp.next;
            temp.next = newNode;
        }
        return this;
    }

    /**
     * 修改链表
     */
    public void update(Hero hero) {
        HeroNode temp = head.next; //第一个节点
        if (temp == null) {
            System.out.println("链表为空,无法修改");
            return;
        }
        boolean flag = false; //找到的标志
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.hero.no == hero.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.setHero(hero);
        } else {
            System.out.println("未找到要修改的英雄");
        }
    }

    /**
     * 删除节点
     */

    public HeroNode delete(int no) {
        HeroNode temp = head;
        if (head.next == null) {
            System.out.println("链表为空,无法删除");
            return null;
        }
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.hero.no == no) { //该节点的下一个节点和当前匹配,这样就可以删掉了
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            HeroNode delete = temp.next;
            temp.next = delete.next;
            return delete;
        } else {
            System.out.println("未找到要删除的节点: " + no);
            return null;
        }
    }


    /**
     * 遍历链表,打印
     */
    public void list() {
        HeroNode temp = head;
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

    public HeroNode getHead() {
        return head;
    }
}