/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demineur;

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
    FenetrePrincipale Window = new FenetrePrincipale(10,10);
    Window.pack();
    Window.setBounds(200, 0, 600, 600);
//    Window.addWindowListener(new WindowCloser());
    Window.setVisible(true);
            }
    
}
