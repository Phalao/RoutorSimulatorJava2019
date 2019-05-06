package Bassez.Bortolotti.Desmarescaux.sample;

import Bassez.Bortolotti.Desmarescaux.Object.Ville;
import Bassez.Bortolotti.Desmarescaux.utile.Position;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        //Fenetre
        final Pane root = new Pane();

        //Ville
        final Ville villeA = new Ville(100,new Position(100,100),root);

        final Ville villeB = new Ville(100,new Position(400,100),root);
        //blabla
        //Mise en place de texte
        final Text text = new Text("Salut le monde !");
        text.setLayoutX(100);
        text.setLayoutY(100);
        text.setFill(Color.BLUE);

        final Scene scene = new Scene(root, 600, 600);
        //Taille de la fenetre d'affichage
        primaryStage.setTitle("Affichage de plusieurs cercles");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

//test