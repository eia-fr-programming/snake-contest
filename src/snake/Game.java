package snake;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import snake.strategies.Strategy;
import snake.util.Direction;
import snake.util.Point;

/**
 * This class is responsible for the execution of one round
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

public class Game {
    
    Field field;
    Parameters param;
    ArrayList<Strategy> strategies;
    ArrayList<Snake> snakes;
    int[] scores;
    
    ArrayList<Point> snakePositions;
    ArrayList<Point> goodiePositions;
    
    ArrayList<Visualizer> visualizers;
    

    
    int goodiesConsumed;
    int snakesAlive;
    int iteration;
    
    Game(Parameters param) {
        this.param = param;
        strategies = new ArrayList<Strategy>();
        snakes = new ArrayList<Snake>();
        visualizers = new ArrayList<Visualizer>();
        snakePositions = new ArrayList<Point>();
        goodiePositions = new ArrayList<Point>();
        setup();
    }
    
    final void setup() {
        snakesAlive = 0;
        goodiesConsumed = 0;
        field = new Field(param.field);
        // Generate snakes and strategies
        for (int i=0; i<param.strategies.size(); i++) {
            Snake snake = new Snake(param.startPoints.get(i), param.startDirections.get(i), 5, i);
            snakes.add(snake);
            snakesAlive++;
            snakePositions.add(snake.getHeadPosition());
            field.setSnakeHead(snake);
            
            Class c = param.strategies.get(i);
            try {
                Constructor constructor = c.getDeclaredConstructor(Field.class, Snake.class, Random.class);
                Strategy strategy = (Strategy) constructor.newInstance(field,snake,param.random);
                strategies.add(strategy);
            } catch (Exception ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        field.setSnakePositions(snakePositions);
        scores = new int[strategies.size()];
        Arrays.fill(scores,0);
        for (int i=0; i<param.sameTimeGoodies; i++) {
            placeNewGoodie(null);
        }
    }
    
    int[] runGame() {
        iteration = 0;
        boolean gameOver = false;
        while (!gameOver) {
            iteration++;
            Direction [] directions = runStrategiesSequentially();
            
            
            // Collect decisions from strategies and move snakes
            for (int player=0; player < strategies.size(); player++) {
                Snake snake = snakes.get(player);
                if (snake.isAlive()) {
                    scores[player]++;
                    snake.setDirection(directions[player]);
                    snake.updatePosition();
                }
            }
            
            // Compute possible head to head collisions
            boolean[] headCollisions = new boolean[strategies.size()];
            Arrays.fill(headCollisions,false);
            for (int p1=0; p1 < strategies.size(); p1++) {
                if (snakes.get(p1).isAlive()) {
                    for (int p2=p1+1; p2 < strategies.size(); p2++) {
                        if (snakes.get(p2).isAlive()) {
                            if (snakes.get(p1).getHeadPosition().equals(snakes.get(p2).getHeadPosition())) {
                                headCollisions[p1] = true;
                                headCollisions[p2] = true;
                            }
                        }
                    }
                }
            }
            for (int p1=0; p1 < strategies.size(); p1++) {
                if (headCollisions[p1]) {
                    snakes.get(p1).die("Head to head collision");
                    scores[p1] += param.beingKilledPoints;
                    if (scores[p1]<0) {
                        scores[p1] = 0;
                    }
                }
            }
            // Check if snakes are moving onto a special field (wall, snake or goodie)
            for (int player=0; player < strategies.size(); player++) {
                Snake snake = snakes.get(player);
                if (snake.isAlive()) {
                    Point snakePosition = snake.getHeadPosition();
                    Element element = field.getElementAt(snakePosition);
                    if (!element.canWalkOn()) {
                        snake.die();
                        // Run into another snake? Give the other snake points!
                        if (element.elementType == Element.ElementType.SNAKE && element.snake!=player) {
                            scores[element.snake] += param.killingPoints;
                            snake.reasonOfDeath = "run into another snake";
                        }
                        if (element.elementType == Element.ElementType.SNAKE && element.snake == player) {
                            scores[player] += param.suicidePoints;
                            snake.reasonOfDeath = "suicide";
                        } else {
                            snake.reasonOfDeath = "just another brick in the wall";
                            scores[player] += param.beingKilledPoints;
                        }
                        if (scores[player]<0) {
                            scores[player]=0;
                        }
                    } else { // Must be empty or goodie
                        if (element.elementType == Element.ElementType.GOODIE) {
                            goodiesConsumed++;
                            scores[player]+=param.goodiePoints;
                            snake.increaseLength(param.goodieIncrease);
                            placeNewGoodie(snakePosition);
                        }
                    }
                }
            }
            // Update Snake-Positions and alive-Counter, assign them to field
            for (int player=0; player < strategies.size(); player++) {
                Snake snake = snakes.get(player);
                if (snake.isAlive()) {
                    Point oldPos = snakePositions.get(player);
                    field.headAway(oldPos,snake.getDirection());
                    field.setSnakeHead(snake);
                }
            }
             
            snakesAlive = 0;
            snakePositions.clear();
            for (int player=0; player < strategies.size(); player++) {
                if (snakes.get(player).isAlive()) {
                    snakesAlive++;
                    snakePositions.add(snakes.get(player).getHeadPosition());
                } else {
                    snakePositions.add(null);
                }
            }
            
            field.setSnakePositions(this.snakePositions);
            if (snakesAlive==0 || (goodiesConsumed==param.totalGoodies) || (param.maxIteration>=0 && iteration>=param.maxIteration)) {
                gameOver = true;
                for (int player=0; player < strategies.size(); player++) {
                    if (snakes.get(player).isAlive()) {
                        scores[player]+=param.survivingPoints;
                    }
                }
            }
            
            // Update all Elements
            field.update();
            
            for (Visualizer v : visualizers) {
                v.update();
            }
            try {
                Thread.sleep(param.miliSleep);
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return scores;
    }

    private Direction[] runStrategiesSequentially() {
        Direction[] results = new Direction[strategies.size()];
        for (int player=0; player < strategies.size(); player++) {
            if (snakes.get(player).isAlive()) {
                Strategy s = strategies.get(player);
                System.gc();                      // Suggest garbage collection before measuring time
                long timeUsed = System.nanoTime();
                results[player] = s.computeNextStep();
                timeUsed = System.nanoTime() - timeUsed;
                if (timeUsed > 1e6*param.miliStrategy) {
                    System.out.format("WARNING: Strategy %s used %f seconds! (max %f are allowed). Its snake now dies.%n",s.getClass().getName(),1e-9*timeUsed,0.001*param.miliStrategy);
                    snakes.get(player).die();
                    snakes.get(player).reasonOfDeath = "Timeout";
                }
            }
        }
        return results;
    }
    
    
    private void placeNewGoodie(Point oldPosition) {
        if (oldPosition != null) {
            for (Point p : goodiePositions) {
                if (p.equals(oldPosition)) {
                    goodiePositions.remove(p);
                    break;
                }
            }
        }
        boolean posOK = false;
        int px=1;
        int py=1;
        while (!posOK) {
            posOK = true;
            px = param.random.nextInt(field.getWidth()-6)+3;
            py = param.random.nextInt(field.getHeight()-6)+3;
            if (! field.isEmpty(px,py)) {
                posOK = false;
            } else {
                for (Snake s : snakes) {
                    if (s.isAlive()) {
                        Point p = s.getHeadPosition();
                        if ((Math.abs(px-p.x)+Math.abs(py-p.y))<param.mindistance) {
                            posOK= false;
                            break;
                        }
                    }
                }
            }
        }
        Point p = new Point(px,py);
        goodiePositions.add(p);
        field.setGoodie(p);
        field.setGoodiePositions(goodiePositions);
    }
     
}
