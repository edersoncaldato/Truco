package src;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class Jogador {

    public List<Carta> cartas;
    public String nome;

    public Jogador(String nome) {
        this.cartas = new ArrayList<>();
        this.nome = nome;
    }

    public Carta jogada(Carta cartaNaMesa) {
        return jogar(cartaNaMesa);
    }

    private Carta jogar(Carta cartaNaMesa) {
        if (cartas.size() == 3) {
            Optional<Carta> optionalCartaAlta = cartas.stream()
                    .max(Comparator.comparingInt(c -> c.valor));
            if (cartaNaMesa == null) {
                Carta cartaAlta = optionalCartaAlta.get();
                if (cartaAlta.valor >= 130 && cartaAlta.valor <= 130000) {
                    return cartaAlta;
                }
            }
            return cartas.stream()
                    .max(Comparator.comparingInt(c -> c.numero))
                    .orElse(null);

        } else if (cartas.size() == 2 || cartas.size() == 1) {
            return getCarta();
        } else {
            System.err.println("Jogador ta com muita carta: " + cartas.size());
            return null;
        }
    }

    private Carta getCarta() {
        Optional<Carta> optionalCartaAlta = cartas.stream()
                .max(Comparator.comparingInt(c -> c.valor));
        if (optionalCartaAlta.isPresent()) {
            Carta cartaAlta = optionalCartaAlta.get();
            if (cartaAlta.valor >= 130 && cartaAlta.valor <= 130000) {
                return cartaAlta;
            } else {
                return cartas.stream()
                        .max(Comparator.comparingInt(c -> c.numero))
                        .orElse(null);
            }
        } else {
            System.err.println("Jogador ta sem carta pra jogar!");
            return null;
        }
    }

    public void exibeCartas() {
        cartas.forEach(c -> System.out.print(cartas + " "));
        System.out.println();
    }
}
