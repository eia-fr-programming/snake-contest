package snake.strategies;

import java.util.ArrayList;
import java.util.Random;
import snake.Field;
import snake.Snake;
import snake.util.Direction;
import snake.util.Point;

/**
 * Very simple strategy as an example
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


public class SimpleStrategy extends Strategy {

    
    public SimpleStrategy(Field field, Snake snake, Random random) {
        super(field,snake,random);
    }
    
    @Override
    public Direction computeNextStep() {
        // first idea: keep direction
        Direction nextDirection = snake.getDirection();
        
        // current position
        Point myPosition = snake.getHeadPosition();
        
        // Assuming there is only one goodie
        Point goodie = field.getGoodiePositions().get(0);
        
        if (goodie.x<myPosition.x && nextDirection!=Direction.RIGHT) {
            nextDirection = Direction.LEFT;
        } else if (goodie.x>myPosition.x && nextDirection!=Direction.LEFT) {
            nextDirection = Direction.RIGHT;
        } else if (goodie.y>myPosition.y && nextDirection!=Direction.UP) {
            nextDirection = Direction.DOWN;
        } else if (goodie.y<myPosition.y && nextDirection!=Direction.DOWN) {
            nextDirection = Direction.UP;
        }
        
        // In case we would bang our head
        if (!field.canWalkOn(myPosition.movedTo(nextDirection))) {
            // randomly choose between going left or right
            if (random.nextInt(3)==0) {
                nextDirection = nextDirection.left();
            } else {
                nextDirection = nextDirection.right();
            }
            // Still bang our head? Then go the opposite way and hope for the best
            if (!field.canWalkOn(myPosition.movedTo(nextDirection))) {
                nextDirection = nextDirection.opposite();
            }
        }  
        return nextDirection;
    }
}
