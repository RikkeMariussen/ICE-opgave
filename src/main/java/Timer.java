import processing.core.PApplet;

public class Timer extends PApplet {

    int startTime; //For the dropBalls
    int interval;

    //Constructor
    Timer(int timeInterval) {
        interval = timeInterval;
    }

    //Methods
    void startTimer() {
        startTime = millis();
    }


    boolean complete() {
        int elapsedTime = millis() - startTime;
        if (elapsedTime > interval) {
            return true;
        } else {
            return false;
        }
    }
}
