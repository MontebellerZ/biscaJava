import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Baralho {

    /** Lista que armazena todas as cartas do baralho */
    public List<Carta> baralho;
    /** Variável que salva a referência para o trunfo do baralho */
    public Carta trunfo;

    /**
     * Gera uma instância do baralho com todas as cartas embaralhadas e pega o
     * trunfo (última carta do baralho)
     */
    public Baralho() {
        this.baralho = new ArrayList<Carta>() {
        };

        this.novoBaralho();
        this.embaralhar();
        this.pegarTrunfo();
    }

    /**
     * Cria um novo baralho ordenado e atribui ao atributo baralho
     */
    public void novoBaralho() {
        /** Lista com todas as cartas em ordem de força */
        List<String> cartasLabel = new ArrayList<String>() {
        };
        cartasLabel.add("2");
        cartasLabel.add("3");
        cartasLabel.add("4");
        cartasLabel.add("5");
        cartasLabel.add("6");
        cartasLabel.add("Q");
        cartasLabel.add("J");
        cartasLabel.add("K");
        cartasLabel.add("7");
        cartasLabel.add("A");

        /**
         * Lista com os valores de pontuação de cada carta (na mesma ordem da lista de
         * cartasLabel)
         */
        List<Integer> valores = new ArrayList<Integer>() {
        };
        valores.add(0);
        valores.add(0);
        valores.add(0);
        valores.add(0);
        valores.add(0);
        valores.add(2);
        valores.add(3);
        valores.add(4);
        valores.add(10);
        valores.add(11);

        /** Lista com todos os naipes disponíveis */
        List<String> naipes = new ArrayList<String>() {
        };
        naipes.add("copas");
        naipes.add("espadas");
        naipes.add("ouros");
        naipes.add("paus");

        // para cada naipe
        for (int j = 0; j < 4; j++) {
            // para cada carta
            for (int i = 0; i < 10; i++) {
                /**
                 * Cria uma instância de Carta combinando um naipe, uma cartaLabel, seu
                 * respectivo valor e sua posição no vetor (essa que definirá se uma carta é
                 * mais forte que a outra -> se a carta X vem depois da Y no vetor, a Y é mais
                 * forte). Por fim, adiciona essa instância no baralho
                 */
                this.baralho.add(new Carta(cartasLabel.get(i), naipes.get(j), valores.get(i), i));
            }
        }
    }

    /**
     * Realiza o embaralhamento da lista de cartas
     */
    public void embaralhar() {
        Collections.shuffle(this.baralho);
    }

    /**
     * Pega a última carta do baralho e a define como trunfo do baralho
     */
    public void pegarTrunfo() {
        this.trunfo = this.baralho.get(this.baralho.size() - 1);
    }

    /**
     * Remove a carta do topo do baralho e a retorna
     * 
     * @return Carta do topo do baralho
     */
    public Carta pegarCarta() {
        return this.baralho.remove(0);
    }

    /**
     * Retorna o total de cartas que ainda restam no baralho
     * 
     * @return Total de cartas restantes no baralho
     */
    public int cartasRestantes() {
        return this.baralho.size();
    }

}