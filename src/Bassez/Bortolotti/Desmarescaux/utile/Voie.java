package Bassez.Bortolotti.Desmarescaux.utile;

import Bassez.Bortolotti.Desmarescaux.Object.Car;
import Bassez.Bortolotti.Desmarescaux.Route.Route;
import Bassez.Bortolotti.Desmarescaux.sample.Main;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class Voie {
    public ArrayList<Car> ListVoiture;
    public Line line;
    public Route route;
    public Position A;
    public Position B;

    //=== Constructeur ===
    public Voie(Position posA, Position posB, Route r, Main m){
        this.ListVoiture = new ArrayList<>();
        this.route = r;
        this.A = posA;
        this.B = posB;
        m.repository.ListVoie.add(this);
    }

    public void afficher(Main m){
        line = new Line(A.getX(),A.getY(),B.getX(),B.getY());
        line.setStroke(Color.BLACK);//Mise en place d'une bordure de couleur noir
        line.setStrokeWidth(0.25);//Taille de la bordure
        m.root.getChildren().add(line);
    }
}
