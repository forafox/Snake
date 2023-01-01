package orgg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * @author Karabanov Andrey
 * @version 1.0
 * @date 27.12.2022 1:15
 */
public class GamePanel extends JPanel implements ActionListener {
    static boolean checkMessageDialog;
    static boolean alreadyExecuted;
    static int levelDifficult;
    static final int levelDifficultFactor = 5;
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE; //Number of cells
    static final int DELAY = 75;//Game speed
    final int[] x = new int[GAME_UNITS];//array with max number cells
    final int[] y = new int[GAME_UNITS];//array with max number cells

    final int[] xBarrier = new int[GAME_UNITS];//array for barrier
    final int[] yBarrier = new int[GAME_UNITS];//array for barrier
    int bodyParts = 6; //Start length Snake
    int applesEaten; //Score
    int appleX; //Apple Coordinate X
    int appleY; //Apple Coordinate Y
    char direction = 'R'; //Starting direction of the snake
    boolean running = false; //Checking of is started game or not
    Timer timer;
    Random random;

    long startTime;
    long stopTime;
    long elapsedTime;

    GamePanel(int number) {
        levelDifficult = number;
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT)); //set new Size
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame() {
        alreadyExecuted = false;
        checkMessageDialog = true;
        newApple(); //create a new apple
        running = true;//start a new game
        timer = new Timer(DELAY, this);
        startTime = System.currentTimeMillis();
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
        createBarrier(g);
    }

    public void createBarrier(Graphics g) {
        g.setColor(Color.GRAY);
        if (!alreadyExecuted)
            for (int i = 0; i < levelDifficultFactor * levelDifficult; i++) {
                xBarrier[i] = (random.nextInt(SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
                yBarrier[i] = (random.nextInt((SCREEN_HEIGHT / UNIT_SIZE) - 1) + 1) * UNIT_SIZE;
            }

        for (int i = 0; i < levelDifficultFactor * levelDifficult; i++) {
            g.fillRect(xBarrier[i], yBarrier[i], UNIT_SIZE, UNIT_SIZE);
        }
        alreadyExecuted = true;
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
            g.setFont(new Font("Ink Free", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());

        } else {
            gameOver();
        }
    }

    public void newApple() {
        appleX = random.nextInt((SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        appleY = random.nextInt((SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
        for (int i = 0; i < levelDifficultFactor * levelDifficult; i++) {
            if (appleX == xBarrier[i] && appleY == yBarrier[i]) newApple();
        }
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
                break;
            }
        }
        //Checking that the head don't clash with the barrier
        for (int i = 0; i < levelDifficultFactor * levelDifficult; i++) {
            if ((x[0] == xBarrier[i]) && (y[0] == yBarrier[i])) {
                running = false;
                break;
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
            SnakeGame.frameCheck = new GameFrame(0);
        }

    }
    //Action//

    public void gameOver() {
        stopTime = System.currentTimeMillis();
        System.out.println(elapsedTime);
        if (checkMessageDialog) {
            checkMessageDialog = false;
            JOptionPane.showMessageDialog(null, "The game is over. \nResult\nApples eaten: " + applesEaten + "\nTime has passed: " + (stopTime - startTime) / 1000 + " second");
        }
        createMain();
    }

    public void createMain() {
        SnakeGame.frameCheck.dispose();
        SnakeGame.frameCheck = new GameFrame(0);
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

    //choosing the direction of the snake and checking that the directions are not opposite
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
