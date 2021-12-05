package com.github.jstN0body.adventofcode.day4;

import java.util.ArrayList;
import java.util.List;

public class BingoCard {
    public Integer[] numbers;
    public List<Integer> markedNumbers = new ArrayList<>();

    public int finishingNumber = -1;
    boolean finished = false;

    List<List<Integer>> columns = new ArrayList<>();
    List<List<Integer>> rows = new ArrayList<>();
    List<List<Integer>> diagonals = new ArrayList<>();

    public BingoCard(Integer... numbers) {
        this.numbers = numbers;

        for (int i = 0; i < 5; i++) {
            // add the starting 5 rows and columns, as well as the 2 diagonals.
            rows.add(new ArrayList<>());
            columns.add(new ArrayList<>());
            if (i < 2) diagonals.add(new ArrayList<>());
        }

        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                rows.get(y).add(numbers[x + (y * 5)]);
                columns.get(x).add(numbers[x + (y * 5)]);

                if (x == y) diagonals.get(0).add(numbers[x + (y * 5)]);
                if ((x + y == 5)) diagonals.get(1).add(numbers[x + (y * 5)]);
            }
        }
    }

    public void markNumber(int num) {
        if (!markedNumbers.contains(num) && !finished) markedNumbers.add(num);
    }

    public boolean check(int num) {
        boolean value = false;
        for (List<Integer> column : columns) {
            if (markedNumbers.containsAll(column)) value = true;
        }
        for (List<Integer> row : rows) {
            if (markedNumbers.containsAll(row)) value = true;
        }
        for (List<Integer> diagonal : diagonals) {
            if (markedNumbers.containsAll(diagonal)) value = true;
        }

        if (value && !finished) {
            finishingNumber = num;
            finished = true;
        }

        return value;
    }

    public int score() {
        List<Integer> unmarkedNums = new ArrayList<>();
        for (Integer i : numbers) {
            if (!markedNumbers.contains(i)) unmarkedNums.add(i);
        }
        int score = 0;
        for (Integer i : unmarkedNums) {
            score += i;
        }
        score *= finishingNumber;

        return score < 1 ? 0 : score;
    }
}
