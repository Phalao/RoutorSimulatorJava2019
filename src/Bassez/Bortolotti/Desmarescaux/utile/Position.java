package Bassez.Bortolotti.Desmarescaux.utile;

public class Position {
    //=== Position en x ===
    private int x;
    //=== Position en y ===
    private int y;

    //=== Getter and Setter ===
    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }

    //=== Constructeur ===
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
