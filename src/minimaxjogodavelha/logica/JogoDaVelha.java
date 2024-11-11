/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minimaxjogodavelha.logica;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import minimaxjogodavelha.Interface.Cena;

/**
 *
 * @author Erick Gomes
 */
public class JogoDaVelha {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Cena cena = new Cena();
                cena.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                cena.execultarCena();
                cena.setVisible(true);
            }
        });
    }
}
