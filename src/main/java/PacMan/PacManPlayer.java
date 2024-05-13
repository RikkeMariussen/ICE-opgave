package PacMan;

import processing.core.PApplet;

public class PacManPlayer {

    private boolean god;
    private PApplet parent;
    private int hp;
    int start = 9;
    private int xPos = start;
    private int yPos = start;
    private int HP;

    public PacManPlayer(PApplet parent, int hp, int xPos, int yPos,boolean god) {
        this.parent = parent;
        this.hp = hp;
        this.xPos = xPos;
        this.yPos = yPos;
        this.god = god;
    }

    public void drawPacMan() {
        if (god == true){
            parent.fill(111);
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
            drawPacMan();
            //System.out.println(" ");
            //PacManGame.printMap();

        }else {
            return;
        }

        parent.keyPressed = false;
    }

    public void setHP ( int HP){
            this.HP = HP;
        }

        public int getHP () {
            return HP;
        }
    }

