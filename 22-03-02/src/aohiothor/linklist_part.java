package aohiothor;

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










public class linklist_part {

    public static void main(String[] args) {
       MyLinkedList m1 = new MyLinkedList();
       m1.addAtHead(1);
       m1.addAtTail(3);
       m1.addAtIndex(1,2);
       m1.get(1);
       m1.deleteAtIndex(1);
       m1.get(1);
    }






}
