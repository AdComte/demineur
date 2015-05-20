/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demineur;

import demineur.controler.CaseListener;
import demineur.modele.Jeu;
import demineur.vue.FenetrePrincipale;
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
    Jeu jeu = new Jeu(10,5);
    FenetrePrincipale Window = new FenetrePrincipale(jeu);
    Window.pack();
    Window.setBounds(200, 0, 600, 600);
    CaseListener cl = new CaseListener(jeu, Window);
    for(int i=0; i<jeu.getX();i++)
    {
        for(int j=0;j<jeu.getY();j++)
        {
            cl.getFenetre().getGrille().getComponentAt(i, j).addMouseListener(cl);
        }
    }
//    Window.addWindowListener(new WindowCloser());
    Window.setVisible(true);
        
            }
    
}
