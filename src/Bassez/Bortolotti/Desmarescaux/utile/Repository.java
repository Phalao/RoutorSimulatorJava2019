package Bassez.Bortolotti.Desmarescaux.utile;

import Bassez.Bortolotti.Desmarescaux.Object.Obstacle.Obstacle;
import Bassez.Bortolotti.Desmarescaux.Object.Ville;
import Bassez.Bortolotti.Desmarescaux.Route.Route;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Repository {

    public static ArrayList<? extends Obstacle> ListObstalce;

    public static ArrayList<Ville> ListVille;

    public static ArrayList<? extends Route> ListRoute;

    public static void afficher(Pane r,Node n){
        r.getChildren().add(n);
    }

}
