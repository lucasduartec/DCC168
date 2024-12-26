package main;

import java.lang.Math;

public class Jogo {
	private static final int TAM_TABULEIRO = 6;

	private int[][] tabuleiro;

	public Jogo() {
		tabuleiro = new int[TAM_TABULEIRO][TAM_TABULEIRO];
	}

	public void iniciar() {
		System.out.println();

		for (int i = 0; i < TAM_TABULEIRO; i++) {
			for (int j = 0; j < TAM_TABULEIRO; j++) {
				tabuleiro[i][j] = obterNumeroAleatorio();
				System.out.print(tabuleiro[i][j] + " ");
			}
			System.out.println();
		}
	}

	private void gerarNovaConfiguracao() {

		int[][] novoTabuleiro = new int[TAM_TABULEIRO][TAM_TABULEIRO];

		for (int i = 0; i < TAM_TABULEIRO; i++) {
			for (int j = 0; j < TAM_TABULEIRO; j++) {
				
				int qtdVizinhosVivos = calcularQuantidadeVizinhos(i,j);

				// Qualquer célula com dois vizinhos vivos continua no mesmo estado para a
				// próxima geração.
				if (qtdVizinhosVivos == 2) {
					novoTabuleiro[i][j] = tabuleiro[i][j];
				} else {
					if (tabuleiro[i][j] == 1) {

						// Qualquer célula viva com menos de dois vizinhos vivos morre de solidão
						if (qtdVizinhosVivos < 2) {
							novoTabuleiro[i][j] = 0;
						}

						// Qualquer célula viva com mais de três vizinhos vivos morre de superpopulação.
						if (qtdVizinhosVivos > 3) {
							novoTabuleiro[i][j] = 0;
						}

					}

					// Qualquer célula morta com exatamente três vizinhos vivos se torna uma célula
					// viva.
					if (tabuleiro[i][j] == 0 && qtdVizinhosVivos == 3) {
						novoTabuleiro[i][j] = 1;
					}

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

			if (vizinhoCoordenadaX >= 0 && vizinhoCoordenadaX < TAM_TABULEIRO && vizinhoCoordenadaY >= 0
					&& vizinhoCoordenadaY < TAM_TABULEIRO) {

				if (tabuleiro[vizinhoCoordenadaX][vizinhoCoordenadaY] == 1)
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

		System.out.println();

		for (int i = 0; i < TAM_TABULEIRO; i++) {
			for (int j = 0; j < TAM_TABULEIRO; j++) {
				System.out.print(tabuleiroAnterior[i][j] + " ");
			}

			System.out.print("      ");

			for (int j = 0; j < TAM_TABULEIRO; j++) {
				System.out.print(tabuleiro[i][j] + " ");
			}
			System.out.println();
		}
	}

	private int obterNumeroAleatorio() {
		return (int) Math.round(Math.random());
	}
}
