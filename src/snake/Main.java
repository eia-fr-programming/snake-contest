package snake;

import snake.strategies.*;
import java.util.Arrays;

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

public class Main {

    // minimal main for playing
    public static void main(String[] args) {
        
        
        // Initialize Parameters
        final Parameters param = new Parameters();
        
        // Add up to four Strategy Classes
        param.strategies.add(SimpleStrategy.class);
        param.strategies.add(SimpleStrategy.class);
        param.strategies.add(SimpleStrategy.class);
        param.strategies.add(SimpleStrategy.class);
        

        // Initialize the Playing Field
        FieldFactory.standardField(param);
        //FieldFactory.crossField(param);
        // FieldFactory.turning(param);
        // FieldFactory.rooms(param);
        //FieldFactory.diufSmiley(param);
        
        // Set up a new game
        Game game = new Game(param);
        
        // Set up a Terminal Visualizer
        Visualizer vis = new TerminalVisualizer();
        vis.init(game);
        game.visualizers.add(vis);

        // Set up a graphical Visualizer
        Visualizer bitmap = new BitmapVisualizer();
        bitmap.init(game);
        game.visualizers.add(bitmap);
              
        // Start the game, get the scores back
        int[] scores = game.runGame();

        // Finish the visualizers
        vis.finish();
        bitmap.finish();
            
        System.out.format("Scores are %s%n",Arrays.toString(scores));
        
    }
}
