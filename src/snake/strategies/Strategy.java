package snake.strategies;

import java.util.Random;
import snake.Field;
import snake.Snake;
import snake.util.Direction;

/**
 * This class provides some variables and methods for a Strategy. Every
 * Strategy <b>must</b> extend this class. Further you <b>have to implement</b>
 * a constructor with the same signature (Field, Snake, Random) where you <b>have</b>
 * to call super(...) with the 3 Parameters.
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


public abstract class Strategy {
    
    /** Field of the game, initialized in the constructor */
    Field field;
    /** Your snake, initialized in the constructor */
    Snake snake;
    /** A random generator, initialized in the constructor */
    Random random;
    
    /**
     * Constructor to set the variables. You will have to call this constructor with super(...)
     * @param field
     * @param snake
     * @param random 
     */
    public Strategy(Field field, Snake snake, Random random) {
        this.field = field;
        this.snake = snake;
        this.random = random;
    }
    
    /**
     * This method computes the next {@link oop.snake.util.Direction} the snake shall take and
     * stores it into the variable {@code nextDirection} (which will be read by the 
     * {@link oop.snake.Game} via the getter-method).
     * 
     * This method must <b>not use more than 100ms to complete</b> or it will be aborted. 
     * You may set nextDirection before that to a sensible value and continue your
     * computations.
     * 
     * If your method takes much time, make sure to periodically check
     *               {@code Thread.interrupted()}
     * and terminate immediately if {@code Thread.interrupted()} is {@code true}.
     * 
     * Strategies exceeding the time limit and not reacting to Thread.interrupted()
     * will be disqualified.
     */
    abstract public Direction computeNextStep();
    
    
    
}
