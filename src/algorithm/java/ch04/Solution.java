package ch04;
public class Solution {
	
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (remove(head, n) > 0) {
            return head.next;   
        }

        return head;
    }

    private int remove(ListNode node, int n) {
        if (node.next == null) return 1;
        int rank;
        rank = remove(node.next, n) + 1;

        // the way to delete the nth node
        // is to set the (n-1)th node's next
        // as nth node's next
        // [tricky] return a very big negtive number
        // to indicate we are success
        if (rank == n + 1) {
            node.next = node.next.next;
            return Integer.MIN_VALUE;
        }
        return rank;
    }
}