package Bassez.Bortolotti.Desmarescaux.Object.Obstacle;

import Bassez.Bortolotti.Desmarescaux.Route.Route;

import javax.swing.text.Position;

public class Feu extends Obstacle{

    public Feu(Position pos, Route routeA, Route routeB, Priorite ordrePrio) {
        super(pos, routeA, routeB, ordrePrio);
    }
}
