package LinkedList;

public class deleteDuplicationInSortedLinkedList {

    public static void main(String[] args) {
        /**
         * test case 1
         * input
         * 1->1-2>-3
         * output
         * 1->2>3
         */

        ListNode l1 = new ListNode(1,new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(3)))));
        Utils.printListNode(deleteDuplicates(l1));

    }

    /**
     * 给定一个已排序的链表的头 l1 ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
     * @param args
     */

    public static ListNode deleteDuplicates(ListNode l1) {
        if(l1 ==null || l1.next ==null){
            return l1;
        }
        ListNode  head =null;
        ListNode pre = l1;
        ListNode curr= l1.next;
        head = pre;
            while(pre.next != null) {
                if (curr.val== pre.val){
                    // remove curr
                    pre.next= curr.next;
                    curr = curr.next;
                }else {
                    pre=curr;
                    curr = curr.next;
                }
            }


         return head;
    }
}
