package Bassez.Bortolotti.Desmarescaux.sample;

import Bassez.Bortolotti.Desmarescaux.Object.Car;
import Bassez.Bortolotti.Desmarescaux.Object.Ville;
import Bassez.Bortolotti.Desmarescaux.Route.Autoroute;
import Bassez.Bortolotti.Desmarescaux.Route.Departemental;
import Bassez.Bortolotti.Desmarescaux.Route.Natinonal;
import Bassez.Bortolotti.Desmarescaux.Route.Route;
import Bassez.Bortolotti.Desmarescaux.utile.Position;
import Bassez.Bortolotti.Desmarescaux.utile.Repository;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.event.ActionEvent;

public class Main extends Application {

    private AnimationTimer timer;

    @Override
    public void start(Stage primaryStage) {
        Repository repository = new Repository();
        Pane root = new Pane();
        Ville villeA = new Ville("VilleA", 10, new Position(500, 200), root, repository);
        Ville villeB = new Ville("VilleB", 10, new Position(100, 100), root, repository);
        Route N = new Natinonal(villeA, villeB, 100, root, repository);
        Ville villeC = new Ville("VilleC", 10, new Position(500, 300), root, repository);
        Ville villeD = new Ville("VilleD", 10, new Position(100, 300), root, repository);
        Route D = new Departemental(villeC, villeD, 100, root, repository);
        repository.afficher(root);

        Car car = new Car(villeC, villeD,true, root, repository);
        root.getChildren().add(car.rectangle);
        Car car2 = new Car(villeC, villeD,true, root, repository);
        root.getChildren().add(car2.rectangle);
        Car car3 = new Car(villeD, villeC,false, root, repository);
        root.getChildren().add(car3.rectangle);
        Car car4 = new Car(villeD, villeC,false, root, repository);
        root.getChildren().add(car4.rectangle);

        //Fenetre
        Scene scene = new Scene(root, 600, 600);
        primaryStage.setTitle("Simulation de voiture");
        primaryStage.setScene(scene);
        primaryStage.show();

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(Math.random() >=0.99) {
                    Car car = new Car(villeA, villeB,true, root, repository);
                    root.getChildren().add(car.rectangle);
                }
                if(Math.random() >=0.99) {
                    Car car2 = new Car(villeC, villeD,true, root, repository);
                    root.getChildren().add(car2.rectangle);
                }
                if(Math.random() >=0.99) {
                    Car car3 = new Car(villeD, villeC,false, root, repository);
                    root.getChildren().add(car3.rectangle);
                }

            }
        };
        timer.start();




    }

    public static void main(String[] args) {
        launch(args);
    }
}