package com.github.jstN0body.adventofcode.java.day9;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SmokeBasin {
    public static void main(String[] args) {
        int[][] heightMap = new int[args.length][args[0].length()];
        for (int y = 0; y < args.length; y++) {
            for (int x = 0; x < args[y].length(); x++) {
                int i = Character.getNumericValue(args[y].charAt(x));
                heightMap[y][x] = i;
            }
        }

        List<Point> lowPoints = part1(heightMap);
        part2(heightMap, lowPoints);
    }

    public static List<Point> part1(int[][] heightMap) {
        List<Point> lowPoints = new ArrayList<>();
        int total = 0;
        for (int y = 0; y < heightMap.length; y++) {
            for (int x = 0; x < heightMap[y].length; x++) {
                int i = heightMap[y][x];
                List<Integer> neighbors = getNeighbors(x, y, heightMap);

                if (lowPoint(i, neighbors)) {
                    total += (i + 1);
                    lowPoints.add(new Point(x, y, i));
                }
            }
        }
        System.out.println(total);
        return lowPoints;
    }

    public static boolean lowPoint(int point, List<Integer> neighbors) {
        for (int i : neighbors) {
            if (point >= i) return false;
        }
        return true;
    }

    public static void part2(int[][] heightMap, List<Point> lowPoints) {
        List<Integer> basinSizes = new ArrayList<>();

        for (Point point : lowPoints) {
            basinSizes.add(findBasinSize(point, heightMap));
        }

        basinSizes = basinSizes.stream().sorted().collect(Collectors.toList());
        System.out.println(basinSizes);
        int size = basinSizes.size();
        int total = basinSizes.get(size - 1) * basinSizes.get(size - 2) * basinSizes.get(size - 3);
        System.out.println(total);
    }

    public static int findBasinSize(Point lowPoint, int[][] heightMap) {
        int size = 1;
        List<Point> points = new ArrayList<>();
        points.add(lowPoint);

        for (int height = lowPoint.height + 1; height < 9; height++) {
            List<Point> temp = new ArrayList<>();
            for (Point point : points) {
                for (Point p : getNeighboringPoints(point, heightMap)) {
                    boolean contains = false;
                    for (Point p1 : temp) {
                        if (p.equals(p1)) {
                            contains = true;
                            break;
                        }
                    }
                    if (!contains && p.height == height) {
                        temp.add(p);
                    }
                }
            }
            size += temp.size();
            points = temp;
        }
        return size;
    }

    public static List<Integer> getNeighbors(int x, int y, int[][] heightMap) {
        List<Integer> neighbors = new ArrayList<>();
        if (y - 1 >= 0) neighbors.add(heightMap[y-1][x]);
        if (x - 1 >= 0) neighbors.add(heightMap[y][x-1]);
        if (y + 1 < heightMap.length) neighbors.add(heightMap[y+1][x]);
        if (x + 1 < heightMap[y].length) neighbors.add(heightMap[y][x+1]);
        return neighbors;
    }

    public static List<Point> getNeighboringPoints(Point p, int[][] heightMap) {
        List<Point> neighbors = new ArrayList<>();
        int x = p.x, y = p.y;
        if (y - 1 >= 0) neighbors.add(new Point(x, y-1, heightMap[y-1][x]));
        if (x - 1 >= 0) neighbors.add(new Point(x-1, y, heightMap[y][x-1]));
        if (y + 1 < heightMap.length) neighbors.add(new Point(x, y+1, heightMap[y+1][x]));
        if (x + 1 < heightMap[y].length) neighbors.add(new Point(x+1, y, heightMap[y][x+1]));
        return neighbors;
    }

}

class Point {
    public final int x;
    public final int y;
    public final int height;

    public Point(int x, int y, int height) {
        this.x = x;
        this.y = y;
        this.height = height;
    }

    public boolean equals(Point p) {
        return x == p.x && y == p.y;
    }
}