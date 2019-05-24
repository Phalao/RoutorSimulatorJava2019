package Bassez.Bortolotti.Desmarescaux.Object;

import Bassez.Bortolotti.Desmarescaux.Object.Obstacle.Feu;
import Bassez.Bortolotti.Desmarescaux.Route.Departemental;
import Bassez.Bortolotti.Desmarescaux.Route.Natinonal;
import Bassez.Bortolotti.Desmarescaux.sample.Main;
import Bassez.Bortolotti.Desmarescaux.utile.BackTracking;
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
    private Car c;
    private boolean sens;
    private ArrayList<Pair<Voie,Boolean>> p;
    private int indice;

    private static final double t = 0.01;

    public synchronized void avancement(Main m){
        voieOccupee = voieDepart;
        vmax = ((Math.random() * 60) + voieOccupee.route.getVitesse()-20);
        if(sens)
            this.pos = new Position(this.voieOccupee.A);
        else
            this.pos = new Position(this.voieOccupee.B);
        Distance = 0;
        timer = new AnimationTimer() {
            @Override
            public synchronized void handle(long now) {
                if (libre(voieOccupee) == null && !voieOccupee.ListVoiture.contains(c)) {
                    voieOccupee.ListVoiture.add(c);
                    m.repository.ListCar.add(c);
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
                        vitesse = vitesse - 3;
                    }
                    else if(voieOccupee != voieDepart && libre(voieOccupee) != null) {
                        if (vitesse >= 2)
                            vitesse = vitesse - 3;
                        else
                            vitesse = 0;
                    }

                    //=== Si c'est fini ===
                    if (Distance >= distance) {
                        stop();
                        indice++;
                        if(villeFin != voieDepart.route.getNoeud(sens)){
                            switch (voieDepart.route.getNoeud(sens).toString().charAt(0)){
                                case 'F':
                                    new AnimationTimer() {
                                        @Override
                                        public void handle(long now) {
                                            Feu f = (Feu) voieOccupee.route.getNoeud(sens);
                                            if(f.getvert()){
                                                    vitesse = 0;
                                                }else if(libre(p.get(indice).getKey()) == null){
                                                    voieOccupee.ListVoiture.remove(c);
                                                    sens = p.get(indice).getValue();
                                                    voieDepart = p.get(indice).getKey();
                                                    avancement(m);
                                                    stop();
                                                    }
                                            }
                                    }.start();
                                    break;
                                case 'V':
                                    voieOccupee.ListVoiture.remove(c);
                                    sens = p.get(indice).getValue();
                                    voieDepart = p.get(indice).getKey();
                                    avancement(m);
                                    stop();
                                    break;
                            }
                        }else {
                            voieOccupee.ListVoiture.remove(c);
                            m.repository.ListCar.remove(c);
                            rectangle.setVisible(false);
                        }
                    } else {
                        pos.setX(x);
                        pos.setY(y);
                        if (vitesse <= vmax)
                            vitesse++;
                        if(Distance / distance >= 0.8)
                            if(vitesse > 20)
                                vitesse = vitesse -2;
                    }
                }
            }
        };
        timer.start();
    }

    public Car(Ville A, Ville B, Main m){
        villeDepart = A;
        villeFin = B;
        indice = 0;

        if(A != B) {
            BackTracking b = new BackTracking();
            p = b.Parcour(m, villeDepart, villeFin);
            sens = p.get(indice).getValue();
            voieDepart = p.get(indice).getKey();
            vitesse = 0;
            taille = (Math.random() * 0 + 10);
            c = this;
            avancement(m);
        }
        else{
            System.out.println("Même ville...");
        }
    }

    public void afficher(Main m){
        rectangle = new Rectangle(villeDepart.pos.getX(),villeDepart.pos.getY(), taille, 3);
        rectangle.setFill(Color.BLUE);
        m.root.getChildren().add(rectangle);
    }

    public void refresh(Main m) {
        Boolean changement = false;
        if (vitesse <= 20) {
            if(rectangle.getFill() == Color.BLUE)
                changement = true;
            rectangle.setFill(Color.RED);
        }
        else {
            if(rectangle.getFill() == Color.RED)
                changement = true;
            rectangle.setFill(Color.BLUE);
        }

        if(changement) {
            m.root.getChildren().remove(rectangle);
            rectangle = new Rectangle(villeDepart.pos.getX(), villeDepart.pos.getY(), taille, 3);
            m.root.getChildren().add(rectangle);
        }
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
            max = arrondiNDecimales(Distance + (taille*1.1) + vitesse/2,2);
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
