/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demineur.controler;

import demineur.modele.Jeu;
import demineur.vue.CaseVue;
import demineur.vue.FenetrePrincipale;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vladimir
 */
public class CaseListener implements ActionListener, MouseListener, ItemListener {

    private Jeu jeu;
    private FenetrePrincipale fenetre;

    public void listenToGame() {
        if (this.jeu != null) {
            for (int i = 0; i < jeu.getX(); i++) {
                for (int j = 0; j < jeu.getY(); j++) {
                    if(this.getFenetre().getGrille().getComponentAt(i, j)==null){
                        System.out.println("Composant "+ i + " ; " + j + " de la grille est nul");
                    } else {
                    this.getFenetre().getGrille().getComponentAt(i, j).addMouseListener(this);}
                }
            }
        }
    }

    public Jeu getJeu() {
        return jeu;
    }

    public void setJeu(Jeu jeu) {
        this.jeu = jeu;
    }

    public FenetrePrincipale getFenetre() {
        return fenetre;
    }

    public void setFenetre(FenetrePrincipale fenetre) {
        this.fenetre = fenetre;
    }

    public CaseListener(Jeu jeu, FenetrePrincipale fenetre) {
        this.jeu = jeu;
        this.fenetre = fenetre;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        CaseVue c = (CaseVue) e.getSource();
        if (e.getButton() == MouseEvent.BUTTON3) {
            c.getCase().estClique(true);
        } else if (e.getButton() == MouseEvent.BUTTON1) {
            c.getCase().estClique(false);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("ActionPerformed by : " + e.getSource());
        if (e.getSource().equals(this.fenetre.getBouton_diff())) {
            //Lancer une nouvelle partie selon la difficulté
            if (e.getActionCommand().equals("Facile")) {
                this.jeu = new Jeu(10, 10, 15);
            } else if (e.getActionCommand().equals("Moyen")) {
                this.jeu = new Jeu(20, 20, 40);

            } else if (e.getActionCommand().equals("Difficile")) {
                this.jeu = new Jeu(50, 50, 100);
            }
            try {
                this.fenetre.setFenetreJeu(this.jeu);
                this.listenToGame();
            } catch (IOException ex) {
                Logger.getLogger(CaseListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource().equals(this.fenetre.getBouton_perso())) {
            //Lancer une nouvelle partie personnalisée
            this.jeu = new Jeu((int) this.fenetre.getWidth_spinner().getValue(),
                    (int) this.fenetre.getHeight_spinner().getValue(),
                    (int) this.fenetre.getMines_spinner().getValue());
            this.listenToGame();
            try {
                this.fenetre.setFenetreJeu(this.jeu);
            } catch (IOException ex) {
                Logger.getLogger(CaseListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource().equals(this.fenetre.getMenuItem_parametres())) {
            //On affiche la page des paramètres
            this.fenetre.setFenetreMenu();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        System.out.println("itemStateChanged");
    }
}
