package LinkedList;

import static LinkedList.Utils.printListNode;

public class isPalindromeWithFastNode {

    public static boolean isPalindrome(ListNode head) {
        boolean isPalin = true;
        int sizeofListNode = Utils.getLengthOfListNode(head);
        ListNode left = head;
        ListNode right = head.next;
        ListNode low = head;

        int i =1;
        int limit =  (sizeofListNode/2);


        if(sizeofListNode%2==0)
        {
            // 偶数

            while(i<(limit)){
                left = left.next;
                right = right.next;
                low = low.next;
                i++;

            }


        }else{
            while(i<= limit){
                left = left.next;
                right = right.next;
                low = low.next;
                i++;

            }
        }




       // left.next = null;  low = left so when you check low.next !=null , it will always be false since low.next==null; then you cannot step into while loop downbelow

        while(low.next != null) {
            right.next = low;
            low= right;
            right = right.next;
            
        }

        //  left.next = null;  deadlock with left=3 and low=3 left.next = low, and low.next = left



        /****
         * head ->1->2->3<-3<-2-<1-low
         */

        while(head.next != null) {
            if(head.val != low.val){
                isPalin = false;
                break;
            }
            head = head.next;
            low = low.next;
        }

        /**
         * 还原list
         *
         */



        return  isPalin;
    }
    public static void main(String[] args) {
        /**test case
         * input
         * 1->2->3->3->2->1
         * output true
         */

        ListNode l1 = new ListNode(1,new ListNode(1,new ListNode(3,new ListNode(3,new ListNode(2,new ListNode(1))))));
   //     System.out.println("list node "+ isPalindrome(l1));
        printListNode(Utils.reverseList(l1));
    }
}
