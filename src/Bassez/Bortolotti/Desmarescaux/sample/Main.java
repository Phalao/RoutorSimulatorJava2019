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
        Ville villeA = new Ville("VilleA", 10, new Position(100, 100), this);
        Ville villeC = new Ville("VilleC", 10, new Position(400, 100), this);
        //Ville villeD = new Ville("VilleD", 10, new Position(300, 200), this);

        //=== Creation des intersection ===
        Feu F = new Feu(new Position(250,100), Obstacle.Priorite.UN,this);

        //=== Creation des different Route ===
        Route N1 = new Natinonal(villeA ,F, 100,this);
        Route N2 = new Natinonal(F,villeC, 100,this);
        //Route N3 = new Departemental(F, villeD, 100,this);
        //Route N4 = new Departemental(villeA, villeC, 100,this);

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
                if(Math.random() >= 0.99) {
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