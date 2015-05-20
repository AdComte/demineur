/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demineur.vue;
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
//    private JPanel aside;

    public FenetrePrincipale(int x, int y) throws IOException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setLayout(new BorderLayout());
        this.menu_bar = new JMenuBar();
        menu_bar.add(new JLabel("Menu"));
        this.corps = new JPanel();
        this.grille = new JPanel(new GridLayout(x,y));
        
        for(int i = 0; i<x;i++)
        {
            for(int j=0;j<y;j++)
            {
                CaseVue cv = new CaseVue(i,j);
                cv.addMouseListener(null);
                this.grille.add(cv);
            }
        }
        
        
        this.add(menu_bar, BorderLayout.NORTH);
        this.add(corps);
        this.add(grille, BorderLayout.CENTER);
    }
    
    
}
