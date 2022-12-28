package org;

import javax.swing.*;

/**
 * @author Karabanov Andrey
 * @version 1.0
 * @date 27.12.2022 1:14
 */
public class GameFrame extends JFrame {
    GameFrame(){
        this.add(new GamePanel());
        this.setTitle("Snake"); // set Title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //programming the action after closing the window
        this.setResizable(false); //Can change this frame?
        this.pack(); //Automatic window resizing
        this.setVisible(true);//set window visible
        this.setLocationRelativeTo(null); //Window is locate in centre
        ;
    }
}
