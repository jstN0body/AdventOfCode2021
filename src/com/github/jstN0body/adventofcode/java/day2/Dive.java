package com.github.jstN0body.adventofcode.java.day2;

public class Dive {
    public static void main(String[] args) {
        int horizontalLoc = 0, depth = 0, aim = 0;
        for (int i = 1; i < args.length; i+= 2) {
            switch (args[i-1]) {
                case "forward":
                    horizontalLoc += Integer.parseInt(args[i]);
                    break;
                case "up":
                    depth -= Integer.parseInt(args[i]);
                    break;
                case "down":
                    depth += Integer.parseInt(args[i]);
            }
        }

        System.out.println(horizontalLoc + " horiz.");
        System.out.println(depth + " depth");
        System.out.println(horizontalLoc * depth + " final answer\n");

        horizontalLoc = 0;
        depth = 0;
        for (int i = 1; i < args.length; i+= 2) {
            switch (args[i-1]) {
                case "forward":
                    horizontalLoc += Integer.parseInt(args[i]);
                    depth += Integer.parseInt(args[i]) * aim;
                    break;
                case "up":
                    aim -= Integer.parseInt(args[i]);
                    break;
                case "down":
                    aim += Integer.parseInt(args[i]);
            }
        }

        System.out.println(horizontalLoc + " horiz.");
        System.out.println(depth + " depth");
        System.out.println(horizontalLoc * depth + " final answer");
    }
}
