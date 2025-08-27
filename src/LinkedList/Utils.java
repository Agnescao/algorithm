package LinkedList;

public class Utils {
    /**
     * print LinkedList.ListNode value with -> separator
     *
     * **/
    public  static void printListNode(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print("->");
            }
            current = current.next;
        }
        System.out.println();
    }

    public static int getLengthOfListNode(ListNode reversedListNode1) {
        int lengthOfListNode = 0;
        while(reversedListNode1!=null){
            lengthOfListNode++;
            reversedListNode1=reversedListNode1.next;
        }
        return  lengthOfListNode;
    }



    public static ListNode reverseList(ListNode l1) {
        ListNode prev= null;
        ListNode next = null;
        ListNode head = l1;
        while(head != null){
            next = head.next;
            head.next = prev;
            prev= head;
            head = next;
        }
        return prev;

    }
}
