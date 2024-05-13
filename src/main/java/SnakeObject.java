import processing.core.PVector;
import java.util.LinkedList;

public class SnakeObject {
    LinkedList<PVector> segments = new LinkedList<>();
    PVector direction = new PVector(1, 0);  // Initial direction to the right

    public void addSegment(int x, int y) {
        segments.addFirst(new PVector(x, y));
    }

    public void move() {
        PVector head = segments.getFirst().copy();  // Copy the head to create a new head in the next cell
        head.add(direction);  // Move the head in the current direction
        segments.addFirst(head);  // Add new head
        segments.removeLast();  // Remove the last segment
    }

    public void changeDirection(int x, int y) {
        direction.set(x, y);
    }

    public LinkedList<PVector> getSegments() {
        return segments;
    }
}
