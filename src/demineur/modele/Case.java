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
    private boolean exploded;
    private Jeu jeu;
    private int bombes_adjacentes;

    public Case(boolean flagged) {
        this.flagged = flagged;
    }

    public Case(boolean flagged, boolean revealed, boolean mined) {
        this.flagged = flagged;
        this.revealed = revealed;
        this.mined = mined;
    }

    public Case() {
        this.bombes_adjacentes = 0;
        this.flagged = false;
        this.revealed = false;
        this.mined = false;
        this.exploded = false;
    }

    public void trouverBombes_Adjacentes() {
        ArrayList<Case> voisins = this.getVoisins();
        for (Case c : voisins) {
            if (c.isMined()) {
                this.bombes_adjacentes++;
            }
        }
    }

    public void estClique(boolean flag, boolean voisin) {
        //flag == true --> clic droit -- mettre un drapeau
        //flag == false --> clic gauche -- révéler la case
        //voisin == true --> parcours des voisins pour les révéler
        //voisin == false --> parcours normal

        if (voisin) {
            if (this.bombes_adjacentes == this.getNBVoisinsFlagged()) {
                ArrayList<Case> voisins = this.getVoisins();
                for (Case v : voisins) {
                    v.estClique(false, false);
                }
            }
        }

        if (!this.isRevealed()) {
            if (flag) {
                this.setFlagged(!this.isFlagged());
                //Mise à jour du compteur de bombes restantes dans la grille à -1 / +1 si on en rajoute un un jour
            } else {
                if (!this.isFlagged()) {
                    this.setRevealed(true);
                    if (this.isMined()) {
                        this.setExploded(true);
                        this.jeu.revealAll(true);//faire perdre le joueur
                    } else {
                        this.jeu.nb_cases_dec();
                        if (this.getBombes_adjacentes() == 0) {
                            ArrayList<Case> voisins = this.getVoisins();
                            for (Case v : voisins) {
                                v.estClique(false, false);
                            }
                        }
                    }
                }
            }
        }
    }

    public void refresh() {
        this.setChanged();
        this.notifyObservers();
    }

    public int getNBVoisinsFlagged() {
        int i = 0;
        ArrayList<Case> voisins = this.getVoisins();
        for (Case c : voisins) {
            if (c.isFlagged()) {
                i++;
            }
        }
        return i;
    }
//Getters et setters

    public boolean isMined() {
        return mined;
    }

    public void setMined(boolean mined) {
        this.mined = mined;
        this.setChanged();
        this.notifyObservers();
    }

    public boolean isFlagged() {
        return flagged;
    }

    public Jeu getJeu() {
        return jeu;
    }

    public void setJeu(Jeu jeu) {
        this.jeu = jeu;
        this.setChanged();
        this.notifyObservers();
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
        if (flagged) {
            this.jeu.setNb_drapeaux(this.jeu.getNb_drapeaux() + 1);
        } else {
            this.jeu.setNb_drapeaux(this.jeu.getNb_drapeaux() - 1);
        }
        setChanged();
        notifyObservers();
    }

    public int getBombes_adjacentes() {
        return bombes_adjacentes;
    }

    public void setBombes_adjacentes(int bombes_adjacentes) {
        this.bombes_adjacentes = bombes_adjacentes;
    }

    public ArrayList<Case> getVoisins() {
        ArrayList<Case> voisins;
        voisins = this.jeu.getVoisins(this);
        return voisins;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
        this.jeu.setRevelees(this.jeu.getRevelees() + 1);
        setChanged();
        notifyObservers();
    }

    public boolean isExploded() {
        return exploded;
    }

    public void setExploded(boolean exploded) {
        this.exploded = exploded;
    }

}
