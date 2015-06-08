/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demineur.vue;

import demineur.controler.CaseListener;
import demineur.modele.Jeu;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Vladimir
 */
public class FenetrePrincipale extends JFrame implements Observer {

    private JMenuBar menu_bar;
    private JMenuItem MenuItem_parametres;
    private JPanel corps;
    private JPanel grille;
    private Jeu jeu;
    private JRadioButtonMenuItem rbMenuItem1, rbMenuItem2, rbMenuItem3;
    private JSpinner height_spinner, width_spinner, mines_spinner;
    private JButton bouton_diff, bouton_perso;
    private ButtonGroup group_radio;
    private JLabel menu_label;

    public FenetrePrincipale() throws IOException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        //Instanciation des composants de jeu :
        this.menu_bar = new JMenuBar();
        MenuItem_parametres = new JMenuItem("Paramètres");
        JMenu Menu = new JMenu("Menu");
        Menu.add(MenuItem_parametres);
        this.grille = new JPanel(new GridLayout());

        rbMenuItem1 = new JRadioButtonMenuItem("Facile");
        rbMenuItem1.setSelected(true);
        rbMenuItem1.setMnemonic(KeyEvent.VK_E);
        rbMenuItem1.setActionCommand("Facile");

        rbMenuItem2 = new JRadioButtonMenuItem("Moyen");
        rbMenuItem2.setMnemonic(KeyEvent.VK_M);
        rbMenuItem2.setActionCommand("Moyen");

        rbMenuItem3 = new JRadioButtonMenuItem("Difficile");
        rbMenuItem3.setMnemonic(KeyEvent.VK_H);
        rbMenuItem3.setActionCommand("Difficile");

        bouton_diff = new JButton("Jouer");

        height_spinner = new JSpinner(new SpinnerNumberModel(0, 0, 40, 1));
        width_spinner = new JSpinner(new SpinnerNumberModel(0, 0, 40, 1));
        mines_spinner = new JSpinner(new SpinnerNumberModel(0, 0, 40, 1));

        bouton_perso = new JButton("Jouer");

        this.menu_label = new JLabel();
        // TODO : IL FAUT INSTANCIER LE JMENU POUR Y AJOUTER LES OPTIONS AVANT DE L'ADD
        menu_bar.add(Menu);

        this.add(menu_bar, BorderLayout.NORTH);
        this.setFenetreMenu();
    }

    public void setFenetreJeu(Jeu jeu) throws IOException {

        if (corps != null) {
            this.remove(corps);
        }

        jeu.setJeu();
        this.jeu = jeu;
        this.jeu.addObserver(this);
        int x = jeu.getX();
        int y = jeu.getY();

        this.grille = new JPanel(new GridLayout(x, y));

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                CaseVue cv = new CaseVue(i, j, this.jeu);
                cv.addMouseListener(new CaseListener(this.jeu, this));
                this.grille.add(cv);
            }
        }
        this.menu_label.setText("                              "//Espace l'affichage
                + "Il vous reste" + (this.jeu.getNb_mines() - this.jeu.getNb_drapeaux()) + "bombes à désamorcer");
        this.menu_bar.add(this.menu_label);
        //setContentPane(grille);
        this.add(grille, BorderLayout.CENTER);
        this.revalidate();
    }

    public final void setFenetreMenu() {
        this.menu_bar.remove(this.menu_label);
        if (grille != null) {
            this.remove(grille);
        }
        this.corps = new JPanel(new BorderLayout());
        JPanel panel1 = new JPanel();
        group_radio = new ButtonGroup();

        JLabel difficulty_label = new JLabel("Choisissez un niveau de difficulté");
        panel1.add(difficulty_label);

        group_radio.add(rbMenuItem1);
        panel1.add(rbMenuItem1);

        group_radio.add(rbMenuItem2);
        panel1.add(rbMenuItem2);

        group_radio.add(rbMenuItem3);
        panel1.add(rbMenuItem3);

        panel1.add(bouton_diff);
        corps.add(panel1, BorderLayout.NORTH);

        JPanel panel2 = new JPanel();
        JLabel personnalise = new JLabel("Paramtrez votre grille de jeu : ");
        panel2.add(personnalise);

        JLabel label_height = new JLabel("Hauteur");
        panel2.add(label_height);
        panel2.add(height_spinner);

        JLabel label_width = new JLabel("Largeur");
        panel2.add(label_width);
        panel2.add(width_spinner);

        JLabel label_mines = new JLabel("Mines");
        panel2.add(label_mines);
        panel2.add(mines_spinner);

        panel2.add(bouton_perso);
        this.corps.add(panel2, BorderLayout.CENTER);
        this.add(corps);
        this.revalidate();

    }

    @Override
    public void update(Observable o, Object arg) {
        if (this.jeu.isVictoire() == false) {
            if (this.jeu.getNb_cases_restantes() > this.jeu.getNb_mines()) {
                JOptionPane.showMessageDialog(null, "Partie Perdue", "Defaite", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Partie Gagnée", "Victoire", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
// Getters & Setters

    public JRadioButtonMenuItem getRbMenuItem1() {
        return rbMenuItem1;
    }

    public void setRbMenuItem1(JRadioButtonMenuItem rbMenuItem1) {
        this.rbMenuItem1 = rbMenuItem1;
    }

    public JRadioButtonMenuItem getRbMenuItem2() {
        return rbMenuItem2;
    }

    public void setRbMenuItem2(JRadioButtonMenuItem rbMenuItem2) {
        this.rbMenuItem2 = rbMenuItem2;
    }

    public JRadioButtonMenuItem getRbMenuItem3() {
        return rbMenuItem3;
    }

    public void setRbMenuItem3(JRadioButtonMenuItem rbMenuItem3) {
        this.rbMenuItem3 = rbMenuItem3;
    }

    public JButton getBouton_diff() {
        return bouton_diff;
    }

    public void setBouton_diff(JButton bouton_diff) {
        this.bouton_diff = bouton_diff;
    }

    public JButton getBouton_perso() {
        return bouton_perso;
    }

    public void setBouton_perso(JButton bouton_perso) {
        this.bouton_perso = bouton_perso;
    }

    public JSpinner getHeight_spinner() {
        return height_spinner;
    }

    public void setHeight_spinner(JSpinner height_spinner) {
        this.height_spinner = height_spinner;
    }

    public JSpinner getWidth_spinner() {
        return width_spinner;
    }

    public void setWidth_spinner(JSpinner width_spinner) {
        this.width_spinner = width_spinner;
    }

    public JSpinner getMines_spinner() {
        return mines_spinner;
    }

    public void setMines_spinner(JSpinner mines_spinner) {
        this.mines_spinner = mines_spinner;
    }

    public JMenuBar getMenu_bar() {
        return menu_bar;
    }

    public JMenuItem getMenuItem_parametres() {
        return MenuItem_parametres;
    }

    public void setMenuItem_parametres(JMenuItem MenuItem_parametres) {
        this.MenuItem_parametres = MenuItem_parametres;
    }

    public void setMenu_bar(JMenuBar menu_bar) {
        this.menu_bar = menu_bar;
    }

    public JPanel getCorps() {
        return corps;
    }

    public void setCorps(JPanel corps) {
        this.corps = corps;
    }

    public JPanel getGrille() {
        return grille;
    }

    public void setGrille(JPanel grille) {
        this.grille = grille;
    }

    public Jeu getJeu() {
        return jeu;
    }

    public void setJeu(Jeu jeu) {
        this.jeu = jeu;
    }

    public ButtonGroup getGroup_radio() {
        return group_radio;
    }

    public void setGroup_radio(ButtonGroup group_radio) {
        this.group_radio = group_radio;
    }

}
