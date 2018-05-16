package com.haha.Misc;

public class test2 {
    public static void main(String[] args) {
        int N = -100;
        if (N<0) {
            throw new IllegalArgumentException("negative values are not supported");
        }
        int max_count = 0;
        int count =0 ;
        while (N!=0) {
            int base = N % 2;
            if (base==0) {
                count++;
            } else {
                count =0;
            }
            if (count>max_count) {
                max_count = count;
            }
            N =N/2;
        }
        System.out.println(""+max_count);
    }
}
