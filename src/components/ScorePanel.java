package components;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * ScorePanel
 * @author Aryan Patel
 */
public class ScorePanel extends JPanel{
    
    private int initScore = 0;
    private int score = 0;
    private int highScore = 0;
    private static final Font SMALL_FONT = new Font(Font.DIALOG, Font.PLAIN, 16);
    private static final Font BIG_FONT = new Font(Font.DIALOG, Font.BOLD, 36);
    private JLabel scoreLabel = new JLabel("0");
    private JLabel highScoreLabel = new JLabel("0");
    private JLabel highScoreTitleLabel;
    private String fileName = "src/highScore.txt";

    public ScorePanel(int initScore, Color color) {
        this.initScore = initScore;
        score = initScore;
        
        setBackground(color);
        
        loadInfo();
        
        JLabel scoreTitleLabel = new JLabel("Score: ");
        highScoreTitleLabel = new JLabel("Highscore: ");
        
        scoreTitleLabel.setFont(SMALL_FONT);
        highScoreTitleLabel.setFont(SMALL_FONT);
        
        add(highScoreTitleLabel);
        highScoreLabel.setFont(BIG_FONT);
        add(highScoreLabel);
        
        add(scoreTitleLabel);
        scoreLabel.setFont(BIG_FONT);
        add(scoreLabel);
        
        highScoreLabel.setText(highScore + "");
    }
    
    public void addToScore(int points) {
        score += points;
        scoreLabel.setText(score + "");
    }
    
    public void reset() {
        score = initScore;
        scoreLabel.setText(score + "");
    }
    
    public void highScore() {
        if(score > this.highScore) {       
            this.highScore = score;
            highScoreLabel.setText(highScore + "");
        }
    }
    
    public int getScore() {
        return score;
    }
    
    public void saveInfo() {
        try {
            //making objects to write in file 
            FileWriter fw = new FileWriter(fileName); 
            BufferedWriter bw = new BufferedWriter(fw);
                        
            String highScoreString = highScore + "";
            
            //writing in file
            bw.write(highScoreString);
            bw.newLine();
            //closing writer
            bw.close();
        }
        catch(IOException ex) {
            System.out.println("Error saveing info");
        }
    }
    
    public void loadInfo() {
        try {
            //making objects to read from file 
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            
            //reading from file
            highScore = Integer.parseInt(br.readLine());
            //highScorePlayerName = br.readLine();
            
            //closing reader  
            br.close();
        }
        catch(IOException ex) {
            System.out.println("Error loading info.");
        } 
    }
    
}
