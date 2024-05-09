import processing.core.PApplet;

import java.util.ArrayList;

public class Main extends PApplet{

    ArrayList<IGames> shapes = new ArrayList<>();
    public void settings() {
        size(800, 800);
    }

    PlayerPlate playerPlate = new PlayerPlate(this,width/2,height+600,80,20);
    //Bombs bombs = new Bombs(this);
    public void setup() {
//Background:
        background(255);
        //  this.ellipse(50, 50, 100, 50);
        //   IGames circle = new BallDrop();
        //   IGames rectangle = new Snake();

        //Tilføjer former til arraylisten:
        //   shapes.add(circle);
        //   shapes.add(rectangle);


    }

    public void draw() {
        background(255);
        playerPlate.update();
        playerPlate.display();
/*
        //For-each loop der kalder på array/metode
        for (IGames shape : shapes) {
            //For at få dem til at bevæge sig fra venstre til højre
            shape.xPos = frameCount * shape.speed;
            //For at få dem til at bevæge sig nedad
            shape.yPos = frameCount * shape.speed;


            shape.displayShape();
        }
*/

      //  bombs.update();
      //  bombs.display();
    }

    public static void main(String[] args) {
        String[] appletArgs = new String[]{"Main"}; //det i tuborg parenteser skal hedde det samme som klassen
        PApplet.main(appletArgs);

    }
}