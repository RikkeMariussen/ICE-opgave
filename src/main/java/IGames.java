public interface IGames {
    String getTitle();
    void setDiff(int value);
    String playGame();
    String setHighScore();
    String getHighScore();
    String keyPressed();
    String howToPlay();
    String youDied();
    String difficulties();
    String lives();
    void restartGame();

    void updateGame();

    void displayGame();
}
