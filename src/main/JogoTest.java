package main;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JogoTest {

    // CT01: Célula viva com 1 vizinho deve morrer (solidão)
    @Test
    public void testCT01_CelulaMorre() {
        Jogo jogo = new Jogo();
        int[][] board = new int[6][6];
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 6; j++){
                board[i][j] = 0;
            }
        }
        // Configura: célula (2,2) viva e somente 1 vizinho (2,3) vivo
        board[2][2] = 1;
        board[2][3] = 1;
        
        jogo.setTabuleiro(board);
        jogo.proximaGeracao();
        int[][] novoBoard = jogo.getTabuleiro();
        
        // Verifica que a célula (2,2) morreu (deve ser 0)
        assertEquals(1, novoBoard[2][2], "CT01 falhou: a célula (2,2) deveria morrer.");
    }

    // CT07: Célula morta com 2 vizinhos permanece morta
    @Test
    public void testCT07_CelulaPermaneceMorta() {
        Jogo jogo = new Jogo();
        int[][] board = new int[6][6];
        // Inicializa com 0
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 6; j++){
                board[i][j] = 0;
            }
        }
        // Configura: célula (2,2) morta com 2 vizinhos vivos
        board[2][1] = 1;
        board[2][3] = 1;
        
        jogo.setTabuleiro(board);
        jogo.proximaGeracao();
        int[][] novoBoard = jogo.getTabuleiro();
        
        // Verifica que a célula (2,2) continua morta
        assertEquals(0, novoBoard[2][2], "CT07 falhou: a célula (2,2) deveria permanecer morta.");
    }

    // CT10: Célula morta com 7 vizinhos permanece morta
    @Test
    public void testCT10_CelulaPermaneceMorta() {
        Jogo jogo = new Jogo();
        int[][] board = new int[6][6];
        // Inicializa com 0
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 6; j++){
                board[i][j] = 0;
            }
        }
        // Para a célula (2,2), os 8 vizinhos são:
        // (1,1), (1,2), (1,3), (2,1), (2,3), (3,1), (3,2), (3,3)
        // Configuramos 7 vizinhos vivos (deixando, por exemplo, (1,1) como morto)
        board[1][2] = 1;
        board[1][3] = 1;
        board[2][1] = 1;
        board[2][3] = 1;
        board[3][1] = 1;
        board[3][2] = 1;
        board[3][3] = 1;
        
        jogo.setTabuleiro(board);
        jogo.proximaGeracao();
        int[][] novoBoard = jogo.getTabuleiro();
        
        // Verifica que a célula (2,2) permanece morta (pois não tem exatamente 3 vizinhos vivos)
        assertEquals(0, novoBoard[2][2], "CT10 falhou: a célula (2,2) deveria permanecer morta.");
    }
}
