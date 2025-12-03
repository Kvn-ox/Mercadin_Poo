package model;

import java.util.ArrayList;

public class Cliente {
    private String nome;
    private String cpf;

    private ArrayList<Produto> carrinho = new ArrayList<>();

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public void adicionarProduto(Produto p) {
        carrinho.add(p);
    }

    public ArrayList<Produto> getCarrinho() {
        return carrinho;
    }

    public void mostrarCompras() {
        System.out.println("\n--- Produtos comprados por " + nome + " ---");

        if (carrinho.isEmpty()) {
            System.out.println("Nenhum item comprado.");
            return;
        }

        double total = 0;

        for (Produto p : carrinho) {
            System.out.println("- " + p.getNome() + " | R$ " + p.getPreco());
            total += p.getPreco();
        }

        System.out.println("TOTAL = R$ " + total);
    }

    public String getNome() {
        return nome;
    }
}
