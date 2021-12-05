package com.github.jstN0body.adventofcode.java.day5;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HydrothermalVenture {
    public static void main(String[] args) {
        List<LineSegment> lineSegments = parseData(args);
        HashMap<Point, Integer> allPoints = new HashMap<>();

        for (LineSegment segment : lineSegments) {
            for (Point point : segment.pointsAlongLine()) {
                if (allPoints.containsKey(point)) {
                    allPoints.put(point, allPoints.get(point) + 1);
                } else {
                    allPoints.put(point, 1);
                }
            }
        }

        int total = 0;
        for (Integer i : allPoints.values()) {
            if (i > 1) total++;
        }
        System.out.println(total);
    }

    static List<LineSegment> parseData(String[] args) {
        List<LineSegment> segments = new ArrayList<>();
        for (int i = 0; i < args.length; i += 3) {
            List<Point> points = new ArrayList<>();
            int commaIndex = 0;
            for (int x = 0; x < 3; x++) {
                if (x == 1) continue;
                String point = args[i+x];
                for (int y = 0; y < point.length(); y++) {
                    if (point.charAt(y) == ',') {
                        commaIndex = y;
                        break;
                    }
                }
                int xVal = Integer.parseInt(point.substring(0, commaIndex));
                int yVal = Integer.parseInt(point.substring(commaIndex+1));
                points.add(new Point(xVal, yVal));
            }
            LineSegment segment = new LineSegment(points.get(0), points.get(1));
            segments.add(segment);
        }
        return segments;
    }
}