/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demineur.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Vladimir
 */
public class CaseVue extends JPanel {
    private int x;
    private int y;
    private boolean flagged;
    
    public CaseVue(int x, int y) throws IOException {
        super(new BorderLayout());
        this.x = x;
        this.y = y;
        BufferedImage myPicture = ImageIO.read(new File("C:/Users/Vladimir/Documents/NetBeansProjects/Demineur/src/img/case_vide.png"));
        ImageIcon pic = new ImageIcon(myPicture);
        JLabel piclabel = new JLabel(pic);
        this.add(piclabel/*, BorderLayout.CENTER*/);
        this.setSize(10, 10);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(new JTextField("("+x+","+y+")"), BorderLayout.SOUTH);
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }
    
    public void setImage(String path) throws IOException
    {
        BufferedImage myPicture = ImageIO.read(new File(path));
        ImageIcon pic = new ImageIcon(myPicture);
        JLabel piclabel = new JLabel(pic);
        this.add(piclabel/*, BorderLayout.CENTER*/);
    }
}
