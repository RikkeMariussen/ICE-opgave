import java.util.ArrayList;
import java.util.Scanner;

public class TextUI {
    private Scanner scan = new Scanner(System.in);

    public void displayList(ArrayList<String> list){
        for(String entry : list){
            displayMessage(entry);
        }
    }

    public void displayList(ArrayList<String> list, String msg){
        displayMessage(msg);
        for(String entry : list){
            displayMessage(entry);
        }
    }

    String promptText(String msg){
        displayMessage(msg);
        return scan.nextLine();
    }

    public boolean promptBinary(String msg, String accept, String reject){
        String input = promptText(msg);
        if(input.equalsIgnoreCase(accept)){
            return true;
        }else if(input.equalsIgnoreCase(reject)){
            return false;
        }else{
            return promptBinary(msg,accept, reject);
        }
    }

    public int promptNumeric(String msg){
        String input = promptText(msg);
        return Integer.parseInt(input);
    }

    public int promptChoice(ArrayList<String> optionList, String msg){
        displayMessage(msg);
        displayList(optionList, msg);
        return promptNumeric("");
    }

    void displayMessage(String msg){
        System.out.println(msg);
    }
}
