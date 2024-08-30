import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * find all the line segments that contains four points
 */
public class BruteCollinearPoints {

    private final ArrayList<LineSegment> lineSegments;

    /**
     * @param points an array contains four points
     * time complexity: O(N^4)
     */
    public BruteCollinearPoints(Point[] points) {
        if (!checkInput(points)) {
            throw new IllegalArgumentException();
        }

        this.lineSegments = new ArrayList<>();

        int length = points.length;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                for (int k = j + 1; k < length; k++) {
                    for (int m = k + 1; m < length; m++) {
                        Point a = points[i];
                        Point b = points[j];
                        Point c = points[k];
                        Point d = points[m];

                        if (areCollinear(a, b, c, d)) {
                            buildSegments(a, b, c, d);
                        }
                    }
                }
            }
        }
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

    /**
     * check the input
     * input == null / point == null / points repeated
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

        Point[] temp = points.clone();
        boolean result = true;
        for (Point p: points) {
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
     * build segments for the four points
     */
    private void buildSegments(Point a, Point b, Point c, Point d) {
        Point loPoint = a;
        Point hiPoint = a;
        Point[] others = new Point[]{b, c, d};
        for (Point p: others) {
            if (loPoint.compareTo(p) > 0) {
                loPoint = p;
            }
            else if (hiPoint.compareTo(p) < 0) {
                hiPoint = p;
            }
        }
        this.lineSegments.add(new LineSegment(loPoint, hiPoint));
    }


    /**
     * check if the four points are collinear
     */
    private boolean areCollinear(Point a, Point b, Point c, Point d) {
        double slopeAB = a.slopeTo(b);
        double slopeAC = a.slopeTo(c);
        double slopeAD = a.slopeTo(d);
        return slopeAB == slopeAC && slopeAB == slopeAD;
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
