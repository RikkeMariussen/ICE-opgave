import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public class GamesButton {
    private PApplet parent;
    PVector buttonSize, buttonLocation;
    String nameOfGame;
    int price;
    int bought = 0;
    int coins;
    Boolean inf_buys;

    GamesButton(float w, float h, float x, float y, String z) {
        buttonLocation = new PVector(x, y);
        buttonSize = new PVector(w, h);
        nameOfGame = z;
    }


    void gamesDisplay() {
        parent.strokeWeight(7);
        parent.fill(255, 255, 50);
        parent.rect(buttonLocation.x, buttonLocation.y, buttonSize.x, buttonSize.y);
        parent.fill(0);
        parent.textAlign(PConstants.CENTER);
        parent.push();
        parent.text(nameOfGame, buttonLocation.x + buttonSize.x / 2 - 2, buttonLocation.y + buttonSize.y / 2);
        parent.pop();
    }


    int click(int a) {
        coins = a;
        if ((parent.mouseX < (buttonLocation.x + buttonSize.x) && parent.mouseX > buttonLocation.x) && (parent.mouseY < (buttonLocation.y + buttonSize.y) && parent.mouseY > buttonLocation.y)) {
            if ((coins >= price) && ((inf_buys == true || bought == 0))) {
                bought += 1;
            }
        }
        return coins;
    }

    public boolean check_click() {
        if ((parent.mouseX < (buttonLocation.x + buttonSize.x) && parent.mouseX > buttonLocation.x) && (parent.mouseY < (buttonLocation.y + buttonSize.y) && parent.mouseY > buttonLocation.y)) {
            parent.fill(200, 200, 50);
            parent.rect(buttonLocation.x, buttonLocation.y, buttonSize.x, buttonSize.y);
            parent.fill(0, 0, 0);
            parent.text("Play?", buttonLocation.x + buttonSize.x / 2, buttonLocation.y + buttonSize.y / 2);
            if (parent.mousePressed) {
                //todo: call method for next part
                return true;
            }
        }else {
            parent.strokeWeight(1);
        }
        return false;
    }


}


