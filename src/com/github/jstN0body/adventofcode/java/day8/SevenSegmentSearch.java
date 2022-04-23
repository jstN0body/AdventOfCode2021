package com.github.jstN0body.adventofcode.java.day8;

import java.util.*;

public class SevenSegmentSearch {
    public static void main(String[] args) {
        List<String[]> outputValues = new ArrayList<>();
        for (int i = 15; i <= args.length; i += 15) {
            String[] ar = new String[4];
            for (int x = 0; x < 4; x++) {
                ar[3 - x] = args[i - (x + 1)];
            }
            outputValues.add(ar);
        }

        List<String[]> inputValues = new ArrayList<>();
        for (int i = 0; i < args.length; i+= 15) {
            String[] ar = new String[9];
            System.arraycopy(args, i, ar, 0, 9);
            inputValues.add(ar);
        }

        part1(outputValues);
        System.out.print("\n");
        part2(inputValues, outputValues);
    }

    public static void part1(List<String[]> input) {
        int total = 0;
        for (String[] ar : input) {
            for (String s : ar) {
                if (s.length() <= 4 || s.length() == 7) total++;
            }
        }
        System.out.println(total);
    }

    public static void part2(List<String[]> inputValues, List<String[]> outputValues) {
        // I give up...
    }
}
