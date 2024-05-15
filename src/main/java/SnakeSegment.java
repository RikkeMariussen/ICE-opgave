import processing.core.PApplet;

public class SnakeSegment {
    protected int x, y;
    private PApplet parent;
    private int scl = 20;

    public SnakeSegment(PApplet p, int x, int y) {
        parent = p;
        this.x = x;
        this.y = y;
    }

    public void show() {
        parent.fill(0, 255, 0);
        parent.rect(x, y, scl, scl);
    }
}
