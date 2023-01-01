package orgg;

import javax.swing.*;

/**
 * @author Karabanov Andrey
 * @version 1.0
 * @date 27.12.2022 1:14
 */
public class GameFrame extends JFrame {
    GameFrame(int number) {
        if (number == 0) {
            this.add(new StartGamePanel());
        } else {
            this.add(new GamePanel(number));
        }
        this.setTitle("Snake"); // set Title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //programming the action after closing the window
        this.setResizable(false); //Can change this frame?
        this.pack(); //Automatic window resizing
        this.setVisible(true);//set window visible
        this.setLocationRelativeTo(null); //Window is locate in centre
        setDefaultLookAndFeelDecorated(true);
    }
}
