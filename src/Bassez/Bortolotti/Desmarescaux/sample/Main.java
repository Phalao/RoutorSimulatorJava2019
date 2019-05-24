package Bassez.Bortolotti.Desmarescaux.sample;

import Bassez.Bortolotti.Desmarescaux.Object.Car;
import Bassez.Bortolotti.Desmarescaux.Object.Obstacle.Feu;
import Bassez.Bortolotti.Desmarescaux.Object.Obstacle.Obstacle;
import Bassez.Bortolotti.Desmarescaux.Object.Ville;
import Bassez.Bortolotti.Desmarescaux.Route.Departemental;
import Bassez.Bortolotti.Desmarescaux.Route.Natinonal;
import Bassez.Bortolotti.Desmarescaux.Route.Route;
import Bassez.Bortolotti.Desmarescaux.utile.Position;
import Bassez.Bortolotti.Desmarescaux.utile.Repository;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    public Repository repository;
    public Pane root;
    public Main Instance;

    @Override
    public void start(Stage primaryStage) {
        //=== Initialisation ===
        repository = new Repository();
        Instance = this;

        //=== Creation de la ville ===
        Ville villeA = new Ville("VilleA", 25, new Position(200,100), this);
        Ville villeB = new Ville("VilleB", 25, new Position(800,175), this);
        Ville villeC = new Ville("VilleC", 25, new Position(50,300), this);
        Ville villeD = new Ville("VilleD", 25, new Position(700,375), this);
        Ville villeE = new Ville("VilleE", 25, new Position(150,450), this);
        Ville villeF = new Ville("VilleF", 25, new Position(600,550), this);

        //=== Creation des intersection ===
        Feu F1 = new Feu(new Position(400,250), Obstacle.Priorite.UN,this);
        Feu F2 = new Feu(new Position(350,400), Obstacle.Priorite.UN,this);

        //=== Creation des different Route ===
        new Departemental(villeA,F1, 110,this);
        new Departemental(F1,villeD, 110,this);

        new Departemental(villeC,F2, 110,this);
        new Departemental(F2,villeF, 110,this);

        new Natinonal(villeA,villeB, 80,this);
        new Departemental(villeA,villeC, 110,this);
        new Natinonal(villeB,villeD, 80,this);
        new Natinonal(villeC,villeE, 80,this);
        new Natinonal(villeC,villeD, 80,this);
        new Natinonal(villeD,villeF, 80,this);
        new Natinonal(villeE,villeF, 80,this);

        //=== On affiche les different element ===
        root = new Pane();
        Scene scene = new Scene(root, 900, 700);
        primaryStage.setTitle("Simulation de voiture");
        primaryStage.setScene(scene);
        primaryStage.show();
        repository.afficher(this);

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(Math.random() >= 0.90) {
                    Ville d = repository.ListVille.get((int) (Math.random() * repository.ListVille.size()));
                    Ville f = repository.ListVille.get((int) (Math.random() * repository.ListVille.size()));
                    while (d.toString() == f.toString()) {
                        d = repository.ListVille.get((int) (Math.random() * repository.ListVille.size()));
                        f = repository.ListVille.get((int) (Math.random() * repository.ListVille.size()));
                    }
                    Car c = new Car(d, f, Instance);
                    c.afficher(Instance);
                    repository.refresh(Instance);
                }
            }
        }.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}