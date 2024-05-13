package PacMan;
import processing.core.PApplet;


public class Main extends PApplet{
    Map map = new Map(this);
    Cherry cherry = new Cherry(this);
    Cheese cheese = new Cheese(this);
    PacManPlayer pacManPlayer = new PacManPlayer(this,3,9,9,false);
    Highscore highscore = new Highscore(this,0);


    public void settings(){
        size(1140,1140);
    }

    public void setup() {
        PacManGame.populateMap();
        highscore.setCurrentAmountOfCheese();

    }
    public void draw(){
        map.createMap();
        cherry.spawnCherry();
        cheese.spawnCheese();
        pacManPlayer.movePacMan();
        pacManPlayer.drawPacMan();
        highscore.checkScore();
        highscore.drawScore();

    }

    public static void main(String[] args) {
        PApplet.main("PacMan.Main");
    }


}