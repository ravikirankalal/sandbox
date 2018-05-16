package com.haha.Misc;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution2 {
    public static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            ListNode l1 = this;
            while (l1!=null) {
                stringBuilder.append(":"+l1.val);
                l1 = l1.next;
            }
            return stringBuilder.toString();
        }
     }



    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int cp = 0;
        ListNode root = null;
        ListNode current = null;
        while(l1!=null && l2!=null) {
            int sum = (l1.val + l2.val + cp);
            int delta =  sum % 10;
            cp = sum / 10;
            l1 = l1.next;
            l2 = l2.next;
            if (current==null) {
                root = new ListNode(delta);
                current = root;
            } else {
                current.next = new ListNode(delta);
                current = current.next;
            }
        }
        ListNode rest = null;
        if (l1==null) {
            rest = l2;
        } else {
            rest = l1;
        }
        while (rest!=null) {
            int sum = (rest.val+cp)%10;
            cp = (rest.val+cp) /10;
            current.next = new ListNode(sum);
            rest = rest.next;
            current = current.next;
        }
        return root;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        Solution2 solution2 = new Solution2();
        System.out.println(solution2.addTwoNumbers(l1,l2));
    }
}