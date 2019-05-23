package Bassez.Bortolotti.Desmarescaux.Object;

import Bassez.Bortolotti.Desmarescaux.Route.Departemental;
import Bassez.Bortolotti.Desmarescaux.Route.Natinonal;
import Bassez.Bortolotti.Desmarescaux.sample.Main;
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
    private Car c;
    private boolean sens;

    private static final double t = 0.01;

    public synchronized void avancement(Pane root){
        voieOccupee = voieDepart;
        if(sens)
            this.pos = new Position(this.voieOccupee.A);
        else
            this.pos = new Position(this.voieOccupee.B);
        Distance = 0;
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (libre(voieOccupee) == null && !voieOccupee.ListVoiture.contains(c)) {
                    voieOccupee.ListVoiture.add(c);
                }
                if (voieOccupee.ListVoiture.contains(c)) {
                    double distance = (Math.sqrt(Math.pow(voieOccupee.B.getX() - voieOccupee.A.getX(), 2) + Math.pow(voieOccupee.B.getY() - voieOccupee.A.getY(), 2)));
                    Distance += t * vitesse;
                    double x;
                    double y;
                    if(sens) {
                        x = voieOccupee.A.getX() + (voieOccupee.B.getX() - voieOccupee.A.getX()) * Distance / distance;
                        y = voieOccupee.A.getY() + (voieOccupee.B.getY() - voieOccupee.A.getY()) * Distance / distance;
                    }else {
                        x = voieOccupee.B.getX() + (voieOccupee.A.getX() - voieOccupee.B.getX()) * Distance / distance;
                        y = voieOccupee.B.getY() + (voieOccupee.A.getY() - voieOccupee.B.getY()) * Distance / distance;
                    }
                    if (vitesse != 0)
                        animation(x,y,t);

                    //=== Avancement ===
                    if (voieOccupee == voieDepart && libre(voieOccupee) != null) {
                        boolean depassement = false;
                        Car car = libre(voieOccupee);
                        switch (voieOccupee.route.toString().charAt(0)) {
                            case 'N':
                                if (libre(((Natinonal) voieOccupee.route).getVoie(sens)) == null && car.vmax < vmax) {
                                    depassement = true;
                                    voieOccupee.ListVoiture.remove(c);
                                    voieOccupee = ((Natinonal) voieOccupee.route).getVoie(sens);
                                } else
                                    depassement = false;
                                break;
                            case 'D':
                                if (libre(((Departemental) voieOccupee.route).getVoie(sens)) == null  && car.vmax < vmax) {
                                    depassement = true;
                                    voieOccupee.ListVoiture.remove(c);
                                    voieOccupee = ((Departemental) voieOccupee.route).getVoie(sens);
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
                    else if (voieOccupee != voieDepart && libre(voieDepart) == null) {
                        voieOccupee.ListVoiture.remove(c);
                        voieOccupee = voieDepart;
                    }
                    else if(voieOccupee != voieDepart && libre(voieOccupee) != null) {
                        if (vitesse >= 2)
                            vitesse = vitesse - 2;
                        else
                            vitesse = 0;
                    }

                    //=== Si c'est fini ===
                    if (Distance >= distance) {
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
                if(this.villeDepart == v.route.A && this.villeFin == v.route.B)
                    if(v.route.getDebut(sens) == v)
                        p.add(v);
        }
        return p;
    }

    public Car(Ville A, Ville B,boolean s, Main m){
        villeDepart = A;
        villeFin = B;
        sens = s;

        ArrayList<Voie> p = Parcour(m.repository);
        if(p.size() != 0){
            voieDepart = p.get(0);
        }
        else {
            System.out.println("La voiture ne peux pas y arrivé");
        }

        vmax = ((Math.random()*60)+60);
        vitesse = 0;
        taille = (Math.random()*0 + 10);
        c = this;
        avancement(m.root);
    }

    public void afficher(Main m){
        rectangle = new Rectangle(villeDepart.pos.getX(),villeDepart.pos.getY(), taille, 3);
        rectangle.setFill(Color.RED);
        m.root.getChildren().add(rectangle);
    }

    public void animation(double x,double y,double t){
        if(rectangle != null) {
            final Path path = new Path(
                    new MoveTo(pos.getX(), pos.getY()),
                    new LineTo(x, y));
            PathTransition p = new PathTransition(Duration.seconds(t), path, rectangle);
            p.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            p.setInterpolator(Interpolator.LINEAR);
            p.setCycleCount(1);
            p.play();
        }
    }

    public Car libre(Voie v){
        double min = 0;
        double max = 0;
        if(v == voieOccupee)
            min = arrondiNDecimales(Distance,2);
        else
            min = arrondiNDecimales(Distance - (taille * 3),2);
        if(v.route.toString() == "Nationnal" && v != voieDepart)
            max = v.route.longueur;
        else
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
