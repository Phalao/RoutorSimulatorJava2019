package Bassez.Bortolotti.Desmarescaux.sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        final Circle circle_1 = new Circle(400, 100,100);//x,y,rayon
        circle_1.setFill(Color.RED);
        final RadialGradient gradient_1 = new RadialGradient(0, 0, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.RED),
                new Stop(1, Color.CRIMSON));
        circle_1.setFill(gradient_1);


        //Tentative de mettre un deuxieme point

        final Circle circle_2 = new Circle(100, 100,100);//x,y,rayon
        circle_2.setFill(Color.GREEN);
        final RadialGradient gradient_2 = new RadialGradient(0, 0, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.CHARTREUSE),
                new Stop(1, Color.GREEN));
        circle_2.setFill(gradient_2);

        //Mise en place d'une route
        Line route_1 = new Line(400,100,100,100);

        route_1.setStroke(Color.BLACK);//Mise en place d'une bordure de couleur noir
        route_1.setStrokeWidth(3);//Taille de la bordure

        //Mise en place de texte
        final Text text = new Text("Salut le monde !");
        text.setLayoutX(100);
        text.setLayoutY(100);
        text.setFill(Color.BLUE);

        //Fenetre

        final Pane root = new Pane();
        root.getChildren().setAll(circle_1,circle_2,route_1,text);//Doit contenir tous les noeuds
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