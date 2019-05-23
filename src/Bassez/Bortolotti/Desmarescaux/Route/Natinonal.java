package Bassez.Bortolotti.Desmarescaux.Route;

import Bassez.Bortolotti.Desmarescaux.Object.Noeud;
import Bassez.Bortolotti.Desmarescaux.sample.Main;
import Bassez.Bortolotti.Desmarescaux.utile.Position;
import Bassez.Bortolotti.Desmarescaux.utile.Voie;

public class Natinonal extends Route {
    private Voie voie1;
    private Voie voie2;

    @Override
    public String toString() {
        return "National";
    }
    public Voie getVoie(boolean t) { return (t)? voie2 : voie1; }
    public Voie getDebut(boolean t){ return (t)? voie1 : voie2; }

    public Natinonal(Noeud A, Noeud B, int vitesseMax, Main m) {
        //=== Initialisation de la première Voie ===
        this.voie1 = new Voie(
                new Position(A.getPos().getX(),A.getPos().getY()+2.5),
                new Position(B.getPos().getX(),B.getPos().getY()+2.5),
                this,m);

        //=== Initialisation de la deuxième Voie ===
        this.voie2 = new Voie(
                new Position(A.getPos().getX(),A.getPos().getY()-2.5),
                new Position(B.getPos().getX(),B.getPos().getY()-2.5),
                this,m);

        //=== Caratéristique ===
        this.A = A;
        this.B = B;
        this.longueur = (B.getPos().getY()-A.getPos().getX())/(B.getPos().getX()-A.getPos().getX());
        this.vMax = vitesseMax;
    }
}
