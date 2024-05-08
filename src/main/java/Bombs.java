import processing.core.PApplet;

public class Bombs extends PApplet {

    private int size;
    private int x;
    private int y;
    private int w;
    private int h;
    private float speed;
    private int color;

    public Bombs(int x, int y, int w, int h, float speed, int color) {
        this.x = (int) random(width);
        this.y = 0;
        this.w = (int) random(20,40);
        this.h = (int) random(20,40);
        this.speed = random(3,15);
        this.color = color(0);
    }

    public void update(){
        y += speed;

        if (y > height + h/2) {
            y = -h/2;
        }
    }

    public void display(){
        fill(color);
        ellipse(x, y, w, h);}

    public void boom(){
        speed = 0; //Stops the balls when the user catches them, so they do not loop around
        y = -2; //Makes the balls stay in the top, when the user has caught them.
    }

}
