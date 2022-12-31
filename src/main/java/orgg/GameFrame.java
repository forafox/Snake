package orgg;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Karabanov Andrey
 * @version 1.0
 * @date 27.12.2022 1:14
 */
public class GameFrame extends JFrame {

    GameFrame(int number) {
        if(number==0) {
            this.add(new StartGamePanel());
            this.setTitle("Snake"); // set Title
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //programming the action after closing the window
            this.setResizable(false); //Can change this frame?
            this.pack(); //Automatic window resizing
            this.setVisible(true);//set window visible
            this.setLocationRelativeTo(null); //Window is locate in centre
            this.setDefaultLookAndFeelDecorated(true);
        }
                else{
                this.add(new GamePanel(number));
                this.setTitle("Snake"); // set Title
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //programming the action after closing the window
                this.setResizable(false); //Can change this frame?
                this.pack(); //Automatic window resizing
                this.setVisible(true);//set window visible
                this.setLocationRelativeTo(null); //Window is locate in centre
                this.setDefaultLookAndFeelDecorated(true);
        }
    }
}
