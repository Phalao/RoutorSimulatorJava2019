package Bassez.Bortolotti.Desmarescaux.utile;

import Bassez.Bortolotti.Desmarescaux.Object.Obstacle.Obstacle;
import Bassez.Bortolotti.Desmarescaux.Object.Ville;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Repository {

    public ArrayList<? extends Obstacle> ListObstalce;
    public ArrayList<Ville> ListVille;
    public ArrayList<Voie> ListVoie;

    public void afficher(Pane root) {
        for ( Voie v: ListVoie)
            root.getChildren().add(v.route);
        for ( Ville v: ListVille)
            root.getChildren().add(v.circle);
    }

    public Repository(){
        ListObstalce = new ArrayList<>();
        ListVille =  new ArrayList<>();
        ListVoie = new ArrayList<>();
    }
}
