package com.github.jstN0body.adventofcode.java.day14;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ExtendedPolymerization {
    public static void main(String[] args) {
        ArrayList<InsertionRule> insertionRules = new ArrayList<>();

        String polymerTemplate = args[0];
        ArrayList<Character> polymerChars = new ArrayList<>();
        for (char c : args[0].toCharArray()) {
            polymerChars.add(c);
        }

        for (int i = 1; i < args.length; i += 3) {
            String pair = args[i];
            Character insert = args[i + 2].charAt(0);
            insertionRules.add(new InsertionRule(pair, insert));
        }

        part1(new Polymer(polymerTemplate, polymerChars), insertionRules);
        //part2(new Polymer(polymerTemplate, polymerChars), insertionRules);
    }

    public static Polymer testInsertionRules(Polymer polymer, ArrayList<InsertionRule> rules) {
        ArrayList<Character> polymerChars = polymer.polymerChars;
        String polymerStr = polymer.polymerStr;
        String updatedString = "";

        for (int i = 1; i < polymerStr.length(); i++) {
            String sub = polymerStr.substring(i-1, i+1);
            for (InsertionRule rule : rules) {
                if (sub.equals(rule.pair)) {
                    int index = polymerChars.size() - ((polymerStr.length() - 1) - i) - 1;
                    polymerChars.add(index, rule.insert);
                }
            }
        }
        for (Character c : polymerChars) {
            updatedString = updatedString.concat(c.toString());
        }

        return new Polymer(updatedString, polymerChars);
    }

    public static void part1(Polymer polymer, ArrayList<InsertionRule> insertionRules) {
        for (int i = 0; i < 10; i++) {
            polymer = testInsertionRules(polymer, insertionRules);
            //System.out.println(polymer.polymerStr);
        }

        LinkedHashMap<Character, Integer> amounts = new LinkedHashMap<>();
        for (Character c : polymer.polymerChars) {
            amounts.put(c, amounts.getOrDefault(c, 0) + 1);
        }

        List<Integer> sorted = amounts.values().stream().sorted().collect(Collectors.toList());
        System.out.println(sorted.get(0));
        System.out.println(sorted.get(sorted.size() - 1));
        System.out.println(sorted.get(sorted.size() - 1) - sorted.get(0));
    }

    public static void part2(Polymer polymer, ArrayList<InsertionRule> insertionRules) { // yeah idk how to do this
        for (int i = 0; i < 40; i++) {
            polymer = testInsertionRules(polymer, insertionRules);
            System.out.println(i);
        }

        LinkedHashMap<Character, Long> amounts = new LinkedHashMap<>();
        for (Character c : polymer.polymerChars) {
            amounts.put(c, amounts.getOrDefault(c, 0L) + 1);
        }

        List<Long> sorted = amounts.values().stream().sorted().collect(Collectors.toList());
        System.out.println(sorted.get(0));
        System.out.println(sorted.get(sorted.size() - 1));
        System.out.println(sorted.get(sorted.size() - 1) - sorted.get(0));
    }
}

class Polymer {
    public final String polymerStr;
    public final ArrayList<Character> polymerChars;

    public Polymer(String polymerStr, ArrayList<Character> polymerChars) {
        this.polymerStr = polymerStr;
        this.polymerChars = polymerChars;
    }
}

class InsertionRule {

    public final String pair;
    public final Character insert;

    public InsertionRule(String pair, Character insert) {
        this.pair = pair;
        this.insert = insert;
    }
}
