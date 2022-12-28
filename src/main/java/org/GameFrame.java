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
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);//Где будет располагаться наше окно
        ;
    }
}
