package aohiothor;

import javax.print.DocFlavor;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }

/**
 * 203. 移除链表元素
 * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 */
class RemoveElements{
    public ListNode removeElements(ListNode head, int val) {
        //排除头节点
        while (head!=null&&head.val == val){
            head = head.next;
        }

        ListNode p = head;
        while (p!=null){
            while (p.next!=null&&p.next.val == val){
                p.next = p.next.next;
            }
            p = p.next;
        }

        return head;
    }
}


/**
 * 707. 设计链表
 *
 * 设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：val 和 next。val 是当前节点的值，next 是指向下一个节点的指针/引用。如果要使用双向链表，则还需要一个属性 prev 以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。
 *
 * 在链表类中实现这些功能：
 *
 *     get(index)：获取链表中第index个节点的值。如果索引无效，则返回-1。
 *     addAtHead(val)：在链表的第一个元素之前添加一个值为val的节点。插入后，新节点将成为链表的第一个节点。
 *     addAtTail(val)：将值为val 的节点追加到链表的最后一个元素。
 *     addAtIndex(index,val)：在链表中的第index个节点之前添加值为val  的节点。如果index等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
 *     deleteAtIndex(index)：如果索引index 有效，则删除链表中的第index 个节点。
 */


class MyLinkedList {

    ListNode head;

    int lenth;
    public MyLinkedList() {
        head = new ListNode(0);
        this.lenth = 0;
    }

    public int get(int index) {
      if (index < 0 || index >= lenth){
          return -1;
      }
      ListNode p = this.head;
        for (int i = 0; i <= index; i++) {
            p = p.next;
        }
        return p.val;
    }

    public void addAtHead(int val) {
        addAtIndex(0,val);
    }

    public void addAtTail(int val) {
        addAtIndex(lenth,val);
    }

    public void addAtIndex(int index, int val) {
        if (index > lenth) {
            return;
        }
        if (index < 0) {
            index = 0;
        }
        this.lenth++;
        ListNode p = head;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        ListNode new_node = new ListNode(val);
        new_node.next = p.next;
        p.next = new_node;
    }

    public void deleteAtIndex(int index) {
        if (index < 0||index >= lenth)return;
        this.lenth--;
        if (index == 0) {
            this.head = head.next;
            return;
        }
        ListNode p = this.head;

        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        p.next = p.next.next;
    }
}

/**
 * 206. 反转链表
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 */
class ReverseList{
    ListNode ToHead(ListNode head,ListNode tail){
        ListNode p = head;
        if (p == null){
            p = new ListNode(tail.val);
            return p;
        }
        ListNode new_n = new ListNode(tail.val);
        new_n.next = p;
        return new_n;
    }
    public ListNode reverseList(ListNode head) {

        ListNode p = head;
        ListNode res = null;
        while (p != null){
            res = ToHead(res,p);
            p = p.next;
        }
        return res;
    }
    public ListNode reverseList2(ListNode head) {
        ListNode p = head;
        ListNode temp = null;
        ListNode prev = null;
        while (p != null){
            temp = p.next;
            p.next = prev;
            prev = p;
            p = temp;
        }
        return prev;
    }
}


/**
 * 24. 两两交换链表中的节点
 *
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。
 * 你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 */

class SwapPairs{
    public ListNode swapPairs(ListNode head) {
        ListNode dey = new ListNode(0);
        dey.next = head;
        ListNode p = dey;
        ListNode f_node = null;//第一个
        ListNode s_node = null;//第二个
        ListNode t_node = null;//第三个

        while (p.next != null&&p.next.next != null){
            t_node = p.next.next.next;
            f_node = p.next;
            s_node = p.next.next;


            p.next = s_node;
            s_node.next = f_node;
            f_node.next = t_node;

            p = f_node;
        }
        return dey.next;
    }
}

/**
 * 19. 删除链表的倒数第 N 个结点
 *
 * 给你一个链表，删除链表的倒数第n个结点，并且返回链表的头结点。
 */
class RemoveNthFromEnd{

    public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dey = new ListNode(0);
    dey.next = head;
    ListNode left = dey;
    ListNode right = dey;
        for (int i = 0; i < n; i++) {
            right = right.next;
        }
        while (right.next != null){
            left = left.next;
            right = right.next;
        }

        left.next = left.next.next;

        return dey.next;

    }

}

/**
 * 面试题 02.07. 链表相交
 *
 * 给你两个单链表的头节点headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
 *
 */


class GetIntersectionNode{
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        ListNode pA = headA;
        ListNode pB = headB;

        while (pA!= pB){
            pA = pA==null?headB:pA.next;
            pB = pB==null?headA:pB.next;
        }
        return pA;

    }


}

/**
 * 给定一个链表的头节点  head，返回链表开始入环的第一个节点。如果链表无环，则返回null。
 *
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 *
 * 不允许修改 链表。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/linked-list-cycle-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * 无所谓，Hashset会出手
 */
class DetectCycle{
    public ListNode detectCycle(ListNode head) {
        HashSet<ListNode> list_set = new HashSet<>();
        ListNode dey = head;
        while (list_set.add(dey) != false){
            if (dey == null) return null;
            dey = dey.next;
        }
        return dey;
    }

}


class ReverseBetween{

    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dem = new ListNode(0);
        dem.next = head;
        ListNode old = dem;
        ListNode start = head;
        for (int i = 0; i < left; i++) {
            old = start;
            start = start.next;
        }
        //翻转

        for (int i = 0; i <right - left; i++) {
            ListNode t = start.next;
            start.next = t.next;
            t.next = old.next;
            old.next = t;
        }
        return dem.next;

    }

}








public class linklist_part {

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);

        ListNode p = n1;
        for (int i = 2; i < 6; i++) {
            p.next = new ListNode(i);
            p = p.next;
        }
        p.next = n1.next.next;

//        new ReverseList().reverseList2(n1);
//        new  SwapPairs().swapPairs(n1);
        new RemoveNthFromEnd().removeNthFromEnd(n1,3);


    }

}
