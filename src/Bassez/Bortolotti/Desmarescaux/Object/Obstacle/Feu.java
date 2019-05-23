package Bassez.Bortolotti.Desmarescaux.Object.Obstacle;

import Bassez.Bortolotti.Desmarescaux.Object.Car;
import Bassez.Bortolotti.Desmarescaux.Object.Noeud;
import Bassez.Bortolotti.Desmarescaux.Object.Ville;
import Bassez.Bortolotti.Desmarescaux.Route.Route;
import Bassez.Bortolotti.Desmarescaux.sample.Main;
import Bassez.Bortolotti.Desmarescaux.utile.Position;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Feu extends Obstacle implements Noeud{
    private Rectangle rectangle;
    public Position pos;
    public boolean vert;
    double temps;
    private static final double t = 0.005;

    public Feu(Position pos, Priorite ordrePrio, Main m) {
        super(pos,ordrePrio);
        this.pos = pos;
        m.repository.ListObstalce.add(this);
        temps = 0;
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                temps += t;
                if(temps >= 1){
                    if(vert)
                        vert = false;
                    else
                        vert = true;

                    temps = 0;
                    System.out.println(vert);
                }
            }
        }.start();
    }

    public boolean getvert(){
        return vert;
    }

    public void afficher(Main m){
        rectangle = new Rectangle(pos.getX()-10,pos.getY()-10, 20, 20);
        rectangle.setFill(Color.BLACK);
        m.root.getChildren().add(rectangle);
    }

    @Override
    public String toString() {
        return "Feu";
    }
    public double gettaille(){ return 20;};
}
