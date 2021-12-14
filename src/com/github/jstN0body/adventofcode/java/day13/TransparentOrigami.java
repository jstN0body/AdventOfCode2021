package com.github.jstN0body.adventofcode.java.day13;

import java.awt.*;
import java.util.ArrayList;

public class TransparentOrigami {
    public static void main(String[] args) {
        int[][] grid;
        ArrayList<Point> points = new ArrayList<>();
        ArrayList<FoldLine> lines = new ArrayList<>();
        for (String s : args) {
            if (Character.isDigit(s.charAt(0))) {
                String[] point = s.split(",");
                points.add(new Point(Integer.parseInt(point[0]), Integer.parseInt(point[1])));
            } else if (s.charAt(0) == 'x' || s.charAt(0) == 'y') {
                String[] foldLine = s.split("=");
                lines.add(new FoldLine(Integer.parseInt(foldLine[1]), s.charAt(0) == 'x' ? 0 : 1));
            }
        }

        int x = 0, y = 0;
        for (Point p : points) {
            if (p.x > x) x = p.x;
            if (p.y > y) y = p.y;
        }
        grid = new int[y + 1][x + 1];
        for (Point p : points) {
            grid[p.y ][p.x] = 1;
        }

        part1(grid, lines.get(0));
        System.out.println();
        part2(grid, lines);
    }

    public static void part1(int[][] grid, FoldLine foldLine) {
        if (foldLine.type == 0) {
            for (int y = 0; y < grid.length; y++) {
                for (int x = foldLine.value; x < grid[y].length; x++) {
                    if (grid[y][x] == 1) {
                        int i = foldLine.value - (x - foldLine.value);
                        grid[y][x] = 0;
                        grid[y][i] = 1;
                    }
                }
            }
        } else {
            for (int y = foldLine.value; y < grid.length; y++) {
                for (int x = 0; x < grid[y].length; x++) {
                    if (grid[y][x] == 1) {
                        int i = foldLine.value - (y - foldLine.value);
                        grid[y][x] = 0;
                        grid[i][x] = 1;
                    }
                }
            }
        }
        System.out.println(countPoints(grid));
    }

    public static void part2(int[][] grid, ArrayList<FoldLine> foldLines) {
        for (FoldLine foldLine : foldLines) {
            if (foldLine.type == 0) {
                for (int y = 0; y < grid.length; y++) {
                    for (int x = foldLine.value; x < grid[y].length; x++) {
                        if (grid[y][x] == 1) {
                            int i = foldLine.value - (x - foldLine.value);
                            grid[y][x] = 0;
                            grid[y][i] = 1;
                        }
                    }
                }
            } else {
                for (int y = foldLine.value; y < grid.length; y++) {
                    for (int x = 0; x < grid[y].length; x++) {
                        if (grid[y][x] == 1) {
                            int i = foldLine.value - (y - foldLine.value);
                            grid[y][x] = 0;
                            grid[i][x] = 1;
                        }
                    }
                }
            }
        }
        printGrid(grid);
    }

    public static int countPoints(int[][] grid) {
        int count = 0;
        for (int[] ar : grid) {
            for (int i : ar) {
                if (i == 1) count++;
            }
        }
        return count;
    }

    public static void printGrid(int[][] grid) {
        for (int y = 0; y < 6; y++) {
            System.out.println();
            for (int x = 0; x < 39; x++) {
                System.out.print(grid[y][x] == 1 ? '#' : ' ');
            }
        }
    }
}

class FoldLine {

    public final int value;

    // 0 = vertical, 1 = horizontal
    public final int type;

    public FoldLine(int value, int type) {
        this.value = value;
        this.type = type;
    }
}