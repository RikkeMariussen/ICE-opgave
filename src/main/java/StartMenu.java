import processing.core.PApplet;

import java.util.ArrayList;

public class StartMenu extends PApplet {

    //For at vise dem i et array
    ArrayList<IGames> shapes = new ArrayList<>();

    public void setup() {
//Background:
        background(0);

        //  this.ellipse(50, 50, 100, 50);
        IGames circle = new Snake();
        IGames rectangle = new Snake();

        //Tilføjer former til arraylisten:
        shapes.add(circle);
        shapes.add(rectangle);


    }

    public void draw() {
        background(0);

        //For-each loop der kalder på array/metode
        for (IGames shape : shapes) {
            //For at få dem til at bevæge sig fra venstre til højre
            shape.xPos = frameCount * shape.speed;
            //For at få dem til at bevæge sig nedad
            shape.yPos = frameCount * shape.speed;


            shape.displayShape();
        }


    }


    public void settings() {
        size(760, 900);
    }
}
