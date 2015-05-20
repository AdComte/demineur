/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demineur.vue;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vladimir
 */
public class CaseListener extends Label implements ActionListener {

    public CaseListener() {
        super("0", Label.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
}
