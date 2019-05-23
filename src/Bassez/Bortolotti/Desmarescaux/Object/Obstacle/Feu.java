package Bassez.Bortolotti.Desmarescaux.Object.Obstacle;

import Bassez.Bortolotti.Desmarescaux.Object.Noeud;
import Bassez.Bortolotti.Desmarescaux.Route.Route;
import Bassez.Bortolotti.Desmarescaux.sample.Main;
import Bassez.Bortolotti.Desmarescaux.utile.Position;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Feu extends Obstacle implements Noeud{
    private Rectangle rectangle;
    public Position pos;

    public Feu(Position pos, Priorite ordrePrio, Main m) {
        super(pos,ordrePrio);
        this.pos = pos;
        m.repository.ListObstalce.add(this);
    }

    public void afficher(Main m){
        rectangle = new Rectangle(pos.getX()-10,pos.getY()-10, 20, 20);
        rectangle.setFill(Color.BLACK);
        m.root.getChildren().add(rectangle);
    }
}
