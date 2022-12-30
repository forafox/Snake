package orgg;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * @author Karabanov Andrey
 * @version 1.0
 * @date 31.12.2022 0:48
 */
public class StartGamePanel extends JPanel {
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;

    StartGamePanel() {
        this.setLayout(new BorderLayout());//set new Layout

        //////////////////set main settings////////////////
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT)); //set new Size
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        //////////////////set main settings////////////////


        //////////////////create button///////////////
        JButton southButtonAboutAuthor = new JButton("About the Author");//Create new Button
        this.add(southButtonAboutAuthor,BorderLayout.SOUTH);// location at the bottom
        //////////////////create button////////////////////

        //////////////////Create Jmenu////////////////
        JMenu menu = new JMenu("Game part");
        JMenuBar menuBar = new JMenuBar();
        JMenuItem level1 = new JMenuItem("level 1");
        JMenuItem level2 = new JMenuItem("level 2 ");
        menu.add(level1);
        menu.add(level2);
        menuBar.add(menu);
        this.add(menuBar,BorderLayout.NORTH);
        //////////////////Create Jmenu////////////////


    }
}
