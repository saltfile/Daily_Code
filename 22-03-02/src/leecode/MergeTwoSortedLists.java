package leecode;



class Solution21 {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null)return list2;
        if (list2 == null) return list1;
        ListNode root = new ListNode(0);
        ListNode res = root;
        while (list1!=null&&list2!=null)
        {
            if (list1.val <= list2.val)
            {
                res.next = list1;
                list1 = list1.next;
            }else
            {
                res.next = list2;
                list2 = list2.next;
            }
            res = res.next;
        }

        res.next = (list1 == null) ? list2 : list1;


        return root.next;



    }
}


public class MergeTwoSortedLists {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;

        ListNode node4 = new ListNode(1);
        ListNode node5 = new ListNode(3);
        ListNode node6 = new ListNode(4);
        node4.next = node5;
        node5.next = node6;
        ListNode res = new Solution21().mergeTwoLists(node1,node4);
        while (res.next != null){
            System.out.println(res.val);
            res = res.next;
        }
        System.out.println(res.val);








    }
}
