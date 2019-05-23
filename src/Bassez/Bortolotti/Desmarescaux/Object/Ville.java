package Bassez.Bortolotti.Desmarescaux.Object;


import Bassez.Bortolotti.Desmarescaux.sample.Main;
import Bassez.Bortolotti.Desmarescaux.utile.Position;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class Ville implements Noeud {
    private int capacity;
    public Position pos;
    public Circle circle;
    public String name;

    @Override
    public String toString() { return this.name; }
    public Position getPos(){ return pos; }
    public double gettaille(){ return capacity;};

    public Ville(String name, int capacity, Position pos, Main m) {
        this.name = name;
        this.capacity = capacity;
        this.pos = pos;
        m.repository.ListVille.add(this);
    }

    public void afficher(Main m){
        circle = new Circle(pos.getX(), pos.getY(),capacity);
        circle.setFill(Color.RED);
        final RadialGradient gradient_1 = new RadialGradient(0, 0, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.RED),
                new Stop(1, Color.CRIMSON));
        circle.setFill(gradient_1);
        Text text = new Text(this.toString());
        text.setLayoutX(pos.getX()+capacity);
        text.setLayoutY(pos.getY()-capacity);
        text.setFill(Color.BLUE);
        m.root.getChildren().add(text);
        m.root.getChildren().add(circle);
    }
}
