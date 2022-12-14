
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Player {

    /** Nome do jogador */
    String nome;
    /** Lista de cartas em posse do jogador */
    List<Carta> mao;
    /** Pontos marcados pelo jogador */
    int pontos;

    /**
     * Cria uma instância de Player com o nome do jogador, a mão vazia e 0 pontos
     * 
     * @param nome nome do jogador
     */
    Player(String nome) {
        this.nome = nome;
        this.mao = new ArrayList<Carta>() {
        };
        this.pontos = 0;
    }

    /**
     * Adiciona uma carta recebida por parâmetro à mão do jogador
     * @param carta Carta a ser adicionada a mão do jogador
     */
    void receberCarta(Carta carta) {
        this.mao.add(carta);
    }

    /**
     * Remove uma carta aleatória da mão do jogador e a retorna
     * @return Carta do jogador
     */
    Carta usarCarta() {
        Random rnd = new Random();
        int rnd1 = rnd.nextInt(this.mao.size());
        return this.mao.remove(rnd1);
    }

    /**
     * Adiciona uma quantidade de pontos ao jogador
     * @param pontos Pontos a serem adicionados ao jogador
     */
    void adicionarPontos(int pontos) {
        this.pontos += pontos;
    }

    /**
     * Retorna o valor total de pontos do jogador
     * @return Total de pontos do jogador
     */
    int consultarPontos() {
        return this.pontos;
    }

}