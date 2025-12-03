package model;

import interfacee.Descartavel;
import exception.ValidadeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Comida extends Produto implements Descartavel {

    private double peso;
    private String validade;

    public Comida(double preco, int quantidade, double peso, String nome, String validade, String tipo) {
        super(quantidade, nome, preco, tipo);
        this.peso = peso;
        this.validade = validade;
    }

    public boolean validadeOk(String hoje) throws ValidadeException {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate dataHoje = LocalDate.parse(hoje, formato);
        LocalDate dataValidade = LocalDate.parse(validade, formato);

        if (dataHoje.isAfter(dataValidade)) {
            throw new ValidadeException("model.Produto vencido: " + getNome());
        }
        return true;
    }
    @Override
    public void mostrar() {
        System.out.println(getNome() + " | R$ " + getPreco() + " | Val: " + validade);
    }
}
