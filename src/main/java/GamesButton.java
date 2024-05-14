import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public class GamesButton {
    private PApplet app;
    protected PVector buttonSize, buttonLocation, defaultLocation;
    protected String nameOfGame, defaultName;

    GamesButton(float w, float h, float x, float y, String z, PApplet app) {
        this.app = app;
        this.buttonSize = new PVector(w, h);
        this.buttonLocation = new PVector(x, y);
        this.nameOfGame = z;
        this.defaultLocation = new PVector(x, y); // Store the default location
        this.defaultName = z; // Store the default name

        app.rectMode(PConstants.CENTER); // This makes position calculations easier
    }

    public void gamesDisplay() {
        app.textSize(20);
        app.strokeWeight(7);
        app.fill(255, 255, 50);
        app.rect(buttonLocation.x, buttonLocation.y, buttonSize.x, buttonSize.y);
        app.fill(0);
        app.textAlign(PConstants.CENTER, PConstants.CENTER);
        app.text(nameOfGame, buttonLocation.x, buttonLocation.y);
    }

    public boolean check_click() {
        if ((app.mouseX < (buttonLocation.x + buttonSize.x/2) && app.mouseX > (buttonLocation.x - buttonSize.x/2)) && (app.mouseY < (buttonLocation.y + buttonSize.y/2) && app.mouseY > (buttonLocation.y - buttonSize.y/2))) {

            app.strokeWeight(7);
            app.fill(200, 200, 50);
            app.rectMode(3);
            app.rect(buttonLocation.x, buttonLocation.y, buttonSize.x, buttonSize.y);
            app.fill(0);
            app.textAlign(PConstants.CENTER);
            app.push();
            app.text(nameOfGame, buttonLocation.x, buttonLocation.y);
            app.pop();

            if (app.mousePressed) {
                return true;
            }
        }else {
            app.strokeWeight(1);
        }
        return false;
    }

    public void setText(String newText) {
        this.nameOfGame = newText;
    }

    public void setPosition(float x, float y) {
        this.buttonLocation.set(x, y);
    }

    public void resetToDefault() {
        this.buttonLocation.set(defaultLocation.x, defaultLocation.y);
        this.nameOfGame = defaultName;
    }
}

