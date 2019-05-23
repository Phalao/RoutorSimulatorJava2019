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
        Ville villeA = new Ville("VilleA", 50, new Position(100, 100), this);
        Ville villeC = new Ville("VilleC", 50, new Position(500, 200), this);
        Ville villeD = new Ville("VilleD", 50, new Position(200, 300), this);
        Ville villeE = new Ville("VilleD", 50, new Position(500, 500), this);

        //=== Creation des intersection ===
        Feu F1 = new Feu(new Position(350,250), Obstacle.Priorite.UN,this);
        Feu F2 = new Feu(new Position(350,350), Obstacle.Priorite.UN,this);

        //=== Creation des different Route ===
        // new Natinonal(villeA ,F1, 100,this);
    new Natinonal(F1,villeC, 100,this);
    new Departemental(villeD, F2, 100,this);
    new Departemental(villeC, F2, 100,this);
    new Departemental(villeA, villeC, 100,this);
    new Departemental(villeD, F1, 100,this);
    new Departemental(villeD, villeE, 100,this);




        //=== On affiche les different element ===
        root = new Pane();
        Scene scene = new Scene(root, 600, 600);
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
                }
            }
        }.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}