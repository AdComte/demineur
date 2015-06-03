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
//        Jeu jeu = new Jeu(10, 10, 10);
//        jeu.setJeu();
        FenetrePrincipale FP = new FenetrePrincipale();
        FP.setResizable(false);     //verrouille la taille de la fenêtre
        FP.setLocation(200, 100);
        FP.setPreferredSize(new Dimension(600, 600));
        FP.setTitle("Jeu du Démineur -- COMTE && MOURIER");
        // Get the OS style and apply it
//        try
//        {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        }
//        catch(ClassNotFoundException|InstantiationException|IllegalAccessException|UnsupportedLookAndFeelException e)
//        {
//            Logger.getLogger(Demineur.class.getName()).log(Level.SEVERE, e.getMessage(), e);
//        }
        //Ajouter un mini logo en haut à gauche de l'application avec la ligne si dessous
        //f.setIconImage(Toolkit.getDefaultToolkit().getImage(Appli0.class.getResource("/icone.gif")));
        FP.pack();
        CaseListener cl = new CaseListener(FP);
        cl.getFenetre().getMenuItem_parametres().addActionListener(cl);
        cl.getFenetre().getBouton_diff().addActionListener(cl);
        cl.getFenetre().getBouton_perso().addActionListener(cl);

        FP.setVisible(true);
    }
}
