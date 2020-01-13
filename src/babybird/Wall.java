
package babybird;

import static babybird.FlightPanel.WIDTH;
import static babybird.FlightPanel.HEIGHT;

import commonmethods.FileIO;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author Aryan Patel
 */
public class Wall {
    
    private static final String WALL_IMAGE = "wall.jpg";
    private static final int CHANGE_X = -10; 
    private static final int TOP_MIN = 100;
    private static final int TOP_MAX = 300;
    private static final int GAP_MIN = 100;
    private static final int GAP_MAX = 240;  
    private static final int POINTS_OFFSET = 80;
    
    private static BufferedImage wallImage;
    private static int width = 72, height = 600;
    
    private int x = WIDTH;
    private int bottomY;
    private int topHeight, bottomHeight;
    private BufferedImage topImage, bottomImage;
    private int points = 1;
    private String pointsString;
    private int pointsX;
    private Random rand = new Random();
    
    public Wall(FontMetrics fm) {
        
        if(wallImage == null){
            wallImage = FileIO.readImageFile(this, WALL_IMAGE);
            width = wallImage.getWidth();
            height = wallImage.getHeight();
        }
        
        int range = GAP_MAX - GAP_MIN;
        int pick = rand.nextInt(range);
        int gap = pick + GAP_MIN;
        
        //calculate the ratio of gap
        float ratio = (float)pick/range; 
        //turning the ratio into a number from 1 to 10
        int intValue = (int)(ratio * 10);
        //change values from 10 - 1
        points = 10 - intValue;
        pointsString = points + "";
        int pointsWidth = fm.stringWidth(pointsString);
        pointsX = (width/2) - (pointsWidth/2);
        
        range = TOP_MAX - TOP_MIN;
        pick = rand.nextInt(range);
        topHeight = pick + TOP_MIN;
        
        bottomY = topHeight + gap;
        bottomHeight = HEIGHT - bottomY;
        
        topImage = wallImage.getSubimage(0, height - topHeight, width, topHeight);
        bottomImage = wallImage.getSubimage(0, 0, width, bottomHeight);
        
    }

    public void draw(Graphics g) {
        if(wallImage == null) {
            g.setColor(Color.CYAN);
            g.fillRect(x, 0, width, topHeight);
            g.fillRect(x, bottomY, width, bottomHeight);
        } else {
            g.drawImage(topImage, x, 0, null);
            g.drawImage(bottomImage, x, bottomY, null);
        }
        g.setColor(Color.CYAN);
        g.drawString(pointsString, x + pointsX, bottomY + POINTS_OFFSET);
    }
    
    public int getPoints() {
        return points;
    }
    
    public boolean isPastWindowEdge() {
        int rightEdgeX = x + width;
        
        if(rightEdgeX < 0) return true;
        return false;
    }
    
    public void move() {
        x += CHANGE_X;
    }
    
    public Rectangle getTopBounds() {
        Rectangle bounds = new Rectangle(x, 0, width, topHeight);
        return bounds;
    }
    
    public Rectangle getBottomBounds() {
        Rectangle bounds = new Rectangle(x, bottomY, width, bottomHeight);
        return bounds;
    }
    
    public int getX () {
        return x;
    }
    
    public int getY() {
        return 0;
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getChangeX() {
        return CHANGE_X;
    }
    
}
