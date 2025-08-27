package LinkedList;

public class twoSingleLinkedListSumrefactor {


    /// 这种的不要reverse list , 如果说是逆序的存储的

    private static ListNode addTwoNumbers(ListNode reversedListNode1, ListNode reversedListNode2) {

        ListNode currentNode = null;
        ListNode head1 = reversedListNode1;
        ListNode head2 = reversedListNode2;
        ListNode resNode = null;
        int carry = 0;
        int digit = 0;
        int sum = 0;
        while(head1 !=null || head2 !=null){
            int val1 = head1!=null? head1.val : 0;
            int val2 = head2!=null? head2.val : 0;
            sum = val2+val1+carry;
            digit = sum%10;
            carry= sum/10;
            if (resNode==null){
                currentNode=resNode=new ListNode(digit);
            }else {
                currentNode.next = new ListNode(digit);;
                currentNode= currentNode.next;
            }
            if (head1!=null){
                head1=head1.next;
            }
            if (head2!=null){
                head2=head2.next;
            }
        }


        if (carry>0){
            currentNode.next= new ListNode(carry);
        }

        return  resNode;

    }
    private static ListNode reverseList(ListNode l1) {
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



    public static void main(String[] args) {


        /** test case 1
         *  *input
         *      * 2->4->3
         *      * 5->6->4
         *      * output
         *      * 7->0->8
         */
        LinkedList.ListNode l1 = new LinkedList.ListNode(2, new LinkedList.ListNode(4, new LinkedList.ListNode(3)));
        LinkedList.ListNode l2 = new LinkedList.ListNode(5, new LinkedList.ListNode(6, new LinkedList.ListNode(4)));

        LinkedList.ListNode res1=  addTwoNumbers(l1,l2);


        printListNode(res1);

        /** test case 2
         *  *input
         *      * 0
         *      * 0
         *      * output
         *      * 0
         */
 /*       LinkedList.ListNode l3 = new LinkedList.ListNode(0);
        LinkedList.ListNode l4 = new LinkedList.ListNode(0);
        LinkedList.ListNode res2=  addTwoNumbers(l3,l4);

        printListNode(res2);
*/
        /*
            * test case 3
            * input
            * 9->9->9->9->9->9->9
            * 9->9->9
            * output
            * 1->0->0->0->0->0->0->8

         */
        /*LinkedList.ListNode l5 =new LinkedList.ListNode(9,new LinkedList.ListNode(9,new LinkedList.ListNode(9,new LinkedList.ListNode(9,new LinkedList.ListNode(9,new LinkedList.ListNode(9,new LinkedList.ListNode(9)))))));
        LinkedList.ListNode l6 = new LinkedList.ListNode(9,new LinkedList.ListNode(9,new LinkedList.ListNode(9)));
        LinkedList.ListNode res3=  addTwoNumbers(l5,l6);
        printListNode(res3);*/

        /**
         *
         * l1 =
         * [2,4,9]
         * l2 =
         * [5,6,4,9]
         */

        ListNode l7= new ListNode(2, new ListNode(4, new ListNode(9)));
        ListNode l8 = new ListNode(5, new ListNode(6, new ListNode(4, new ListNode(9))));
        ListNode res4=  addTwoNumbers(l7,l8);
        printListNode(res4);

    }

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
}
