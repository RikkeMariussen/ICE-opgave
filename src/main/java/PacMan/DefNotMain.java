package PacMan;
import processing.core.PApplet;


public class DefNotMain extends PApplet{
    boolean gameStart = true;


    Map map = new Map(this);
    Cherry cherry = new Cherry(this);
    Cheese cheese = new Cheese(this);
    PacManPlayer pacManPlayer = new PacManPlayer(this,3,9,9,false,new Score(this,0));
    Score score = new Score(this,0);

    public void PacManDraw(){
        if(gameStart){
            PacManGame.populateMap();
            PacManGame.PopulateMapCherry();
            score.setCurrentAmountOfCheese();
            gameStart = false;
        }
        map.createMap();
        cherry.spawnCherry();
        cheese.spawnCheese();
        pacManPlayer.movePacMan();
        pacManPlayer.drawPacMan();
        score.checkScore();
        score.drawScore();
    }
}