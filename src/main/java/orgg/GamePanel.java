package orgg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author Karabanov Andrey
 * @version 1.0
 * @date 27.12.2022 1:15
 */
public class GamePanel extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE; //Number of cells
    static final int DELAY = 75;//Game speed
    final int x[] = new int[GAME_UNITS];//array with max number cells
    final int y[] = new int[GAME_UNITS];//array with max number cells
    int bodyParts = 6; //Start length Snake
    int applesEaten; //Score
    int appleX; //Apple Coordinate X
    int appleY; //Apple Coordinate Y
    char direction = 'R'; //Starting direction of the snake
    boolean running = false; //Checking of is started game or not
    Timer timer;
    Random random;

    GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT)); //set new Size
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame() {
        newApple(); //create a new apple
        running = true;//start a new game
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (running) {
            for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {//Creating square layout
                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);//Creating vertical layout lines
                g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);//Creating horizontal layout lines
            }
            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);//coordinates and dimensions
            for (int i = 0; i < bodyParts; i++) {//painting the parts of the snake
                if (i == 0) {
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);//paint the square
                } else {
                    g.setColor(new Color(45, 180, 0));
                    //Random color for the part of the snake
                    // g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            //Print the score
            g.setColor(Color.red);
            g.setFont(new Font("Ink Free",Font.BOLD,40));
            FontMetrics metrics= getFontMetrics(g.getFont());
            g.drawString("Score: "+applesEaten,(SCREEN_WIDTH-metrics.stringWidth("Score: "+applesEaten))/2,g.getFont().getSize());

        }
        else{
            gameOver(g);
        }
    }
    public void newApple() {
        appleX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        appleY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }

    public void move() {
        //Snake body movement
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        //Snake head movement
        switch (direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }
    }

    public void checkApple() {
        if ((x[0] == appleX) && (y[0] == appleY)) {
            bodyParts++; // increase by one body
            applesEaten++; // increase by one score
            newApple(); //Create a new apple
        }
    }

    public void checkCollisions() {
        //Checking that the head don't clash with the body
        for (int i = bodyParts; i > 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }
        //Checking that the head don't clash with the left wall
        if (x[0] < 0) {
            running = false;
        }
        //Checking that the head don't clash with the right wall
        if (x[0] > SCREEN_WIDTH) {
            running = false;
        }
        //Checking that the head don't clash with the Upper wall
        if (y[0] < 0) {
            running = false;
        }
        //Checking that the head don't clash with the down wall
        if (y[0] > SCREEN_HEIGHT) {
            running = false;
        }
        if (!running) {
            timer.stop();
        }
    }

    //Action//
    public class TestActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            SnakeGame.frameCheck.dispose();
            GameFrame gamePanel= new GameFrame(0);
            SnakeGame.frameCheck=gamePanel;
        }

    }
    //Action//

    public void gameOver(Graphics g) {
        JOptionPane.showMessageDialog(null,"Игра окончена. \nРезультат\nЯблок съедено: "+applesEaten);
        createMain();
    }

    public void createMain(){
        SnakeGame.frameCheck.dispose();
        GameFrame startGamePanel = new GameFrame(0);
        SnakeGame.frameCheck=startGamePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move(); //Snake is running
            checkApple();//Apple is checking
            checkCollisions();//Collisions are checking

        }
        repaint(); // repair window element
    }
    /*
    R-> <-L
    ^-U v-D
    choosing the direction of the snake and checking that the directions are not opposite
     */
    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
            }
        }
    }
}
