package model;

public class Utensilio extends Produto {

    private String marca;
    private String categoria;

    public Utensilio(int quantidade, String nome, double preco, String marca, String categoria) {
        super(quantidade, nome, preco, categoria);
        this.marca = marca;
        this.categoria = categoria;
    }

    @Override
    public void mostrar() {
        System.out.println(getNome() + " | " + marca + " | R$ " + getPreco());
    }
}
