package snake;

import java.lang.reflect.Method;
import snake.util.Direction;
import snake.util.Point;

/**
 * Static methods to add walls and starting position to a {@link Field} (given
 * inside a {@link Parameters} Object)
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


public class FieldFactory {

    /**
     * Field with a cross in the middle
     */
    static void crossField(Parameters param) {
        int w = param.field.getWidth();
        int h = param.field.getHeight();
        for (int x = w / 4; x < 3 * w / 4; x++) {
            param.field.elements[x][h / 2].setWall();
        }
        for (int y = h / 4; y < 3 * h / 4; y++) {
            param.field.elements[w / 2][y].setWall();
        }
        param.startPoints.clear();
        param.startDirections.clear();

        param.startPoints.add(new Point(w / 5, h / 5));
        param.startDirections.add(Direction.RIGHT);

        param.startPoints.add(new Point(4 * w / 5, h / 5));
        param.startDirections.add(Direction.DOWN);

        param.startPoints.add(new Point(4 * w / 5, 4 * h / 5));
        param.startDirections.add(Direction.LEFT);

        param.startPoints.add(new Point(w / 5, 4 * h / 5));
        param.startDirections.add(Direction.UP);
    }

    /**
     * Empty field
     */
    static void standardField(Parameters param) {
        int w = param.field.getWidth();
        int h = param.field.getHeight();

        param.startPoints.clear();
        param.startDirections.clear();

        param.startPoints.add(new Point(w / 5, h / 5));
        param.startDirections.add(Direction.RIGHT);

        param.startPoints.add(new Point(4 * w / 5, h / 5));
        param.startDirections.add(Direction.DOWN);

        param.startPoints.add(new Point(4 * w / 5, 4 * h / 5));
        param.startDirections.add(Direction.LEFT);

        param.startPoints.add(new Point(w / 5, 4 * h / 5));
        param.startDirections.add(Direction.UP);
    }

    /**
     * Field looking a bit like a four-armed spiral
     */
    static void turning(Parameters param) {
        int w = param.field.getWidth();
        int h = param.field.getHeight();

        param.startPoints.clear();
        param.startDirections.clear();

        for (int x = 0; x < 3 * w / 5; x++) {
            param.field.elements[x][h / 4].setWall();
            param.field.elements[w - 1 - x][3 * h / 4].setWall();
        }

        for (int y = 0; y < 3 * h / 5; y++) {
            param.field.elements[3 * w / 4][y].setWall();
            param.field.elements[w / 4][h - 1 - y].setWall();
        }

        param.startPoints.add(new Point(w / 8, h / 8));
        param.startDirections.add(Direction.RIGHT);

        param.startPoints.add(new Point(7 * w / 8, h / 8));
        param.startDirections.add(Direction.DOWN);

        param.startPoints.add(new Point(7 * w / 8, 7 * h / 8));
        param.startDirections.add(Direction.LEFT);

        param.startPoints.add(new Point(w / 8, 7 * h / 8));
        param.startDirections.add(Direction.UP);

    }

    /**
     * Four rooms, that are connected in the middle of the field
     */
    static void rooms(Parameters param) {
        int w = param.field.getWidth();
        int h = param.field.getHeight();

        param.startPoints.clear();
        param.startDirections.clear();

        for (int x = 0; x < w; x++) {
            if (Math.abs(x - w / 2) > 2) {
                param.field.elements[x][h / 2].setWall();
            }
        }

        for (int y = 0; y < h; y++) {
            if (Math.abs(y - h / 2) > 2) {
                param.field.elements[w / 2][y].setWall();
            }
        }

        param.startPoints.add(new Point(w / 8, h / 8));
        param.startDirections.add(Direction.RIGHT);

        param.startPoints.add(new Point(7 * w / 8, h / 8));
        param.startDirections.add(Direction.DOWN);

        param.startPoints.add(new Point(7 * w / 8, 7 * h / 8));
        param.startDirections.add(Direction.LEFT);

        param.startPoints.add(new Point(w / 8, 7 * h / 8));
        param.startDirections.add(Direction.UP);

    }

    static void diufSmiley(Parameters param) {
        int w = param.field.getWidth();
        int h = param.field.getHeight();

        if (h < 30) {
            throw new RuntimeException("Field must be at least 30 units high for diufSmiley!");
        }

        int x = (w - 24) / 2;
        int y = (h - 25) / 2;

        param.field.elements[x + 10][y + 0].setWall();
        param.field.elements[x + 11][y + 0].setWall();
        param.field.elements[x + 12][y + 0].setWall();
        param.field.elements[x + 13][y + 0].setWall();
        param.field.elements[x + 14][y + 0].setWall();
        param.field.elements[x + 15][y + 0].setWall();
        param.field.elements[x + 7][y + 1].setWall();
        param.field.elements[x + 8][y + 1].setWall();
        param.field.elements[x + 9][y + 1].setWall();
        param.field.elements[x + 10][y + 1].setWall();
        param.field.elements[x + 11][y + 1].setWall();
        param.field.elements[x + 12][y + 1].setWall();
        param.field.elements[x + 13][y + 1].setWall();
        param.field.elements[x + 14][y + 1].setWall();
        param.field.elements[x + 15][y + 1].setWall();
        param.field.elements[x + 16][y + 1].setWall();
        param.field.elements[x + 17][y + 1].setWall();
        param.field.elements[x + 18][y + 1].setWall();
        param.field.elements[x + 5][y + 2].setWall();
        param.field.elements[x + 6][y + 2].setWall();
        param.field.elements[x + 7][y + 2].setWall();
        param.field.elements[x + 8][y + 2].setWall();
        param.field.elements[x + 9][y + 2].setWall();
        param.field.elements[x + 16][y + 2].setWall();
        param.field.elements[x + 17][y + 2].setWall();
        param.field.elements[x + 18][y + 2].setWall();
        param.field.elements[x + 19][y + 2].setWall();
        param.field.elements[x + 4][y + 3].setWall();
        param.field.elements[x + 5][y + 3].setWall();
        param.field.elements[x + 6][y + 3].setWall();
        param.field.elements[x + 18][y + 3].setWall();
        param.field.elements[x + 19][y + 3].setWall();
        param.field.elements[x + 20][y + 3].setWall();
        param.field.elements[x + 3][y + 4].setWall();
        param.field.elements[x + 4][y + 4].setWall();
        param.field.elements[x + 5][y + 4].setWall();
        param.field.elements[x + 20][y + 4].setWall();
        param.field.elements[x + 21][y + 4].setWall();
        param.field.elements[x + 2][y + 5].setWall();
        param.field.elements[x + 3][y + 5].setWall();
        param.field.elements[x + 4][y + 5].setWall();
        param.field.elements[x + 21][y + 5].setWall();
        param.field.elements[x + 22][y + 5].setWall();
        param.field.elements[x + 2][y + 6].setWall();
        param.field.elements[x + 3][y + 6].setWall();
        param.field.elements[x + 8][y + 6].setWall();
        param.field.elements[x + 9][y + 6].setWall();
        param.field.elements[x + 10][y + 6].setWall();
        param.field.elements[x + 11][y + 6].setWall();
        param.field.elements[x + 12][y + 6].setWall();
        param.field.elements[x + 13][y + 6].setWall();
        param.field.elements[x + 21][y + 6].setWall();
        param.field.elements[x + 22][y + 6].setWall();
        param.field.elements[x + 1][y + 7].setWall();
        param.field.elements[x + 2][y + 7].setWall();
        param.field.elements[x + 3][y + 7].setWall();
        param.field.elements[x + 10][y + 7].setWall();
        param.field.elements[x + 11][y + 7].setWall();
        param.field.elements[x + 12][y + 7].setWall();
        param.field.elements[x + 13][y + 7].setWall();
        param.field.elements[x + 22][y + 7].setWall();
        param.field.elements[x + 23][y + 7].setWall();
        param.field.elements[x + 1][y + 8].setWall();
        param.field.elements[x + 2][y + 8].setWall();
        param.field.elements[x + 10][y + 8].setWall();
        param.field.elements[x + 11][y + 8].setWall();
        param.field.elements[x + 12][y + 8].setWall();
        param.field.elements[x + 22][y + 8].setWall();
        param.field.elements[x + 23][y + 8].setWall();
        param.field.elements[x + 1][y + 9].setWall();
        param.field.elements[x + 2][y + 9].setWall();
        param.field.elements[x + 10][y + 9].setWall();
        param.field.elements[x + 11][y + 9].setWall();
        param.field.elements[x + 12][y + 9].setWall();
        param.field.elements[x + 22][y + 9].setWall();
        param.field.elements[x + 23][y + 9].setWall();
        param.field.elements[x + 0][y + 10].setWall();
        param.field.elements[x + 1][y + 10].setWall();
        param.field.elements[x + 5][y + 10].setWall();
        param.field.elements[x + 6][y + 10].setWall();
        param.field.elements[x + 7][y + 10].setWall();
        param.field.elements[x + 10][y + 10].setWall();
        param.field.elements[x + 11][y + 10].setWall();
        param.field.elements[x + 12][y + 10].setWall();
        param.field.elements[x + 15][y + 10].setWall();
        param.field.elements[x + 16][y + 10].setWall();
        param.field.elements[x + 17][y + 10].setWall();
        param.field.elements[x + 22][y + 10].setWall();
        param.field.elements[x + 23][y + 10].setWall();
        param.field.elements[x + 0][y + 11].setWall();
        param.field.elements[x + 1][y + 11].setWall();
        param.field.elements[x + 5][y + 11].setWall();
        param.field.elements[x + 6][y + 11].setWall();
        param.field.elements[x + 7][y + 11].setWall();
        param.field.elements[x + 10][y + 11].setWall();
        param.field.elements[x + 11][y + 11].setWall();
        param.field.elements[x + 12][y + 11].setWall();
        param.field.elements[x + 15][y + 11].setWall();
        param.field.elements[x + 16][y + 11].setWall();
        param.field.elements[x + 17][y + 11].setWall();
        param.field.elements[x + 22][y + 11].setWall();
        param.field.elements[x + 23][y + 11].setWall();
        param.field.elements[x + 0][y + 12].setWall();
        param.field.elements[x + 1][y + 12].setWall();
        param.field.elements[x + 10][y + 12].setWall();
        param.field.elements[x + 11][y + 12].setWall();
        param.field.elements[x + 12][y + 12].setWall();
        param.field.elements[x + 22][y + 12].setWall();
        param.field.elements[x + 23][y + 12].setWall();
        param.field.elements[x + 0][y + 13].setWall();
        param.field.elements[x + 1][y + 13].setWall();
        param.field.elements[x + 10][y + 13].setWall();
        param.field.elements[x + 11][y + 13].setWall();
        param.field.elements[x + 22][y + 13].setWall();
        param.field.elements[x + 23][y + 13].setWall();
        param.field.elements[x + 0][y + 14].setWall();
        param.field.elements[x + 1][y + 14].setWall();
        param.field.elements[x + 10][y + 14].setWall();
        param.field.elements[x + 11][y + 14].setWall();
        param.field.elements[x + 21][y + 14].setWall();
        param.field.elements[x + 22][y + 14].setWall();
        param.field.elements[x + 0][y + 15].setWall();
        param.field.elements[x + 1][y + 15].setWall();
        param.field.elements[x + 9][y + 15].setWall();
        param.field.elements[x + 10][y + 15].setWall();
        param.field.elements[x + 11][y + 15].setWall();
        param.field.elements[x + 21][y + 15].setWall();
        param.field.elements[x + 22][y + 15].setWall();
        param.field.elements[x + 1][y + 16].setWall();
        param.field.elements[x + 2][y + 16].setWall();
        param.field.elements[x + 9][y + 16].setWall();
        param.field.elements[x + 10][y + 16].setWall();
        param.field.elements[x + 11][y + 16].setWall();
        param.field.elements[x + 20][y + 16].setWall();
        param.field.elements[x + 21][y + 16].setWall();
        param.field.elements[x + 1][y + 17].setWall();
        param.field.elements[x + 2][y + 17].setWall();
        param.field.elements[x + 9][y + 17].setWall();
        param.field.elements[x + 10][y + 17].setWall();
        param.field.elements[x + 11][y + 17].setWall();
        param.field.elements[x + 18][y + 17].setWall();
        param.field.elements[x + 19][y + 17].setWall();
        param.field.elements[x + 20][y + 17].setWall();
        param.field.elements[x + 1][y + 18].setWall();
        param.field.elements[x + 2][y + 18].setWall();
        param.field.elements[x + 3][y + 18].setWall();
        param.field.elements[x + 9][y + 18].setWall();
        param.field.elements[x + 10][y + 18].setWall();
        param.field.elements[x + 11][y + 18].setWall();
        param.field.elements[x + 12][y + 18].setWall();
        param.field.elements[x + 16][y + 18].setWall();
        param.field.elements[x + 17][y + 18].setWall();
        param.field.elements[x + 18][y + 18].setWall();
        param.field.elements[x + 2][y + 19].setWall();
        param.field.elements[x + 3][y + 19].setWall();
        param.field.elements[x + 10][y + 19].setWall();
        param.field.elements[x + 11][y + 19].setWall();
        param.field.elements[x + 12][y + 19].setWall();
        param.field.elements[x + 13][y + 19].setWall();
        param.field.elements[x + 14][y + 19].setWall();
        param.field.elements[x + 15][y + 19].setWall();
        param.field.elements[x + 16][y + 19].setWall();
        param.field.elements[x + 2][y + 20].setWall();
        param.field.elements[x + 3][y + 20].setWall();
        param.field.elements[x + 4][y + 20].setWall();
        param.field.elements[x + 3][y + 21].setWall();
        param.field.elements[x + 4][y + 21].setWall();
        param.field.elements[x + 5][y + 21].setWall();
        param.field.elements[x + 19][y + 21].setWall();
        param.field.elements[x + 20][y + 21].setWall();
        param.field.elements[x + 4][y + 22].setWall();
        param.field.elements[x + 5][y + 22].setWall();
        param.field.elements[x + 6][y + 22].setWall();
        param.field.elements[x + 7][y + 22].setWall();
        param.field.elements[x + 18][y + 22].setWall();
        param.field.elements[x + 19][y + 22].setWall();
        param.field.elements[x + 5][y + 23].setWall();
        param.field.elements[x + 6][y + 23].setWall();
        param.field.elements[x + 7][y + 23].setWall();
        param.field.elements[x + 8][y + 23].setWall();
        param.field.elements[x + 9][y + 23].setWall();
        param.field.elements[x + 10][y + 23].setWall();
        param.field.elements[x + 15][y + 23].setWall();
        param.field.elements[x + 16][y + 23].setWall();
        param.field.elements[x + 17][y + 23].setWall();
        param.field.elements[x + 18][y + 23].setWall();
        param.field.elements[x + 7][y + 24].setWall();
        param.field.elements[x + 8][y + 24].setWall();
        param.field.elements[x + 9][y + 24].setWall();
        param.field.elements[x + 10][y + 24].setWall();
        param.field.elements[x + 11][y + 24].setWall();
        param.field.elements[x + 12][y + 24].setWall();
        param.field.elements[x + 13][y + 24].setWall();
        param.field.elements[x + 14][y + 24].setWall();
        param.field.elements[x + 15][y + 24].setWall();
        param.field.elements[x + 16][y + 24].setWall();


        param.startPoints.clear();
        param.startDirections.clear();

        param.startPoints.add(new Point(3, 3));
        param.startDirections.add(Direction.RIGHT);

        param.startPoints.add(new Point(w - 4, 3));
        param.startDirections.add(Direction.LEFT);

        param.startPoints.add(new Point(3, h - 4));
        param.startDirections.add(Direction.RIGHT);

        param.startPoints.add(new Point(w - 4, h - 4));
        param.startDirections.add(Direction.LEFT);
    }

    /**
     * Get an Array of all Methods in this class
     */
    static Method[] getAllFieldMethods() {
        Method[] methods = FieldFactory.class.getDeclaredMethods();
        Method[] res = new Method[methods.length - 1];
        int p = 0;
        for (Method m : methods) {
            if (!m.getName().equals("getAllFieldMethods")) {
                res[p++] = m;
            }
        }
        return res;
    }
}
