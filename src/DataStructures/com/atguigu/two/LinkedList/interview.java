package DataStructures.com.atguigu.two.LinkedList;

import java.util.Stack;

/**
 * @author zx
 * @date 2022/9/17 17:28
 */
public class interview {
   /*
    1>求单链表中有效节点的个数
    2>查找单链表中的倒数第k个节点 【新浪】
    3>单链表的反转【腾讯】
    4>从尾到头打印单链表 【百度】
    5>合并两个有序的单链表，合并之后的链表依然有序
    */

    public static void main(String[] args) {

        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        //创建要给链表
        SingleLL singleLL = new SingleLL();
        //加入
        singleLL.add(hero1);
        singleLL.add(hero2);
        singleLL.add(hero3);
        singleLL.add(hero4);

        // 单链表的反转【腾讯面试题，有点难度】
        System.out.println("原来链表~~");
        singleLL.list();
        System.out.println("反转单链表~~~~");
        reversetList(singleLL.getHead());
        singleLL.list();

        System.out.println("逆序打印单链表~~");
        reversePrint(singleLL.getHead());

        //求单链表中有效节点的个数
        System.out.println("有效的节点个数=" + getLength(singleLL.getHead()));

        //查找单链表中的倒数第k个结点 【新浪面试题】
        HeroNode res = findLastIndexNode(singleLL.getHead(), 3);
        System.out.println("res=" + res);

    }

    //Stack栈
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;//空链表，不能打印
        }
        //创建要给一个栈，将各个节点压入栈
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next;
        //将链表的所有节点压入栈
        while (cur != null) {
            stack.push(cur);
            cur = cur.next; //cur后移，这样就可以压入下一个节点
        }
        //将栈中的节点进行打印,pop 出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop()); //stack的特点是先进后出
        }
    }

    //将单链表反转
    public static void reversetList(HeroNode head) {
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }
        //定义一个辅助的指针(变量)，帮助我们遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next = null;// 指向当前节点[cur]的下一个节点
        HeroNode reverseHead = new HeroNode(0, "", "");
        //遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead 的最前端
        while (cur != null) {
            next = cur.next;//先暂时保存当前节点的下一个节点，因为后面需要使用
            cur.next = reverseHead.next;//将cur的下一个节点指向新的链表的最前端
            reverseHead.next = cur; //将cur 连接到新的链表上
            cur = next;//让cur后移
        }
        //将head.next 指向 reverseHead.next , 实现单链表的反转
        head.next = reverseHead.next;
    }

    //查找单链表中的倒数第k个结点 【新浪面试题】
    //1. 编写一个方法，接收head节点，同时接收一个index
    //2. index 表示是倒数第index个节点
    //3. 先把链表从头到尾遍历，得到链表的总的长度 getLength
    //4. 得到size 后，我们从链表的第一个开始遍历 (size-index)个，就可以得到
    //5. 如果找到了，则返回该节点，否则返回nulll
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        //判断如果链表为空，返回null
        if (head.next == null) {
            return null;//没有找到
        }
        //第一个遍历得到链表的长度(节点个数)
        int size = getLength(head);
        //第二次遍历  size-index 位置，就是我们倒数的第K个节点
        //先做一个index的校验
        if (index <= 0 || index > size) {
            return null;
        }
        //定义给辅助变量， for 循环定位到倒数的index
        HeroNode cur = head.next; //3 // 3 - 1 = 2
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    //方法：获取到单链表的节点的个数(如果是带头结点的链表，需求不统计头节点)

    /**
     * @param head 链表的头节点
     * @return 返回的就是有效节点的个数
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) { //空链表
            return 0;
        }
        int length = 0;
        //定义一个辅助的变量, 这里我们没有统计头节点
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next; //遍历
        }
        return length;
    }

}

//合并两个有序的单链表，合并之后的链表依然有序
//Node类，每个Node对象就是一个节点
class Node {
    public int no;
    public String name;
    public Node next;

    public Node(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Node [no=" + no + ", name=" + name + "]";
    }
}

class SinglList {
    // 先初始化头节点，头节点不动
    private Node head = new Node(0, "");

    //获取链表的头节点
    public Node getHead() {
        return head;
    }

    //添加节点时，根据编号按顺序将节点插入到指定位置,如果链表中已有这个编号，则添加失败，并给出提示
    public void addByOrder(Node node) {
        //头节点不能动，通过一个辅助指针（变量）帮助找到需要添加的位置,因为是单链表，找到的temp是位于添加位置的前一个节点，否则无法插入
        Node temp = head;
        boolean flag = false;//flag标志添加的编号是否存在，默认为false
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > node.no) {
                break;
            }
            if (temp.next.no == node.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.printf("输入的编号%d已经存在,不能加入\n", node.no);
        } else {
            node.next = temp.next;
            temp.next = node;
        }
    }

    //显示链表【遍历】
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动，需要一个辅助变量来遍历
        Node temp = head.next;
        while (true) {
            //判断是否到链表最后
            if (temp == null)
                break;
            System.out.println(temp);
            temp = temp.next;//将temp后移
        }
    }
}

//合并两个有序的单链表，合并之后的链表依然有序
class TwoLinkedList {
    public static void main(String[] args) {
        //创建节点
        Node node1 = new Node(1, "大娃");
        Node node2 = new Node(2, "二娃");
        Node node3 = new Node(3, "三娃");
        Node node4 = new Node(4, "四娃");
        Node node5 = new Node(5, "五娃");
        Node node6 = new Node(6, "六娃");
        Node node7 = new Node(7, "七娃");
        Node node8 = new Node(8, "葫芦小金刚");
        //创建链表
        SinglList singleLinkedList1 = new SinglList();
        SinglList singleLinkedList2 = new SinglList();
        SinglList singleLinkedList3 = new SinglList();
        //添加节点
        singleLinkedList1.addByOrder(node1);
        singleLinkedList1.addByOrder(node3);
        singleLinkedList1.addByOrder(node6);
        singleLinkedList1.addByOrder(node7);
        singleLinkedList2.addByOrder(node2);
        singleLinkedList2.addByOrder(node4);
        singleLinkedList2.addByOrder(node5);
        singleLinkedList2.addByOrder(node8);
        //显示两个链表
        System.out.println("单链表1：");
        singleLinkedList1.list();
        System.out.println("单链表2：");
        singleLinkedList2.list();
        //显示合并后的链表
        twoLinkedList(singleLinkedList1.getHead(), singleLinkedList2.getHead(), singleLinkedList3.getHead());
        System.out.println("组合后的链表：");
        singleLinkedList3.list();
    }

    //twoLinkedList方法传入待合并的两个链表的头节点以及第三个单链表的头节点
    public static void twoLinkedList(Node head1, Node head2, Node head3) {
        // 如果两个链表均为空，则无需合并，直接返回
        if (head1.next == null && head2.next == null) {
            return;
        }
        // 如果链表1为空，则将head3.next指向head2.next，实现链表2中的节点连接到链表3
        if (head1.next == null) {
            head3.next = head2.next;
        } else if (head2.next == null) {
            head3.next = head1.next;
        } else {
            head3.next = head1.next;//将head3.next指向head1.next，实现链表1中的节点连接到链表3
            Node cur2 = head2.next;//定义一个辅助的指针（变量），帮助我们遍历链表2
            Node cur3 = head3;//定义一个辅助的指针（变量），帮助我们遍历链表3
            Node next = null;

            //遍历链表2，将其节点按顺序连接至链表3
            while (cur2 != null) {
                //链表3遍历完毕后，可以直接将链表2剩下的节点连接至链表3的末尾
                if (cur3.next == null) {
                    cur3.next = cur2;
                    break;
                }
                //在链表3中，找到第一个大于链表2中的节点编号的节点因为是单链表，找到的节点是位于添加位置的前一个节点，否则无法插入
                if (cur2.no <= cur3.next.no) {
                    next = cur2.next;//先暂时保存链表2中当前节点的下一个节点，方便后续使用
                    cur2.next = cur3.next;//     将cur2的下一个节点指向cur3的下一个节点
                    cur3.next = cur2;//     将cur2连接到链表3上
                    cur2 = next;//     让cur2后移
                }
                cur3 = cur3.next;//遍历链表3
            }
        }
    }
}


