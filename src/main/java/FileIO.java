// package utility;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;


public class FileIO {
    private ArrayList<String[]> gameList = new ArrayList<>();
    private final String highScorePath = "data/HighScore.csv";

    // Returnere en liste af alle high scores (dog højest 10) for et spil
    public ArrayList<String[]> getGameHighScore(String gameTitle) {
        ArrayList<String[]> data = new ArrayList<>();
        for (String[] element : gameList) {
            if (element[0].equalsIgnoreCase(gameTitle)) {
                data.add(element);
            }
        }
        return data;
    }

    // Returnere en liste med kun scores fra spillet
    public ArrayList<String> getScoresOnly(String gameTitle){
        return getEntryOnly(gameTitle,1);
    }

    // Returnere en liste med kun navnene fra spillet
    public ArrayList<String> getNamesOnly(String gameTitle){
        return getEntryOnly(gameTitle, 2);
    }

    private ArrayList<String> getEntryOnly(String gameTitle, int index){
        ArrayList<String> returnData = new ArrayList<>();
        ArrayList<String[]> data = getGameHighScore(gameTitle);

        for(String[] entry : data){
            returnData.add(entry[index]);
        }
        return returnData;
    }

    // Finder den højeste high score for et spil.
    public int getHighestScore(String gameTitle){
        return Integer.parseInt(getGameHighScore(gameTitle).getFirst()[1]);
    }

    // Finder den laveste score for spillet, for nemt at kunne kontrollere om en spiller kan komme med på high scoren
    public int getLowestScore(String gameTitle){
        return Integer.parseInt(getGameHighScore(gameTitle).getLast()[1]);
    }

    // Giver positionen af spillerens score i high scoren. Det er ikke en god ide at bruge denne metode direkte, men i
    // stedet gemme retur værdien i en variabel, og tjekker om den er større eller lig med 0.
    public int getScoreIndex(String gameTitle, int score){
        for(int i = 0; i < getGameHighScore(gameTitle).size(); i++){
            if(score > Integer.parseInt(getGameHighScore(gameTitle).get(i)[1])){
                return i;
            }
        }
        return -1;
    }

    public void loadHighScoreCSV() {
        loadFromCSV();
    }

    private void loadFromCSV() {
        gameList.clear();

        try (BufferedReader br = new BufferedReader(new FileReader(highScorePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");

                if (data.length >= 3) { // Check if all parts are present
                    String gameTitle = data[0].trim();
                    String score = data[1].trim();
                    String playerName = data[2].trim();

                    gameList.add(new String[]{gameTitle, score, playerName});
                } else {
                    System.err.println("Skipping malformed line: " + line);
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }


    public void saveHighScore(String gameTitle, int score, String playerName) {
        saveGameHighScore(gameTitle, score, playerName);
    }

    private void saveGameHighScore(String gameTitle, int score, String playerName) {

        try {
            BufferedReader br = new BufferedReader(new FileReader(highScorePath));
            List<String> highScoreToEdit = new ArrayList<>();
            List<String> otherHighScores = new ArrayList<>();
            String line;
            int counter = 0;
            boolean gamePresent = false;

            // Læser hele filen igennem
            while ((line = br.readLine()) != null) {
                if(line.split(";")[0].trim().equalsIgnoreCase(gameTitle)){
                    highScoreToEdit.add(line);
                    gamePresent = true;
                }else{
                    otherHighScores.add(line);
                }
                counter++;
            }

            br.close();

            // tilføjer spillet hvis det ikke findes
            if(gamePresent == false){
                highScoreToEdit = isGamePresent(highScoreToEdit, gameTitle);
            }

            // Tilføjer score og playerName til high scoren
            modifyScore(gameTitle, score, playerName, highScoreToEdit);

            // Skriver alle linjer til én arraylist
            List<String> allLines = new ArrayList<>();
            allLines.addAll(otherHighScores);
            allLines.addAll(highScoreToEdit);

            // Write all lines back to the file, overwriting the old content
            BufferedWriter bw = new BufferedWriter(new FileWriter(highScorePath, false));
            for (String s : allLines) {
                bw.write(s + "\n");
            }
            bw.close();

            // opdatering af gameList med de nye scores
            gameList.clear();
            for (String allLine : allLines) {
                String[] lineData = {allLine.split(";")[0], allLine.split(";")[1], allLine.split(";")[2]};
                gameList.add(lineData);
            }
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    private List<String> modifyScore(String gameTitle, int score, String playerName, List<String> gameData){

        // Sætter player score in på linjen under en anden tidligere score som er lige stor
        if (Integer.parseInt(gameData.getFirst().split(";")[1].trim()) == score) {
            gameData.removeLast();
            gameData.add(1, gameTitle + "; " + score + "; " + playerName);
            return gameData;
        }

        // Sætter player score ind på sidste plads, hvis player score er mindre end tidligere mindste score
        if(
                Integer.parseInt(gameData.getLast().split(";")[1].trim()) < score &&
                Integer.parseInt(gameData.get(gameData.size() - 2).split(";")[1].trim()) > score) {
            gameData.removeLast();
            gameData.addLast(gameTitle + "; " + score + "; " + playerName);
        }

        // Sætter player score ind på første plads, hvis player score er højere end tidligere største score
        else if (Integer.parseInt(gameData.getFirst().split(";")[1].trim()) < score) {
            gameData.removeLast();
            gameData.addFirst(gameTitle + "; " + score + "; " + playerName);
        }

        // Sætter player score ind på imellem to tidligere scores
        else {
            for (int i = 0; i < gameData.size() - 1; i++) {
                if (
                        Integer.parseInt(gameData.get(i).split(";")[1].trim()) > score &&
                        Integer.parseInt(gameData.get(i + 1).split(";")[1].trim()) < score ||
                        Integer.parseInt(gameData.get(i).split(";")[1].trim()) == score) {
                    gameData.removeLast();
                    gameData.add(i+1, gameTitle + "; " + score + "; " + playerName);
                    return gameData;
                }
            }
        }
        return gameData;
    }

    private List<String> isGamePresent(List<String> data, String gameTitle){
        for(int i = 0; i < data.size(); i++){
            if(data.get(i).split(";")[0].trim().equalsIgnoreCase(gameTitle)){
                return data;
            }
        }
        for(int i = 0; i < 10; i++){
            data.add(gameTitle + "; 0; ...");
        }
        return data;
    }
}
