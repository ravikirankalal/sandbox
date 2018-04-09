package com.haha.test;

public class test2 {
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
        int A = 123456;
        if (A<0) {
            throw new IllegalArgumentException();
        }
        String S = String.valueOf(A);
        StringBuilder stringBuilder = new StringBuilder();
        int count = S.length();
        int front_count = 0;
        int backcount = count-1;
        boolean front = true;
        while (count!=0) {
            if (front) {
                stringBuilder.append(S.charAt(front_count));
                front_count++;
            } else {
                stringBuilder.append(S.charAt(backcount));
                backcount--;
            }
            count--;
            front = (!front);

        }

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
