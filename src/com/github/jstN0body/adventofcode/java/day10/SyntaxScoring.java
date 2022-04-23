package com.github.jstN0body.adventofcode.java.day10;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SyntaxScoring {
    public static void main(String[] args) {
        part1(args);
        part2(args);
    }

    public static void part1(String[] args) {
        int total = 0;
        for (String s : args) {
            List<Character> targetChar = new ArrayList<>();
            for (char c : s.toCharArray()) {
                int index = targetChar.size() - 1;
                if (c == '{' || c == '[' || c == '(' || c == '<') {
                    targetChar.add(reverseBracket(c));
                } else if (targetChar.get(index) == c) {
                    targetChar.remove(index);
                } else {
                    total += missingBracketScore(c);
                    break;
                }
            }
        }
        System.out.println("Part 1: " + total);
    }

    public static void part2(String[] args) {
        List<String> incompleteLines = new ArrayList<>();
        for (String s : args) {
            incompleteLines.add(s);
            List<Character> targetChar = new ArrayList<>();
            for (char c : s.toCharArray()) {
                int index = targetChar.size() - 1;
                if (isOpeningBracket(c)) {
                    targetChar.add(reverseBracket(c));
                } else if (targetChar.get(index) == c) {
                    targetChar.remove(index);
                } else {
                    incompleteLines.remove(s);
                    break;
                }
            }
        }

        List<BigInteger> scores = new ArrayList<>();
        for (String line : incompleteLines) {
            List<Character> openSequence = new ArrayList<>();
            for (char c : line.toCharArray()) {
                if (isOpeningBracket(c)) {
                    openSequence.add(c);
                } else {
                    openSequence.remove(openSequence.size() - 1);
                }
            }
            Character[] closeSequence = new Character[openSequence.size()];
            for (int i = 0; i < closeSequence.length; i++) {
                int index = openSequence.size() - (i + 1);
                closeSequence[i] = reverseBracket(openSequence.get(index));
            }
            scores.add(score(closeSequence));
        }
        scores = scores.stream().sorted().collect(Collectors.toList());
        System.out.println("Part 2: " + scores.get(scores.size() / 2));
    }

    public static BigInteger score(Character[] sequence) {
        BigInteger total = BigInteger.ZERO;
        for (char c : sequence) {
            total = total.multiply(BigInteger.valueOf(5));
            total = total.add(BigInteger.valueOf(bracketScore(c)));
        }
        return total;
    }

    public static int bracketScore(char bracket) {
        switch (bracket) {
            case ')':
                return 1;
            case ']':
                return 2;
            case '}':
                return 3;
            case '>':
                return 4;
        }
        return -1;
    }

    public static boolean isOpeningBracket(char c) {
        return c == '{' || c == '[' || c == '(' || c == '<';
    }

    public static int missingBracketScore(char bracket) {
        switch (bracket) {
            case ')':
                return 3;
            case ']':
                return 57;
            case '}':
                return 1197;
            case '>':
                return 25137;
        }
        return -1;
    }

    public static char reverseBracket(char bracket) {
        switch (bracket) {
            case '{':
                return '}';
            case '[':
                return ']';
            case '(':
                return ')';
            case '<':
                return '>';
        }
        return ' ';
    }
}