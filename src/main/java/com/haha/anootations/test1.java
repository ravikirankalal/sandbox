package com.haha.anootations;

import java.util.ArrayList;

public class test1 {
    public static void main(String[] args) {
        ArrayList list=new ArrayList();
        list.add("sonoo");
        list.add("vimal");
        list.add("ratan");

        for(Object obj:list)
            System.out.println(obj);
    }
}
