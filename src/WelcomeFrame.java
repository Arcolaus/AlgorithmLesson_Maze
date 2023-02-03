import javax.swing.*;
import java.awt.*;

public class WelcomeFrame extends JLabel {
    private ImageIcon WelcomeCover =new ImageIcon("src/image/WelcomeCover.jpg");
    JLabel CoverShow =new JLabel();
    public WelcomeFrame(){
        WelcomeCover.setImage(WelcomeCover.getImage().getScaledInstance(800,700, Image.SCALE_DEFAULT));
        CoverShow.setBounds(135,120,530,450);
        CoverShow.setOpaque(false);
        this.add(CoverShow);
        this.setIcon(WelcomeCover);
    }
}
