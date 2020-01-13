package components;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import static javax.swing.SwingConstants.CENTER;

/**
 * TitleLabel
 * @author Aryan Patel
 */
public class TitleLabel extends JLabel{

    public TitleLabel(String title) {
        Font font = new Font("Comic Sans MS", Font.BOLD, 48);
        setFont(font);
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
        setOpaque(true);
        setHorizontalAlignment(CENTER);
        setText(title);
    }
}
