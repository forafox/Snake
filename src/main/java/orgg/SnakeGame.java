package orgg;

/**
 * @author Karabanov Andrey
 * @version 1.0
 * @date 27.12.2022 1:12
 */

/*
Main class, contains MAIN.class
 */
public class SnakeGame {
    public static GameFrame frameCheck;
    public static void main(String[] args) {
        GameFrame frame = new GameFrame(0);//Create a new Game logic
        frameCheck=frame;
    }
}
