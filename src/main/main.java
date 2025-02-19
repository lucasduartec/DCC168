package main;

import java.util.Scanner;

public class main {

	public static void main(String[] args) {

        Jogo jogo = new Jogo();
        Scanner scanner = new Scanner(System.in);

        jogo.iniciar();

        boolean continuar = true;
        while (continuar) {
            System.out.println("\nDigite 'G' para gerar nova rodada ou 'S' para sair:");
            String escolha = scanner.nextLine().trim().toUpperCase();

            if (escolha.equals("G")) {
                jogo.gerarRodada();
            } else if (escolha.equals("S")) {
                continuar = false;
                System.out.println("Encerrando o jogo...");
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
        scanner.close();
	}
}
