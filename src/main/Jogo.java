package main;

import java.lang.Math;

public class Jogo {
    private static final int TAM_TABULEIRO = 6;
    private int[][] tabuleiro;

    public Jogo() {
        tabuleiro = new int[TAM_TABULEIRO][TAM_TABULEIRO];
    }

    public void iniciar() {
        System.out.println("\nBem-vindo ao Jogo da Vida!\n");

        for (int i = 0; i < TAM_TABULEIRO; i++) {
            for (int j = 0; j < TAM_TABULEIRO; j++) {
                tabuleiro[i][j] = obterNumeroAleatorio();
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void proximaGeracao() {
        gerarNovaConfiguracao();
    }

    private void gerarNovaConfiguracao() {
        int[][] novoTabuleiro = new int[TAM_TABULEIRO][TAM_TABULEIRO];

        for (int i = 0; i < TAM_TABULEIRO; i++) {
            for (int j = 0; j < TAM_TABULEIRO; j++) {

                int qtdVizinhosVivos = calcularQuantidadeVizinhos(i, j);

                if (tabuleiro[i][j] == 1) {
                    if (qtdVizinhosVivos < 2 || qtdVizinhosVivos > 3)
                        novoTabuleiro[i][j] = 0;
                    else
                        novoTabuleiro[i][j] = 1;
                }
                if (tabuleiro[i][j] == 0) {
                    if (qtdVizinhosVivos == 3)
                        novoTabuleiro[i][j] = 1;
                    else
                        novoTabuleiro[i][j] = 0;
                }
            }
        }
        tabuleiro = novoTabuleiro;
    }

    private int calcularQuantidadeVizinhos(int i, int j) {
        int qtdVizinhosVivos = 0;

        int[] deslocamentoX = { -1, -1, -1, 0, 0, 1, 1, 1 };
        int[] deslocamentoY = { -1, 0, 1, -1, 1, -1, 0, 1 };

        for (int k = 0; k < 8; k++) {
            int vizinhoCoordenadaX = i + deslocamentoX[k];
            int vizinhoCoordenadaY = j + deslocamentoY[k];

            if (vizinhoCoordenadaX >= 0 && vizinhoCoordenadaX < TAM_TABULEIRO &&
                    vizinhoCoordenadaY >= 0 && vizinhoCoordenadaY < TAM_TABULEIRO
                    && tabuleiro[vizinhoCoordenadaX][vizinhoCoordenadaY] == 1) {
                qtdVizinhosVivos++;
            }
        }
        return qtdVizinhosVivos;
    }

    public void gerarRodada() {
        int[][] tabuleiroAnterior = new int[TAM_TABULEIRO][TAM_TABULEIRO];

        for (int i = 0; i < TAM_TABULEIRO; i++) {
            for (int j = 0; j < TAM_TABULEIRO; j++) {
                tabuleiroAnterior[i][j] = tabuleiro[i][j];
            }
        }

        gerarNovaConfiguracao();

        System.out.println("\n Anterior               Atual");

        for (int i = 0; i < TAM_TABULEIRO; i++) {
            for (int j = 0; j < TAM_TABULEIRO; j++) {
                System.out.print(tabuleiroAnterior[i][j] + " ");
            }

            if (i == 3)
                System.out.print("   ==>   ");
            else
                System.out.print("         ");

            for (int j = 0; j < TAM_TABULEIRO; j++) {
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
    }

    private int obterNumeroAleatorio() {
        return (int) Math.round(Math.random());
    }

    public void setTabuleiro(int[][] novoTabuleiro) {
        for (int i = 0; i < TAM_TABULEIRO; i++) {
            for (int j = 0; j < TAM_TABULEIRO; j++) {
                if (novoTabuleiro[i][j] != 0 && novoTabuleiro[i][j] != 1) {
                    throw new IllegalArgumentException(
                            "Valor inválido na posição (" + i + "," + j + "). Somente 0 ou 1 são permitidos.");
                }
            }
        }
        this.tabuleiro = novoTabuleiro;
    }

    public int[][] getTabuleiro() {
        return tabuleiro;
    }
}
