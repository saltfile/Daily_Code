
class Node{
    public Integer data;
    public Node next;

    public Node(Integer data) {
        this.data = data;
    }
}








public class TescTest {
    public static void main(String[] args) {
        Node n = new Node(0);
        Node n2 = new Node(1);
        Node n3 = new Node(2);
        Node n4 = new Node(3);

        n.next = n2;
        n2.next = n3;
        n3.next = n4;
        System.out.println(getPerice(n,2));


    }
    public static Integer getPerice(Node head,int target) {
        Node demy = new Node(0);
        demy.next = head;

        Node slow = demy;
        Node fast = demy;

        for (int i = 0; i < target; i++) {
            fast = fast.next;
        }

        while (fast != null){
            slow = slow.next;
            fast = fast.next;
        }
        return slow.data;



    }







}
