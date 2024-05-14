import processing.core.PApplet;

public class Timer extends PApplet {

    private int startTime; //For the dropBalls
    private int interval;

    //Constructor
    Timer(int timeInterval) {
        interval = timeInterval;
    }

    //Methods
    public void startTimer() {
        startTime = millis();
    }


    public boolean complete() {
        int elapsedTime = millis() - startTime;
        if (elapsedTime > interval) {
            return true;
        } else {
            return false;
        }
    }
}
