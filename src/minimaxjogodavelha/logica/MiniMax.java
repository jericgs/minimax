/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minimaxjogodavelha.logica;

import java.util.Random;

/**
 *
 * @author jeric
 */
public class MiniMax {


    private int[][] ganharCombinacoes = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9},
    {1, 4, 7}, {2, 5, 8}, {3, 6, 9}, {1, 5, 9}, {3, 5, 7}};

    // Metodo de AI
    public int min_max(int[] board) {
        int melhorVal = -1000000;
        int index = 0;
        int[] melhorMovimento = new int[10];
        int[] p_lib = new int[10];
        p_lib = poz_lib_cp(board);
        int nr_poz = 0;

        for (int cc = 1; cc <= 9; cc++) {
            if (p_lib[cc] > 0) {
                nr_poz++;
            }
        }

        int nr = 1;
        while (nr <= nr_poz) {
            int mut = p_lib[nr];
            board[mut] = 2;

            int val = MinMove(board);
            if (val > melhorVal) {
                melhorVal = val;
                index = 0;
                melhorMovimento[index] = mut;
            } else if (val == melhorVal) {
                index++;
                melhorMovimento[index] = mut;
            }
            board[mut] = 0;
            nr++;
        }

        int r = 0;
        if (index > 0) {
            Random x = new Random();
            r = x.nextInt(index);
        }
        return melhorMovimento[r];
    }

    public int MinMove(int[] board) {
        int pos_value = table_winner(board);

        if (pos_value != -1) {
            return pos_value;
        }

        int best_val = 1000000;
        int[] p_lib = new int[10];
        p_lib = poz_lib_cp(board);
        int nr_poz = 0;
        for (int cc = 1; cc <= 9; cc++) {
            if (p_lib[cc] > 0) {
                nr_poz++;
            }
        }
        int nr = 1;
        while (nr <= nr_poz) {
            int mut = p_lib[nr];
            board[mut] = 1;
            int val = MaxMove(board);
            if (val < best_val) {
                best_val = val;
            }
            board[mut] = 0;
            nr++;
        }
        return best_val;
    }

    public int MaxMove(int[] board) {
        int pos_value = table_winner(board);

        if (pos_value != -1) {
            return pos_value;
        }
        int best_val = -1000000;
        int[] p_lib = new int[10];
        p_lib = poz_lib_cp(board);
        int nr_poz = 0;
        for (int cc = 1; cc <= 9; cc++) {
            if (p_lib[cc] > 0) {
                nr_poz++;
            }
        }
        int nr = 1;

        while (nr <= nr_poz) {
            int mut = p_lib[nr];
            board[mut] = 2;
            int val = MinMove(board);
            if (val > best_val) {
                best_val = val;
            }

            board[mut] = 0;
            nr++;
        }
        return best_val;
    }

    public int[] poz_lib_cp(int[] cop) {
        int bb = 0;
        int[] poz_0 = new int[10];
        for (int b = 1; b <= 9; b++) {
            if (cop[b] == 0) {
                bb++;
                poz_0[bb] = b;
            }
        }
        return poz_0;
    }

    @SuppressWarnings("unused")
    public int table_winner(int[] cc) {
        int rez = -1;
        int zero = 0;
        String winn = "";
        boolean game_over = false;

        for (int i = 0; i <= 7; i++) {
            if ((cc[ganharCombinacoes[i][0]] == 1)
                    && (cc[ganharCombinacoes[i][0]] == cc[ganharCombinacoes[i][1]])
                    && (cc[ganharCombinacoes[i][1]] == cc[ganharCombinacoes[i][2]])
                    && (cc[ganharCombinacoes[i][0]] != 0)) {
                game_over = true;
                winn = "X";
                rez = -1000000;
            }

            if ((cc[ganharCombinacoes[i][0]] != 2)
                    || (cc[ganharCombinacoes[i][0]] != cc[ganharCombinacoes[i][1]])
                    || (cc[ganharCombinacoes[i][1]] != cc[ganharCombinacoes[i][2]])
                    || (cc[ganharCombinacoes[i][0]] == 0)) {
                continue;
            }
            game_over = true;
            winn = "O";
            rez = 1000000;
        }

        for (int c = 1; c <= 9; c++) {
            if (cc[c] != 0) {
                zero++;
            }
        }

        if ((zero >= 9) && (!game_over)) {
            winn = "Empate";
            rez = 0;
        }

        return rez;
    }
}
