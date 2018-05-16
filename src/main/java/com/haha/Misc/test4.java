package com.haha.Misc;

public class test4 {
    public static void main(String[] args) {
        int[] A = {3,8,9,7,6};
        int K = 3;

        for (int i = 0; i < A.length; i++) {
            System.out.println(""+A[i]);
        }
    }

    public static void swap(int[] A, int x, int y) {
        int temp = A[x];
        A[x] = A[y];
        A[y] = temp;
    }
}
