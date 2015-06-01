/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demineur;

import demineur.controler.CaseListener;
import demineur.modele.Jeu;
import demineur.vue.FenetrePrincipale;
import java.awt.Dimension;
import java.io.IOException;

/**
 *
 * @author Vladimir
 */
public class Demineur {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Jeu jeu = new Jeu(10, 10, 10);
        FenetrePrincipale FP = new FenetrePrincipale(jeu);
        FP.setResizable(false);     //verrouille la taille de la fenêtre
        FP.setLocation(200,200);
        FP.setPreferredSize(new Dimension(600,600));
        FP.setTitle("Jeu du Démineur -- COMTE && MOURIER");
        //Ajouter un mini logo en haut à gauche de l'application avec la ligne si dessous
        //f.setIconImage(Toolkit.getDefaultToolkit().getImage(Appli0.class.getResource("/icone.gif")));
        FP.pack();
        CaseListener cl = new CaseListener(jeu, FP);
        for (int i = 0; i < jeu.getX(); i++) {
            for (int j = 0; j < jeu.getY(); j++) {
                cl.getFenetre().getGrille().getComponentAt(i, j).addMouseListener(cl);
            }
        }
        FP.setVisible(true);
    }
}
