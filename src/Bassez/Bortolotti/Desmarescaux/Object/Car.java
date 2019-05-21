package Bassez.Bortolotti.Desmarescaux.Object;

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
    private double position;
    private Voie voieOccupee;
    private Ville villeDepart;
    private Ville villeFin;
    public Rectangle rectangle;
    private AnimationTimer timer;
    private Position pos;
    private PathTransition p;
    private Car c;

    public synchronized void Afficher(Pane root){
        voieOccupee.ListVoiture.add(c);
        voieOccupee.ListObstacle.put(c,0.0);
        this.pos = new Position(this.voieOccupee.A);
        rectangle = new Rectangle(pos.getX(),pos.getY(), taille, 3);
        rectangle.setFill(Color.RED);
        Distance = 0;
        timer = new AnimationTimer() {
            @Override
            public synchronized void handle(long now) {
                double distance = (Math.sqrt(Math.pow(voieOccupee.B.getX()-voieOccupee.A.getX(),2)+Math.pow(voieOccupee.B.getY()-voieOccupee.A.getY(),2)));
                double t = 0.01;
                Distance += t*vitesse;
                voieOccupee.ListObstacle.put(c, Distance);
                double rapport = Distance/distance;
                double x =voieOccupee.A.getX() +(voieOccupee.B.getX()-voieOccupee.A.getX())*rapport;
                double y =voieOccupee.A.getY() +(voieOccupee.B.getY()-voieOccupee.A.getY())*rapport;

                final Path path = new Path(
                        new MoveTo(pos.getX(),pos.getY()),
                        new LineTo(x,y));
                p = new PathTransition(Duration.seconds(t), path, rectangle);
                p.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                p.setInterpolator(Interpolator.LINEAR);
                p.setCycleCount(1);
                p.play();
                Boolean b = libre();
                if(!b){
                    vitesse--;
                }
                if(Distance >= distance){
                    //=== FINI ===
                    stop();
                    rectangle.setVisible(false);
                    voieOccupee.ListObstacle.put(c, Distance);
                    voieOccupee.ListVoiture.remove(c);
                }else{
                    pos.setX(x);
                    pos.setY(y);
                    if(vitesse <= vmax )
                        vitesse++;
                }
            }
        };
        timer.start();
    }

    private ArrayList<Voie> Parcour(Repository repository){
        ArrayList<Voie> p = new ArrayList<>();
        for(Voie v: repository.ListVoie){
            if(this.villeDepart == v.r.A && this.villeFin == v.r.B){
                p.add(v);
            }
        }
        return p;
    }

    public Car(Ville A, Ville B, Pane root,Repository repository){
        //=== On met les différente ville
        villeDepart = A;
        villeFin = B;

        //=== On calcule le parcour et on le met sur la première voie
        ArrayList<Voie> p = Parcour(repository);
        if(p.size() != 0){
            voieOccupee = p.get(0);
            //voieOccupee.ListVoiture.put(0,this);
        }

        //=== On calcule différente caratéristique ===
        vmax = ((Math.random()*20)+100);
        vitesse = 0;
        taille = (Math.random()*0 + 10);
        c = this;

        Afficher(root);
    }

    public boolean libre(){
        double min = arrondiNDecimales(Distance,2);
        double max = arrondiNDecimales(Distance + vitesse,2);
        for (Car car:voieOccupee.ListVoiture) {
            if(voieOccupee.ListObstacle.get(car) <= max && voieOccupee.ListObstacle.get(car) >= min && c != car)
                return false;
        }
        return true;
    }

    private static double arrondiNDecimales(double x, int n) {
        double pow = Math.pow(10, n);
        return (Math.floor(x * pow)) / pow;
    }
}
