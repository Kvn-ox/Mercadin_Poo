package service;

import model.Cliente;
import model.Comida;
import model.Produto;
import model.Utensilio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;



public class Mercado {

    private String nome;
    private String endereco;
    private double lucro = 0;

    private ArrayList<Produto> estoque = new ArrayList<>();
    private ArrayList<Comida> comidas = new ArrayList<>();
    private ArrayList<Utensilio> utensilios = new ArrayList<>();

    public ArrayList<Comida> getComidas() { return comidas; }
    public ArrayList<Utensilio> getUtensilios() { return utensilios; }

    public Mercado(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;

        utensilios.add(new Utensilio(10, "Martelo", 35.90, "Tramontina", "Ferramenta"));
        utensilios.add(new Utensilio(5, "Parafusadeira", 199.90, "Bosch", "Elétrica"));
        utensilios.add(new Utensilio(15, "Fita Métrica", 12.50, "Vonder", "Medida"));
        utensilios.add(new Utensilio(8, "Chave de Fenda", 9.90, "Tramontina", "Ferramenta"));

    }

    public ArrayList<Produto> getEstoque() {
        return estoque;
    }

    public void registrarVenda(Cliente c) {
        double soma = 0;

        //StringBuilder para montar o texto da venda linha por linha
        StringBuilder conteudoVenda = new StringBuilder();

        //Cabeçalho com o nome do cliente
        conteudoVenda.append("model.Cliente: ").append(c.getNome()).append("\n");
        conteudoVenda.append("Itens:\n");

        //Percorre o carrinho somando e registrando os itens
        for (Produto p : c.getCarrinho()) {
            soma += p.getPreco();

            // Adiciona linha: " - Nome do model.Produto: R$ 99.99"
            conteudoVenda.append(" - ")
                    .append(p.getNome())
                    .append(": R$ ")
                    .append(p.getPreco())
                    .append("\n");
        }

        lucro += soma;

        //Adiciona o total final e uma linha separadora para a próxima venda
        conteudoVenda.append("Total da Venda: R$ ").append(soma).append("\n");
        conteudoVenda.append("--------------------------------------------------\n");

        try {
            //Escreve o bloco de texto inteiro no arquivo
            Files.writeString(Path.of("src/files/vendas.txt"),
                    conteudoVenda.toString(),
                    java.nio.file.StandardOpenOption.CREATE,
                    java.nio.file.StandardOpenOption.APPEND
            );

        } catch (IOException e) {
            System.out.println("Erro ao registrar venda: " + e.getMessage());
        }
    }

    public void copiarProdutos() {
        estoque.addAll(comidas);
        estoque.addAll(utensilios);
    }


    public void lerEstoque(Path arquivo) {
        try {
            List<String> linhas = Files.readAllLines(arquivo);

            for (String linha : linhas) {
                String[] partes = linha.split("-");
                String nome = partes[0].trim();
                String validade = partes[1].trim();

                comidas.add(new Comida(5.0, 20, 1.0, nome, validade, "alimento"));
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }
    }

}
