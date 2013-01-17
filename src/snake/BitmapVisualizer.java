package snake;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
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

public class BitmapVisualizer extends Canvas implements Visualizer {

    Game game;
    JFrame frame;
    /**
     * This is the size of the bitmaps.
     * Permitted values are 32 and 16 (or others, if you provide the images and
     * adjust the method loadPNG()).
     */
    final int TILE=16;
    int w,h;
    Image doubleBuffer=null;
    Graphics dbGraphics;
    
    Image[] images;
    
    final int BACKGROUND=0;
    final int WALL=1;
    final int BODY=2;
    final int HEAD=4;
    final int CURVE=8;
    final int TAIL=12;
    final int APPLE=16;
    final int BLUE=15; // Body starts at 2!
    
    @Override
    public void init(Game g) {
        game = g;
        w = game.field.getWidth();
        h = game.field.getHeight();
        
        this.setSize(w*TILE, h*TILE);
        
        String colors[] = {"","-blue", "-green", "-yellow"};
        
        // Load all images
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        images = new Image[59];        
        images[BACKGROUND] = loadPNG("background");
        images[WALL] = loadPNG("wall");
        //images[BODY] = loadPNG("body0");
        images[APPLE] = loadPNG("goodie");
        
        int offset = 0;
        for (String color : colors) {
            for (int i=0; i<4; i++) {
                images[HEAD+i+offset] = loadPNG("head"+i+color);
                images[TAIL+i+offset] = loadPNG("tail"+i+color);
                images[CURVE+i+offset] = loadPNG("body"+i+"-"+((i+1)%4)+color);
                if (i<2) {
                    images[BODY+i+offset] = loadPNG("body"+i+color);
                }
            }
            if (offset==0) {
                offset = BLUE;
            } else {
                offset+=14;
            }
        }
        frame = new JFrame("Snake Bitmap Visualizer");

        frame.getContentPane().add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        
    }
    
    Image loadPNG(String name) {
        if (TILE==32) {
            return Toolkit.getDefaultToolkit().getImage(getClass().getResource("/snake/images/large/"+name+".png"));
        } else if (TILE==16) {
            return Toolkit.getDefaultToolkit().getImage(getClass().getResource("/snake/images/small/"+name+".png"));
        } else {
            throw new RuntimeException(String.format("TILE size %d not defined%n",TILE));
        }
    }

    private void initBuffer() {
        doubleBuffer = frame.createImage(w*TILE, h*TILE);
        dbGraphics = doubleBuffer.getGraphics();
    }

     @Override
    public void paint(Graphics graphics) {
        boolean redraw = false;
        if (doubleBuffer==null) {
            initBuffer();
            redraw = true;
        }
        // dbGraphics.clearRect(0, 0, frame.getWidth(), frame.getHeight());
        Element[][] elements = game.field.elements;
        for (int y=0; y<h; y++) {
            for (int x=0; x<w; x++) {
                boolean ok = true;
                if (elements[x][y].dirty) {
                    // If drawing fails, keep it dirty, initialization seems to take some time..
                    ok = ok && dbGraphics.drawImage(images[BACKGROUND],x*TILE,y*TILE, this);
                    switch (elements[x][y].elementType) {
                        case OBSTACLE:
                            ok = ok && dbGraphics.drawImage(images[WALL],x*TILE,y*TILE, this);
                            break;
                        case GOODIE:
                            ok = ok && dbGraphics.drawImage(images[APPLE],x*TILE,y*TILE, this);
                            break;
                        case SNAKE:
                            Element e = elements[x][y];
                            int offset = (new int[] {0,BLUE, BLUE+14, BLUE+28})[e.getSnake()];
                            if (e.isHead()) {
                                ok = ok && dbGraphics.drawImage(images[HEAD+e.inDir.ordinal()+offset],x*TILE,y*TILE, this);
                            } else {
                                if (e.isTail()) {
                                    ok = ok && dbGraphics.drawImage(images[TAIL+e.outDir.ordinal()+offset],x*TILE,y*TILE, this);
                                } else {
                                    if (e.inDir == e.outDir) {
                                        ok = ok && dbGraphics.drawImage(images[BODY+e.outDir.ordinal()%2+offset],x*TILE,y*TILE, this);
                                    } else {
                                        if ((e.inDir==Direction.UP && e.outDir==Direction.RIGHT) || (e.inDir==Direction.LEFT && e.outDir==Direction.DOWN)) {
                                            ok = ok && dbGraphics.drawImage(images[CURVE+offset],x*TILE,y*TILE, this);
                                        }
                                        if ((e.inDir==Direction.UP && e.outDir==Direction.LEFT) || (e.inDir==Direction.RIGHT && e.outDir==Direction.DOWN)) {
                                            ok = ok && dbGraphics.drawImage(images[CURVE+1+offset],x*TILE,y*TILE, this);
                                        }
                                        if ((e.inDir==Direction.RIGHT && e.outDir==Direction.UP) || (e.inDir==Direction.DOWN && e.outDir==Direction.LEFT)) {
                                            ok = ok && dbGraphics.drawImage(images[CURVE+2+offset],x*TILE,y*TILE, this);
                                        }
                                        if ((e.inDir==Direction.DOWN && e.outDir==Direction.RIGHT) || (e.inDir==Direction.LEFT && e.outDir==Direction.UP)) {
                                            ok = ok && dbGraphics.drawImage(images[CURVE+3+offset],x*TILE,y*TILE, this);
                                        }
                                    }
                                }
                            }
                            break;
                    }
                    // If something failed, mark element as dirty
                    elements[x][y].dirty = !ok;
                }
            }
        }
        graphics.drawImage(doubleBuffer, 0, 0, this);
    }
    
    
    @Override
    public void finish() {
        frame.dispose();
    }

    @Override
    public void update(Graphics g) { // Do not clear, to avoid flickering
        paint(g);
    }
    
    @Override
    public void update() {
        this.repaint();
    }

    @Override
    public Direction getInput() {
        return null;
    }

}
