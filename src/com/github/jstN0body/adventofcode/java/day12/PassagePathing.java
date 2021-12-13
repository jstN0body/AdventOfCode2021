package com.github.jstN0body.adventofcode.java.day12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PassagePathing {

    public static void main(String[] args) {
        List<String> caves = new ArrayList<>();
        for (String s : args) {
            String[] ar = s.split("-");
            caves.addAll(Arrays.asList(ar));
        }
        for (String s : caves) {
            new Cave(s, Character.isUpperCase(s.charAt(0)) ? CaveType.LARGE : CaveType.SMALL);
        }
        for (String s : args) {
            String[] ar = s.split("-");
            Cave.ALL_CAVES.get(ar[0]).destinations.add(Cave.ALL_CAVES.get(ar[1]));
            Cave.ALL_CAVES.get(ar[1]).destinations.add(Cave.ALL_CAVES.get(ar[0]));
        }

        Cave start = Cave.ALL_CAVES.get("start");
        Cave end =  Cave.ALL_CAVES.get("end");
        Pathfinder p = new Pathfinder(start, end);
        System.out.print("\n" + p.allPaths.size());
    }
}