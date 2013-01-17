package snake.util;

/**
 * Directions ordered in the trigonometric sense (left, down right, up)
 * 
 * To loop through all directions use
 * <code><pre>
 *    for (Direction dir : Direction.values()) {
 *        // Do something with dir
 *    }
 * </pre></code>
 * 
 * You may also use a Direction directly with
 * <code><pre>
 *    Direction.RIGHT
 *    Direction.DOWN
 *    Direction.LEFT
 *    Direction.UP
 * </pre></code>
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

public enum Direction {
    RIGHT (1,0),
    DOWN (0,1),
    LEFT (-1,0),
    UP (0,-1);
    
    public final int dx;
    public final int dy;
    
    /**
     * Nobody's business, used merely for the initializing of the above constants.
     * @param dx
     * @param dy 
     */
    private Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }
    
    /**
     * Get the opposite Direction of this Direction
     */
    public Direction opposite() {
        return values()[(this.ordinal()+2)%4];
    }
    
    /**
     * Get the Direction by turning this Direction about 90 degrees to the right
     */
    public Direction right() {
        return values()[(this.ordinal()+1)%4];        
    }
    
    /**
     * Get the Direction by turning this Direction about 90 degrees to the left
     */
    public Direction left() {
        return values()[(this.ordinal()+3)%4];                
    }
    
    /**
     * Get the angles 0,1,2,3 for right, down, left, up
     */
    public int angle() {
        return this.ordinal();
    }
}
