package Bassez.Bortolotti.Desmarescaux.utile;

import Bassez.Bortolotti.Desmarescaux.Object.Obstacle.Obstacle;
import Bassez.Bortolotti.Desmarescaux.Object.Ville;
import Bassez.Bortolotti.Desmarescaux.Route.Autoroute;
import Bassez.Bortolotti.Desmarescaux.Route.Departemental;
import Bassez.Bortolotti.Desmarescaux.Route.Natinonal;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Repository {

    public ArrayList<? extends Obstacle> ListObstalce;
    public ArrayList<Ville> ListVille;
    public ArrayList<Natinonal> ListRouteN;
    public ArrayList<Departemental> ListRouteD;
    public ArrayList<Autoroute> ListRouteA;
    public void afficher(Pane root) {
        for ( Natinonal n: ListRouteN)
            root.getChildren().add(n.route);
        for ( Departemental d: ListRouteD)
            root.getChildren().add(d.route);
        for ( Autoroute a: ListRouteA)
            root.getChildren().add(a.route);
        for ( Ville v: ListVille)
            root.getChildren().add(v.circle);
    }

    public void afficher(Pane r,Node n){
        r.getChildren().add(n);
    }

    public Repository(){
        ListObstalce = new ArrayList<>();
        ListVille =  new ArrayList<>();
        ListRouteN = new ArrayList<>();
        ListRouteD = new ArrayList<>();
        ListRouteA = new ArrayList<>();
    }
}
