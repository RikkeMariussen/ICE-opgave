import processing.core.PApplet;

public class BallDropGame extends PApplet {

    private PApplet parent;
    String name;
    int numberOfPointObject;
    int numberOfBombs;
    int pointObjectInterval;
    int bombsInterval;
    int activePointObject;
    int score;

    public void settings() {
        size(800, 800);
    }


    //Objects used for the game:
    PlayerPlate playerPlate = new PlayerPlate(this, width / 2, height + 600, 80, 20);
    Bombs bombs = new Bombs(this);
    PointObject ball = new PointObject(this, (int) random(width), 0, 20, 20, 5, 10, color(105, 105, 105));


    void setBallSpeed(PointObject ball) {
        //Makes the balls go across the screen
        ball.setSpeed(ball.getSpeed());

        //todo: Right now it is not incorporated that if the person does not catch the ball (see if statement), they need to lose a life
       /* if (y > height + h/2) {
            y = -h/2;
        }*/
    }

    void ballsDropping(PointObject ball) {

    }

    /*There is still alot that needs to get done before this is complete*/
}