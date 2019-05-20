package Bassez.Bortolotti.Desmarescaux.Object;

import Bassez.Bortolotti.Desmarescaux.utile.Position;
import Bassez.Bortolotti.Desmarescaux.utile.Voie;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Car {
    int vitesse;
    int acceleration;
    int taille;
    int vLimite;
    Voie voieOccupée;
    Ville villeDepart;
    Ville villeFin;
    Position pos;
    public Rectangle rectangle;

    public Car(Ville A, Ville B, Pane root){
        rectangle = new Rectangle(A.pos.getX(), A.pos.getY(), 5, 2.5);
        rectangle.setFill(Color.RED);
        final Path path = new Path(
                new MoveTo(A.pos.getX(), A.pos.getY()),
                new LineTo(B.pos.getX(), B.pos.getY()));
        final PathTransition pathAnimation = new PathTransition(Duration.seconds(10), path, rectangle);
        pathAnimation.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathAnimation.setCycleCount(PathTransition.INDEFINITE);
        pathAnimation.setInterpolator(Interpolator.LINEAR);
        pathAnimation.play();
    }
}
