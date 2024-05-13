import processing.core.PApplet;
import processing.core.PVector;

public class Snake extends AGames {
    private PApplet parent;
    private SnakeObject snake;
    private PointObject apple;
    private int snakeScore;

    public Snake(PApplet parent) {
        this.parent = parent;
        snake = new SnakeObject();
        snake.addSegment(20, 20);
        apple = new PointObject(parent, 100, 100, 15, 15, 0, 0, parent.color(255, 0, 0));
    }
    @Override
    public void updateGame() {
        snake.move();
        checkCollisions();
        keyPressed();
        //if (isCaught((int) snake.getHead().x,(int)snake.getHead().y)){
        //    apple
        //}
    }

    @Override
    public void displayGame() {
        parent.background(0);
        for (PVector segment : snake.getSegments()) {
            parent.fill(255);
            parent.rect(segment.x, segment.y, 10, 10);
        }
        apple.displayRoundObject();

    }


    private void checkCollisions() {
        PVector head = snake.getSegments().getFirst();
        if (isCaught((int)head.x, (int)head.y)) {
            incrementScore();
            resetPosition();  // Reset apple position
            snake.addSegment((int)head.x, (int)head.y);  // Grow the snake
        }
    }

    public boolean isCaught(int snakeX, int snakeY) {
        float distance = PApplet.dist(snakeX, snakeY, apple.getX(), apple.getY());
        return distance < (float) apple.getW() / 2;  // Assuming the snake's head is about the same width as the apple
    }

    public void resetPosition() {
        //apple.setX() = (int)(parent.random(parent.width));
        //this.y = (int)(parent.random(parent.height));
    }

    public void incrementScore() {
        snakeScore++;
    }

    @Override
    public String getTitle() {
        return "";
    }

    @Override
    public String playGame() {
        return "";
    }

    @Override
    public String setHighScore() {
        return "";
    }

    @Override
    public String getHighScore() {
        return "";
    }

    @Override
    public String keyPressed() {
        if (parent.keyCode == parent.UP) snake.setDir(0, -1);
        else if (parent.keyCode == parent.DOWN) snake.setDir(0, 1);
        else if (parent.keyCode == parent.LEFT) snake.setDir(-1, 0);
        else if (parent.keyCode == parent.RIGHT) snake.setDir(1, 0);
        return null;
    }

    @Override
    public String howToPlay() {
        return "";
    }

    @Override
    public String youDied() {
        return "";
    }

    @Override
    public String difficulties() {
        return "";
    }

    @Override
    public String lives() {
        return "";
    }
}
