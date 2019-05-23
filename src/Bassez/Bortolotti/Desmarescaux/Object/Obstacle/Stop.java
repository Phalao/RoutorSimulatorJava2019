package Bassez.Bortolotti.Desmarescaux.Object.Obstacle;

import Bassez.Bortolotti.Desmarescaux.Route.Route;
import Bassez.Bortolotti.Desmarescaux.utile.Position;

public class Stop extends Obstacle {
    public Stop(Position pos, Route routeA, Route routeB, Priorite ordrePrio) {
        super(pos, ordrePrio);
    }

    @Override
    public String toString() {
        return "Stop";
    }
}
