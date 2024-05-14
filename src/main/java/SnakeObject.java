import processing.core.PApplet;
import java.util.ArrayList;

public class SnakeObject {

    private PApplet parent;
    ArrayList<SnakeSegment> body;
    private int xDir, yDir;
    private int scl;

    public SnakeObject(PApplet p){
        parent = p;
        scl = 20;
        body = new ArrayList<>();
        body.add(new SnakeSegment(parent, 0, 0));
        xDir = 1;
        yDir = 0;
    }

    public void setDirection(int x, int y){
        xDir = x;
        yDir = y;
    }

    public void update(){
        SnakeSegment head = body.get(0);
        int newX = head.x + xDir * scl;
        int newY = head.y + yDir * scl;

        // Screen wrap
        newX = (newX + parent.width) % parent.width;
        newY = (newY + parent.height) % parent.height;

        // add new head and remove last bodypart
        body.add(0, new SnakeSegment(parent, newX, newY));
        body.remove(body.size() - 1);
    }

    public void show(){
        for (SnakeSegment snakeSegment : body){
            snakeSegment.show();
        }
    }

    public boolean eat(int foodX, int foodY){
        SnakeSegment head = body.get(0);
        if (head.x == foodX && head.y == foodY){
            body.add(new SnakeSegment(parent, head.x, head.y));
            return true;
        }
        return false;
    }

    public boolean checkSelfCollision(int headX, int headY, ArrayList<SnakeSegment> body){
        for (int i = 1; i<body.size(); i++){
            SnakeSegment segment = body.get(i);
            if (headX == segment.x && headY == segment.y){
                return true;
            }
        }
        return false;
    }
}
