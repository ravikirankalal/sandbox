package com.haha.test;

public class test3 {
    public static class List {
        int value;
        List next;
    }

    public static class Tree {
        int value;
        Tree left;
        Tree right;
    }






    public static void main(String[] args) {
//        int[] A = {5,5,5,5,3,5,5,5,5};
        int[] A = {1,3,5,3,4};
        int i=check_sorted(A);
        if (i==A.length) {
            System.out.println("true");
            return;
        }
        int index_to_swap1 = i;
        int index_to_swap2 = i+1;
        for (i=i+1;i<A.length;i++) {
            if (A[index_to_swap2]>=A[i]) {
                index_to_swap2 = i;
            }
        }
        int index_to_swap3 = index_to_swap1+1;
        for (int j = 0; j <index_to_swap1 ; j++) {
            if (A[j]>A[index_to_swap3]) {
                index_to_swap3=j;
            }
        }
        swap(A,index_to_swap1,index_to_swap2);
        i = check_sorted(A);
        swap(A,index_to_swap2,index_to_swap1);
        swap(A,index_to_swap1+1,index_to_swap3);
        int j = check_sorted(A);
        if (i==A.length || j==A.length) {
            System.out.println("true");
            return;
        } else {
            System.out.println("false");
            return;
        }
        
    }

    public static void swap(int[] A ,int a, int b) {
        int temp = A[a];
        A[a] = A[b];
        A[b] = temp;
    }

    public static int check_sorted(int[] A) {
        int i=0;
        for (i = 0; i < A.length; i++) {
            if (i<A.length-1 && A[i]>A[i+1]) {
                break;
            }
        }
        return i;
    }












    public static void printArray(int[] A) {
        for (int i = 0; i < A.length; i++) {
            System.out.println(""+A[i]);
        }
    }

    public static void printList(List l1) {
        while (l1!=null) {
            System.out.println(l1.value);
            l1 = l1.next;
        }
    }

    public static void traverseTree(Tree tree) {
        if (tree==null)
            return;
        traverseTree(tree.left);
        System.out.println(""+tree.value);
        traverseTree(tree.right);
    }

    public static List createList(int[] A) {
        List head = null;
        List current = null;
        for (int i = 0; i < A.length; i++) {
            if (head == null) {
                head = new List();
                current = head;
                head.value = A[i];
            } else {
                current.next = new List();
                current = current.next;
                current.value = A[i];
            }
        }
        return head;
    }
}
