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
}
