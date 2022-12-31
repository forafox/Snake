package orgg;

import javax.swing.*;
import java.awt.*;

/**
 * @author Karabanov Andrey
 * @version 1.0
 * @date 27.12.2022 1:14
 */
public class GameFrame extends JFrame {
    GameFrame(int number) {
        switch (number) {
            case (0):
                this.add(new StartGamePanel());
                this.setTitle("Snake"); // set Title
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //programming the action after closing the window
                this.setResizable(false); //Can change this frame?
                this.pack(); //Automatic window resizing
                this.setVisible(true);//set window visible
                this.setLocationRelativeTo(null); //Window is locate in centre
                this.setDefaultLookAndFeelDecorated(true);
                break;
            case (1):
                this.add(new GamePanel());
                this.setTitle("Snake"); // set Title
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //programming the action after closing the window
                this.setResizable(false); //Can change this frame?
                this.pack(); //Automatic window resizing
                this.setVisible(true);//set window visible
                this.setLocationRelativeTo(null); //Window is locate in centre
                this.setDefaultLookAndFeelDecorated(true);
                break;
        }
    }
}
