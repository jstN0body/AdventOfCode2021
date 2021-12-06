package com.github.jstN0body.adventofcode.java.day6;


public class LanternFish {

    public static long[] fishStates = new long[9];

    public static void main(String[] args) {

        String[] input = args[0].split(",");
        for (String s : input) {
            int i = Integer.parseInt(s);
            fishStates[i]++;
        }

        run(80, fishStates);
        run(256, fishStates);
    }

    public static void run(int count, long[] fishStates) {
        for (int c = 0; c < count; c++) {
            long newFish = fishStates[0];
            for (int i = 0; i < fishStates.length; i++) {
                if (i != 8) {
                    fishStates[i] = fishStates[i + 1];
                } else {
                    fishStates[6] += newFish;
                    fishStates[8] = newFish;
                }
            }
        }
        long sum = 0;
        for (long i : fishStates) {
            sum += i;
        }
        System.out.format("Total fish after %d days: %d%n", count, sum);
    }
}


