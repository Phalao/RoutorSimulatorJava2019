package Bassez.Bortolotti.Desmarescaux.utile;

import Bassez.Bortolotti.Desmarescaux.Route.Route;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Voie {
    public Line route;
    public Route r;
    public Position A;
    public Position B;

    //=== Constructeur ===
    public Voie(Position posA, Position posB,Route r,Repository repository){
        this.r = r;
        this.A = posA;
        this.B = posB;
        this.route = new Line(posA.getX(),posA.getY(),posB.getX(),posB.getY());
        this.route.setStroke(Color.BLACK);//Mise en place d'une bordure de couleur noir
        this.route.setStrokeWidth(0.25);//Taille de la bordure
        repository.ListVoie.add(this);
    }
}
