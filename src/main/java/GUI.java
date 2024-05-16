import processing.core.PApplet;

public class GUI extends PApplet {

    public void startScreen(int height, int width, PApplet app) {
        app.fill(0);
        app.textSize(40);
        app.textAlign(CENTER);
        app.text("Welcome to BoredomGames!", (float) width / 2, (float) height / 3);
        app.textSize(20);
        app.text("(click anywhere to continue)", (float) width / 2, (float) height / 2);
    }

    public void comingSoon(int height, int width, PApplet app) {
        app.fill(0);
        app.textSize(40);
        app.textAlign(CENTER);
        app.text("More games coming soon!", (float) width / 2, (float) height / 3);
        app.textSize(20);
        app.text("(click anywhere to go back)", (float) width / 2, (float) height / 2);
    }
}
