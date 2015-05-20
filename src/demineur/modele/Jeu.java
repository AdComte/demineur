/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demineur.modele;

import java.util.Collection;
import java.util.Observable;

/**
 *
 * @author Vladimir
 */
public class Jeu extends Observable{
    private Case[][] cases;

    public Jeu(int nb_cases, int x, int y) {
        this.cases = new Case[x][y];
        for(int i=0;i<x;i++)
            for(int j=0;j<y;j++)
                cases[i][j] = new Case(i,j);
    }
    
}
