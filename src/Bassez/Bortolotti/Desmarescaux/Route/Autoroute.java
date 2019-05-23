package Bassez.Bortolotti.Desmarescaux.Route;

import Bassez.Bortolotti.Desmarescaux.Object.Ville;
import Bassez.Bortolotti.Desmarescaux.sample.Main;
import Bassez.Bortolotti.Desmarescaux.utile.Position;
import Bassez.Bortolotti.Desmarescaux.utile.Repository;
import Bassez.Bortolotti.Desmarescaux.utile.Voie;
import javafx.scene.layout.Pane;

public class Autoroute extends Route {
    private Voie voie1;
    private Voie voie2;
    private Voie voie3;
    private Voie voie4;
    private Voie voie5;
    private Voie voie6;

    public Autoroute(Ville A, Ville B, int v, Main m) {
        this.voie1 = new Voie(//TODO mettre en fonction de cos // sin
                new Position(A.pos.getX()+5,A.pos.getY()+5),
                new Position(B.pos.getX()+5,B.pos.getY()+5),
                this,this.toString(),m);
        this.voie2 = new Voie(
                new Position(A.pos.getX()+10,A.pos.getY()+10),
                new Position(B.pos.getX()+10,B.pos.getY()+10),
                this,this.toString(),m);
        this.voie3 = new Voie(
                new Position(A.pos.getX()-5,A.pos.getY()-5),
                new Position(B.pos.getX()-5,B.pos.getY()-5),
                this,this.toString(),m);
        this.voie4 = new Voie(
                new Position(A.pos.getX()-10,A.pos.getY()-10),
                new Position(B.pos.getX()-10,B.pos.getY()-10),
                this,this.toString(),m);
        this.A = A;
        this.B = B;
        this.longueur = (B.pos.getY() - A.pos.getY()) / (B.pos.getX() - A.pos.getX());
        this.vMax = v;
    }

    @Override
    public String toString() {
        return "Autoroute";
    }

    public Voie getVoie(boolean t) {
        return voie2;
    }
    public Voie getDebut(boolean t) {
        return voie2;
    }

}
