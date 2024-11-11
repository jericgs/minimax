/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minimaxjogodavelha.Interface;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Erick Gomes
 */
public class Tabulero extends JFrame {

    static JPanel jPanelTabuleiro = null;

    public JPanel getJPanelTabuleiro() {
        if (jPanelTabuleiro == null) {
            jPanelTabuleiro = new JPanel();
            jPanelTabuleiro.setLayout(null);
            jPanelTabuleiro.setBounds(new Rectangle(167, 101, 500, 500));
            jPanelTabuleiro.setLayout(new GridLayout(3, 3));
            jPanelTabuleiro.setVisible(true);
            jPanelTabuleiro.setBorder(BorderFactory.createTitledBorder(
                    BorderFactory.createBevelBorder(BevelBorder.LOWERED),
                    "Tabuleiro",
                    TitledBorder.CENTER,
                    TitledBorder.DEFAULT_POSITION,
                    new Font("calibri light", Font.BOLD, 18),
                    new Color(30, 144, 255)));

        }
        return jPanelTabuleiro;
    }
}
