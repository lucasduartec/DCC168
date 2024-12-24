package main;

import java.lang.Math;

public class Jogo {
	private static final int TAM_TABULEIRO = 6;

	private int[][] tabuleiro;

	public Jogo() {
		tabuleiro = new int[TAM_TABULEIRO][TAM_TABULEIRO];
	}
	
	public void iniciar() {
		gerarNovaConfiguracao();
		imprimirTabuleiroInicial();
	}

	private void gerarNovaConfiguracao() {
		for (int i = 0; i < TAM_TABULEIRO; i++) {
			for (int j = 0; j < TAM_TABULEIRO; j++) {
				tabuleiro[i][j] = obterNumeroAleatorio();
			}
		}
	}
	
	private void imprimirTabuleiroInicial() {
		System.out.println();
		
		for (int i = 0; i < TAM_TABULEIRO; i++) {
			for (int j = 0; j < TAM_TABULEIRO; j++) {
				System.out.print(tabuleiro[i][j] + " ");
			}
			System.out.println();
		}
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
