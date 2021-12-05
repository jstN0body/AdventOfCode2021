package com.github.jstN0body.adventofcode.day4;

import java.util.ArrayList;
import java.util.List;

public class GiantSquid {
    public static void main(String[] args) {
        List<BingoCard> cards = new ArrayList<>();
        for (int i = 1; i < args.length; i += 25) {
            List<Integer> nums = new ArrayList<>();
            for (int x = 0; x < 25; x++) {
                int index = i + x;
                nums.add(Integer.parseInt(args[index]));
            }
            cards.add(new BingoCard(nums.toArray(new Integer[0])));
        }

        String nums = args[0];
        List<Integer> calledNumbers = new ArrayList<>();
        int lastComma = -1;
        for (int i = 0; i < nums.length(); i++) {
            if (nums.charAt(i) == ',') {
                int num = Integer.parseInt(nums.substring(lastComma+1, i));
                calledNumbers.add(num);
                lastComma = i;
            }
        }

        List<BingoCard> finishedCards = new ArrayList<>();
        for (int i : calledNumbers) {
            for (BingoCard card : cards) {
                card.markNumber(i);
                if (card.check(i) && !finishedCards.contains(card)) {
                    finishedCards.add(card);
                }
            }
        }

        BingoCard card = finishedCards.get(finishedCards.size()-1);
        System.out.println("Final card finished with a score of: " + card.score());
    }
}
