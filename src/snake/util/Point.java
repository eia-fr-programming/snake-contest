package snake.util;

/**
 * This class represents a position on the game field. 
 * It provides methods to move about.
 * 
 */

/**
 * You may use this code freely for any purposes, as long as this
 * notice remains.
 * 
 * This code was written for and used in the 1st-year Java-Lecture in 2012 at the
 * Informatics Department of the University of Fribourg, Switzerland.
 * 
 * @author ivo.bloechliger@unifr.ch
 * 
 */


public class Point {
    /** x-coordinate of the position */
    public int x;
    /** y-coorinate of the position */
    public int y;
    
    /**
     * Defaultconstructor for a Point at (0,0)
     */
    public Point() {
        x=0;
        y=0;
    }
    
    /**
     * Copyconstructor
     * @param p 
     */
    public Point(Point p) {
        x=p.x;
        y=p.y;
    }
    
    /**
     * New point at (x,y)
     * @param x
     * @param y 
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Moves this point 1 unit in the given Direction
     * Note that this modifies this point, it does not make a new Point.
     * @param dir 
     */
    public void move(Direction dir) {
        x += dir.dx;
        y += dir.dy;
    }
    
    /**
     * A new point, moved 1 unit in the given Direction. This does not
     * change this Point, but makes a new one.
     * @param dir
     * @return new moved Point
     */
    public Point movedTo(Direction dir) {
        Point p = new Point(this);
        p.move(dir);
        return p;
    }
    
    /**
     * Does what it says ;-)
     */
    public boolean equals(Point p) {
        return (x==p.x && y==p.y);
    }
    
    /**
     * Manhattan-Metric (minimal number of steps to get from this Point
     * to the Point p, assuming there are no obstacles).
     * @param p Point to go to
     * @return distance from this Point to Point p
     */
    public int distance(Point p) {
        return Math.abs(x-p.x)+Math.abs(y-p.y);
    }
    
    @Override
    public String toString() {
        return "("+x+", "+y+")";
    }
    
}
