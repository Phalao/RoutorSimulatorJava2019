package Bassez.Bortolotti.Desmarescaux.utile;

public class Position {
    //=== Position en x ===
    private double x;
    //=== Position en y ===
    private double y;

    public Position(Position pos) {
        this.x = pos.getX();
        this.y = pos.getY();
    }

    //=== Getter and Setter ===
    public double getX() { return x; }
    public double getY() { return y; }
    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }

    //=== Constructeur ===
    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
