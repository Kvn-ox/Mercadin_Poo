package model;

public class Produto {
    private int quantidade;
    private String nome;
    private double preco;
    private String tipo;

    public Produto(int quantidade, String nome, double preco, String tipo) {
        this.quantidade = quantidade;
        this.nome = nome;
        this.preco = preco;
        this.tipo = tipo;
    }

    public int getQuantidade() { return quantidade; }
    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    public String getTipo() { return tipo; }

    public void mostrar() {
        System.out.println(nome + " | R$ " + preco + " | Estoque: " + quantidade);
    }
}
