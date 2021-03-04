import java.awt.*;
import java.util.LinkedList;

public class Snake {
    int length;
    /**
     direction 0 -> right
     direction 1 -> up
     direction 2 -> left
     direction 3 -> down
    **/
    int direction;
    LinkedList<Point> snakeParts;
    public Snake(){
        this.direction=0;
        this.snakeParts=new LinkedList<>();
        snakeParts.add(new Point(8,8));

    }
    public void resetSnake(){
        this.snakeParts=new LinkedList<>();
        snakeParts.add(new Point(8,8));
    }
    public int getHeadX(){
        return (int)this.snakeParts.get(0).getX();
    }
    public int getHeadY(){
        return (int)this.snakeParts.get(0).getY();
    }


    public void moveOneStepBody(int direction){
        if (direction<4 && direction>-1) {
            for (int i = this.snakeParts.size() - 1; i > 0; i--) {
                this.snakeParts.get(i).setLocation(this.snakeParts.get(i - 1));
            }
        }
    }

    public void addBodyPart(int direction){
        Point lastPart = this.snakeParts.get(this.snakeParts.size()-1);
        if (this.snakeParts.size()==1) {
                if (direction==0 && lastPart.x-1>0)
                    this.snakeParts.add(new Point(lastPart.x - 1, lastPart.y));
                else if (direction==1 && lastPart.y+1<18)
                    this.snakeParts.add(new Point(lastPart.x, lastPart.y + 1));
                else if (direction==2&& lastPart.x+1<18)
                    this.snakeParts.add(new Point(lastPart.x + 1, lastPart.y));
                else
                    this.snakeParts.add(new Point(lastPart.x, lastPart.y - 1));

        }
        else {
            Point beforeLast= this.snakeParts.get(this.snakeParts.size()-2);
            if (lastPart.y-beforeLast.y<0 && lastPart.y-1>0)
                this.snakeParts.add(new Point(lastPart.x, lastPart.y-1));
            else if (lastPart.y-beforeLast.y>0 && lastPart.y+1<18)
                this.snakeParts.add(new Point(lastPart.x , lastPart.y+1));
            else if (lastPart.x-beforeLast.x<0 && lastPart.x-1>0)
                this.snakeParts.add(new Point(lastPart.x-1, lastPart.y));
            else if (lastPart.x-beforeLast.x>0 && lastPart.x+1<18)
                this.snakeParts.add(new Point(lastPart.x+1 , lastPart.y));

        }
    }


    public Point moveOneStepHead(int direction){
        switch (direction){
            case 0:
                if (getHeadY()+1==18)
                    this.snakeParts.get(0).setLocation(getHeadX(),-1);
                this.snakeParts.get(0).setLocation(getHeadX(),getHeadY()+1);
                break;
            case 3:
                if (getHeadX()+1==18)
                    this.snakeParts.get(0).setLocation(-1,getHeadY());
                this.snakeParts.get(0).setLocation(getHeadX()+1,getHeadY());
                break;
            case 2:
                if (getHeadY()-1<0)
                    this.snakeParts.get(0).setLocation(getHeadX(),18);
                this.snakeParts.get(0).setLocation(getHeadX(),getHeadY()-1);
                break;
            case 1:
                if (getHeadX()-1<0)
                    this.snakeParts.get(0).setLocation(18,getHeadY());
                this.snakeParts.get(0).setLocation(getHeadX()-1,getHeadY());
                break;
        }
        return this.snakeParts.get(0);
    }


}
