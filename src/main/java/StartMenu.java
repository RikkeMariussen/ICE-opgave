import PacMan.PacMan;
import processing.core.PApplet;

import java.util.ArrayList;

public class StartMenu extends PApplet {

    private static AppState currentState = AppState.START_MENU;
    private static SelectedGame selectedGame = SelectedGame.NONE;
    private static Difficulty selectedDifficulty = Difficulty.NONE;


    private GUI gui = new GUI();
    private FileIO fileIO;
    private GamesButton gameButton;
    private static DeathScreen deathScreen;
    private IGames snake;
    //IGames brickBreaker = new BrickBreaker(); //virker ikke grundet forket extension

    private IGames brickBreaker;
    private IGames ballDrop;
    private PacMan pacman;

    private ArrayList<GamesButton> buttonsFrontPage = new ArrayList<>();
    private ArrayList<GamesButton> buttonGameOption = new ArrayList<>();
    private ArrayList<GamesButton> buttonsDifficulties = new ArrayList<>();

    // Initialisering af variablerne i setup metoden
    private GamesButton butt1, butt2, butt3, butt4, butt5, playGame, highScore, goBack, easy, medium, hard, goBack2, moreGamesComingSoon;

    // Enums til at styre programmet
    // Enum er "states" som programmet kan være i, fx starter vi i state "START_MENU", hvor vi bliver vist en velkommen
    // skærm. Klikker vi derefter på musen kommer programmets state til at være "GAME_SELECTION". Dette er for at gøre
    // det nemmere at håndtere forskellige situationer i programmet, og undgå store mængder kode, samt at gøre
    // koden clean.
    enum AppState {
        START_MENU, GAME_SELECTION, GAME_OPTIONS, DIFFICULTY_SELECTION, SHOW_HIGHSCORE, GAMING, DEATH_SCREEN
    }

    enum SelectedGame {
        NONE, SNAKE, BRICKBREAKER, BALLDROP, PACMAN
    }

    enum Difficulty {
        NONE(0), EASY(1), MEDIUM(2), HARD(3), DEFAULT(4);
        private final int value;

        Difficulty(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public void settings() {
        size(800, 600);
    }

    public void setup() {
        this.fileIO = new FileIO();
        fileIO.loadHighScoreCSV();

        //buttons flyttet hertil for at kunne bruge width og height til placering
        butt1 = new GamesButton(250, 100, (float) (width * 3) / 10, (float) (height * 2) / 5, "Snake", this);
        butt2 = new GamesButton(250, 100, (float) (width * 7) / 10, (float) (height * 2) / 5, "Brick Breaker", this);
        butt3 = new GamesButton(250, 100, (float) (width * 3) / 10, (float) (height * 3) / 5, "Ball drop", this);
        butt4 = new GamesButton(250, 100, (float) (width * 7) / 10, (float) (height * 3) / 5, "PacMan", this);
        butt5 = new GamesButton(250, 100, (float) (width * 5) / 10, (float) (height * 4) / 5, "NEXT PAGE", this);

        playGame = new GamesButton(250, 100, (float) width / 2, 160, "Choose difficulty", this);
        highScore = new GamesButton(250, 100, (float) width / 2, 310, "See high scores", this);
        goBack = new GamesButton(250, 100, (float) width / 2, 460, "Go back to the main menu", this);
        easy = new GamesButton(250, 100, (float) width * 3 / 10, (float) (height * 1) / 3, "Easy", this);
        medium = new GamesButton(250, 100, (float) width * 7 / 10, (float) (height * 1) / 3, "Medium", this);
        hard = new GamesButton(250, 100, (float) width / 2, (float) (height * 2) / 3, "Hard", this);
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

        // For testing
        //if(frameCount % 20 == 0){
        //    System.out.println();
        //    System.out.println(currentState);
        //    System.out.println(selectedGame);
        //    System.out.println(selectedDifficulty);
        //}

        if (currentState != AppState.GAMING) {
            background(100);
        }
        if (currentState != AppState.GAMING && selectedGame == SelectedGame.NONE && selectedDifficulty == Difficulty.NONE) {
            windowResize(800, 600);
        }

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
            case DEATH_SCREEN:
                if (deathScreen != null) {
                    deathScreen.display();
                }
                break;
        }
    }

    private void checkGameSelection() {
        //Snake
        if (butt1.check_click()) {
            currentState = AppState.GAME_OPTIONS;
            selectedGame = SelectedGame.SNAKE;

            //This is BrickBreaker
        } else if (butt2.check_click()) {
            currentState = AppState.GAME_OPTIONS;
            selectedGame = SelectedGame.BRICKBREAKER;

            //Ball Drop
        } else if (butt3.check_click()) {
            currentState = AppState.GAME_OPTIONS;
            selectedGame = SelectedGame.BALLDROP;

            //PacMan
        } else if (butt4.check_click()) {
            currentState = AppState.GAME_OPTIONS;
            selectedGame = SelectedGame.PACMAN;

            //Next page
        } else if (butt5.check_click()) {
            currentState = AppState.GAME_OPTIONS;
        }
    }

    private void checkOptionSelection() {
        if (playGame.check_click()) {
            currentState = AppState.DIFFICULTY_SELECTION;

        } else if (highScore.check_click()) {
            currentState = AppState.SHOW_HIGHSCORE;

        } else if (goBack.check_click()) {
            currentState = AppState.GAME_SELECTION;
            selectedGame = SelectedGame.NONE;
        }
    }

    private void checkDifficultySelection() {
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

    private void checkHighScoreSelection() {
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
                    snake = new Snake(this,selectedDifficulty.getValue());
                    snake.playGame();
                }
                snake.updateGame();
                snake.displayGame();
                break;

            case BRICKBREAKER:

                if (brickBreaker == null) {
                    brickBreaker = new BrickBreaker(this,width,height, selectedDifficulty.getValue());
                    brickBreaker.playGame();
                }
                brickBreaker.updateGame();
                brickBreaker.displayGame();
                break;


            case BALLDROP:

                if (ballDrop == null) {
                    ballDrop = new BallDrop(this, width, height, selectedDifficulty.getValue());
                    ballDrop.playGame();
                }
                ballDrop.displayGame();
                ballDrop.updateGame();
                break;

            case PACMAN:
                if (pacman == null) {
                    pacman = new PacMan(this);
                }
                pacman.PacManDraw();
                break;
        }
    }


    public static synchronized void endCurrentGame() {
        currentState = AppState.GAME_SELECTION;
        selectedGame = SelectedGame.NONE;
        selectedDifficulty = Difficulty.NONE;
    }

    public void setDeathState(String gameTitle, int score) {
        currentState = AppState.DEATH_SCREEN;
        deathScreen = new DeathScreen(this, gameTitle, score, fileIO); // Pass the current instance of StartMenu
    }

    public void keyPressed() {
        if (currentState == AppState.DEATH_SCREEN && deathScreen != null) {
            deathScreen.keyPressed();
        }
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
            String scoreText = scoreEntry[1] + " - " + scoreEntry[2]; // playerName - score
            text(scoreText, (float) width / 2, y);
            y += 20; // Increment y for the next score
        }
        goBack.setText("Back");
        goBack.gamesDisplay();
    }


    public void runDialog() {
        gui.startScreen(height, width, this);
    }
}