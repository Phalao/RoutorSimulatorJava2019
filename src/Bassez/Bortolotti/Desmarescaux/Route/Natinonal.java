package Bassez.Bortolotti.Desmarescaux.Route;

import Bassez.Bortolotti.Desmarescaux.Object.Ville;
import Bassez.Bortolotti.Desmarescaux.utile.Position;
import Bassez.Bortolotti.Desmarescaux.utile.Repository;
import Bassez.Bortolotti.Desmarescaux.utile.Voie;
import javafx.scene.layout.Pane;

public class Natinonal extends Route {
    private Voie voie1;
    private Voie voie2;

    public Voie getVoie1() {
        return voie1;
    }

    public Voie getVoie(boolean t) {
        return voie2;
    }

    public Natinonal(Ville A, Ville B, int v, Pane root, Repository repository) {
        this.voie1 = new Voie(//TODO mettre en fonction de cos // sin
                new Position(A.pos.getX(),A.pos.getY()+2.5),
                new Position(B.pos.getX(),B.pos.getY()+2.5),
                this,repository);
        this.voie2 = new Voie(
                new Position(A.pos.getX(),A.pos.getY()-2.5),
                new Position(B.pos.getX(),B.pos.getY()-2.5),
                this,repository);
        this.A = A;
        this.B = B;
        this.longueur = (B.pos.getY()-A.pos.getY())/(B.pos.getX()-A.pos.getX());
        this.vMax = v;
    }

    @Override
    public String toString() {
        return "National";
    }

    public Voie getDebut(boolean t){return voie1;}
}
