package main;

import java.util.Scanner;

public class main {

	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);

		System.out.println("\nBem-vindo ao Jogo da Vida!");

		Jogo jogo = new Jogo();
		jogo.iniciar();

		int opcao = 0;

		while (opcao != 2) {
			System.out.println("\nSelecione uma opção:\n1 - Gerar nova configuração\n2 - Sair\n");
			opcao = teclado.nextInt();

			if (opcao == 1) {
				jogo.gerarRodada();
			}
		}

		teclado.close();
	}
}
