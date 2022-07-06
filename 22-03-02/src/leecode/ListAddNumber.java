package leecode;

/**
 * 链表和相加
 * 问题描述:
 * 给定两个代表两个非负整数的非空链表。
 * 这些数字以相反的顺序存储，并且它们的每个节点都包含一个数字。
 * 将两个数字相加并将总和作为链表返回。
 * 类似于
 * l1: 2->4->3
 * l2: 5->6->4
 * 输出：7->0->8
 *
 */


public class ListAddNumber {


    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode res = addTwoNumbers(l1,l2);
        ListNode node = res;
        while (node.next != null){
            System.out.println(node.val);
            node = node.next;
        }
        System.out.println(node.val);


    }
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);
        ListNode p = l1,q = l2,cre = res;
        int carry = 0;
        while (p!=null||q!=null){
            int x = (p != null)?p.val:0;
            int y = (q != null)?q.val:0;
            cre.next = new ListNode((carry+x+y)%10);
            cre = cre.next;//计算下一位
            //查看加法进位
            carry = (x+y+carry)/10;
            if(p != null){
                p = p.next;
            }
            if(q != null){
                q = q.next;
            }
        }
        if(carry > 0){
            cre.next = new ListNode(carry);
        }
        return res.next;


    }
}
