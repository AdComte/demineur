/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demineur.vue;

import demineur.controler.CaseListener;
import demineur.modele.Case;
import demineur.modele.Jeu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
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
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Vladimir
 */
public class CaseVue extends JPanel implements Observer {

    private Case Case;
    private ImageIcon pic;



    public CaseVue(int x, int y, Jeu jeu) throws IOException {
        super(new BorderLayout());
        this.Case = jeu.getCases()[x][y];
        this.Case.trouverBombes_Adjacentes();
        this.Case.addObserver(this);
        this.setSize(60, 60);
        this.setImage("src/img/case_vide.png");
        this.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public void setImage(String path) throws IOException {
        BufferedImage BI = ImageIO.read(new File(path));
        pic = new ImageIcon(BI.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
        JLabel label = new JLabel(pic);
        this.add(label);
        this.updateUI();
    }

    @Override
    public void update(Observable o, Object arg) {
        this.removeAll();
        try {
            if(this.Case.isFlagged() && this.Case.getJeu().isVictoire() && !this.Case.isMined()) {
                this.setImage("src/img/flag_WRONG.png");
            } else if (this.Case.isFlagged()) {
                this.setImage("src/img/flag.png");
            } else if (!this.Case.isRevealed()) {
                this.setImage("src/img/case_vide.png");
            } else if (this.Case.isExploded()) {
                this.setImage("src/img/explode.png");
            } else if (this.Case.isMined() && !this.Case.isExploded()) {
                this.setImage("src/img/bombe.png");
            } else if (this.Case.isRevealed() && !this.Case.isMined()) {
                this.setImage("src/img/case_vide_revelee.png");
                if(Case.getBombes_adjacentes()!=0){
                JLabel numero = new JLabel(Integer.toString(Case.getBombes_adjacentes()), JLabel.CENTER);
                /* SERT POUR LA TRANSPARANCE
                numero.setBackground(new Color(0, 0, 0, 255));
                numero.setOpaque(false);
                */
                numero.setFocusable(false);
                numero.setFont(new Font("Bernard MT Condensed", Font.BOLD, 30));
                //TODO : faire la mise en forme du JLabel pour un affichage propre du numéro
                this.add(numero);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(CaseListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.updateUI();
    }
        public Case getCase() {
        return Case;
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
}
