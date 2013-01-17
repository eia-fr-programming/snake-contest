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

public class TerminalVisualizer implements Visualizer {

    Game game;
    
    final String[] snakebody = new String[] {"==","||","//","\\\\"};
   
    
    @Override
    public void init(Game g) {
        game = g;
    }

    @Override
    public void update() {
        Field field = game.field;
        StringBuilder output = new StringBuilder();
        for (int y=0; y<field.getHeight(); y++) {
            for (int x=0; x<field.getWidth(); x++) {
                Element e = field.getElementAt(x, y);
                switch (e.elementType) {
                    case OBSTACLE:
                        output.append("##");
                        break;
                    case EMPTY:
                        output.append("  ");
                        break;
                    case GOODIE:
                        output.append("**");
                        break;
                    case SNAKE:
                        if (e.isHead()) {
                            //System.out.format("Found snake head %d%n",e.snake);
                            output.append(e.snake).append(e.snake);
                        } else {
                            //System.out.format("Found snake body%n");
                            if (e.inDir == e.outDir) {
                                output.append(snakebody[e.inDir.ordinal()%2]);
                            } else {
                                if (e.inDir.ordinal()+e.outDir.ordinal()==3) {
                                    output.append(snakebody[2]);
                                } else {
                                    output.append(snakebody[3]);
                                }
                            }
                        }
                }
            }
            output.append("\n");
        }
        output.append("Goodies consumed: ").append(game.goodiesConsumed).append("\n");
        for (int i=0; i<game.scores.length; i++) {
            output.append("Snake ").append(i).append(" piloted by ");
            output.append(game.strategies.get(i).getClass().getName());
            output.append(" ").append(game.scores[i]);
            if (! game.snakes.get(i).isAlive()) {
                output.append(" Reason of death: ").append(game.snakes.get(i).reasonOfDeath);
            }
            output.append("\n");
        }
        // Clear Screen
        //System.out.print("\u001b[2J");
        System.out.print(output.toString());
    }

    @Override
    public Direction getInput() {
        return null;
    }

    @Override
    public void finish() {
    }

    
}
