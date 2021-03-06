/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demineur.modele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
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
    private int nb_mines, nb_cases_restantes, nb_drapeaux;
    private boolean victoire;

    public Jeu(int x, int y) {
        this.CreerJeu(x, y, 0);
    }

    public Jeu(int x, int y, int nb_mines) {
        this.CreerJeu(x, y, nb_mines);
        this.PlacerMines(nb_mines);
    }

    public final void CreerJeu(int x, int y, int nb_mines) {
        this.taille_x = x;
        this.taille_y = y;
        this.nb_mines = nb_mines;
        this.nb_drapeaux = 0;
        this.nb_cases_restantes = x * y;
        this.revelees = 0;
        this.victoire = false;
        this.cases = new Case[x][y];
        this.positions = new Position[x][y];
        this.HM = new HashMap();
        this.HMR = new HashMap();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                cases[i][j] = new Case();
                positions[i][j] = new Position(i, j);
                HM.put(cases[i][j], positions[i][j]);
                HMR.put(positions[i][j], cases[i][j]);
            }
        }
    }

    public final void PlacerMines(int nb_mines) {

        Random xpos = new Random(), ypos = new Random();
        if (nb_mines > taille_x * taille_y) {
            this.nb_mines = (taille_x * taille_y) / 3;
        }
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
        Position p = HM.get(c);
        if (p.x != 0) {
            if (p.y != 0) {
                voisins.add(HMR.get(positions[p.x - 1][p.y - 1]));
            }
            if (p.y != taille_y - 1) {
                voisins.add(HMR.get(positions[p.x - 1][p.y + 1]));
            }
            voisins.add(HMR.get(positions[p.x - 1][p.y]));
        }
        if (p.x != taille_x - 1) {
            if (p.y != 0) {
                voisins.add(HMR.get(positions[p.x + 1][p.y - 1]));
            }
            if (p.y != taille_y - 1) {
                voisins.add(HMR.get(positions[p.x + 1][p.y + 1]));
            }
            voisins.add(HMR.get(positions[p.x + 1][p.y]));
        }
        if (p.y != taille_y - 1) {
            voisins.add(HMR.get(positions[p.x][p.y + 1]));
        }
        if (p.y != 0) {
            voisins.add(HMR.get(positions[p.x][p.y - 1]));
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

    public void enregistrer() throws FileNotFoundException, IOException {
        File fichier = new File("save.txt");
        if (!fichier.exists()) {
            fichier.createNewFile();
        }
        FileOutputStream FOS = new FileOutputStream(fichier);
        String partie = new String();
        partie += this.taille_x + "\n";
        partie += this.taille_y + "\n";
        partie += this.nb_mines + "\n";
        for (int i = 0; i < taille_x; i++) {
            for (int j = 0; j < taille_y; j++) {
                if (cases[i][j].isRevealed()) {
                    partie += "1\n";
                } else if (cases[i][j].isFlagged()) {
                    if (cases[i][j].isMined()) {
                        partie += "3\n";
                    } else {
                        partie += "2\n";
                    }
                } else if (cases[i][j].isMined()) {
                    partie += "-1\n";
                }else {
                    partie += "0\n";
                }
            }
        }
        FOS.write(partie.getBytes("ascii"));
    }

    public Jeu charger() throws FileNotFoundException, IOException {
        //Déclaration des objets permettant la lecture ligne à ligne du fichier
        File fichier = new File("save.txt");
        FileReader FR = new FileReader(fichier);
        BufferedReader bf = new BufferedReader(FR);
        int i = 0, j = 0;
        //Si le fichier de sauvegarde existe
        if (fichier.exists()) {
            String ligne = bf.readLine();//La première ligne du fichier => la taille en x
            taille_x = Integer.parseInt(ligne);
            ligne = bf.readLine();//La seconde représente la taille en y
            taille_y = Integer.parseInt(ligne);
            ligne = bf.readLine();//La 3e représente le nombre de bombes
            this.nb_mines = Integer.parseInt(ligne);
            this.nb_drapeaux = 0;
            this.nb_cases_restantes = this.taille_x * this.taille_y;
            this.revelees = 0;
            this.victoire = false;
            this.cases = new Case[taille_x][taille_y];
            this.positions = new Position[taille_x][taille_y];
            this.HM = new HashMap();
            this.HMR = new HashMap();
            
            for (i = 0; i < taille_x; i++) {
                for (j = 0; j < taille_y; j++) {
                    this.cases[i][j] = new Case();//On crée des cases et on les ajoute aux HashMaps
                    positions[i][j] = new Position(i, j);
                    HM.put(cases[i][j], positions[i][j]);
                    HMR.put(positions[i][j], cases[i][j]);
                }
            }
            this.setJeu();//Pour chaque case, on définit leur attribut jeu comme l'objet courant (this)
            for (i = 0; i < taille_x; i++) {
                for (j = 0; j < taille_y; j++) {//On parcourt les cases
                    ligne = bf.readLine();
                    switch (Integer.parseInt(ligne)) {//Selon la valeur lue, on en déduit le statut
                        case -1:                      // de la case
                            cases[i][j].setMined(true);
                            break;
                        case 1:
                            cases[i][j].setRevealed(true);
                            break;
                        case 2:
                            cases[i][j].setFlagged(true);
                            break;
                        case 3:
                            cases[i][j].setFlagged(true);
                            cases[i][j].setMined(true);
                    }
                    cases[i][j].refresh();
                }
            }
        }
        setChanged();
        notifyObservers();
        return this;
    }

    public int getNb_drapeaux() {
        return nb_drapeaux;
    }

//setters et getters
    public void setNb_drapeaux(int nb_drapeaux) {
        this.nb_drapeaux = nb_drapeaux;
        this.setChanged();
        this.notifyObservers();
    }

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
                if (this.cases[i][j] == null) {
                    System.out.println("case " + i + "," + j + " nulle");
                } else {
                    this.cases[i][j].setJeu(this);
                }
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
