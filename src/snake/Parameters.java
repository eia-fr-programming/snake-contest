package snake;

import java.util.ArrayList;
import java.util.Random;
import snake.util.Direction;
import snake.util.Point;

/**
 * Various parameters for the {@link Game}.
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


public class Parameters {
    /** List of classes to use as Strategies */
    ArrayList<Class> strategies;
    /** List of corresponding starting Positions */
    ArrayList<Point> startPoints;
    /** List of corresponding starting Direction */
    ArrayList<Direction> startDirections;
    
    /** Width of the playing field */
    int width=40;
    /** Height of the playing field */
    int height=30;
    
    /** Field (including walls etc...) */
    Field field;
    
    /** How many Goodies to play (-1): Last snake standing */
    int totalGoodies=100;
    
    /** How many Goodies simultanously on the board? */
    int sameTimeGoodies=1;
    
    /** How many points for eating a goodie? */
    int goodiePoints = 300;
    
    /** How many points for surviving a game? */
    int survivingPoints = 500;
    
    /** How many points of "killing" another snake? */
    int killingPoints = 1000;
    
    /** How many points for being killed? */
    int beingKilledPoints = -500;
    
    /** How many points for suicide? */
    int suicidePoints = -1000;
    
    /** Unique random generator for all */
    Random random;
    
    /** Minimum distance from snake heads for goodies to appear. */
    int mindistance=5;
    
    /** How much a snake increases when eating a goodie */
    int goodieIncrease=5;
    
    /** Miliseconds of sleep between two steps */
    int miliSleep = 30;
    
    /** Miliseconds allowed to a strategy */
    int miliStrategy = 20;
    
    /** Maximum number of steps until game over */
    int maxIteration = 3000;
    
    /** Default constructor */
    Parameters() {
        strategies = new ArrayList<Class>();
        startPoints = new ArrayList<Point>();
        startDirections = new ArrayList<Direction>();
        random = new Random();
        setField(new Field(width, height));
        
        
        startPoints.add(new Point(10,10));
        startDirections.add(Direction.UP);
        
        startPoints.add(new Point(20,10));
        startDirections.add(Direction.DOWN);
        
        startPoints.add(new Point(15,5));
        startDirections.add(Direction.RIGHT);
        
        startPoints.add(new Point(15,15));
        startDirections.add(Direction.LEFT);
        
    }
    
    private void setField(Field f) {
        this.field = f;        
        width = f.getWidth();
        height = f.getHeight();
    }
        
    /** Copy-constructor */
    Parameters(Parameters p) {
        width = p.width;
        height = p.height;
        field = new Field(p.field);
        totalGoodies = p.totalGoodies;
        sameTimeGoodies = p.sameTimeGoodies;
        random = p.random;
        
        strategies = new ArrayList<Class>();
        startPoints = new ArrayList<Point>();
        
        for (Point pt : p.startPoints) {
            startPoints.add(new Point(pt));
        }
        for (Class s : p.strategies) {
            strategies.add(s);
        }
        for (Direction d : p.startDirections) {
            startDirections.add(d);
        }
    }
}
