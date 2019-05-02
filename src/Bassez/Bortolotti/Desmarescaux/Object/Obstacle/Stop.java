package Bassez.Bortolotti.Desmarescaux.Object.Obstacle;

import Bassez.Bortolotti.Desmarescaux.Route.Route;

import javax.swing.text.Position;

public class Stop extends Obstacle {
    public Stop(Position pos, Route routeA, Route routeB, Priorite ordrePrio) {
        super(pos, routeA, routeB, ordrePrio);
    }
}
