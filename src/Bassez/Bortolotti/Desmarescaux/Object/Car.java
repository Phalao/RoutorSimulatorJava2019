package Bassez.Bortolotti.Desmarescaux.Object;

import Bassez.Bortolotti.Desmarescaux.Route.Autoroute;
import Bassez.Bortolotti.Desmarescaux.Route.Departemental;
import Bassez.Bortolotti.Desmarescaux.Route.Natinonal;
import Bassez.Bortolotti.Desmarescaux.utile.Noeud;
import Bassez.Bortolotti.Desmarescaux.utile.Position;
import Bassez.Bortolotti.Desmarescaux.utile.Repository;
import Bassez.Bortolotti.Desmarescaux.utile.Voie;
import javafx.animation.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.util.Pair;

import java.util.ArrayList;

public class Car {
    private double vitesse;
    private double vmax;
    private double taille;
    private double Distance;
    private Voie voieOccupee;
    private Voie voieDepart;
    private Ville villeDepart;
    private Ville villeFin;
    public Rectangle rectangle;
    private AnimationTimer timer;
    private Position pos;
    private PathTransition p;
    private Car c;
    private boolean sens;

    public synchronized void Afficher(Pane root){
        voieOccupee = voieDepart;

        this.pos = new Position(this.voieOccupee.A);
        rectangle = new Rectangle(villeDepart.pos.getX(),villeDepart.pos.getY(), taille, 3);
        rectangle.setFill(Color.RED);
        Distance = 0;
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (libre(voieOccupee) == null && !voieOccupee.ListVoiture.contains(c)) {
                    voieOccupee.ListVoiture.add(c);
                }
                if (voieOccupee.ListVoiture.contains(c)) {
                    double distance = (Math.sqrt(Math.pow(voieOccupee.B.getX() - voieOccupee.A.getX(), 2) + Math.pow(voieOccupee.B.getY() - voieOccupee.A.getY(), 2)));
                    double t = 0.005;
                    Distance += t * vitesse;
                    double rapport = Distance / distance;
                    double x = voieOccupee.A.getX() + (voieOccupee.B.getX() - voieOccupee.A.getX()) * rapport;
                    double y = voieOccupee.A.getY() + (voieOccupee.B.getY() - voieOccupee.A.getY()) * rapport;
                    if (vitesse != 0) {//=== Evite le clignotement si jamais elle sont arrête
                        final Path path = new Path(
                                new MoveTo(pos.getX(), pos.getY()),
                                new LineTo(x, y));
                        p = new PathTransition(Duration.seconds(t), path, rectangle);
                        p.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                        p.setInterpolator(Interpolator.LINEAR);
                        p.setCycleCount(1);
                        p.play();
                    }
                    //=== On regarde si elle peuvent avancé ===
                    boolean depassement = false;
                    if (voieOccupee == voieDepart) {
                        if (libre(voieOccupee) != null) {
                            Car car = libre(voieOccupee);
                            switch (voieOccupee.r.toString().charAt(0)) {
                                case 'N':
                                    if (libre(((Natinonal) voieOccupee.r).getVoie(true)) == null  && car.vmax < vmax) {
                                        depassement = true;
                                        voieOccupee.ListVoiture.remove(c);
                                        voieOccupee = ((Natinonal) voieOccupee.r).getVoie(true);
                                    } else
                                        depassement = false;
                                    break;
                                case 'D':
                                    if (libre(((Departemental) voieOccupee.r).getVoie(sens)) == null  && car.vmax < vmax) {
                                        depassement = true;
                                        voieOccupee.ListVoiture.remove(c);
                                        voieOccupee = ((Departemental) voieOccupee.r).getVoie(sens);
                                    } else
                                        depassement = false;
                                    break;
                            }
                            if (!depassement)
                                if (vitesse >= 2)
                                    vitesse = vitesse - 2;
                                else
                                    vitesse = 0;
                        }
                    } else {
                        if (libre(voieDepart) == null) {
                            voieOccupee.ListVoiture.remove(c);
                            voieOccupee = voieDepart;
                        }else if(libre(voieOccupee) != null) {
                            if (vitesse >= 2)
                                vitesse = vitesse - 2;
                            else
                                vitesse = 0;
                        }
                    }
                    if (Distance >= distance) {
                        //=== FINI ===
                        stop();
                        rectangle.setVisible(false);
                        voieOccupee.ListVoiture.remove(c);
                    } else {
                        pos.setX(x);
                        pos.setY(y);
                        if (vitesse <= vmax)
                            vitesse++;
                    }
                }
            }
        };
        timer.start();
    }

    private ArrayList<Voie> Parcour(Repository repository){
        ArrayList<Voie> p = new ArrayList<>();
        for(Voie v: repository.ListVoie){
            if(sens){//TODO ne peux mettre la 2ème voie
                if(this.villeDepart == v.r.A && this.villeFin == v.r.B)
                    if(v.r.getDebut(true) == v)
                        p.add(v);
                }
            else{
                if(this.villeDepart == v.r.B && this.villeFin == v.r.A)
                    if(v.r.getDebut(false) == v)
                        p.add(v);
            }
        }
        System.out.println(p);
        return p;
    }

    public Car(Ville A, Ville B,boolean s, Pane root,Repository repository){
        //=== On met les différente ville
        villeDepart = A;
        villeFin = B;
        sens = s;

        //=== On calcule le parcour et on le met sur la première voie
        ArrayList<Voie> p = Parcour(repository);
        if(p.size() != 0){
            voieDepart = p.get(0);
        }

        //=== On calcule différente caratéristique ===
        vmax = ((Math.random()*60)+60);
        vitesse = 0;
        taille = (Math.random()*0 + 10);
        c = this;

        Afficher(root);
    }

    public Car(Ville A, Ville B,double vitesse, Pane root,Repository repository){
        //=== On met les différente ville
        villeDepart = A;
        villeFin = B;

        //=== On calcule le parcour et on le met sur la première voie
        ArrayList<Voie> p = Parcour(repository);
        if(p.size() != 0){
            voieDepart = p.get(0);
        }

        //=== On calcule différente caratéristique ===
        vmax = vitesse;
        this.vitesse = vitesse;
        taille = (Math.random()*0 + 10);
        c = this;
        if(p.size() != 0) {
            voieDepart = p.get(0);
            Afficher(root);
        }
    }

    public Car libre(Voie v){
        double min = 0;
        double max = 0;
        if(v == voieOccupee)
            min = arrondiNDecimales(Distance,2);
        else
            min = arrondiNDecimales(Distance - (taille * 3),2);
        max = arrondiNDecimales(Distance + (taille*1.1) + vitesse/3,2);
        for (Car car:v.ListVoiture) {
                if (car.Distance <= max && car.Distance >= min && c != car ) {
                    return car;
            }
        }
        return null;
    }

    private static double arrondiNDecimales(double x, int n) {
        double pow = Math.pow(10, n);
        return (Math.floor(x * pow)) / pow;
    }
}
