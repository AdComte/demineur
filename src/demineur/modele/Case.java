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
    
    public ArrayList<Case> getVoisins(){
        return this.jeu.getVoisins(this);
    }
    public boolean isRevealed() {
        return revealed;
    }

    private void setRevealed(boolean revealed) {
        this.revealed = revealed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public Case(int x, int y) {
        this.x = x;
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
    
    public void estClique(boolean flag)
    {
        if (flag)
        {
            if (!this.isRevealed())
            {
                this.setFlagged(!this.isFlagged());
                //Mis à jour du compteur de bombes restantes dans la grille à -1 / +1 si on en rajoute un un jour
            }
        }
        else
        {
            if(!this.isFlagged())
            {
                this.setRevealed(true);
                if()
                //etape 1 : réveler la case
                //etape 2 :
                    //si c'est une bombe, le joueur a perdu
                    //si c'est un nombre, on le laisse tel quel
                    //si c'est un 0, on récupère la liste des adjacents, et on les révèle
                
                //TODO : Logique du clic gauche et de la révélation des cases adjacentes ou non / bombe fin de partie / valeur de la case
                //Ne pas oublier de décompter le nombre de cases restantes à reveler pour le compteur de fin de partie
            }
        }
        setChanged();
        notifyObservers();
    }
    
    //Ajouter une fonction logique d'initialisation qui parcourt les voisins et qui compte les bombes voisines, seulement si la case n'est pas une bombe elle même
}
