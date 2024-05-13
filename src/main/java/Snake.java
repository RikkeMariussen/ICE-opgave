import processing.core.PApplet;
import processing.core.PVector;
import processing.core.*;

import java.awt.*;

public class Snake extends AGames {
    private PApplet parent;
    private SnakeObject snake;
    private PointObject apple;

    public Snake(PApplet parent) {
        this.parent = parent;
        snake = new SnakeObject();
        snake.addSegment(20, 20);
        apple = new PointObject(parent, 100, 100, 10, 10, 0, 0, parent.color(255, 0, 0));
    }
    @Override
    public void updateGame() {
        snake.move();
        checkCollisions();
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
        if (apple.isCaught((int)head.x, (int)head.y)) {
            apple.incrementScore();
            apple.resetPosition();  // Reset apple position
            snake.addSegment((int)head.x, (int)head.y);  // Grow the snake
        }
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
        return "";
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
