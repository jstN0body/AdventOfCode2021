package com.github.jstN0body.adventofcode.java.day7;

import java.util.ArrayList;
import java.util.List;

public class TreacheryOfWhales {
    public static void main(String[] args) {
        String[] input = args[0].split(",");
        List<Integer> parsedInput = new ArrayList<>();
        for (String s : input) {
            parsedInput.add(Integer.parseInt(s));
        }

        part1(parsedInput);
        part2(parsedInput);
    }

    public static void part1(List<Integer> input) {
        Object[] sorted = input.stream().sorted().toArray();
        int closest = (int) sorted[sorted.length/2];
        int fuel = 0;
        for (Object o : sorted) {
            int i = (int) o;
            int diff = Math.abs(closest - i);
            fuel += diff;
        }
        System.out.println(fuel);
    }

    public static void part2(List<Integer> input) {
        int i = 0;
        for (Integer x : input) {
            i += x;
        }
        i /= input.size();

        int fuel = 0;
        for (Integer x : input) {
            int diff = Math.abs(i - x);
            fuel += (diff * (diff + 1)) / 2;
        }
        System.out.println(fuel);
    }
}
