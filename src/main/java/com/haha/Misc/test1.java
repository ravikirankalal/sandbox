package com.haha.Misc;

public class test1 {
    public static void main(String[] args) {
        int[] A = {0,0,0};
        for (int i=0;i<A.length;i++) {
            if (A[i]<0) {
                A[i] =0;
            }
        }
        for (int i = 0; i < A.length; i++) {
            if (A[i] <= A.length) {
                int temp = A[i];
                if (temp==0) {
                    continue;
                }
                if (temp<0) {
                    temp = 0-temp;
                }
                if (A[temp-1]>0) {
                    A[temp-1] = 0-A[temp-1];
                }
            }
        }
        for (int i = 0; i < A.length; i++) {
            if (A[i] >=0) {
                System.out.printf(""+(i+1));
                return;
            }
        }
        System.out.printf(""+(A.length+1));
    }
}
