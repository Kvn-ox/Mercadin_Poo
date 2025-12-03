package app;

import exception.ValidadeException;
import model.Cliente;
import model.Comida;
import model.Produto;
import model.Utensilio;
import service.Mercado;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // CLIENTE
        Cliente c1 = new Cliente("Kevin", "13276703726");
        String hoje = "20/02/2026";

        // MERCADO
        Mercado mercado = new Mercado("Mercadinn", "Rua XPTO, 200");

        // LENDO O ARQUIVO TXT (estoque)
        mercado.lerEstoque(Paths.get("src/files/Listadevalidade.txt"));
        ArrayList<Produto> produtos = mercado.getEstoque();
        mercado.copiarProdutos();

        int op = -1;

        while (op != 0) {
            System.out.println("\n=== MERCADIN ===");
            System.out.println("1 - Listar produtos");
            System.out.println("2 - Comprar");
            System.out.println("3 - Finalizar compra");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            try {
                op = sc.nextInt();
                sc.nextLine();
            }
            catch(InputMismatchException e)
            {
                e.printStackTrace();
                sc.nextLine();
            }

            if (op == 1) {
                for (int i = 0; i < produtos.size(); i++) {
                    System.out.print(i + " - ");
                    produtos.get(i).mostrar();
                }
            }

            else if (op == 2) {

                System.out.println("\nO que deseja comprar?");
                System.out.println("1 - Comidas");
                System.out.println("2 - Utensílios");
                System.out.print("Escolha: ");
                int tipo = sc.nextInt();

                if (tipo == 1) {
                    System.out.println("\n--- COMIDAS DISPONÍVEIS ---");
                    int i = 1;
                    for (Comida c : mercado.getComidas()) {
                        System.out.print(i + " - ");
                        c.mostrar();
                        i++;
                    }

                    System.out.print("\nEscolha o número da comida: ");
                    int escolha = sc.nextInt();

                    try {
                        Comida escolhida = mercado.getComidas().get(escolha - 1);
                        escolhida.validadeOk(hoje);
                        c1.adicionarProduto(escolhida);
                        System.out.println("Adicionado!");

                    } catch (ValidadeException e) {
                        System.out.println("ERRO: " + e.getMessage());
                    }

                } else if (tipo == 2) {
                    System.out.println("\n--- UTENSÍLIOS DISPONÍVEIS ---");
                    int i = 1;
                    for (Utensilio u : mercado.getUtensilios()) {
                        System.out.print(i + " - ");
                        u.mostrar();
                        i++;
                    }

                    System.out.print("\nEscolha o número do utensílio: ");
                    int escolha = sc.nextInt();

                    Utensilio escolhido = mercado.getUtensilios().get(escolha - 1);
                    c1.adicionarProduto(escolhido);

                    System.out.println("Adicionado!");
                }
            }

            else if (op == 3) {
                Thread proc = new Thread(() -> {
                    System.out.print("\nProcessando pagamento");
                    try {
                        for (int i = 0; i < 3; i++) {
                            Thread.sleep(700);
                            System.out.print(".");
                        }

                        System.out.println("\nPagamento Aprovado!");
                        c1.mostrarCompras();

                        mercado.registrarVenda(c1);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });

                proc.start();

                try {
                    proc.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
