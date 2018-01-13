package com.haha;

import java.util.*;
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++) {
            int key = nums[i];
            int balance = target-key;
            if(map.containsKey(balance) && map.get(balance)!=i) {
                int result[];
                result = new int[] {i, map.get(balance)};
                return new int [] {i, balance};
            }
        }
        throw new IllegalStateException();
    }
}