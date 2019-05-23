package Bassez.Bortolotti.Desmarescaux.Route;

import Bassez.Bortolotti.Desmarescaux.Object.Ville;
import Bassez.Bortolotti.Desmarescaux.utile.Position;
import Bassez.Bortolotti.Desmarescaux.utile.Repository;
import Bassez.Bortolotti.Desmarescaux.utile.Voie;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Departemental extends Route {
    private Voie voie1;
    private Voie voie2;
    private Voie voie3;
    private Voie voie4;
    private Line barriere;

    public Departemental(Ville A, Ville B, int v, Pane root, Repository repository) {
        this.voie1 = new Voie(//TODO mettre en fonction de cos // sin
                new Position(A.pos.getX(), A.pos.getY() + 8),
                new Position(B.pos.getX(), B.pos.getY() + 8),
                this, repository);
        this.voie2 = new Voie(
                new Position(A.pos.getX(), A.pos.getY() + 4),
                new Position(B.pos.getX(), B.pos.getY() + 4),
                this, repository);
        this.voie3 = new Voie(//TODO mettre en fonction de cos // sin
                new Position(B.pos.getX(), B.pos.getY() - 4),
                new Position(A.pos.getX(), A.pos.getY() - 4),
                this, repository);
        this.voie4 = new Voie(
                new Position(B.pos.getX(), B.pos.getY() - 8),
                new Position(A.pos.getX(), A.pos.getY() - 8),
                this, repository);
        this.A = A;
        this.B = B;
        this.longueur = (B.pos.getY() - A.pos.getY()) / (B.pos.getX() - A.pos.getX());
        this.vMax = v;
        barriere = new Line(A.pos.getX(),A.pos.getY(),B.pos.getX(),B.pos.getY());
        barriere.setStroke(Color.BLACK);//Mise en place d'une bordure de couleur noir
        barriere.setStrokeWidth(0.75);//Taille de la bordure;
        root.getChildren().add(barriere);
    }

    @Override
    public String toString() {
        return "Departemental";
    }

    public Voie getVoie(boolean t) {
        if (t)
            return voie2;
        else
            return voie3;
    }

    public Voie getDebut(boolean t){
        if (t)
            return voie1;
        else
            return voie4;
    }
}
