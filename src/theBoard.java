import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.TimerTask;

import static javax.swing.JOptionPane.showMessageDialog;

public class theBoard implements KeyListener {
    Game game;
    JFrame frame;
    JLabel[][] grid = new JLabel[19][19];
    /**
     * making sure to continue in same direction till the user press another key!
     **/
    int direction=-1;
    int points=0;
    boolean isFoodAvailable = false;
    public theBoard(Game game, JFrame frame) {
        this.game = game;
        this.frame = frame;

    }

    public void drawBoard() throws InterruptedException {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(new GridLayout(19, 19));
        for (int i = 0; i < game.boardGame.length; i++) {
            for (int j = 0; j < game.boardGame.length; j++) {
                grid[i][j] = new JLabel();
                grid[i][j].setBorder(new LineBorder(Color.BLACK));
                if (game.boardGame[i][j] == 1)
                    grid[i][j].setBackground(Color.black);
                else if (game.boardGame[i][j] == 2)
                    grid[i][j].setBackground(Color.blue);
                else if (game.boardGame[i][j] == 4)
                    grid[i][j].setBackground(Color.DARK_GRAY);
                grid[i][j].setOpaque(true);
                frame.add(grid[i][j]);
            }
        }
        frame.addKeyListener(this);
        play();
    }


    public void play() throws InterruptedException {
        while (true) {
            game.getSnake().moveOneStepBody(this.direction);
            Point snakeHeadCord = game.getSnake().moveOneStepHead(this.direction);
            // if head hits the grey boards
            if (grid[snakeHeadCord.x][snakeHeadCord.y].getBackground()==Color.DARK_GRAY)
            {
                showMessageDialog(null, "Game over with " + this.points + " points.");
                resetGame();
            }
            //if the snake ate the food
            if (grid[snakeHeadCord.x][snakeHeadCord.y].getBackground()==Color.red) {
                this.isFoodAvailable = false;
                this.game.snake.addBodyPart(this.direction);
                points++;
            }

            generateFood();
            Thread.sleep(100);
            this.game.resetBoardValues();
            resetBoardColors(this.grid);
            this.game.updateBoard();
            for (int i = 0; i < game.boardGame.length; i++) {
                for (int j = 0; j < game.boardGame.length; j++) {
                    if (game.boardGame[i][j] == 1)
                        grid[i][j].setBackground(Color.black);
                    if (game.boardGame[i][j] == 2)
                        grid[i][j].setBackground(Color.blue);
                }
            }
            //if the snake touched its body, game over
            if (grid[snakeHeadCord.x][snakeHeadCord.y].getBackground()==Color.black) {
                showMessageDialog(null, "Game over with " + this.points + " points.");
                resetGame();
            }

            frame.setVisible(true);
        }

    }


    //resets game so it starts over
    public void resetGame(){
        this.points=0;
        this.direction=4;
        this.game.snake.resetSnake();
        resetBoardColors(this.grid);
        this.game.resetBoardValues();
    }


    /** this method generates two random numbers between 1 and 19 as food**/
    public void generateFood(){
        if (!isFoodAvailable) {
            Random rand = new Random();
            int low = 1;
            int high = 18;
            int r1 = rand.nextInt(high-low) + low;
            int r2 = rand.nextInt(high-low) + low;
            while (this.game.boardGame[r1][r2] == 1 || this.game.boardGame[r1][r2] == 2 || this.game.boardGame[r1][r2] == 4) {
                r2 = rand.nextInt(high-low) + low;
                r1 = rand.nextInt(high-low) + low;
            }
            System.out.println(r1+" and " + r2);
            this.grid[r1][r2].setBackground(Color.red);
            this.game.boardGame[r1][r2] = 3;
            this.isFoodAvailable=true;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (direction!=2)
                this.direction=0;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (direction!=3)
                this.direction=1;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (direction!=0)
                this.direction=2;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (direction!=1)
                this.direction=3;
        }
    }


    public void resetBoardColors(JLabel[][] grid) {
        for (int i = 0; i < game.boardGame.length; i++) {
            for (int j = 0; j < game.boardGame.length; j++) {
                if (grid[i][j].getBackground()==Color.BLACK || grid[i][j].getBackground()==Color.blue)
                    grid[i][j].setBackground(Color.white);

            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        theBoard theBoard = new theBoard(new Game(new int[19][19]), new JFrame("Snake"));
        theBoard.drawBoard();
    }
}
