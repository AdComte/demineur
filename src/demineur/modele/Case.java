/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demineur.modele;

import java.util.Observable;

/**
 *
 * @author Vladimir
 */
public class Case extends Observable {

    private boolean flagged;
    private boolean revealed;
    private final int x, y;

    public boolean isRevealed() {
        return revealed;
    }

    public void setRevealed(boolean revealed) {
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
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        System.out.println("Entree dans setFlag");
        this.flagged = flagged;
        setChanged();
        notifyObservers();
    }

}
