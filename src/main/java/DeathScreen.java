import processing.core.PApplet;

// -------------------------------------------------------------
// For at bruge DeathScreen skal denne linje tilføjes til koden:
// ((StartMenu) parent).setDeathState("BallDrop", score);
// -------------------------------------------------------------

public class DeathScreen {
    FileIO io;
    private PApplet parent;
    private String playerName = "";
    private int score;
    private String gameTitle;

    public DeathScreen(PApplet parent, String gameTitle, int score, FileIO io) {
        this.parent = parent;
        this.gameTitle = gameTitle;
        this.score = score;
        this.io = io;
    }

    public void display() {
        parent.background(0); // Black background for death screen
        parent.fill(255, 0, 0);
        parent.textSize(50);
        parent.textAlign(parent.CENTER, parent.CENTER);
        parent.text("Game Over", parent.width / 2, parent.height / 2 - 100);
        parent.textSize(32);
        parent.text("Your score: " + score, parent.width / 2, parent.height / 2 - 50);
        parent.text("Type your name:", parent.width / 2, parent.height / 2 + 50);
        parent.textSize(24);
        parent.text(playerName, parent.width / 2, parent.height / 2 + 100);
        parent.textSize(18);
        parent.text("Press ENTER to submit", parent.width / 2, parent.height / 2 + 150);
    }

    public void keyPressed() {
        if (parent.key == PApplet.ENTER) {
            // Submit the score with the player's name
            saveScore(playerName);
            StartMenu.endCurrentGame();
        } else if (parent.key == PApplet.BACKSPACE && playerName.length() > 0) {
            playerName = playerName.substring(0, playerName.length() - 1);
        } else if (parent.key != PApplet.CODED) {
            playerName += parent.key;
        }
    }

    private void saveScore(String playerName) {
        io.saveHighScore(gameTitle, score, playerName);
    }
}
