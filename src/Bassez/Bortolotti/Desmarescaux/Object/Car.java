package Bassez.Bortolotti.Desmarescaux.Object;

import Bassez.Bortolotti.Desmarescaux.utile.Noeud;
import Bassez.Bortolotti.Desmarescaux.utile.Repository;
import Bassez.Bortolotti.Desmarescaux.utile.Voie;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
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
    private int vitesse;
    private int acceleration;
    private int taille;
    private Voie voieOccupée;
    private Ville villeDepart;
    private Ville villeFin;
    public Rectangle rectangle;

    public void Afficher(Pane root,int Taille){
        rectangle = new Rectangle(this.voieOccupée.A.getX(), this.voieOccupée.A.getY(), Taille, 3);
        rectangle.setFill(Color.RED);
        final Path path = new Path(
                new MoveTo(this.voieOccupée.A.getX(), this.voieOccupée.A.getY()),
                new LineTo(this.voieOccupée.B.getX(), this.voieOccupée.B.getY()));
        final PathTransition pathAnimation = new PathTransition(Duration.seconds(5), path, rectangle);
        pathAnimation.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathAnimation.setInterpolator(Interpolator.LINEAR);
        pathAnimation.setCycleCount(1);
        pathAnimation.play();
        pathAnimation.setOnFinished(e -> rectangle.setFill(Color.TRANSPARENT));
    }

    public ArrayList<Voie> Parcour(Repository repository){
        ArrayList<Voie> p = new ArrayList<>();
        for(Voie v: repository.ListVoie){
            if(this.villeDepart == v.r.A && this.villeFin == v.r.B){
                p.add(v);
            }
        }
        return p;
    }

    public Car(Ville A, Ville B, Pane root,Repository repository){
        this.villeDepart = A;
        this.villeFin = B;
        ArrayList<Voie> p = Parcour(repository);
        if(p.size() != 0)
            this.voieOccupée = p.get(0);
        vitesse = (int) (Math.random()*20 + 90);
        acceleration = (int) (Math.random()*2 + 10);
        taille = (int) (Math.random()*5 + 10);
        Afficher(root,taille);
    }
}
