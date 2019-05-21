package Bassez.Bortolotti.Desmarescaux.Route;

import Bassez.Bortolotti.Desmarescaux.Object.Ville;
import Bassez.Bortolotti.Desmarescaux.utile.Repository;
import Bassez.Bortolotti.Desmarescaux.utile.Voie;
import javafx.scene.layout.Pane;

public class Departemental extends Route {
    private Voie voie1;
    private Voie voie2;
    private Voie voie3;

    public Departemental(Ville A, Ville B, int v, Pane root,Repository repository){
        //TODO mettre en fonction de cos // sin
        voie1 = new Voie(A.pos,B.pos,this,repository);
        voie2 = new Voie(A.pos,B.pos,this,repository);
        voie3 = new Voie(A.pos,B.pos,this,repository);
        this.A = A;
        this.B = B;
        this.longueur = (B.pos.getY()-A.pos.getY())/(B.pos.getX()-A.pos.getX());
        this.vMax = v;
    }

    @Override
    public String toString() {
        return "Departemental";
    }
}
