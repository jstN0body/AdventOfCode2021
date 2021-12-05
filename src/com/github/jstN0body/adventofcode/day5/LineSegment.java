package com.github.jstN0body.adventofcode.day5;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class LineSegment {

    private final Point startPoint;
    private final Point endPoint;

    public LineSegment(Point start, Point finish) {
        startPoint = start;
        endPoint = finish;
    }

    public boolean isStraight() {
        return startPoint.x == endPoint.x || startPoint.y == endPoint.y;
    }

    public List<Point> pointsAlongLine() {
        List<Point> points = new ArrayList<>();
        if (startPoint.x == endPoint.x) {
            Point startingPoint = startPoint.y < endPoint.y ? startPoint : endPoint;
            Point endingPoint = startPoint.y < endPoint.y ? endPoint : startPoint;

            for (int i = 0; i <= (endingPoint.y - startingPoint.y); i++) {
                points.add(new Point(startingPoint.x, startingPoint.y + i));
            }
        } else if (startPoint.y == endPoint.y) {
            Point startingPoint = startPoint.x < endPoint.x ? startPoint : endPoint;
            Point endingPoint = startPoint.x < endPoint.x ? endPoint : startPoint;

            for (int i = 0; i <= (endingPoint.x - startingPoint.x); i++) {
                points.add(new Point(startingPoint.x + i, startingPoint.y));
            }
        }

        return points;
    }
}
