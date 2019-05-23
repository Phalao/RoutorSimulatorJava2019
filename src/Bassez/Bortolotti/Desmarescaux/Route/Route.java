package Bassez.Bortolotti.Desmarescaux.Route;

import Bassez.Bortolotti.Desmarescaux.utile.Noeud;
import Bassez.Bortolotti.Desmarescaux.utile.Voie;
import javafx.util.Pair;

public abstract class Route{
    public Noeud A;
    public Noeud B;
    public double longueur;
    public double vMax;

    public Pair<Noeud,Noeud> getVille(){
        return new Pair<>(A,B);
    }
    public abstract Voie getVoie(boolean t);
    public abstract Voie getDebut(boolean t);
}
