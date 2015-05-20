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
    private int x, y;

    public Case(int x, int y) {
        this.x = x;
        this.y = y;
        this.flagged = false;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
        setChanged();
        notifyObservers();
    }

}
