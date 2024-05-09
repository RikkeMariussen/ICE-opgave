package PacMan;
import processing.core.PApplet;


public class Main extends PApplet{
    Map map = new Map(this);
    Cherry cherry = new Cherry(this);

    public void settings(){
        size(1140,1140);
    }

    public void setup() {
        PacManGame.populateMap();
        map.createMap();
        cherry.spawnCherry();
        PacManGame.printMap();
    }
    public void draw(){

    }

    public static void main(String[] args) {
        PApplet.main("PacMan.Main");
    }


}