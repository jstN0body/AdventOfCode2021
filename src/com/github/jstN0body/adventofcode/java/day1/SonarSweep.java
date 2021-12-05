package com.github.jstN0body.adventofcode.java.day1;

import java.util.ArrayList;
import java.util.List;

public class SonarSweep {
    public static void main(String[] args) {
        List<Integer> data = new ArrayList<>();
        for (String s : args) {
            data.add(Integer.parseInt(s));
        }

        int increases = 0;
        for (int i = 1; i < data.size(); i++) {
            if (data.get(i) > data.get(i-1)) increases++;
        }
        System.out.println(increases + " increases\n");

        int sumIncreases = 0;
        for (int i = 1; i < data.size(); i++) {
            if (!((i + 2) < data.size())) continue;
            int prevSum = data.get(i - 1) + data.get(i) + data.get(i + 1);
            int currentSum = data.get(i) + data.get(i + 1) + data.get(i + 2);

            if (currentSum > prevSum) sumIncreases++;
        }
        System.out.println(sumIncreases + " sum increases");
    }
}
