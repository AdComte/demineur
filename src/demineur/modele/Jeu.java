/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demineur.modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

/**
 *
 * @author Vladimir
 */
public class Jeu extends Observable {

    private Case[][] cases;
    private int taille_x, taille_y;

    public class Position {

        private int x;
        private int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

    }
    private HashMap<Case, Position> HM;
    private HashMap<Position, Case> HMR;

    public ArrayList<Case> getVoisins(Case c) {
        ArrayList<Case> voisins = new ArrayList<>();
        int x = c.getX(), y = c.getY();
        if (x != 0) {
            if (y != 0) {
                voisins.add(HMR.get(new Position(x - 1, y - 1)));
            }
            if (y != taille_y - 1) {
                voisins.add(HMR.get(new Position(x - 1, y + 1)));
            }
            voisins.add(HMR.get(new Position(x - 1, y)));
        }
        if (x != taille_x - 1) {
            if (y != 0) {
                voisins.add(HMR.get(new Position(x + 1, y - 1)));
            }
            if (y != taille_y - 1) {
                voisins.add(HMR.get(new Position(x + 1, y + 1)));
            }
            voisins.add(HMR.get(new Position(x + 1, y)));
        }
        if (y != taille_y - 1) {
            voisins.add(HMR.get(new Position(x, y + 1)));
        }
        if (y != 0) {
            voisins.add(HMR.get(new Position(x, y - 1)));
        }
        return voisins;
    }

    public int getX() {
        return taille_x;
    }

    public void setX(int x) {
        this.taille_x = x;
    }

    public int getY() {
        return taille_y;
    }

    public void setY(int y) {
        this.taille_y = y;
    }

    public Jeu(int x, int y) {
        this.taille_x = x;
        this.taille_y = y;
        this.cases = new Case[x][y];
        this.HM = new HashMap();
        this.HMR = new HashMap();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                cases[i][j] = new Case(i, j);
                HM.put(cases[i][j], new Position(i, j));
                HMR.put(new Position(i, j), cases[i][j]);
                cases[i][j].setJeu(this);
            }
        }
    }

}
