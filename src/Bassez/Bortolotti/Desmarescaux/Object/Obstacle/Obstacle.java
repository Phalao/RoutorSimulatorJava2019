package Bassez.Bortolotti.Desmarescaux.Object.Obstacle;

import Bassez.Bortolotti.Desmarescaux.Route.Route;

import javax.swing.text.Position;

public class Obstacle{

    public static enum Priorite {
        UN,DEUX,TROIS
    }

    private Position pos;
    private Route routeA;
    private Route routeB;
    private Priorite ordrePrio;

    public Obstacle(Position pos, Route routeA, Route routeB, Priorite ordrePrio) {
        this.pos = pos;
        this.routeA = routeA;
        this.routeB = routeB;
        this.ordrePrio = ordrePrio;
    }

    public Position getPos() { return pos; }

    public void setPos(Position pos) { this.pos = pos; }

    public Route getRouteA() { return routeA; }

    public void setRouteA(Route routeA) { this.routeA = routeA; }

    public Route getRouteB() { return routeB; }

    public void setRouteB(Route routeB) { this.routeB = routeB; }

    public Priorite getOrdrePrio() { return ordrePrio; }

    public void setOrdrePrio(Priorite ordrePrio) { this.ordrePrio = ordrePrio; }
}
