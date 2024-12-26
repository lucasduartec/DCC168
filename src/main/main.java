package main;

import java.util.Scanner;

public class main {

	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);

		Jogo jogo = new Jogo();
		jogo.iniciar();

		int opcao = 0;
		int numeroGeracao = 1;

		while (opcao != 2) {
			System.out.println("\nSelecione uma opção:\n1 - Gerar nova configuração\n2 - Sair\n");
			opcao = teclado.nextInt();

			if (opcao == 1) {
				System.out.println("\n================================");
				System.out.println("\nGeração: " + numeroGeracao);
				numeroGeracao++;
				jogo.gerarRodada();
			}
		}

		teclado.close();
	}
}
