package com.haha.hack;

public class Polygon {
    public static void main(String[] args) {

    }

    private int getShapes(long a, long b, long c, long d) {
        if ((a == b) && (b == c) && (c == d)) {
            return 0;
        }
        else if ((a == c) && (b == d)) {
             return 1;
        } else {
             return 2;
        }
    }

}
