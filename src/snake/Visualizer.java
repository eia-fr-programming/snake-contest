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


public interface Visualizer {
    
    
    /**
     * Initialization stuff
     * @param g
     */
    void init(Game g);
    
    /**
     * Finish everything (like closing a graphics window)
     */
    void finish();
    
    
    /**
     * Redraw dirty Elements in Field
     * This method can reset the dirty flag in the Elements
     */
    void update();
    
    /**
     * Get the direction from an input, or null when no input ready
     */
    Direction getInput();
    
}
