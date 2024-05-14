package PacMan;

import processing.core.PApplet;
import java.util.Timer;
import java.util.TimerTask;


public class PacManPlayer {

    private boolean god;
    private PApplet parent;
    private int hp;
    private Score score;
    int start = 9;
    private int xPos = start;
    private int yPos = start;
    private int HP;
    Timer timer = new Timer();

    public PacManPlayer(PApplet parent, int hp, int xPos, int yPos,boolean god,Score score) {
        this.parent = parent;
        this.hp = hp;
        this.xPos = xPos;
        this.yPos = yPos;
        this.god = god;
        this.score = score;
    }

    public void drawPacMan() {
        if (god){
            parent.fill(20,65,244);
            PacManGame.map[yPos][xPos] = PacManGame.PLAYER;
            parent.ellipse(xPos * Map.getWallSpacing(), yPos * Map.getWallSpacing(), 55, 55);
        }
        PacManGame.map[yPos][xPos] = PacManGame.PLAYER;
        parent.ellipse(xPos * Map.getWallSpacing(), yPos * Map.getWallSpacing(), 55, 55);
    }


    public void movePacMan() {
        if (!parent.keyPressed) return;

        int nextY = yPos, nextX = xPos;

        if (parent.key == 'w') nextY--;
        else if (parent.key == 'a') nextX--;
        else if (parent.key == 'd') nextX++;
        else if (parent.key == 's') nextY++;
        else {
            parent.keyPressed = false;
            return;
        }

        if (PacManGame.map[nextY][nextX] == PacManGame.CHEESE || PacManGame.map[nextY][nextX] == PacManGame.EMPTY) {
            PacManGame.map[yPos][xPos] = PacManGame.EMPTY;
            yPos = nextY;
            xPos = nextX;
            PacManGame.map[yPos][xPos] = PacManGame.PLAYER;
            drawPacMan();
            //System.out.println(" ");
            //PacManGame.printMap();
        } else if(PacManGame.map[nextY][nextX] == PacManGame.CHERRY) {
            PacManGame.map[yPos][xPos] = PacManGame.EMPTY;
            yPos = nextY;
            xPos = nextX;
            PacManGame.map[yPos][xPos] = PacManGame.PLAYER;
            god = true;
            godTimer();
            drawPacMan();
            //System.out.println(" ");
            //PacManGame.printMap();

        }else {
            return;
        }

        parent.keyPressed = false;
    }

    private void godTimer(){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                god = false;
            }
        }, 10000);
    }

    public void setHP ( int HP){
            this.HP = HP;
        }

        public int getHP () {
            return HP;
        }
    }

