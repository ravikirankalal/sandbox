//package com.haha.hack;
//
//import javafx.util.Pair;
//
//public class Delta {
//    public static void main(String[] args) {
//        int [] yo = {25626,25757,24367,24267,16,100,2,7277};
//
//    }
//
//    private int[] getDelta(int[] array) {
//        int[] haha = new int[];
//        haha[0] = array[0];
//        int prev = haha[0];
//        for (int i = 1; i < array.length; i++) {
//            haha[i]=prev-array[i];
//        }
//        Pair
//        return haha;
//    }
//
//}
//
//    static int howManyAgentsToAdd(int noOfCurrentAgents, List<List<Integer>> callsTimes) {
//        List<Pair> newList = new ArrayList<>();
//        Integer concurrent =0;
//        for(int i=0; i< callsTimes.size(); i++) {
//            List<Integer> calls = callsTimes.get(i);
//            Pair<Integer, Integer> call = new Pair(calls.get(0), calls.get(1));
//            newList.add(call);
//            Integer othercallsCount = newList.stream().filter(x -> (x.getValue0() >= call.getValue0() || x.End <= call.getValue1())).count();
//            if(othercallsCount> concurrent) {
//                concurrent = othercallsCount;
//            }
//        }
//
//        return concurrent - noOfCurrentAgents;
//    }