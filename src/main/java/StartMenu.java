import processing.core.PApplet;

import java.io.File;
import java.util.ArrayList;

public class StartMenu {
    GUI gui;
    FileIO fileIO;
    GamesButton gameButton;
    ArrayList<GamesButton> buttonsFrontPage = new ArrayList<>();
    ArrayList<GamesButton> buttonGameOption = new ArrayList<>();
    ArrayList<GamesButton> buttonsDifficulties = new ArrayList<>();
    GamesButton butt1 = new GamesButton(250, 100, 225, 160, "Snake");
    GamesButton butt2 = new GamesButton(250, 100, 225, 310, "Brick Breaker");
    GamesButton butt3 = new GamesButton(250, 100, 525, 160, "Ball drop");
    GamesButton butt4 = new GamesButton(250, 100, 525, 310, "PacMan");
    GamesButton butt5 = new GamesButton(250, 100, 375, 460, "NEXT PAGE");

    GamesButton playGame = new GamesButton(250, 100, 225, 160, "Choose difficulty");
    GamesButton highScore = new GamesButton(250, 100, 225, 310, "See high scores");
    GamesButton goBack = new GamesButton(250, 100, 525, 460, "Go back to the main menu");
    GamesButton easy = new GamesButton(250, 100, 225, 160, "Easy");
    GamesButton medium = new GamesButton(250, 100, 225, 310, "Medium");
    GamesButton hard = new GamesButton(250, 100, 525, 160, "Hard");
    GamesButton goBack2 = new GamesButton(250, 100, 525, 310, "Go back to the main menu");

    GamesButton moreGamesComingSoon = new GamesButton(250, 250, 375, 160, "More games, are coming soon");

    public StartMenu() {
        //Added the buttons to use in the method chooseGame
        buttonsFrontPage.add(butt1);
        buttonsFrontPage.add(butt2);
        buttonsFrontPage.add(butt3);
        buttonsFrontPage.add(butt4);
        buttonsFrontPage.add(butt5);

        //Buttons for when the user has chosen a game
        buttonGameOption.add(playGame);
        buttonGameOption.add(highScore);
        buttonGameOption.add(goBack);

        //Buttons for when the user has chosen to start the game
        buttonsDifficulties.add(easy);
        buttonsDifficulties.add(medium);
        buttonsDifficulties.add(hard);
        buttonsDifficulties.add(goBack2);
    }

    public int showGames() {
        butt1.gamesDisplay();
        butt2.gamesDisplay();
        butt3.gamesDisplay();
        butt4.gamesDisplay();
        butt5.gamesDisplay();
        return 1;
    }

    public int showOptions() {
        playGame.gamesDisplay();
        highScore.gamesDisplay();
        goBack.gamesDisplay();
        return 1;
    }

    public int showDifficulties() {
        easy.gamesDisplay();
        medium.gamesDisplay();
        hard.gamesDisplay();
        return 1;
    }

    public void showMoreGamesComingSoon() {
        moreGamesComingSoon.gamesDisplay();
    }

    public void runDialog() {
        gui.startScreen();
    }

    public void chooseGame() {
        int action = 0;
        while (action != buttonsFrontPage.size()) {// the quit action is the last action
            action = showGames();

            switch (action) {
                case 1: //Snake
                    //todo: run snake game
                    butt1.check_click(); //Checks if the buttons was clicked by the mouse
                    nextStep();
                    break;
                case 2: //Brick Breaker
                    //todo: run Brick breaker game
                    butt2.check_click(); //Checks if the buttons was clicked by the mouse
                    nextStep();
                    break;
                case 3: //Ball drop
                    //todo: run ball drop game
                    butt3.check_click(); //Checks if the buttons was clicked by the mouse
                    nextStep();
                    break;
                case 4: //PacMan
                    //todo: run PacMan game
                    butt4.check_click(); //Checks if the buttons was clicked by the mouse
                    nextStep();
                    break;
                case 5: //Next Page
                    butt5.check_click(); //Checks if the buttons was clicked by the mouse
                    showMoreGamesComingSoon();
                    chooseGame();
                    break;
            }
        }
    }

    public void nextStep() {
        int action = 0;
        while (action != buttonGameOption.size()) {// the quit action is the last action
            action = showOptions();

            switch (action) {
                case 1: //Start the game/choose difficulty
                    //todo: call the method for choosing difficulties
                    break;
                case 2: //See highscore
                    //todo: call the method for seeing the high scores

                    break;
                case 3: //Go back to main menu
                    runDialog();
                    break;
            }
        }
    }

    public void chooseDifficulty() {
        int action = 0;
        while (action != buttonsDifficulties.size()) {// the quit action is the last action
            action = showDifficulties();

            switch (action) {
                case 1: //Easy
                    //todo: run game on easy
                    easy.check_click();
                    break;
                case 2: //Medium
                    //todo: run game on medium
                    medium.check_click();
                    break;
                case 3: //Hard
                    //todo: run game on hard
                    hard.check_click();
                    break;
                case 4: // Go back to main menu
                    runDialog();
                    break;
            }
        }
    }
}