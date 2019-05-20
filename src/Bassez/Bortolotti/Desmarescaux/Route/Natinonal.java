package Bassez.Bortolotti.Desmarescaux.Route;

import Bassez.Bortolotti.Desmarescaux.Object.Ville;
import Bassez.Bortolotti.Desmarescaux.utile.Position;
import Bassez.Bortolotti.Desmarescaux.utile.Repository;
import Bassez.Bortolotti.Desmarescaux.utile.Voie;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Natinonal extends Route {
    private Voie voie1;
    private Voie voie2;

    public Natinonal(Ville A, Ville B, int v, Pane root,Repository repository) {
        this.voie1 = new Voie();
        this.voie2 = new Voie();
        this.route = new Line(A.pos.getX(),A.pos.getY(),B.pos.getX(),B.pos.getY());
        this.A = A;
        this.B = B;
        this.pos = new Position(A.pos.getX()-B.pos.getY(),A.pos.getY()-B.pos.getY());
        this.longueur = (int) (B.pos.getY()-A.pos.getY())/(B.pos.getX()-A.pos.getX());
        this.vMax = v;
        this.route.setStroke(Color.BLACK);//Mise en place d'une bordure de couleur noir
        this.route.setStrokeWidth(3);//Taille de la bordure
        repository.ListRouteN.add(this);
    }
}
