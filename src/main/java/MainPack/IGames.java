package MainPack;

public interface IGames {
    String getTitle();
    String playGame();
    String setHighScore();
    String getHighScore();
    String keyPressed();
    String howToPlay();
    String youDied();
    String difficulties();
    String lives();

    void updateGame();

    void displayGame();
}
