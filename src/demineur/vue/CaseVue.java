/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demineur.vue;

import demineur.controler.CaseListener;
import demineur.modele.Case;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Vladimir
 */
public class CaseVue extends JPanel implements Observer {

    private Case Case;
    private JButton bouton;
    private ImageIcon pic;

    public Case getCase() {
        return Case;
    }

    public JButton getBouton() {
        return bouton;
    }

    public void setBouton(JButton bouton) {
        this.bouton = bouton;
    }

    public ImageIcon getPic() {
        return pic;
    }

    public void setPic(ImageIcon pic) {
        this.pic = pic;
    }

    public void setCase(Case Case) {
        this.Case = Case;
    }

    public CaseVue(int x, int y) throws IOException {
        super(new BorderLayout());
//        this.bouton = new JButton();
        this.Case = new Case(x, y);
        this.Case.addObserver(this);
        this.setImage("C:/Users/Vladimir/Documents/NetBeansProjects/Demineur/src/img/case_vide.png");
//        this.add(bouton);
        this.setSize(10, 10);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
//        this.add(new JTextField("(" + x + "," + y + ")"), BorderLayout.SOUTH);
    }

    public void setImage(String path) throws IOException {
        this.removeAll();
        BufferedImage BI = ImageIO.read(new File(path));
        pic = new ImageIcon(BI);
        JLabel label = new JLabel(pic);
        this.add(label);
//        this.bouton.setIcon(pic);
//        this.prepareImage(BI, this);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Entree dans update");
        this.removeAll();
        try {
            if (this.Case.isFlagged() == true) {
                this.setImage("C:/Users/Vladimir/Documents/NetBeansProjects/Demineur/src/img/flag.png");
            } else {
                this.setImage("C:/Users/Vladimir/Documents/NetBeansProjects/Demineur/src/img/case_vide.png");
            }
        } catch (IOException ex) {
            Logger.getLogger(CaseListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.removeAll();
    }
}
