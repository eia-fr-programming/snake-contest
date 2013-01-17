package snake;

import snake.util.Direction;
import snake.util.Point;

/**
 * Represents a snake.
 * 
 * Knows about the position, the direction, its index and if it is alive or not.
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

public class Snake {
    
    private Point position;
    private int length;
    private Direction lookTowards;
    private boolean alive=true;

    String reasonOfDeath="alive";
    
    /**
     * Index of this snake
     */
    int index;
    
    /**
     * Place a snake at Point start, looking in Direction lookTowards with given length
     * @param start
     * @param length 
     */
    
    Snake(Point start, Direction lookTowards, int length, int index) {
        position = new Point(start);
        this.lookTowards = lookTowards;
        this.length = length;
        this.index = index;
    }
    
    /**
     * Get the position as a {@link Point} of the snake's head.
     * @return Position of the snake's head
     */
    public Point getHeadPosition() {
        return new Point(position);
    }
    
    /**
     * Length of the snake
     * @return length
     */
    public int getLength() {
        return length;
    }
    
    /**
     * Get this {@link Snake}'s index
     */
    public int getIndex(){ 
        return index;
    }
    
    /**
     * Direction towards which the snake is facing
     * @return Direction
     */
    public Direction getDirection() {
        return lookTowards;
    }

    /**
     * Sets the heading of the snake
     * @param direction 
     */
    void setDirection(Direction direction) {
        lookTowards = direction;
    }
    

    /**
     * Advances one step.
     */
    void updatePosition() {
        if (alive) {
            position.move(lookTowards);
        }
    }
   
    /**
     * R.I.P.
     */
    void die() {
        alive = false;
    }
    
    void die(String reason) {
        alive = false;
        this.reasonOfDeath = reason;
    }
    
    /**
     * 
     * @return true if Snake is alive, false otherwise
     */
    boolean isAlive() {
        return alive;
    }
    
    /**
     * Increase length
     * @param inc number of segments to increase the length by
     */
    void increaseLength(int inc) {
        length+=inc;
    }
}
