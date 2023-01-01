package orgg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Karabanov Andrey
 * @version 1.0
 * @date 31.12.2022 0:48
 */
public class StartGamePanel extends JPanel {
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;

    //Action//
    public class TestActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case ("level 1 work"):
                    SnakeGame.frameCheck.dispose();
                    SnakeGame.frameCheck = new GameFrame(1);
                    break;
                case ("level 2 work"):
                    SnakeGame.frameCheck.dispose();
                    SnakeGame.frameCheck = new GameFrame(2);
                    break;
                case ("level 3 work"):
                    SnakeGame.frameCheck.dispose();
                    SnakeGame.frameCheck = new GameFrame(3);
                    break;
                case ("level 4 work"):
                    SnakeGame.frameCheck.dispose();
                    SnakeGame.frameCheck = new GameFrame(4);
                    break;
                case ("level 5 work"):
                    SnakeGame.frameCheck.dispose();
                    SnakeGame.frameCheck = new GameFrame(5);
                    break;
            }
        }
    }

    StartGamePanel() {
        this.setLayout(new BorderLayout());//set new Layout
        //////////////////set main settings////////////////
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT)); //set new Size
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        //////////////////set main settings////////////////

        //images//
        final JLabel picLabel = new JLabel(new ImageIcon((this.getClass().getResource("/SnakePicture.png"))));
        picLabel.setBounds(300, 300, 50, 50);
        this.add(picLabel);
        //images//


        //Action//
        ActionListener actionListener = new TestActionListener();
        //Action//


        //////////////////create button///////////////
        JButton southButtonAboutAuthor = new JButton("About the Author");//Create new Button
        this.add(southButtonAboutAuthor, BorderLayout.SOUTH);// location at the bottom
        //////////////////create button////////////////////


        //////////////////Create Jmenu////////////////
        JMenu menu = new JMenu("Game part");
        JMenuBar menuBar = new JMenuBar();
        JMenuItem level1 = new JMenuItem("level 1");
        JMenuItem level2 = new JMenuItem("level 2");
        JMenuItem level3 = new JMenuItem("level 3");
        JMenuItem level4 = new JMenuItem("level 4");
        JMenuItem level5 = new JMenuItem("level 5");

        level1.setActionCommand("level 1 work");
        level1.addActionListener(actionListener);
        level2.setActionCommand("level 2 work");
        level2.addActionListener(actionListener);
        level3.setActionCommand("level 3 work");
        level3.addActionListener(actionListener);
        level4.setActionCommand("level 4 work");
        level4.addActionListener(actionListener);
        level5.setActionCommand("level 5 work");
        level5.addActionListener(actionListener);

        menu.add(level1);
        menu.add(level2);
        menu.add(level3);
        menu.add(level4);
        menu.add(level5);

        menuBar.add(menu);
        this.add(menuBar, BorderLayout.NORTH);
        //////////////////Create Jmenu////////////////
    }
}
