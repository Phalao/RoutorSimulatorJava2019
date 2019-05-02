package Bassez.Bortolotti.Desmarescaux.sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        final Circle circle_1 = new Circle(400, 100,100);//x,y,rayon
        circle_1.setFill(Color.RED);

        //Tentative de mettre un deuxieme point

        final Circle circle_2 = new Circle(100, 100,100);//x,y,rayon
        circle_2.setFill(Color.GREEN);

        //Mise en place de texte
        final Text text = new Text("Salut le monde !");
        text.setLayoutX(100);
        text.setLayoutY(100);
        text.setFill(Color.BLUE);

        //Fenetre

        final Pane root = new Pane();
        root.getChildren().setAll(circle_1,circle_2,text);//Doit contenir tous les noeuds
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