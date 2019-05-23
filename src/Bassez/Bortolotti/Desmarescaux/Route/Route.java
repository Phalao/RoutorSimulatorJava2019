package Bassez.Bortolotti.Desmarescaux.Route;

import Bassez.Bortolotti.Desmarescaux.Object.Noeud;
import Bassez.Bortolotti.Desmarescaux.utile.Voie;

public abstract class Route{
    public Noeud A;
    public Noeud B;
    public double longueur;
    public double vMax;

    public abstract Voie getVoie(boolean t);
    public abstract Voie getDebut(boolean t);
}
