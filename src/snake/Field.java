package snake;

import java.util.ArrayList;
import java.util.Arrays;
import snake.util.Direction;
import snake.util.Point;

/**
 * A playing Field, basically a two-dimensional Array of {@link Element}s.
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


public class Field {
    

    /**
     * Width
     */
    private int width;
    
    /**
     * Height
     */
    private int height;
    
    /** Elements of this field */
    Element[][] elements;  // Not private, not protected and certainly not public. Only classes withing this package can access this member
    
    private ArrayList<Point> snakePositions;
    private ArrayList<Point> goodiePositions;
    
    /** Empty Field with walls all around */
    Field(int width, int height) {
        this.width = width;
        this.height = height;
        elements = new Element[width][height];
        for (int x=0; x<width; x++) {
            for (int y=0; y<height; y++) {
                elements[x][y] = new Element();
                if (x==0 || y==0 || x==width-1 || y==height-1) {
                    elements[x][y].setWall();
                }
            }
        }
        
    }
    
    Field(Field f) {
        width = f.width;
        height = f.height;
        elements = f.getElements();
    }
    
    /**
     * Test if a Point p is on the field
     * @param p
     * @return true if Point p is on field
     */
    public boolean isOnField(Point p) {
        return (p.x>=0 && p.y>=0 && p.x<width && p.y<height);
    }
    
    /** Getter
     * @return height of the field
     */
    public int getHeight() {
        return height;
    }

    /** Getter
     * @return Width of the field
     */
    public int getWidth() {
        return width;
    }

    void setSnakePositions(ArrayList<Point> snakePositions) {
        this.snakePositions = snakePositions;
    }

    void setGoodiePositions(ArrayList<Point> goodiePositions) {
        this.goodiePositions = goodiePositions;
    }
    
    
    
    /**
     * Get an {@link Element}. Make sure, that x,y are actually on the board!
     * @param x x-Koordinate 
     * @param y y-Koordinate
     * @return Copy of the actual {@link Element}
     */
    public Element getElementAt(int x, int y) {
        return new Element(elements[x][y]);
    }
    
    /**
     * Get an {@link Element}. Make sure, that x,y are actually on the board!
     * @param where 
     * @return Copy of the actual {@link Element}
     */
    public Element getElementAt(Point where) {
        return getElementAt(where.x, where.y);
    }
    
    
    /**
     * Get a deep copy of the whole field. Use this rather than making many method calls to {@code getElement()}.
     * @return A deep copy Array of the whole field
     */
    public Element[][] getElements() {
        Element[][] copy = new Element[width][height];
        for (int x=0; x<width; x++) {
            for (int y=0; y<height; y++) {
                copy[x][y] = new Element(elements[x][y]);
            }
        }
        return copy;
    }
    
    /**
     * ArrayList of head positions of alive snakes (contains null otherwise!)
     * @return new ArrayList with Position (or null when the corresponding snake is dead).
     */
    public ArrayList<Point> getSnakePositions() {
        ArrayList<Point> copy = new ArrayList<Point>();
        for (Point p : snakePositions) {
            if (p!=null) {
                copy.add(new Point(p));
            } else {
                copy.add(null);
            }
        }
        return copy;
    }
    
    
    /**
     * ArrayList of goodie positions
     * @return new ArrayList with the Positions of the goodies
     */
    public ArrayList<Point> getGoodiePositions() {
        ArrayList<Point> copy = new ArrayList<Point>();
        for (Point p : goodiePositions) {
            if (p!=null) {
                copy.add(new Point(p));
            } else {
                copy.add(null);
            }
        }
        return copy;
    }
    
    /**
     * Tests, if one can walk on the Element at coordinates {@code where}
     * @param where Coordinates of the Element
     */
    public boolean canWalkOn(Point where) {
        return elements[where.x][where.y].canWalkOn();
    }
           
    void clearElement(Point where) {
        elements[where.x][where.y].empty();
    }
    
    /**
     * Check if the {@link Element} at point x,y is of {@code Element.ElementType.EMPTY}
     * @param x
     * @param y
     */
    public boolean isEmpty(int x, int y) {
        return (elements[x][y].elementType == Element.ElementType.EMPTY);
    }
    
    /**
     * Check if the {@link Element} at {@code where} is of {@code Element.ElementType.EMPTY}
     * @param where 
     */
    public boolean isEmpty(Point where) {
        return isEmpty(where.x, where.y);
    }

    void setGoodie(int x, int y) {
        elements[x][y].setGoodie();
    }

    
    void setGoodie(Point p) {
        setGoodie(p.x, p.y);
    }
    
    void setSnakeHead(Snake s) {
        elements[s.getHeadPosition().x][s.getHeadPosition().y].setSnakeHead(s);
    }

    void headAway(Point pos, Direction direction) {
        elements[pos.x][pos.y].headAway(direction);
    }

    void update() {
        for (Element[] col : elements) {
            for (Element e : col) {
                e.update();
            }
        }
    }

    /**
     * Check if there is a Goodie at position x,y.
     */    
    public boolean isGoodie(int x, int y) {
        return elements[x][y].elementType == Element.ElementType.GOODIE;
    }
    
    /**
     * Check if there is a Goodie at position p.
     */    
    public boolean isGoodie(Point p) {
        return isGoodie(p.x,p.y);
    }

}
