package Bassez.Bortolotti.Desmarescaux.Object;


import Bassez.Bortolotti.Desmarescaux.utile.Position;
import Bassez.Bortolotti.Desmarescaux.utile.Repository;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ville implements Noeud{
    int capacity;
    Position pos;

    public Ville(int capacity, Position pos, Pane root) {
        this.capacity = capacity;
        this.pos = pos;
        final Circle circle = new Circle(pos.getX(), pos.getY(),capacity);//x,y,rayon
        circle.setFill(Color.RED);
        //Tentative de mettre un deuxieme point

        Repository.afficher(root,circle);

    }
}
