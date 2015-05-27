/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demineur.vue;

import demineur.controler.CaseListener;
import demineur.modele.Jeu;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 *
 * @author Vladimir
 */
public class FenetrePrincipale extends JFrame {

    private JMenuBar menu_bar;
    private JPanel corps;
    private JPanel grille;
    private Jeu jeu;

    public JMenuBar getMenu_bar() {
        return menu_bar;
    }

    public void setMenu_bar(JMenuBar menu_bar) {
        this.menu_bar = menu_bar;
    }

    public JPanel getCorps() {
        return corps;
    }

    public void setCorps(JPanel corps) {
        this.corps = corps;
    }

    public JPanel getGrille() {
        return grille;
    }

    public void setGrille(JPanel grille) {
        this.grille = grille;
    }

    public Jeu getJeu() {
        return jeu;
    }

    public void setJeu(Jeu jeu) {
        this.jeu = jeu;
    }

    public FenetrePrincipale(Jeu jeu) throws IOException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jeu=jeu;
        int x = jeu.getX();
        int y = jeu.getY();
        this.setLayout(new BorderLayout());
        this.menu_bar = new JMenuBar();
        menu_bar.add(new JMenu("Menu"));     // TODO : IL FAUT INSTANCIER LE JMENU POUR Y AJOUTER LES LABEL AVANT DE L'ADD
        
        this.corps = new JPanel();
        this.grille = new JPanel(new GridLayout(x, y));

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                CaseVue cv = new CaseVue(i, j);
                cv.addMouseListener(new CaseListener(this.jeu, this));
//                cv.getBouton().addActionListener(new CaseListener(this.jeu, this));
                this.grille.add(cv);
            }
        }

        this.add(menu_bar, BorderLayout.NORTH);
        this.add(corps);
        this.add(grille, BorderLayout.CENTER);
    }

}
