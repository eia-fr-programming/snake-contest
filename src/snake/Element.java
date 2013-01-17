package snake;

import snake.util.Direction;

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

public class Element {



    
    /**
     * Type of the Field
     */
    public enum ElementType {
        /**
         * Empty field
         */
        EMPTY,
        /**
         * A field containing a goodie
         */
        GOODIE,
        /**
         * A field containing a wall
         */
        OBSTACLE,
        /**
         * A field containing a part of a snake
         */
        SNAKE};
    
    ElementType elementType;
    
    /**
     * Element has to be redrawn
     */
    boolean dirty = true;
    
    /**
     * How long this element stays on the field (-1 means for ever)
     */
    int timer;
    
    /**
     * Snake related stuff
     */
    int snake;
    Direction inDir;
    Direction outDir;
    boolean head=false;
    boolean tail=false;
    
    
    
    Element() {
        elementType = ElementType.EMPTY;
        timer = -1;
        dirty = true;
        snake = -1;
    }
    
    Element(Element e) {
        elementType = e.elementType;
        timer = e.timer;
        snake = e.snake;
        inDir = e.inDir;
        outDir = e.outDir;
        head = e.head;
        dirty = e.dirty;
    }
    
    void setSnakeHead(int index, Direction dir, int length) {
        elementType = ElementType.SNAKE;
        timer = length;
        inDir =dir;
        outDir = dir;
        head = true;
        dirty = true;
        snake = index;
        tail = false;
    }
    
    void setSnakeHead(Snake s) {
        setSnakeHead(s.index, s.getDirection(), s.getLength());
    }

    
    /**
     * Transform the head of a snake into body (when it advances in direction dir)
     * @param dir 
     */
    void headAway(Direction dir) {
        head = false;
        outDir = dir;
        dirty = true;
        tail = false;
    }
    
    void setWall() {
        elementType = ElementType.OBSTACLE;
        head = false;
        timer = -1;
        dirty = true;
        tail = false;
    }
    
    void setGoodie() {
        elementType = ElementType.GOODIE;
        timer = -1;
        dirty = true;
        head = false;
        tail = false;
    }

    
    void update() {
        if (timer>0) {
            timer--;
            if (timer==1 && elementType==ElementType.SNAKE) {
                tail = true;
                dirty = true;
            }
            if (timer==0) {
                elementType = ElementType.EMPTY;
                tail = false;
                head = false;
                dirty = true;
                snake = -1;
            }
        }
    }
    
    void empty() {
        elementType = ElementType.EMPTY;
        head = false;
        timer = -1;
        dirty = true;
        tail = false;
        snake = -1;
    }
    
    
    /**
     * Tests, if one can walk on this field (EMPTY or GOODIE)
     * @return true if one can walk on this field
     */
    public boolean canWalkOn() {
        return (elementType == ElementType.EMPTY || elementType == ElementType.GOODIE);
    }

    /**
     * Get the Type of this Element
     * @return
     */
    public ElementType getElementType() {
        return elementType;
    }

    /**
     * Checks if this Element is the head of a snake
     * @return true if it is a snake's head
     */
    public boolean isHead() {
        return head;
    }
    
    /**
     * Checks if this Element is the tail of a snake
     * @return true if it is a snake's tail
     */
    public boolean isTail() {
        return tail;
    }


    /**
     * Return the index of the snake on this field (or some non-sense value if no snake is on this field).
     * @return index of the snake (or non-sense if there is no snake)
     */
    public int getSnake() {
        return snake;
    }

    /**
     * Number of steps this field will remain (only useful if it is a snake's part)
     * @return positive value, if it is part of a snake
     */
    public int getTimer() {
        return timer;
    }
    
    /**
     * Get the direction towards which the snake faced when entering this {@link Element}.
     * This only make sense when this Element is actually part of a Snake, i.e. if the {@link ElementType} is {@code SNAKE}
     * @return 
     */
    public Direction getInDir() {
        return inDir;
    }
        
    /**
     * Get the direction in which the snake left this {@link Element}.
     * This only make sense when this Element is actually part of a Snake, i.e. if the {@link ElementType} is {@code SNAKE}
     * @return 
     */
    public Direction getOutDir() {
        return outDir;
    }
    
    
}
