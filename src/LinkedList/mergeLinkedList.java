package LinkedList;

public class mergeLinkedList {
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 ==null || list2==null){
            return  list1==null?list2:list1;
        }
        ListNode mergedListNode =list1.val>list2.val? list2:list1;
        ListNode pre = mergedListNode;
        ListNode curr1 = mergedListNode.next;
        ListNode curr2 = mergedListNode==list1?list2:list1; // ？ 的使用 很多
        while(curr1!=null && curr2!=null){
            if (curr1.val<curr2.val){//当你看到三个对比，那本质上是两个，两个的对比，小问题不能一次性解决，那就再细分小问题
                pre.next = curr1;
                curr1= curr1.next;
            }else {
                pre.next = curr2;
                curr2=curr2.next;
            }
            pre = pre.next;

        }
        pre.next = curr1!=null?curr1:curr2;


        return  mergedListNode;
    }
    public static void main(String[] args) {
        /** test case 1
         * input 1->2>3
         * 1->4->5
         * outout:
         * 1->1->2->
         */

        ListNode l1 = new ListNode(1,new ListNode(4,new ListNode(5)));
        ListNode l2 = new ListNode(1,new ListNode(2,new ListNode(3)));

        ListNode res = mergeTwoLists(l1,l2);
        Utils.printListNode(res);

    }
}
