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
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    public Repository repository;
    public Pane root;

    @Override
    public void start(Stage primaryStage) {
        //=== Initialisation ===
        repository = new Repository();

        //=== Creation de la ville ===
        //Ville villeA = new Ville("VilleA", 10, new Position(500, 200), this);
        //Ville villeB = new Ville("VilleB", 10, new Position(100, 100), this);
        Ville villeC = new Ville("VilleC", 10, new Position(500, 400), this);
        Ville villeD = new Ville("VilleD", 10, new Position(100, 400), this);

        //=== Creation des intersection ===
        Feu F = new Feu(new Position(300,100), Obstacle.Priorite.UN,this);

        //=== Creation des different Route ===
        //Route N1 = new Natinonal(villeA, F, 100,this);
        Route N2 = new Natinonal(villeC, F, 100,this);
        Route N3 = new Departemental(F, villeD, 100,this);

        //=== On affiche les different element ===
        root = new Pane();
        Scene scene = new Scene(root, 600, 600);
        primaryStage.setTitle("Simulation de voiture");
        primaryStage.setScene(scene);
        primaryStage.show();
        repository.afficher(this);

        Car c = new Car(villeC,villeD,this);
        c.afficher(this);

        //Car c2 = new Car(villeC,villeD,this);
        //c2.afficher(this);
        //Car c3 = new Car(villeC,villeD,this);
        //c3.afficher(this);
    }

    public static void main(String[] args) {
        launch(args);
    }
}