import processing.core.PApplet;

public class Snake extends AGames {
    private PApplet parent;
    private SnakeObject snake;
    private PointObject apple;
    private int score = 0;
    private int scl = 20;
    private boolean gameover = false;
    private boolean startGame = true;
    int difficulty;

    public Snake(PApplet parent, int difficulty) {
        this.difficulty = difficulty;
        this.parent = parent;
        snake = new SnakeObject(parent);
        apple = new PointObject(parent, 100, 100, 15, 15, 0, 0, parent.color(255, 0, 0));
    }

    @Override
    public void updateGame() {
        if (!gameover) {
            if (parent.frameCount % (12 / difficulty) == 0) {
                snake.update();
                keyPressed();
                if (snake.checkSelfCollision(snake.body.getFirst().x, snake.body.getFirst().y, snake.body)) {
                    gameover = true;
                }
                if (snake.eat(apple.getX(), apple.getY())) {
                    placeFood();
                    incrementScore();
                }
            }
        }
        if (startGame) {
            startGame = false;
        }
    }

    @Override
    public void displayGame() {
        parent.background(0);
        displaySnake();
        apple.displaySquareObject();
        if (!gameover) {
            displayScore();
        } else if (gameover) {
            ((StartMenu) parent).setDeathState("Snake", score);
            restartGame();
        }
    }


    public void placeFood() {
        apple.setX((int) (parent.random(parent.width / scl)) * scl);
        apple.setY((int) (parent.random(parent.height / scl)) * scl);
    }

    public void incrementScore() {
        score++;
    }

    public void displayScore() {
        parent.fill(255);
        parent.textSize(20);
        parent.text("Score: " + score, 40, 30);
    }

    public void displaySnake() {
        for (SnakeSegment snakeSegment : snake.body) {
            parent.fill(0, 255, 0);
            parent.rect(snakeSegment.x, snakeSegment.y, 18, 18);
        }
    }

    @Override
    public void restartGame() {
        gameover = false;
        score = 0;
        snake.body.clear();
        snake.body.add(0, new SnakeSegment(parent, 0, 0));
    }

    @Override
    public String keyPressed() {
        if (parent.keyCode == parent.UP) snake.setDirection(0, -1);
        else if (parent.keyCode == parent.DOWN) snake.setDirection(0, 1);
        else if (parent.keyCode == parent.LEFT) snake.setDirection(-1, 0);
        else if (parent.keyCode == parent.RIGHT) snake.setDirection(1, 0);
        return null;
    }

    @Override
    public String getTitle() {
        return "";
    }

    @Override
    public void setDiff(int diff) {
        this.difficulty = diff;
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

    //Kept old methods, to see how we did it when deathScreen was individual for each game.
    public void resetGameKey() {
        if ((parent.key == 'r' || parent.key == 'R') && parent.keyPressed) {
            restartGame();
        } else if ((parent.key == 'b' || parent.key == 'B') && parent.keyPressed) {
            //parent.keyCode = parent.RETURN;
            this.gameover = false;
            this.startGame = true;
            restartGame();
            StartMenu.endCurrentGame();
        }

    }

    public void displayGameover() {
        parent.fill(255);
        parent.textSize(50);
        parent.text("your final score: " + score, parent.width / 2, parent.height / 2);
        parent.text("Press R to try again or B to go back", parent.width / 2, parent.height / 2 + 80);
    }
}
