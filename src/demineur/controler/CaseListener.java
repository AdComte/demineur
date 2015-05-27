/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demineur.controler;

import demineur.modele.Jeu;
import demineur.vue.CaseVue;
import demineur.vue.FenetrePrincipale;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Vladimir
 */
public class CaseListener implements MouseListener {
    private Jeu jeu;
    private FenetrePrincipale fenetre;

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
        CaseVue b = (CaseVue)e.getSource();
        if(e.getButton() == MouseEvent.BUTTON3)
        {
            System.out.println("flag de la case ["+b.getCase().getX()+","+b.getCase().getY()+"]"+" ="+b.getCase().isFlagged());
            b.getCase().setFlagged(!b.getCase().isFlagged());
            System.out.println("flag ="+b.getCase().isFlagged());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
   }

    @Override
    public void mouseReleased(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        CaseVue b = (CaseVue)e.getSource();
//        System.out.println("flag de la case ["+b.getCase().getX()+","+b.getCase().getY()+"]"+" ="+b.getCase().isFlagged());
//        b.getCase().setFlagged(!b.getCase().isFlagged());
//        System.out.println("flag ="+b.getCase().isFlagged());    }
}
