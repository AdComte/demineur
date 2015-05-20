/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demineur.controler;

import demineur.vue.CaseVue;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vladimir
 */
public class CaseListener extends Label implements MouseListener {
    
    public CaseListener() {
        }

    @Override
    public void mouseClicked(MouseEvent e) {
        CaseVue b = (CaseVue) e.getSource();
        b.setFlagged(!b.isFlagged());
        try {
            if (b.isFlagged()) {
                b.setImage("C:/Users/Vladimir/Documents/NetBeansProjects/Demineur/src/img/");
            } else {
                b.setImage("C:/Users/Vladimir/Documents/NetBeansProjects/Demineur/src/img/");
            }
        } catch (IOException ex) {
            Logger.getLogger(CaseListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
