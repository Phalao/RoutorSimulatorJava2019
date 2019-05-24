package Bassez.Bortolotti.Desmarescaux.utile;

import Bassez.Bortolotti.Desmarescaux.Object.Car;
import Bassez.Bortolotti.Desmarescaux.Object.Obstacle.Feu;
import Bassez.Bortolotti.Desmarescaux.Object.Ville;
import Bassez.Bortolotti.Desmarescaux.Route.Departemental;
import Bassez.Bortolotti.Desmarescaux.sample.Main;

import java.util.ArrayList;

public class Repository {

    public ArrayList<Departemental> ListRoute;
    public ArrayList<Feu> ListObstalce;
    public ArrayList<Ville> ListVille;
    public ArrayList<Voie> ListVoie;
    public ArrayList<Car> ListCar;

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

    public void refresh(Main m){
        for ( Car c: ListCar)
            c.refresh(m);
        for ( Feu f: ListObstalce)
            f.refresh(m);
        for ( Ville v: ListVille)
            v.refresh(m);
    }

    public Repository(){
        ListCar = new ArrayList<>();
        ListRoute = new ArrayList<>();
        ListObstalce = new ArrayList<>();
        ListVille = new ArrayList<>();
        ListVoie = new ArrayList<>();
    }
}
