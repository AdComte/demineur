/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demineur.modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Random;

/**
 *
 * @author Vladimir
 */
public class Jeu extends Observable {

    private Case[][] cases;
    private int taille_x, taille_y, revelees;

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
    private Position[][] positions;
    private int nb_mines, nb_cases_restantes;
    private boolean victoire;

    public Jeu(int x, int y, int nb_mines) {
        this.taille_x = x;
        this.taille_y = y;
        this.nb_mines = nb_mines;
        this.nb_cases_restantes = x * y;
        this.revelees = 0;
        this.victoire = false;
        this.cases = new Case[x][y];
        this.positions = new Position[x][y];
        this.HM = new HashMap();
        this.HMR = new HashMap();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                cases[i][j] = new Case(i, j);
                positions[i][j] = new Position(i, j);
                if (HM.put(cases[i][j], positions[i][j]) == null) {
//                    System.out.println("insertion hashmap ok");
                }
                if (HMR.put(positions[i][j], cases[i][j]) == null) {
//                    System.out.println("insertion HMR ok");
                }
            }
        }
        Random xpos = new Random(), ypos = new Random();
        while (nb_mines > 0) {
            int X = xpos.nextInt(this.taille_x);
            int Y = ypos.nextInt(this.taille_y);
            if (!cases[X][Y].isMined()) {
                cases[X][Y].setMined(true);
                nb_mines--;
            }
        }
    }
    public ArrayList<Case> getVoisins(Case c) {
        ArrayList<Case> voisins = new ArrayList<>();
        int x = c.getX(), y = c.getY();
        if (x != 0) {
            if (y != 0) {
                voisins.add(HMR.get(positions[x - 1][y - 1]));
            }
            if (y != taille_y - 1) {
                voisins.add(HMR.get(positions[x - 1][y + 1]));
            }
            voisins.add(HMR.get(positions[x - 1][y]));
        }
        if (x != taille_x - 1) {
            if (y != 0) {
                voisins.add(HMR.get(positions[x + 1][y - 1]));
            }
            if (y != taille_y - 1) {
                voisins.add(HMR.get(positions[x + 1][y + 1]));
            }
            voisins.add(HMR.get(positions[x + 1][y]));
        }
        if (y != taille_y - 1) {
            voisins.add(HMR.get(positions[x][y + 1]));
        }
        if (y != 0) {
            voisins.add(HMR.get(positions[x][y - 1]));
        }

        return voisins;
    }
    
    public void revealAll(boolean victoire) {
        this.victoire = victoire;
        for (int i = 0; i < this.taille_x; i++) {
            for (int j = 0; j < this.taille_y; j++) {
                this.cases[i][j].setRevealed(true);
            }
        }
        setChanged();
        notifyObservers();
    }

    public void nb_cases_dec() {
        this.nb_cases_restantes--;
        if (this.nb_cases_restantes == this.nb_mines) {
            setChanged();
            notifyObservers();
            this.revealAll(true);
            //la partie est gagnée
        }
    }

//setters et getters
    public boolean isVictoire() {
        return victoire;
    }

    public int getNb_mines() {
        return nb_mines;
    }

    public int getNb_cases_restantes() {
        return nb_cases_restantes;
    }

    public int getRevelees() {
        return revelees;
    }

    public void setRevelees(int revelees) {
        this.revelees = revelees;
    }

    public int getX() {
        return taille_x;
    }

    public void setJeu() {
        for (int i = 0; i < this.taille_x; i++) {
            for (int j = 0; j < this.taille_y; j++) {
                cases[i][j].setJeu(this);
                if(cases[i][j].isMined()){System.out.print("x |");}else{System.out.print("  |");}
                if(j==this.taille_x-1){System.out.println();}
            }
        }
        this.setChanged();
        this.notifyObservers();
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

    public Case[][] getCases() {
        return cases;
    }

    public void setCases(Case[][] cases) {
        this.cases = cases;
    }

}
