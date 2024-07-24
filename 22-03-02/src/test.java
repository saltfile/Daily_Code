class ListNode {
      int val;
     ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
class test {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        removeElements(node1,3);

        System.out.println(node2.next.val);



    }
    public static ListNode removeElements(ListNode head, int val) {
        ListNode denty = new ListNode(0);
        if(head == null){
            return head;
        }
        denty.next = head;
        ListNode node = denty;
        while(node.next != null){
            if(node.next.val == val){
                node.next = node.next.next;
            }else{
                node = node.next;
            }
        }
        return denty.next;
    }
}