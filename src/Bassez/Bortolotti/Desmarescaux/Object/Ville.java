package Bassez.Bortolotti.Desmarescaux.Object;


import Bassez.Bortolotti.Desmarescaux.utile.Position;
import Bassez.Bortolotti.Desmarescaux.utile.Repository;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class Ville implements Noeud{
    private int capacity;
    public Position pos;
    public Circle circle;
    public String name;

    public Ville(String name,int capacity, Position pos, Pane root, Repository repository) {
        this.name = name;
        this.capacity = capacity;
        this.pos = pos;
        circle = new Circle(pos.getX(), pos.getY(),capacity);//x,y,rayon
        circle.setFill(Color.RED);
        //Tentative de mettre un deuxieme point
        final RadialGradient gradient_1 = new RadialGradient(0, 0, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.RED),
                new Stop(1, Color.CRIMSON));
        circle.setFill(gradient_1);
        repository.ListVille.add(this);
        Text text = new Text(this.toString());
        text.setLayoutX(pos.getX()+capacity);
        text.setLayoutY(pos.getY()-capacity);
        text.setFill(Color.BLUE);
        root.getChildren().add(text);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
