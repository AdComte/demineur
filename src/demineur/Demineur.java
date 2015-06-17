/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demineur;

import demineur.controler.CaseListener;
import demineur.vue.FenetrePrincipale;
import java.awt.Dimension;
import java.awt.Toolkit;
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
        FenetrePrincipale FP = new FenetrePrincipale();
        FP.setResizable(false);     //verrouille la taille de la fenêtre
        FP.setLocation(200, 100);
        FP.setPreferredSize(new Dimension(600, 600));
        FP.setTitle("Jeu du Démineur -- COMTE && MOURIER");
        FP.setIconImage(Toolkit.getDefaultToolkit().getImage("src/img/logo.jpg"));
        FP.pack();
        CaseListener cl = new CaseListener(FP);
        cl.getFenetre().getMenuItem_parametres().addActionListener(cl);
        cl.getFenetre().getMenuItem_charger().addActionListener(cl);
        cl.getFenetre().getMenuItem_enregistrer().addActionListener(cl);
        cl.getFenetre().getBouton_diff().addActionListener(cl);
        cl.getFenetre().getBouton_perso().addActionListener(cl);

        FP.setVisible(true);
    }
}
