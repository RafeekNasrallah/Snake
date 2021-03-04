import java.awt.*;

public class Game {
    int[][] boardGame;
    Snake snake;
    /**
     boardGame is filled with 3 values!
     0 -> empty cells
     1 -> the snake's head
     2 -> snake's body
     */
    public Game(int[][] boardGame){
        this.boardGame=boardGame;
        this.snake=new Snake();
        updateBoard();
    }
    public Snake getSnake() {
        return snake;
    }
    public void updateBoard(){
        //draw the borders of the gameboard
        for (int i = 0; i <this.boardGame.length ; i++) {
            this.boardGame[0][i]=4;
            this.boardGame[18][i]=4;
        }
        for (int i = 0; i <this.boardGame.length ; i++) {
            this.boardGame[i][0]=4;
            this.boardGame[i][18]=4;
        }
        //draw the gaps
        this.boardGame[8][0]=0;
        this.boardGame[9][0]=0;
        this.boardGame[8][18]=0;
        this.boardGame[9][18]=0;
        this.boardGame[0][8]=0;
        this.boardGame[0][9]=0;
        this.boardGame[18][8]=0;
        this.boardGame[18][9]=0;
        //draw the snake
        for (int i = 0; i <this.snake.snakeParts.size() ; i++) {
            Point part = this.snake.snakeParts.get(i);
            if (i==0)
                this.boardGame[(int)part.getX()][(int)part.getY()]=2;
            else
                this.boardGame[(int)part.getX()][(int)part.getY()]=1;
        }
    }
    public void resetBoardValues(){
        for (int i = 0; i <this.boardGame.length ; i++) {
            for (int j = 0; j <this.boardGame.length ; j++) {
                if (this.boardGame[i][j]!=3 && this.boardGame[i][j]!=4)
                    this.boardGame[i][j]=0;
            }
        }
    }


}
