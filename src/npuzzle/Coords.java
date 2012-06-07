/**
 * 
 */
package npuzzle;

/**
 * Klasa koordynatów.
 * 
 * @author mateusz
 */
public class Coords {
    public Coords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Coords add(Coords other) {
        return new Coords(this.x + other.getX(), this.y + other.getY());
    }

    public Coords sub(Coords other) {
        return new Coords(this.x - other.getX(), this.y - other.getY());
    }

    public int calculateTaxcabDistance() {
        return Math.abs(x) + Math.abs(y);
    }

    private int x = 0;
    private int y = 0;
}
