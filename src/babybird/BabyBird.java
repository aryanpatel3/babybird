package babybird;

import components.ScorePanel;
import components.TitleLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * BabyBird
 * @author Aryan Patel
 */
public class BabyBird extends JFrame{

    private static final int LIVES = 4;
    
    private FlightPanel flightPanel = new FlightPanel(this);
    private ScorePanel scorePanel = new ScorePanel(0, Color.GREEN);
    private BirdNestPanel birdNestPanel;
    
    public BabyBird() {
        initGUI();
        setTitle("Baby Bird");
        setResizable(false);
        //setLocation(500,0);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }
    
    private void initGUI() {
        //title panel
        TitleLabel titleLabel = new TitleLabel("Baby Bird");
        add(titleLabel, BorderLayout.PAGE_START);
        
        //main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.GREEN);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        add(mainPanel, BorderLayout.CENTER);
        
        //score panel
        mainPanel.add(scorePanel);
        
        //flight panel
        mainPanel.add(flightPanel);
        
        //bottom panel
        JPanel bottomPanel = new JPanel();
        Bird bird = flightPanel.getBird();
        BufferedImage birdImage = bird.getImage();
        add(bottomPanel, BorderLayout.PAGE_END);
        
        //birdnest panel
        birdNestPanel = new BirdNestPanel(birdImage, LIVES-1);
        bottomPanel.setBackground(Color.BLACK);
        bottomPanel.add(birdNestPanel);
        
    }
    
    public static void main(String[] args) {
        new BabyBird();
    }

    public void nextBird() {
        int birdsRemaining = birdNestPanel.removeBird();
        
        if(birdsRemaining <= 0) {
            scorePanel.highScore();
            scorePanel.saveInfo();
            String msg = "Game Over. Play again?";
            int option = JOptionPane.showConfirmDialog(this, msg, "Play again?", JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION) {
                birdNestPanel.setBirdCount(LIVES-1);
                scorePanel.reset();
                flightPanel.restart();
            } else {
                System.exit(0);
            }
        }
    }
    
    public void addToScore(int points) {
        scorePanel.addToScore(points);
    }
}
