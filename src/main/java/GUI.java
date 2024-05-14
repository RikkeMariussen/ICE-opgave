import processing.core.PApplet;

public class GUI extends PApplet {


    public void startScreen(int height, int width, PApplet app) {
        app.fill(0);
        app.textSize(25);
        app.textAlign(CENTER);
        app.text("Welcome to BoredomGames!", (float) width / 2, (float) height / 3);
        //app.text("Choose a game", (float) width / 2, (float) height / 2);
        app.textSize(16);
        app.text("(click anywhere to continue)", (float) width / 2, (float) height / 2);
    }

    public void displayMsg(PApplet app, String s, int height, int width) {
        app.fill(0);
        app.textSize(25);
        app.textAlign(CENTER);
        app.text(s, (float) width / 2, (float) height / 2);
    }

   /* public void highScoreMsg(PApplet app, int height, int width) {
        app.fill(0);
        app.textSize(25);
        app.textAlign(CENTER);
        app.text("Type your name to add it to the high score list:", (float) width / 2, (float) height / 2);
    }*/

    public void youLose(PApplet app, String s, int height, int width) {
        app.fill(0);
        app.textSize(25);
        app.textAlign(CENTER);
        app.text(s, (float) width / 2, (float) height / 2);
    }
}
