/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demineur.modele;

import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author Vladimir
 */
public class Case extends Observable {

    private boolean flagged;
    private boolean revealed;
    private boolean mined;
    private Jeu jeu;
    private final int x, y;
    private int bombes_adjacentes;

    public ArrayList<Case> getVoisins() {
        ArrayList<Case> voisins;
        if (this.jeu == null) {
            System.out.println("POURQUOI ?????????");
        }
        voisins = this.jeu.getVoisins(this);
        return voisins;
    }

    public boolean isRevealed() {
        return revealed;
    }

    private void setRevealed(boolean revealed) {
        this.revealed = revealed;
    }

    public void trouverBombes_Adjacentes() {
        ArrayList<Case> voisins = this.getVoisins();
        for (Case c : voisins) {
            if (c.isMined()) {
                this.bombes_adjacentes++;
            }
        }
    }

    public int getBombes_adjacentes() {
        return bombes_adjacentes;
    }

    public void setBombes_adjacentes(int bombes_adjacentes) {
        this.bombes_adjacentes = bombes_adjacentes;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Case(int x, int y) {
        this.x = x;
        this.bombes_adjacentes = 0;
        this.y = y;
        this.flagged = false;
        this.revealed = false;
        this.mined = false;
    }

    public boolean isMined() {
        return mined;
    }

    public void setMined(boolean mined) {
        this.mined = mined;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public Jeu getJeu() {
        return jeu;
    }

    public void setJeu(Jeu jeu) {
        this.jeu = jeu;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    public void estClique(boolean flag) {
        //flag == true --> clic droit
        //flag == false --> clic gauche

        if (!this.isRevealed()) {
            if (flag) {
                this.setFlagged(!this.isFlagged());
                //Mis à jour du compteur de bombes restantes dans la grille à -1 / +1 si on en rajoute un un jour

            } else {
                if (!this.isFlagged()) {
                    this.setRevealed(true);
                    if (this.isMined()) {
                        //faire perdre le joueur
                    } else {
                        if (this.getBombes_adjacentes() == 0) {
                            if (this.getVoisins() != null) {
                                ArrayList<Case> voisins = this.getVoisins();
                                for (Case v : voisins) {
                                    if (v != null) {
                                        v.estClique(false);
                                    } else {
                                        System.out.println("voisins ne contient rien");
                                    }
                                }
                            } else {
                                System.out.println("On a un probleme : getvoisins est null");
                            }
                        }
                    }
                    //Ne pas oublier de décompter le nombre de cases restantes à reveler pour le compteur de fin de partie
                }
            }
        }

        setChanged();
        notifyObservers();
    }

    //Ajouter une fonction logique d'initialisation qui parcourt les voisins et qui compte les bombes voisines, seulement si la case n'est pas une bombe elle même
}
