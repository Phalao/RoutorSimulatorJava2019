package Bassez.Bortolotti.Desmarescaux.Route;

import Bassez.Bortolotti.Desmarescaux.Object.Noeud;
import Bassez.Bortolotti.Desmarescaux.sample.Main;
import Bassez.Bortolotti.Desmarescaux.utile.Position;
import Bassez.Bortolotti.Desmarescaux.utile.Voie;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Departemental extends Route {
    private Voie voie1;
    private Voie voie2;
    private Voie voie3;
    private Voie voie4;
    private Line barriere;

    @Override
    public String toString() { return "Departemental"; }
    public Voie getVoie(boolean t) { return (t)? voie2 : voie3; }
    public Voie getDebut(boolean t){ return (t)? voie1 : voie4; }

    public Departemental(Noeud A, Noeud B, int v, Main m) {
        //=== Initialisation de la première Voie ===
        this.voie1 = new Voie(
                new Position(A.getPos().getX(),A.getPos().getY()+5),
                new Position(B.getPos().getX(),B.getPos().getY()+5),
                this,m);

        //=== Initialisation de la première Voie ===
        this.voie2 = new Voie(
                new Position(A.getPos().getX(),A.getPos().getY()+2.5),
                new Position(B.getPos().getX(),B.getPos().getY()+2.5),
                this,m);

        //=== Initialisation de la première Voie ===
        this.voie3 = new Voie(
                new Position(A.getPos().getX(),A.getPos().getY()-2.5),
                new Position(B.getPos().getX(),B.getPos().getY()-2.5),
                this,m);

        //=== Initialisation de la première Voie ===
        this.voie4 = new Voie(
                new Position(A.getPos().getX(),A.getPos().getY()-5),
                new Position(B.getPos().getX(),B.getPos().getY()-5),
                this,m);

        //=== Caratéristique ===
        this.A = A;
        this.B = B;
        this.longueur = (B.getPos().getY()-A.getPos().getX())/(B.getPos().getX()-A.getPos().getX());
        this.vMax = v;
        m.repository.ListRoute.add(this);
    }

    public void afficher(Main m){
        barriere = new Line(A.getPos().getX(),A.getPos().getY(),B.getPos().getX(),B.getPos().getY());
        barriere.setStroke(Color.BLACK);
        barriere.setStrokeWidth(0.75);
        m.root.getChildren().add(barriere);
    }
}
