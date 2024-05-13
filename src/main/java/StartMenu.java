import processing.core.PApplet;

import java.util.ArrayList;

public class StartMenu extends PApplet{
    GUI gui = new GUI();
    FileIO fileIO;
    GamesButton gameButton;
    IGames snake = new Snake();
    //IGames brickBreaker = new BrickBreaker(); virker ikke grundet forket extension
    //IGames ballDrop = new BallDrop(); virker ikke grundet forket extension
    IGames pacman = new PacMan();

    ArrayList<GamesButton> buttonsFrontPage = new ArrayList<>();
    ArrayList<GamesButton> buttonGameOption = new ArrayList<>();
    ArrayList<GamesButton> buttonsDifficulties = new ArrayList<>();

    // Initialisering af variablerne i setup metoden
    GamesButton butt1, butt2, butt3, butt4, butt5, playGame, highScore, goBack, easy, medium, hard, goBack2, moreGamesComingSoon;

    // Enums til at styre programmet
    // Enum er "states" som programmet kan være i, fx starter vi i state "START_MENU", hvor vi bliver vist en velkommen
    // skærm. Klikker vi derefter på musen kommer programmets state til at være "GAME_SELECTION". Dette er for at gøre
    // det nemmere at håndtere forskellige situationer i programmet, og undgå store mængder kode, samt at gøre
    // koden clean.
    enum AppState {
        START_MENU, GAME_SELECTION, GAME_OPTIONS, DIFFICULTY_SELECTION, SHOW_HIGHSCORE, GAMING
    }

    enum SelectedGame {
        NONE, SNAKE, BRICKBREAKER, BALLDROP, PACMAN
    }

    enum Difficulty {
        NONE, EASY, MEDIUM, HARD, DEFAULT
    }

    // Deklarering af "state" variablerne
    AppState currentState;
    SelectedGame selectedGame;
    Difficulty selectedDifficulty;



    public void settings(){
        size(800,600);
        currentState = AppState.START_MENU;
    }

    public void setup(){
        this.fileIO = new FileIO();
        fileIO.loadHighScoreCSV();

        //buttons flyttet hertil for at kunne bruge width og height til placering
        butt1 = new GamesButton(250, 100, (float) (width * 3) /10 , (float) (height * 2) /5, "Snake", this);
        butt2 = new GamesButton(250, 100, (float) (width * 7) /10, (float) (height * 2) /5, "Brick Breaker", this);
        butt3 = new GamesButton(250, 100, (float) (width * 3) /10, (float) (height * 3) /5, "Ball drop", this);
        butt4 = new GamesButton(250, 100, (float) (width * 7) /10, (float) (height * 3) /5, "PacMan", this);
        butt5 = new GamesButton(250, 100, (float) (width * 5) /10, (float) (height * 4) /5, "NEXT PAGE", this);

        playGame = new GamesButton(250, 100, (float) width/2, 160, "Choose difficulty", this);
        highScore = new GamesButton(250, 100, (float) width/2, 310, "See high scores", this);
        goBack = new GamesButton(250, 100, (float) width/2, 460, "Go back to the main menu", this);
        easy = new GamesButton(250, 100, (float) width*3/10, (float) (height * 1) /3, "Easy", this);
        medium = new GamesButton(250, 100, (float) width*7/10, (float) (height * 1) /3, "Medium", this);
        hard = new GamesButton(250, 100, (float) width/2, (float) (height * 2) /3, "Hard", this);
        goBack2 = new GamesButton(250, 100, 525, 310, "Go back to the main menu", this);

        moreGamesComingSoon = new GamesButton(250, 250, 375, 160, "More games, are coming soon", this);

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

    public void draw() {
        background(100);
        switch (currentState) {
            case START_MENU:
                runDialog();
                if (mousePressed) {
                    currentState = AppState.GAME_SELECTION;
                    mousePressed = false;  // Sørger for at man ikke kan komme til at klike forkert ved at holde musse knappen nede.
                }
                break;
            case GAME_SELECTION:
                showGames();  // Display all game buttons
                checkGameSelection();
                if (mousePressed) {
                    mousePressed = false;
                }
                break;
            case GAME_OPTIONS:
                showOptions();
                checkOptionSelection();
                if (mousePressed) {
                    mousePressed = false;
                }
                break;
            case DIFFICULTY_SELECTION:
                showDifficulties();
                checkDifficultySelection();
                if (mousePressed) {
                    mousePressed = false;
                }
                break;
            case SHOW_HIGHSCORE:
                showHighScores(selectedGame);
                checkHighScoreSelection();
                if (mousePressed) {
                    mousePressed = false;
                }
                break;
            case GAMING:
                gaming();
                if (mousePressed) {
                    mousePressed = false;
                }
                break;

            // Handle other states similarly...
        }
    }

    private void checkGameSelection() {
        if (butt1.check_click()) {
            currentState = AppState.GAME_OPTIONS;
            selectedGame = SelectedGame.SNAKE;

        } else if (butt2.check_click()) {
            currentState = AppState.GAME_OPTIONS;
            selectedGame = SelectedGame.BRICKBREAKER;

        } else if (butt3.check_click()) {
            currentState = AppState.GAME_OPTIONS;
            selectedGame = SelectedGame.BALLDROP;

        } else if (butt4.check_click()) {
            currentState = AppState.GAME_OPTIONS;
            selectedGame = SelectedGame.PACMAN;

        } else if (butt5.check_click()) {
            currentState = AppState.GAME_OPTIONS;
        }
    }

    private void checkOptionSelection(){
        if (playGame.check_click()) {
            currentState = AppState.DIFFICULTY_SELECTION;

        } else if (highScore.check_click()) {
            currentState = AppState.SHOW_HIGHSCORE;

        } else if (goBack.check_click()) {
            currentState = AppState.GAME_SELECTION;
            selectedGame = SelectedGame.NONE;
        }
    }

    private void checkDifficultySelection(){
        if (easy.check_click()) {
            selectedDifficulty = Difficulty.EASY;
            currentState = AppState.GAMING;

        } else if (medium.check_click()) {
            selectedDifficulty = Difficulty.MEDIUM;
            currentState = AppState.GAMING;

        } else if (hard.check_click()) {
            selectedDifficulty = Difficulty.HARD;
            currentState = AppState.GAMING;

        }
    }

    private void checkHighScoreSelection(){
        if (goBack.check_click()) {
            currentState = AppState.GAME_OPTIONS;
        }
    }

    public void showGames() {
        butt1.gamesDisplay();
        butt2.gamesDisplay();
        butt3.gamesDisplay();
        butt4.gamesDisplay();
        butt5.gamesDisplay();
    }

    public void showOptions() {
        playGame.gamesDisplay();
        highScore.gamesDisplay();
        goBack.setText("Back To Games");
        goBack.gamesDisplay();
    }

    public void showDifficulties() {
        easy.gamesDisplay();
        medium.gamesDisplay();
        hard.gamesDisplay();
    }

    public void gaming() {
        switch (selectedGame) {
            case SNAKE:
                if (snake == null) {
                    snake = new Snake();
                    snake.playGame();
                }
                //snake.updateGame();
                //snake.displayGame();
                break;

            case BRICKBREAKER:
                /*
                if (brickbreaker == null) {
                    brickbreaker = new Snake();
                    brickbreaker.playGame();
                }
                //brickbreaker.updateGame();
                //brickbreaker.displayGame();
                break;
                */

            case BALLDROP:
                /*
                if (balldrop == null) {
                    balldrop = new Snake();
                    balldrop.playGame();
                }
                //balldrop.updateGame();
                //balldrop.displayGame();
                break;
                */

            case PACMAN:
                if (pacman == null) {
                    pacman = new Snake();
                    pacman.playGame();
                }
                //pacman.updateGame();
                //snake.displayGame();
                break;
        }
    }


    private void endCurrentGame() {
        currentState = AppState.GAME_SELECTION;
        selectedGame = SelectedGame.NONE;
        selectedDifficulty = Difficulty.NONE;
    }

    public void showHighScores(SelectedGame game) {

        background(100); // Clear the screen
        ArrayList<String[]> scores = fileIO.getGameHighScore(game.toString());
        float y = 50; // Starting y position for scores
        textAlign(CENTER, TOP);
        textSize(24);
        fill(255);
        text("High Scores for " + game, (float) width / 2, 10);
        textSize(20);
        for (String[] scoreEntry : scores) {
            String scoreText = scoreEntry[2] + " - " + scoreEntry[1]; // playerName - score
            text(scoreText, (float) width / 2, y);
            y += 20; // Increment y for the next score
        }
        goBack.setText("Back");
        goBack.gamesDisplay();
    }


    public void runDialog() {
        gui.startScreen(height, width, this);
    }



    /*

    //todo: -----------------------------------------------------------------------------------
    //todo: -----------------------------------------------------------------------------------
    //todo: Alle Metoder nedenfor gør ikke noget funktionelt ind til videre (måske unødvendigt)
    //todo: -----------------------------------------------------------------------------------
    //todo: -----------------------------------------------------------------------------------

    public void showMoreGamesComingSoon() {
        moreGamesComingSoon.gamesDisplay();
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
    */

}