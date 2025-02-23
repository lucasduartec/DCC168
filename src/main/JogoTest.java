package main;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class JogoTest {
	
    private Jogo jogo;
    private int[][] board;
    
    @BeforeEach
    public void setUp() {
        this.jogo = new Jogo();
        this.board = new int[6][6]; // Arrays de int são automaticamente inicializados com 0
    }
    
    // CT01 <1,1>
    @Test
    public void dadoCelulaVivaCom1VizinhoVivoQuandoProximaGeracaoEntaoCelulaMorre() {
        board[2][2] = 1;
        
        //somente 1 vizinho vivo
        board[2][3] = 1;
        
        jogo.setTabuleiro(board);
        jogo.proximaGeracao();
        int[][] novoBoard = jogo.getTabuleiro();
        
        assertEquals(0, novoBoard[2][2], "CT01 falhou: a célula (2,2) deveria morrer.");
    }
    
    // CT02 - <1, 0>
    @Test
    public void dadoCelulaVivaCom0VizinhosVivosQuandoProximaGeracaoEntaoCelulaMorre() {
        board[2][2] = 1; 
        
        // Nenhum vizinho é configurado como vivo
        
        jogo.setTabuleiro(board);
        jogo.proximaGeracao();
        int[][] novoBoard = jogo.getTabuleiro();
        
        assertEquals(0, novoBoard[2][2], "CT02 falhou: a célula (2,2) deveria morrer.");
    }
    
    // CT03 - <1, 4>
    @Test
    public void dadoCelulaVivaCom4VizinhosVivosQuandoProximaGeracaoEntaoCelulaMorre() {
        board[2][2] = 1;
        
        // Configura 4 vizinhos vivos
        board[1][1] = 1;
        board[1][2] = 1;
        board[1][3] = 1;
        board[2][1] = 1;
        
        jogo.setTabuleiro(board);
        jogo.proximaGeracao();
        int[][] novoBoard = jogo.getTabuleiro();
        
        assertEquals(0, novoBoard[2][2], "CT03 falhou: a célula (2,2) deveria morrer.");
    }
    
    // CT04 - <1, 2>
    @Test
    public void dadoCelulaVivaCom2VizinhosVivosQuandoProximaGeracaoEntaoCelulaPermaneceViva() {
        board[2][2] = 1;
        
        // Configura 2 vizinhos vivos
        board[1][1] = 1;
        board[1][2] = 1;
        
        jogo.setTabuleiro(board);
        jogo.proximaGeracao();
        int[][] novoBoard = jogo.getTabuleiro();
        
        assertEquals(1, novoBoard[2][2], "CT04 falhou: a célula (2,2) deveria permanecer viva.");
    }
    
    // CT05 - <1, 3>
    @Test
    public void dadoCelulaVivaCom3VizinhosVivosQuandoProximaGeracaoEntaoCelulaPermaneceViva() {
        board[2][2] = 1;
        // Configura 3 vizinhos vivos
        board[1][1] = 1;
        board[1][2] = 1;
        board[2][1] = 1;
        
        jogo.setTabuleiro(board);
        jogo.proximaGeracao();
        int[][] novoBoard = jogo.getTabuleiro();
        
        assertEquals(1, novoBoard[2][2], "CT05 falhou: a célula (2,2) deveria permanecer viva.");
    }
    
    // CT06 - <0, 3>
    @Test
    public void dadaCelulaMortaCom3VizinhosVivosQuandoProximaGeracaoEntaoCelulaTornaSeViva() {
        // Configura 3 vizinhos vivos ao redor de (2,2)
        board[1][1] = 1;
        board[1][2] = 1;
        board[1][3] = 1;
        
        jogo.setTabuleiro(board);
        jogo.proximaGeracao();
        int[][] novoBoard = jogo.getTabuleiro();
        
        assertEquals(1, novoBoard[2][2], "CT06 falhou: a célula (2,2) deveria se tornar viva.");
    }

    // CT07 - <0, 2>
    @Test
    public void dadaCelulaMortaCom2VizinhosVivosQuandoProximaGeracaoEntaoCelulaPermaneceMorta() {
        // Configura 2 vizinhos vivos ao redor de (2,2)
        board[1][1] = 1;
        board[1][2] = 1;
        
        jogo.setTabuleiro(board);
        jogo.proximaGeracao();
        int[][] novoBoard = jogo.getTabuleiro();
        
        assertEquals(0, novoBoard[2][2], "CT07 falhou: a célula (2,2) deveria permanecer morta.");
    }
    
    // CT08 - < -1, 0>
    @Test
    public void dadoEstadoInvalidoNegativoParaCelulaQuandoProximaGeracaoEntaoErro() {
        board[2][2] = -1;
        
        assertThrows(IllegalArgumentException.class, () -> {
            jogo.setTabuleiro(board);
        }, "CT08 falhou: esperava erro para estado inválido da célula (-1).");
    }
    
    // CT09 - <2, 0>
    @Test
    public void dadoEstadoInvalidoPositivoParaCelulaQuandoProximaGeracaoEntaoErro() {
        board[2][2] = 2;
        
        assertThrows(IllegalArgumentException.class, () -> {
            jogo.setTabuleiro(board);
        }, "CT09 falhou: esperava erro para estado inválido da célula (2).");
    }

    // CT10 - <0, 7>
    @Test
    public void dadaCelulaMortaCom7VizinhosVivosQuandoProximaGeracaoEntaoCelulaPermaneceMorta() {
        // Configura 7 vizinhos vivos ao redor de (2,2) (ex.: todas, exceto (3,3))
        board[1][1] = 1;
        board[1][2] = 1;
        board[1][3] = 1;
        board[2][1] = 1;
        board[2][3] = 1;
        board[3][1] = 1;
        board[3][2] = 1;
        
        jogo.setTabuleiro(board);
        jogo.proximaGeracao();
        int[][] novoBoard = jogo.getTabuleiro();
        
        assertEquals(0, novoBoard[2][2], "CT10 falhou: a célula (2,2) deveria permanecer morta.");
    }
    
    // CT11 - <0, 8>
    @Test
    public void dadaCelulaMortaCom8VizinhosVivosQuandoProximaGeracaoEntaoCelulaPermaneceMorta() {
        // Configura todos os 8 vizinhos de (2,2) como vivos
        board[1][1] = 1;
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
        
        assertEquals(0, novoBoard[2][2], "CT11 falhou: a célula (2,2) deveria permanecer morta.");
    }
}
