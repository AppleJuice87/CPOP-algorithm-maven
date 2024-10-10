package algorithm.service;

import algorithm.model.Pair;
import algorithm.model.Point;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PointHandler {

    List<Point> points;

    public PointHandler() {
        this.points = new ArrayList<>();
    }

    public PointHandler(List<Point> points) {
        this.points = points;
    }

    public int size() {
        return points.size();
    }

    public Point get(int i) {
        return points.get(i);
    }

    public void add(Point point) {
        this.points.add(point);
    }

    public PointHandler getLeftPart() {
        if (this.size() % 2 == 0) {
            return new PointHandler(points.subList(0, this.size() / 2));
        } else {
            return new PointHandler(points.subList(0, (this.size() / 2) + 1));
        }
    }

    public PointHandler getRightPart() {
        if (this.size() % 2 == 0) {
            return new PointHandler(points.subList(this.size() / 2, this.size()));
        } else {
            return new PointHandler(points.subList((this.size() / 2) + 1, this.size()));
        }
    }

    public static double getDistance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2)
                + Math.pow(p1.getY() - p2.getY(), 2));
    }

    /**
     * point 들이 담긴 배열을 받아서 최근접점 쌍을 반환하는 메소드.
     *
     * @return closest pair
     */
    public Pair getClosestPair() {
        // 가독성 좋은 코드.
        int size = points.size();
        Pair closestPair = new Pair(points.get(0), points.get(1));

        if (size == 2) {
            return closestPair;
        }

        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                Pair tempPair = new Pair(points.get(i), points.get(j));

                if (closestPair.getDistance() > tempPair.getDistance()) {
                    closestPair = tempPair;
                }
            }
        }

        return closestPair;

        // 공간복잡도 낮은 코드.
//        int size = points.size();
//        Point index = null;
//        double dist, minDist = PointHandeler.getDistance(points.get(0), points.get(1));
//
//        for (int i = 0; i < size - 1; i++) {
//            for (int j = i + 1; j < size; j++) {
//                dist = PointHandeler.getDistance(points.get(i), points.get(j));
//                if (minDist > dist) {
//                    minDist = dist;
//                    index  = new Point(i, j);
//                }
//            }
//        }
//
//        return new Pair(points.get(index.getX()), points.get(index.getY()));
    }

    public PointHandler getCenterList(double dist) {
        double center;
        int size = points.size();

        // 사이즈가 홀수면
        if (points.size() % 2 == 1) {
            center = (double) points.get(size / 2).getX();
        } else {
            center = (double) (points.get(size / 2).getX() + points.get(size / 2 - 1).getX()) / 2;
        }

        List<Point> centerList = new ArrayList<>();

        for (Point pnt : points) {
            if (pnt.getX() >= center - dist && pnt.getX() <= center + dist) {
                centerList.add(pnt);
            }
        }

        return new PointHandler(centerList);
    }

    public void sort() {
        points.sort(Comparator.comparingInt(Point::getX));
    }

    @Override
    public String toString() {
        return "PointHandler{" +
                "points=" + points +
                '}';
    }
}
