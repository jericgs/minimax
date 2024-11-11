/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minimaxjogodavelha.Interface;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import minimaxjogodavelha.logica.MiniMax;

/**
 *
 * @author Erick Gomes
 */
public class Cena extends JFrame implements ActionListener {

    private JPanel jContentPane = null;
    private JLabel jbtTitulo = null;
    private JLabel jlbSubTitulo = null;
    private JLabel jlbSite = null;

    private static JButton[] buttons = new JButton[10];

    private int[][] ganharCombinacoes = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9},
    {1, 4, 7}, {2, 5, 8}, {3, 6, 9}, {1, 5, 9}, {3, 5, 7}};

    private int[] copiarTabela = new int[10];
    private boolean vitorias = false;
    private int counts = 0;

    private String jogador = null;
    String resultado;
    private static int vizero = 0;
    private static int inzero = 0;
    private static int embate = 0;
    private static int vix = 0;
    private static int inx = 0;

    public void execultarCena() {
        this.setSize(830, 660);
        this.setContentPane(getJContentPane());
        this.setTitle("Jogo Da Velha - MiniMax");
        // Tamanho fixo do programa, sem alteção
        this.setResizable(false);
        // Centralização a tela
        this.setLocationRelativeTo(null);

        for (int i = 1; i <= 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("Arial", Font.BOLD, 72));
            buttons[i].setText("");
            buttons[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            Tabulero.jPanelTabuleiro.add(buttons[i]);
            buttons[i].addActionListener(this);
        }
    }

    private JPanel getJContentPane() {
        if (jContentPane == null) {

            jlbSite = new JLabel();
            jlbSite.setBounds(new Rectangle(326, 611, 186, 20));
            jlbSite.setHorizontalAlignment(SwingConstants.CENTER);
            //jlbSite.setText("https://isjavado.wordpress.com");
            jlbSite.setCursor(new Cursor(Cursor.HAND_CURSOR));
            jlbSite.setToolTipText("Visite o blog");
            jlbSite.setForeground(Color.BLUE);
            jlbSite.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    //String url = "https://isjavado.wordpress.com/";
                    //BareBonesBrowserLaunch.openURL(url);
                }
            });

            jlbSubTitulo = new JLabel();
            jlbSubTitulo.setBounds(new Rectangle(89, 48, 643, 20));
            jlbSubTitulo.setHorizontalAlignment(SwingConstants.CENTER);
            jlbSubTitulo.setForeground(Color.LIGHT_GRAY);
            jlbSubTitulo.setFont(new Font("calibri light", Font.BOLD, 20));
            jlbSubTitulo.setText("Humano vs Computador");

            jbtTitulo = new JLabel();
            jbtTitulo.setBounds(new Rectangle(88, 5, 643, 40));
            jbtTitulo.setHorizontalAlignment(SwingConstants.CENTER);
            jbtTitulo.setFont(new Font("calibri light", Font.BOLD, 38));
            jbtTitulo.setForeground(new Color(0, 0, 204));
            jbtTitulo.setText("JOGO DA VELHA");

            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(new Tabulero().getJPanelTabuleiro(), null);
            jContentPane.add(jbtTitulo, null);
            jContentPane.add(jlbSubTitulo, null);
            jContentPane.add(jlbSite, null);
            //jContentPane.add(getJlbLogo(), null);
        }
        return jContentPane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton pressedButton = (JButton) e.getSource();

        if (pressedButton.getText().equals("")) {
            pressedButton.setFont(new Font("calibri light", Font.BOLD, 150));
            pressedButton.setText("X");
            pressedButton.setForeground(Color.black);
            copiarTabela = copie_Tabela();
            counts += 1;
            checarVencedor();
            
            MiniMax miniMax = new MiniMax();
            int poz_max = miniMax.min_max(copiarTabela);
            buttons[poz_max].setFont(new Font("calibri light", Font.BOLD, 150));
            buttons[poz_max].setText("O");
            buttons[poz_max].setForeground(Color.green);
            counts += 1;
            checarVencedor();
        } else {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Escolha outro movimento!!!!",
                    "Jogo Da Velha ", JOptionPane.ERROR_MESSAGE);

        }
    }

    public int[] copie_Tabela() {
        int[] tabela = new int[10];

        for (int cas = 1; cas <= 9; cas++) {
            if (buttons[cas].getText().equals("X")) {
                tabela[cas] = 1;
            } else if (buttons[cas].getText().equals("O")) {
                tabela[cas] = 2;
            } else {
                tabela[cas] = 0;
            }
        }
        return tabela;
    }

    // Metodo Checar vencedor
    public void checarVencedor() {
        int nr = 0;
        vitorias = false;

        for (int i = 0; i <= 7; i++) {
            if ((buttons[ganharCombinacoes[i][0]].getText().equals("X"))
                    && (buttons[ganharCombinacoes[i][0]].getText()
                    .equals(buttons[ganharCombinacoes[i][1]].getText()))
                    && (buttons[ganharCombinacoes[i][1]].getText()
                    .equals(buttons[ganharCombinacoes[i][2]].getText()))
                    && (!buttons[ganharCombinacoes[i][0]].getText().equals(""))) {
                jogador = "X";
                vitorias = true;
            }

            if ((!buttons[ganharCombinacoes[i][0]].getText().equals("O"))
                    || (!buttons[ganharCombinacoes[i][0]].getText().equals(buttons[ganharCombinacoes[i][1]].getText()))
                    || (!buttons[ganharCombinacoes[i][1]].getText().equals(buttons[ganharCombinacoes[i][2]].getText()))
                    || (buttons[ganharCombinacoes[i][0]].getText().equals(""))) {
                continue;
            }
            jogador = "O";
            vitorias = true;
        }

        for (int c = 1; c <= 9; c++) {
            if ((buttons[c].getText().equals("X"))
                    || (buttons[c].getText().equals("O"))) {
                nr++;
            }
        }
        if (vitorias == true) {
            if (jogador == "X") {
                vix += 1;
                inzero += 1;
                resultado = "\"X\" Ganhou o jogo!!!";
                display_winner(vix, inx, embate, resultado);
            }

            if (jogador == "O") {
                vizero += 1;
                inx += 1;
                resultado = "\"O\" Ganhou o jogo!!!";
                display_winner(vizero, inzero, embate, resultado);
            }
        }

        if ((nr == 9) && (counts >= 9) && (!vitorias)) {
            embate += 1;
            resultado = "Jogo empatado!!!";

            if (JOptionPane.showConfirmDialog(null,"Empate: " + embate + "\n\n"
                    + "Jogar de novo?\n\n", resultado, 0) != 0) {
                    this.dispose();
            } else {
                novoJogo();
            }
        }
    }

    public void display_winner(int vitoria, int derrota, int embate, String nomeJogador) {
        if (JOptionPane.showConfirmDialog(null,"Vitória: " + vitoria + "\n" + 
                                               "Empate: " + embate + "\n" +
                                               "Derrota: " + derrota + "\n\n" + "Jogar de novo?\n\n", nomeJogador, 0) != 0) {            

            // zerando os botões
            for (int i = 1; i <= 9; i++) {
                buttons[i].setText("");
                copiarTabela[i] = 0;
            }// fim do for zerando os botões

        } else {
            novoJogo();
        }
    }
    
    public void novoJogo() {
        counts = 0;
        vitorias = false;
        resultado = "";

        for (int i = 1; i <= 9; i++) {
            buttons[i].setText("");
            copiarTabela[i] = 0;
        }
    }

}
