package PacMan;
import processing.core.PApplet;


public class DefNotMain{
    private PApplet parent;
    private boolean gameStart = true;

    private Map map;
    private Cherry cherry;
    private Cheese cheese;
    private PacManPlayer pacManPlayer;
    private Score score;

    public DefNotMain(PApplet parent){
        this.parent = parent;

        this.map = new Map(parent);
        this.cherry = new Cherry(parent);
        this.cheese = new Cheese(parent);
        this.pacManPlayer = new PacManPlayer(parent,3,9,9,false,new Score(parent,0));
        this.score = new Score(parent,0);
    }


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