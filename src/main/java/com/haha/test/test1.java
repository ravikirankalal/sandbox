package com.haha.test;

public class test1 {
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
//        String S = "Forget  CVs.!Save time ? x x";
        String S = "    .     ?    !   ";

        String[] list =  S.split("[.?|!]");
        int size = 0;
        for (String string: list) {
            int tempsize;
            if (string.trim().length()==0) {
                tempsize =0;
            } else {
                tempsize = string.trim().split("[ ]+").length;
            }

            if (size<tempsize) {
                size = tempsize;
            }
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
