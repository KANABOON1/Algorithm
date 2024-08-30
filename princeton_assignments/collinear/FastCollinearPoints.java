import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {

    private final ArrayList<LineSegment> lineSegments;
    /**
     * finds all line segments containing 4 or more points
     */
    public FastCollinearPoints(Point[] points) {
        if (!checkInput(points)) {
            throw new IllegalArgumentException();
        }
        int threshold = 4;
        lineSegments = new ArrayList<>();

        Point[] temp = points.clone();
        for (Point p: points) {
            Comparator<Point> comparator = p.slopeOrder();
            Arrays.sort(temp, comparator);    // in-place

            int lo = 0;
            int hi = 1;
            for (hi = 1; hi < temp.length; hi++) {
                if (p.slopeTo(temp[lo]) != p.slopeTo(temp[hi])) {  // two different groups
                    if (hi - lo >= threshold - 1) {
                        buildSegments(temp, p, lo, hi - 1);
                    }
                    lo = hi;
                }
            }
            // deal with the last few points
            if (hi - lo >= threshold - 1) {
                buildSegments(temp, p, lo, hi - 1);
            }
        }
    }

    /**
     * build a segment using a range of points
     * time complexity: O(C)
     */
    private void buildSegments(Point[] points, Point originPoint, int lo, int hi) {
        Point lowPoint = originPoint;
        Point highPoint = originPoint;
        for (int i = lo; i < hi + 1; i++) {
            Point p = points[i];
            if (p.compareTo(lowPoint) < 0) {
                lowPoint = p;
            }
            else if (p.compareTo(highPoint) > 0) {
                highPoint = p;
            }
        }

        // note: avoid redundant line segment
        // 指定在所有可能的情况中只有当前点是最低点的时候才能够加入，否则会出现大量的重复
        if (originPoint.compareTo(lowPoint) == 0) {
            this.lineSegments.add(new LineSegment(lowPoint, highPoint));
        }
    }

    /**
     * check the input
     * time complexity: O(N * NlogN)
     */
    private boolean checkInput(Point[] points) {
        if (points == null) {
            return false;
        }
        for (Point p: points) {
            if (p == null) {
                return false;
            }
        }

        boolean result = true;
        Point[] temp = points.clone();
        for (Point p: points) {
            if (p == null) {
                result = false;
                break;
            }
            Arrays.sort(temp, p.slopeOrder());
            if (points.length > 1) {
                if (p.slopeTo(temp[0]) == p.slopeTo(temp[1])) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * the number of line segments
     */
    public int numberOfSegments() {
        return this.lineSegments.size();
    }

    /**
     * the line segments
     */
    public LineSegment[] segments() {
        return this.lineSegments.toArray(new LineSegment[0]);
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
