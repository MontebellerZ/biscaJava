
import java.util.List;
import java.util.ArrayList;

/**
 * Classe de apoio para retornar os resultados de cada rodada
 */
class Resultado {

    Carta cartaGanhador;
    int indexGanhador;
    List<Carta> jogadas;

    /**
     * Cria uma instância de Resultado para retornar os atributos
     * 
     * @param cartaGanhador Carta ganhadora da rodada
     * @param indexGanhador Index do jogador na lista de jogadores da rodada
     * @param jogadas       Lista de Cartas jogadas na rodada
     */
    Resultado(Carta cartaGanhador, int indexGanhador, List<Carta> jogadas) {
        this.cartaGanhador = cartaGanhador;
        this.indexGanhador = indexGanhador;
        this.jogadas = jogadas;
    }

}

public class Bisca {

    /** Lista de jogadores (classe Player) que farão parte da partida */
    public List<Player> players;
    /**
     * Instância da classe Baralho que contém todas as cartas e faz o gerenciamento
     * das mesmas
     */
    public Baralho baralho;
    /** Lista de jogadores (classe Player) que farão parte do time 1 */
    public List<Player> time1;
    /** Lista de jogadores (classe Player) que farão parte do time 2 */
    public List<Player> time2;
    /**
     * Variável sentinela que define se o 7 do naipe do trunfo já foi jogado na
     * partida
     */
    public Boolean reli;

    /**
     * Inicializa o reli como false, prepara um novo baralho e define os jogadores
     * de cada time
     * 
     * @param p1 nome do primeiro jogador
     * @param p2 nome do segundo jogador
     */
    public Bisca(String p1, String p2) {
        this.reli = false;
        this.baralho = new Baralho();

        this.players = new ArrayList<Player>() {
        };
        this.players.add(new Player(p1));
        this.players.add(new Player(p2));

        this.time1 = new ArrayList<Player>() {
        };
        this.time1.add(this.players.get(0));

        this.time2 = new ArrayList<Player>() {
        };
        this.time2.add(this.players.get(1));
    }

    /**
     * Inicializa o reli como false, prepara um novo baralho e define os jogadores
     * de cada time
     * 
     * @param p1 nome do primeiro jogador
     * @param p2 nome do segundo jogador
     * @param p3 nome do terceiro jogador
     * @param p4 nome do quarto jogador
     */
    public Bisca(String p1, String p2, String p3, String p4) {
        this.reli = false;
        this.baralho = new Baralho();

        this.players = new ArrayList<Player>() {
        };
        this.players.add(new Player(p1));
        this.players.add(new Player(p2));
        this.players.add(new Player(p3));
        this.players.add(new Player(p4));

        this.time1 = new ArrayList<Player>() {
        };
        this.time1.add(this.players.get(0));
        this.time1.add(this.players.get(2));

        this.time2 = new ArrayList<Player>() {
        };
        this.time2.add(this.players.get(1));
        this.time2.add(this.players.get(3));
    }

    /**
     * Para cada posição da lista de jogadores: Remove uma carta do topo do baralho
     * e adiciona ela à mão do jogador.
     */
    void darCartas() {
        int tam = this.players.size();

        for (int i = 0; i < tam; i++) {
            Carta cartaPuxada = this.baralho.pegarCarta();
            this.players.get(i).receberCarta(cartaPuxada);
        }
    }

    /**
     * Reordena o vetor de jogadores para que o vencedor da última rodada seja o
     * primeiro da próxima
     * 
     * @param indexInicial index atual do jogador vencedor da última rodada
     */
    void reordenarPlayers(int indexInicial) {
        List<Player> novaOrdemPlayers = new ArrayList<Player>() {
        };

        for (int i = indexInicial; i < this.players.size() + indexInicial; i++) {
            int pegarPos = i % this.players.size();
            Player pegarPlayer = this.players.get(pegarPos);
            novaOrdemPlayers.add(pegarPlayer);
        }

        this.players = novaOrdemPlayers;
    }

    /**
     * Realiza a jogada de cada jogador da lista e realiza as comparações para
     * determinar a vencedora da rodada
     * 
     * @param trunfo Carta trunfo do baralho
     * @return retorna o index do jogador na lista de jogadores, a carta ganhadora e
     *         uma lista com todas as cartas jogadas
     */
    Resultado jogarCartas(Carta trunfo) {
        Carta cartaGanhador = null;
        int indexGanhador = -1;

        List<Carta> jogadas = new ArrayList<Carta>() {
        };

        int tam = this.players.size();
        for (int i = 0; i < tam; i++) {
            Player player = this.players.get(i);
            Carta jogada = player.usarCarta();

            // Tem outra carta na mão E
            // o 7 do trunfo não saiu E
            // carta jogada é o A do trunfo?
            Boolean regra1 = (player.mao.size() > 1 && !(this.reli) && jogada.carta == "A"
                    && jogada.naipe == trunfo.naipe);

            // 7 do trunfo não saiu e a carta jogada é ele mesmo?
            Boolean regra2 = (!(this.reli) && jogada.carta == "7" && jogada.naipe == trunfo.naipe);

            // A do trunfo só pode sair depois que 7 tiver saido
            if (regra1) {
                Carta devolver = jogada;
                jogada = player.usarCarta();
                player.receberCarta(devolver);
            }

            // quando o 7 sai converte a variável reli para true
            if (regra2) {
                this.reli = true;
            }

            jogadas.add(jogada);

            Boolean regra3 = cartaGanhador == null; // é a primeira carta jogada?
            Boolean regra4 = false; // são do mesmo naipe e a jogada é de ordem maior?
            Boolean regra5 = false; // são de naipes diferentes e a jogada é do naipe do trunfo?

            if (!regra3) {
                regra4 = (cartaGanhador.naipe.equals(jogada.naipe) && cartaGanhador.ordem < jogada.ordem);
                regra5 = (!(cartaGanhador.naipe.equals(jogada.naipe)) && jogada.naipe.equals(trunfo.naipe));
            }

            if (regra3 || regra4 || regra5) {
                cartaGanhador = jogada;
                indexGanhador = i;
            }
        }

        return new Resultado(cartaGanhador, indexGanhador, jogadas);
    }

    /**
     * Imprime os resultados das jogadas de cara jogador
     * 
     * @param indexGanhador index do ganhador da rodada na lista de jogadores
     * @param jogadas       lista de cartas jogadas na rodada
     */
    void printJogadas(int indexGanhador, List<Carta> jogadas) {
        int tam = this.players.size();

        for (int i = 0; i < tam; i++) {
            Boolean regraVencedor = i == indexGanhador;
            Player player = this.players.get(i);
            String cartaPlayer = (i + 1) + " - " + player.nome + ": " + jogadas.get(i).printCarta();

            String vencedorPlayer = regraVencedor ? " (vencedor)" : "";

            System.out.println(cartaPlayer + vencedorPlayer);
        }
    }

    /**
     * Contabiliza o total de pontos da rodada
     * 
     * @param jogadas lista de cartas jogadas na rodada
     * @return valor total de pontos da rodada
     */
    int pontosRodada(List<Carta> jogadas) {
        int tam = jogadas.size();
        int soma = 0;
        for (int i = 0; i < tam; i++) {
            soma += jogadas.get(i).valor;
        }
        return soma;
    }

    /**
     * Dá as 3 cartas iniciais para todos os jogadores
     */
    void iniciar() {
        for (int i = 0; i < 3; i++) {
            this.darCartas();
        }
    }

    /**
     * Realiza uma rodada de bisca, computa os resultados, atribui as pontuações e
     * reordena a lista de jogadores para que o vencedor da rodada seja o primeiro a
     * jogar na próxima
     * 
     * @param rod numéro referente à rodada atual do jogo
     */
    void rodada(int rod) {
        Carta trunfo = this.baralho.trunfo;

        System.out.println("\nRodada " + rod + " (trunfo: " + trunfo.printCarta() + "):");

        Resultado resultados = this.jogarCartas(trunfo);

        this.printJogadas(resultados.indexGanhador, resultados.jogadas);

        int pontosRodada = this.pontosRodada(resultados.jogadas);

        this.players.get(resultados.indexGanhador).adicionarPontos(pontosRodada);

        this.reordenarPlayers(resultados.indexGanhador);
    }

    /**
     * Soma as pontuações totais de todos os jogadores de um time
     * 
     * @param time Time a ser somado a pontuação total
     * @return Valor total da soma das pontuações do time
     */
    int somarTime(List<Player> time) {
        int tam = time.size();
        int soma = 0;
        for (int i = 0; i < tam; i++) {
            soma += time.get(i).pontos;
        }
        return soma;
    }

    /**
     * Computa as pontuações de cada time e printa os resultados
     */
    void finalizar() {

        int pontosTime1 = somarTime(this.time1);
        int pontosTime2 = somarTime(this.time2);

        if (pontosTime1 == pontosTime2) {
            System.out.println("\n\nEmpate! " + pontosTime1 + " x " + pontosTime2);
            return;
        }

        List<Player> ganhadores = pontosTime1 > pontosTime2 ? this.time1 : this.time2;

        List<String> nomesGanhadoresArray = new ArrayList<String>() {
        };
        int tam = ganhadores.size();

        for (int i = 0; i < tam; i++) {
            nomesGanhadoresArray.add(ganhadores.get(i).nome);
        }

        String nomesGanhadores = String.join(" e ", nomesGanhadoresArray);

        System.out.println("\n\nVitoria de " + nomesGanhadores + "! " + pontosTime1 + " x " + pontosTime2);
    }

    /**
     * Inicia o jogo, gera novas rodadas enquanto houverem cartas no baralho e nas
     * mãos dos jogadores, depois finaliza o jogo
     */
    void jogar() {
        this.iniciar();

        int rodadas = 0;
        while (this.baralho.cartasRestantes() > 0) {
            rodadas++;

            this.rodada(rodadas);
            this.darCartas();
        }

        for (int i = 0; i < 3; i++) {
            rodadas++;

            this.rodada(rodadas);
        }

        this.finalizar();
    }

}