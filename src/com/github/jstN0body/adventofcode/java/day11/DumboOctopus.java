package com.github.jstN0body.adventofcode.java.day11;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DumboOctopus {
    public static void main(String[] args) {
        Octopus[][] octopi = new Octopus[10][10];
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                octopi[y][x] = new Octopus(x, y, Character.getNumericValue(args[y].charAt(x)));
            }
        }

        part1(octopi);
        part2(octopi);
    }

    public static void part1(Octopus[][] octopi) {
        int flashes = 0;
        for (int i = 0; i < 100; i++) {
            flashes += simulateDay(octopi);
        }
        System.out.println(flashes);
    }

    public static void part2(Octopus[][] octopi) {
        int day = 1;
        while (true) {
            simulateDay(octopi);
            boolean done = true;
            for (int y = 0; y < 10; y++) {
                for (int x = 0; x < 10; x++) {
                    if (octopi[y][x].energyLevel != 9) {
                        done = false;
                        break;
                    }
                }
            }
            day++;
            if (done) {
                System.out.println(day);
                break;
            }
        }
    }

    public static int simulateDay(Octopus[][] octopi) {
        for (Octopus[] ar : octopi) {
            for (Octopus o : ar) {
                o.flashed = false;
                o.update();
            }
        }
        int flashes = 0;
        while (toFlash(octopi)) {
            for (Octopus[] ar : octopi) {
                for (Octopus o : ar) {
                    if (o.energyLevel == 0 && !o.flashed) {
                        flashes++;
                        o.flashed = true;
                        for (Point p : o.neighbors()) {
                            if (octopi[p.y][p.x].energyLevel != 0) {
                                octopi[p.y][p.x].update();
                            }
                        }
                    }
                }
            }
        }
        return flashes;
    }

    public static boolean toFlash(Octopus[][] octopi) {
        for (Octopus[] ar : octopi) {
            for (Octopus o : ar) {
                if (o.energyLevel == 0 && !o.flashed) return true;
            }
        }
        return false;
    }
}

class Octopus {
    public final int x, y;
    public int energyLevel;

    public boolean flashed = false;

    public Octopus(int x, int y, int energyLevel) {
        this.x = x;
        this.y = y;
        this.energyLevel = energyLevel;
    }

    public void update() {
        energyLevel++;
        if (energyLevel > 9 && !flashed) {
            energyLevel = 0;
        }
    }

    public List<Point> neighbors() {
        List<Point> points = new ArrayList<>();
        for (int yOff = -1; yOff < 2; yOff++) {
            for (int xOff = -1; xOff < 2; xOff++) {
                if (xOff == 0 && yOff == 0) continue;
                if ((x + xOff) < 0 || (x + xOff) >= 10) continue;
                if ((y + yOff) < 0 || (y + yOff) >= 10) continue;

                points.add(new Point(x + xOff, y + yOff));
            }
        }
        return points;
    }
}
