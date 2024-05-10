import processing.core.PApplet;

public class GUI extends PApplet {

    protected final float screen = 800;

    public void startScreen(){
        fill(0);
        textSize(25);
        textAlign(CENTER);
        text("Welcome to BoredomGames!",screen/2,screen/2);
        text("Choose a game",screen/2,screen/3);
    }

    public void displayMsg(String s){
        fill(0);
        textSize(25);
        textAlign(CENTER);
        text(s,screen/2,screen/2);
    }



    public void youLose(String s){
        fill(0);
        textSize(25);
        textAlign(CENTER);
        text(s,screen/2,screen/2);
    }

}
