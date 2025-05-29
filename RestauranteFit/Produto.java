/* Documentação
 * 
 * Esse arquivo é competente à classe Produto, assim como seus métodos declarados no documento do projeto.
 * Versão com persistência e serialização por ChatGPT e PedroH em 29/05/2025.
 * 
 * Referências:
 * https://www.w3schools.com/java/
 * https://docs.oracle.com/javase/tutorial/index.html
 * https://www.geeksforgeeks.org/java/
 */
package RestauranteFit;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Produto implements Serializable {
    private static final long serialVersionUID = 1L; // Versão de serialização
    private static final String FILE_NAME = "produtos.ser"; // Arquivo de armazenamento

    public static ArrayList<Produto> listaProdutos = initializeListaProdutos(); // Lista persistida
    private transient Scanner scn; // transient evita serializar Scanner

    public String id, nomeproduto;
    public double valorproduto;
    public int estoque;

    // Construtor padrão
    public Produto() {
        this.scn = new Scanner(System.in); // inicia o scan
    }

    // Construtor com parâmetros
    public Produto(String id, String nomeproduto, double valorproduto, int estoque) {
        this.id = id;
        this.nomeproduto = nomeproduto;
        this.valorproduto = valorproduto;
        this.estoque = estoque;
    }

    // Inicializa lista de produtos (de arquivo ou padrão)
    private static ArrayList<Produto> initializeListaProdutos() {
        ArrayList<Produto> produtosCarregados = carregarProdutos();
        if (produtosCarregados != null) {
            return produtosCarregados;
        }

        ArrayList<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto("PROD-1", "Pizza", 25.90, 1));
        produtos.add(new Produto("PROD-2", "Coxinha", 7.50, 15));
        produtos.add(new Produto("PROD-3", "Pastel", 10.00, 18));
        produtos.add(new Produto("PROD-4", "Espaguete", 23.90, 12));
        produtos.add(new Produto("PROD-5", "Coca", 6.00, 10));
        salvarProdutos(produtos); // Salva a lista padrão ao inicializar
        return produtos;
    }

    // Salvar a lista no arquivo
    public static void salvarProdutos(ArrayList<Produto> produtos) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(produtos);
            System.out.println("Lista de produtos salva com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar os produtos: " + e.getMessage());
        }
    }

    // Método atalho para salvar lista atual
    public static void salvarProdutos() {
        salvarProdutos(listaProdutos);
    }

    // Carregar lista do arquivo
    @SuppressWarnings("unchecked")
    public static ArrayList<Produto> carregarProdutos() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (ArrayList<Produto>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Arquivo não encontrado ou erro ao carregar. Usando lista padrão.");
            return null;
        }
    }

    // Cadastrar novo produto
    public void cadastrarProduto() {
        if (scn == null) scn = new Scanner(System.in); // Garante inicialização do Scanner

        System.out.print("\nDigite o nome do produto: ");
        String nome = scn.nextLine();

        System.out.print("Digite o valor do produto: ");
        double valor = scn.nextDouble();

        System.out.print("Digite a quantidade em estoque: ");
        int quantidade = scn.nextInt();
        scn.nextLine(); // Limpa o buffer do scanner

        id = "PROD-" + (listaProdutos.size() + 1);
        Produto produto = new Produto(id, nome, valor, quantidade);
        listaProdutos.add(produto);
        salvarProdutos(); // Salva após cadastrar

        System.out.println("Produto cadastrado com sucesso! ID: " + id);
        System.out.println("Deseja cadastrar outro produto?\n1 - Sim\n2 - Não");
        int opcao = scn.nextInt();
        while (opcao != 1 && opcao != 2) {
            System.out.println("Opção inválida. 1 - Sim | 2 - Não");
            opcao = scn.nextInt();
        }
        scn.nextLine(); // Limpa o buffer

        if (opcao == 1) {
            cadastrarProduto();
        }
    }

    // Listar todos os produtos
    public void listarProdutos() {
        if (listaProdutos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        listaProdutos.trimToSize();
        System.out.println("\n--- Lista de Produtos ---");
        listaProdutos.forEach(produto -> {
            System.out.println("ID: " + produto.id);
            System.out.println("Nome: " + produto.nomeproduto);
            System.out.println("Valor: R$ " + produto.valorproduto);
            System.out.println("Estoque: " + produto.estoque);
            System.out.println("-------------------------");
        });
    }

    // Atualizar um produto existente
    public void atualizarProduto() {
        if (scn == null) scn = new Scanner(System.in);

        System.out.println("Atualização de produto:" + "\nInforme o nome do produto: ");
        String nomeprodutoNovo = scn.nextLine();
        if (nomeproduto.equals(nomeprodutoNovo)) {
            System.out.println("\nCampo não pode ser o mesmo que antes!");
            return;
        } else {
            nomeproduto = nomeprodutoNovo;
        }

        System.out.println("\nInforme o valor do produto: ");
        double valorprodutoNovo = scn.nextDouble();
        if (valorproduto == valorprodutoNovo) {
            System.out.println("\nCampo não pode ser o mesmo que antes!");
            return;
        } else {
            valorproduto = valorprodutoNovo;
        }

        System.out.println("\nInforme a quantidade em estoque: ");
        int estoqueprodutoNovo = scn.nextInt();
        if (estoque == estoqueprodutoNovo) {
            System.out.println("\nCampo não pode ser o mesmo que antes!");
            return;
        } else {
            estoque = estoqueprodutoNovo;
        }

        System.out.println("Nome: " + nomeproduto + "\nValor: R$ " + valorproduto + "\nEstoque: " + estoque);
        salvarProdutos(); // Salva após atualizar
    }

    // Verifica o estoque de um produto por ID
    public void verificarEstoque() {
        if (scn == null) scn = new Scanner(System.in);

        System.out.println("Insira 'PROD-' + o ID do produto para checar no estoque:");
        String idProduto = scn.nextLine().toUpperCase();

        for (Produto produto : listaProdutos) {
            if (produto.id.equals(idProduto)) {
                if (produto.estoque > 0) {
                    System.out.println("\nProduto:\n" + produto.id + " " + produto.nomeproduto +
                            "\nStatus: DISPONÍVEL\nEstoque: " + produto.estoque);
                } else {
                    System.out.println("\nProduto:\n" + produto.id + " " + produto.nomeproduto +
                            "\nStatus: INDISPONÍVEL :(");
                }
                return;
            }
        }
        System.out.println("Produto não encontrado.");
    }

    // Fecha o Scanner (boa prática)
    public void closeScanner() {
        if (scn != null) {
            scn.close();
        }
    }
}
