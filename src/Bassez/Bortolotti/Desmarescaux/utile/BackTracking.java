package Bassez.Bortolotti.Desmarescaux.utile;

import Bassez.Bortolotti.Desmarescaux.Object.Ville;
import Bassez.Bortolotti.Desmarescaux.sample.Main;
import javafx.util.Pair;

import java.util.ArrayList;

public class BackTracking {
    ArrayList<Voie> parcour;

    public BackTracking(){
        parcour = new ArrayList<>();
    }

    public synchronized ArrayList<Pair<Voie,Boolean>> Parcour(Main m, Ville A, Ville B){
        ArrayList<ArrayList<Pair<Voie,Boolean>>> Totale = new ArrayList<>();
        for (Voie v : m.repository.ListVoie) {
            if (A == v.route.A) {
                if (v.route.getDebut(true) == v) {
                    ArrayList<Pair<Voie, Boolean>> T = new ArrayList<>();
                    T.add(new Pair<>(v.route.getDebut(true), true));
                    Totale.add(T);
                }
            }
            else if (A == v.route.B) {
                if (v.route.getDebut(false) == v) {
                    ArrayList<Pair<Voie, Boolean>> T = new ArrayList<>();
                    T.add(new Pair<>(v.route.getDebut(false), false));
                    Totale.add(T);
                }
            }
        }
        for (int i = 0; i < Totale.size(); i++) {
            ArrayList<Pair<Voie, Boolean>> H = Totale.get(i);
            if (H.get(H.size() - 1).getKey().route.getNoeud(H.get(H.size()-1).getValue()) == B){
                return H;
            } else {
                ArrayList<Pair<Voie, Boolean>> toperelle = new ArrayList<>(H);
                Totale.remove(H);
                i =-1;
                for (Voie v : m.repository.ListVoie) {
                    Pair<Voie, Boolean> p = toperelle.get(toperelle.size()-1);
                    if(p.getKey().route.getNoeud(p.getValue()) == v.route.A && v.route != p.getKey().route){
                        ArrayList<Pair<Voie, Boolean>> T = new ArrayList<>(toperelle);
                        T.add(new Pair<>(v.route.getDebut(true),true));
                        Totale.add(T);
                    }
                    else if(p.getKey().route.getNoeud(p.getValue()) == v.route.B && v.route != p.getKey().route){
                        ArrayList<Pair<Voie, Boolean>> T = new ArrayList<>(toperelle);
                        T.add(new Pair<>(v.route.getDebut(false),false));
                        Totale.add(T);
                    }
                }
            }
        }
        return null;
    }
}
