package leecode;

class Solution19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head.next == null)return null;
        ListNode ptr1 = head;
        ListNode ptr2 = head;
        int lens = 1;
        while (ptr1.next != null){
            lens++;
            ptr1 = ptr1.next;
        }
        if(lens==n){head= head.next;return head;};

        for (int i = 0;i < lens-n-1;i++){
            ptr2 = ptr2.next;
        }
        ListNode ptr3 = ptr2.next;
        ptr2.next = ptr3.next;
        return head;

    }
}




public class RemoveEndList {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        node1.next = node2;
        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;
//        node5.next = node6;


       ListNode root =  new Solution19().removeNthFromEnd(node1,2);
       while (root.next!=null){
           System.out.print(root.val+"   ");
           root = root.next;
       }
        System.out.print(root.val);
        System.out.println();
    }
}
