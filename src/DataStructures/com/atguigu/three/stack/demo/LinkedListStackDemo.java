package DataStructures.com.atguigu.three.stack.demo;

import java.util.Scanner;

/**
 * @author zx
 * @date 2022年10月19日15:33:09
 */
public class LinkedListStackDemo {

    public static void main(String[] args) {
        //测试一下LinkedListStack是否正确
        //先创建一个LinkedListStack对象表示栈
        LinkedListStack stack = new LinkedListStack(3);
        String key = "";
        boolean loop = true;//控制是否退出菜单
        Scanner scanner = new Scanner(System.in);

        System.out.println("show:表示显示栈");
        System.out.println("exit:表示退出程序");
        System.out.println("push:表示添加数据到栈");
        System.out.println("pop:表示从栈中取出数据");

        while (loop) {
            System.out.print("\n请输入你的选择:");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.show();
                    break;
                case "push":
                    System.out.print("请输入一个数:");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.println("出栈的数据是：" + res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}

//定义一个LinkedListStack栈
class LinkedListStack {
    //定义最大长度
    int maxLength;
    //先初始化一个头节点，头节点不要动，不存放具体的数据
    private HeroNode head = new HeroNode(-1, 0);
    private int top = -1;//栈顶指针

    //构造方法
    //传入maxLength设置栈的最大容量
    public LinkedListStack(int maxLength) {
        this.maxLength = maxLength;
    }

    //判断栈是否为空
    public boolean isEmpty() {
        return top == -1;
    }

    //判断栈是否满了
    public boolean isFull() {
        return top >= maxLength - 1;
    }

    //入栈push
    //使用倒插法进行添加
    public void push(int value) {//为新节点传入一个值
        //每添加一个，栈顶指针top后移一位
        top++;
        HeroNode heroNode = new HeroNode(top, value);//新建一个top节点
        //判断栈是否满了
        if (isFull()) {
            System.out.println("栈已满，不能再添加元素");
            return;
        }
        if (head.getNext() == null) {//说明暂时没有节点，则直接添加
            /*
            不能写成heroNode = head.getNext()，因为head.getNext() == null，
            所以这相当于将null赋值给了heroNode，而不是让head的next指针指向heroNode
             */
            //heroNode = head.getNext();
            head.setNext(heroNode);//将新建的节点添加到头节点的后面
        } else {
            //如果链表已有节点，则在头节点和原来的第一个节点中间插入新的节点（倒插）
            heroNode.setNext(head.getNext());
            head.setNext(heroNode);
        }

    }

    //出栈pop
    public int pop() {
        //判断栈是否为空
        if (isEmpty()) {
            throw new RuntimeException("栈已空，无法取出数据");
        }
        //如果不为空，则弹出第一个节点
        int value = head.getNext().getValue();
        head.setNext(head.getNext().getNext());
        top--;
        return value;
    }

    //显示栈
    public void show() {

        //判断栈是否为空
        if (isEmpty()) {
            System.out.println("栈空，无法显示数据");
            return;
        }

        HeroNode temp = head.getNext();//不能写成HeroNode temp = head;否则会显示头节点
        //遍历链表
        while (true) {
            System.out.println(temp);
            /*
            如果push方法中写成heroNode = head.getNext()，则head的下一个节点仍是null而不是heroNode，
            即把null赋给了temp，这会导致下面的if语句中的temp.getNext()出现空指针异常
             */
            if (temp.getNext() == null) {
                break;
            }
            //如果没有找到最后，就将temp后移
            temp = temp.getNext();
        }

    }
}

//定义节点
class HeroNode {
    private int no;//节点编号
    private int value;//节点数据
    private HeroNode next;//指向下一个节点

    //构造方法
    public HeroNode(int no, int value) {
        this.no = no;
        this.value = value;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public HeroNode getNext() {
        return next;
    }

    public void setNext(HeroNode next) {
        this.next = next;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", value=" + value +
                '}';
    }

}
