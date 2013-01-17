package snake;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

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

public class Tournament {

    private static ArrayList<int[]> scores = new ArrayList<int[]>();
    private static ArrayList<Class> strategies = new ArrayList<Class>();
    private static int[] players = new int[]{0, 1, 2, 3};
    private static int numPlayers = 0;

    private static void init(String[] args) {
        for (String name : args) {
            try {
                strategies.add(Class.forName("oop.snake.strategies." + name));
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Tournament.class.getName()).log(Level.SEVERE, null, ex);
                System.exit(-1);
            }
        }
        numPlayers = strategies.size();
    }

    /**
     * Generates the lexicographical selection of 4 players
     *
     * @return true, if the selection rolls over
     */
    private static boolean nextGroupSelection() {
        if (players[3] < numPlayers - 1) {
            players[3]++;
        } else {
            int p = 2;
            while (p >= 0 && players[p] == numPlayers - 4 + p) {
                p--;
            }
            if (p == -1) { // restart
                players = new int[]{0, 1, 2, 3};
                return true;
            } else {  // move this position one up
                players[p]++;
                for (p++; p < 4; p++) { // align other behind
                    players[p] = players[p - 1] + 1;
                }
            }
        }
        return false;
    }

    private static int soloGame(int strategy, int field) {
        Parameters param = new Parameters();
        param.miliSleep = 0;
        param.maxIteration = 5000;
        param.totalGoodies = 500;
        setField(field,param);
        param.strategies.add(strategies.get(strategy));
        Game game = new Game(param);
        int[] score = game.runGame();
        return score[0];
    }

    private static String setField(int field, Parameters param) {
        String fieldName = "";
        switch (field) {
            case 0:
                FieldFactory.standardField(param);
                fieldName = "Standard";
                break;
            case 1:
                FieldFactory.crossField(param);
                fieldName = "Cross";
                break;
            case 2:
                FieldFactory.rooms(param);
                fieldName = "Rooms";
                break;
            case 3:
                FieldFactory.turning(param);
                fieldName = "Turning";
                break;
            case 4:
                FieldFactory.diufSmiley(param);
                fieldName = "DIUF";
                break;
        }
        return fieldName;
    }

    private static void runTournament(int field) {
        for (int g = 0; g < 4; g++) {
            long timeUsed = System.nanoTime();
            Parameters param = new Parameters();
            param.miliSleep = 0;
            for (int i = 0; i < 4; i++) {
                param.strategies.add(strategies.get(players[(i + g) % 4]));
            }
            String fieldName = setField(field,param);
            Game game = new Game(param);

//            Visualizer gif = new BrightSideOfDeathGifVisualizer();
//            gif.init(game);
//            game.visualizers.add(gif);

            int[] score = game.runGame();
            int[] all = new int[numPlayers + 2];
            Arrays.fill(all, -1);
            all[numPlayers] = game.iteration;
            all[numPlayers + 1] = game.goodiesConsumed;

            String alive = "";
            for (int i = 0; i < 4; i++) {
                all[players[(i + g) % 4]] = score[i];
                if (game.snakes.get(i).isAlive()) {
                    alive += ",\"alive\"";
                } else {
                    alive += ",\"" + game.snakes.get(i).reasonOfDeath + "\"";
                }
            }
            timeUsed = System.nanoTime() - timeUsed;
            for (int i = 0; i < numPlayers + 2; i++) {
                if (all[i] != -1) {
                    System.out.format("%d,", all[i]);
                } else {
                    System.out.format("\" \",");
                }
            }
            System.out.format("%d,\"%s\"%s%n", timeUsed / 1000000, fieldName, alive);
            scores.add(all);
            timeUsed = System.nanoTime() - timeUsed;

        }
    }

    private static int binom(int n, int m) {
        if (m > n - m) {
            m = n - m;
        }
        int res = n;
        for (int i = 2; i <= m; i++) {
            res *= (n - i + 1);
            res /= i;
        }
        return res;
    }

    /**
     * Executes a tournament with all Strategy-class-names passed as arguments.
     *
     * Each combination of 4 Strategies is played 4 times the first with a
     * random placement, the 3 following with a 4-cyclic permutation of the
     * starting positions.
     *
     * Rounds with disqualified Strategies will be discarded.
     *
     * @param args
     */
    public static void main(String[] args) {
        int mul = 500;
        args = new String[]{"SimpleStrategy"};


        init(args);

        int k = 1;

        if (k == 0) {

            for (String a : args) {
                System.out.format("\"%s\",", a);
            }

            System.out.format("\"Iterations\", \"Goodies eaten\", \"miliseconds per match\", \"Field\"%n");
            int numMatches = mul * 4 * 4 * binom(numPlayers, 4);
            int numPlayerMatches = mul * 4 * 4 * binom(numPlayers - 1, 3);
            for (int i = 0; i < numPlayers; i++) {
                System.out.format("\"=average(%c4:%c%d)\",", (char) ('A' + i), (char) ('A' + i), numMatches + 3);
            }
            System.out.format("\"average\"%n");
            for (int i = 0; i < numPlayers; i++) {
                System.out.format("\"=2*stdev(%c4:%c%d)/sqrt(%d)\",", (char) ('A' + i), (char) ('A' + i), numMatches + 3, numPlayerMatches);
            }
            System.out.format("\"2sigma on average\"%n");
            for (int i = 0; i < mul; i++) {
                do {
                    for (int field = 0; field < 5; field++) {
                        runTournament(field);
                    }
                } while (!nextGroupSelection());
            }
        } else {
            System.out.format("\" \",");
            for (String a : args) {
                System.out.format("\"%s\",", a);
            }
            System.out.format("%n");
            for (int f = 0; f < 5; f++) {
                System.out.format("\"%s\"",setField(f, new Parameters()));
                for (int s = 0; s < args.length; s++) {
                    double sum = 0;
                    for (int i = 0; i < mul; i++) {
                        sum += soloGame(s, f);
                    }
                    System.out.format(",%.1f",sum/mul);
                }
                System.out.format("%n");
            }
        }
    }
}
