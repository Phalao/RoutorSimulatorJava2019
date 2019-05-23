package Bassez.Bortolotti.Desmarescaux.utile;

import Bassez.Bortolotti.Desmarescaux.Object.Noeud;
import Bassez.Bortolotti.Desmarescaux.Object.Obstacle.Feu;
import Bassez.Bortolotti.Desmarescaux.Object.Obstacle.Obstacle;
import Bassez.Bortolotti.Desmarescaux.Object.Ville;
import Bassez.Bortolotti.Desmarescaux.Route.Departemental;
import Bassez.Bortolotti.Desmarescaux.sample.Main;

import java.util.ArrayList;

public class Repository {

    public ArrayList<Departemental> ListRoute;
    public ArrayList<Feu> ListObstalce;
    public ArrayList<Ville> ListVille;
    public ArrayList<Voie> ListVoie;

    public void afficher(Main m) {
        for ( Voie v: ListVoie)
            v.afficher(m);
        for (Departemental d:ListRoute)
            d.afficher(m);
        for ( Feu f: ListObstalce)
            f.afficher(m);
        for ( Ville v: ListVille)
            v.afficher(m);
    }

    public Repository(){
        ListRoute = new ArrayList<>();
        ListObstalce = new ArrayList<>();
        ListVille = new ArrayList<>();
        ListVoie = new ArrayList<>();
    }
}
