
public class Carta {

    /** nome da carta */
    String carta;
    /** naipe da carta */
    String naipe;
    /** valor de pontuação da carta */
    int valor;
    /** ordem de força da carta */
    int ordem;

    /**
     * Cria uma instância da carta
     * 
     * @param carta nome da carta
     * @param naipe naipe da carta
     * @param valor valor de pontuação da carta
     * @param ordem ordem de força da carta
     */
    Carta(String carta, String naipe, int valor, int ordem) {
        this.carta = carta;
        this.naipe = naipe;
        this.valor = valor;
        this.ordem = ordem;
    }

    /**
     * Converte a carta em uma string de identificação legível para humanos
     * 
     * @return nome de identificação humana da carta
     */
    String printCarta() {
        return this.carta + " de " + this.naipe;
    }

}