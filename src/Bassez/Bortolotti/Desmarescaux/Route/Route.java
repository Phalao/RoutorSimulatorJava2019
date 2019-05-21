package Bassez.Bortolotti.Desmarescaux.Route;

import Bassez.Bortolotti.Desmarescaux.utile.Noeud;
import javafx.util.Pair;

public class Route{
    public Noeud A;
    public Noeud B;
    public double longueur;
    public double vMax;

    public Pair<Noeud,Noeud> getVille(){
        return new Pair<>(A,B);
    }
}
