package RestauranteFit;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Produto implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String FILE_NAME = "produtos.ser";

    public static ArrayList<Produto> listaProdutos = initializeListaProdutos();
    private transient Scanner scn;

    public String id;
    public String nomeproduto;
    public double valorproduto;
    public int estoque;

    public Produto() {
    }

    public Produto(String id, String nomeproduto, double valorproduto, int estoque) {
        this.id = id;
        this.nomeproduto = nomeproduto;
        this.valorproduto = valorproduto;
        this.estoque = estoque;
    }

    private void ensureScannerInitialized() {
        if (this.scn == null) {
            this.scn = new Scanner(System.in);
        }
    }
    
    private static ArrayList<Produto> initializeListaProdutos() {
        ArrayList<Produto> produtosCarregados = carregarProdutosDoArquivo();
        if (produtosCarregados != null && !produtosCarregados.isEmpty()) {
            return produtosCarregados;
        }

        ArrayList<Produto> produtosPadrao = new ArrayList<>();
        produtosPadrao.add(new Produto("PROD-1", "Salada de Frutas", 7.00, 10));
        produtosPadrao.add(new Produto("PROD-2", "Iogurte", 9.50, 15));
        produtosPadrao.add(new Produto("PROD-3", "Salmão Assado", 25.00, 18));
        produtosPadrao.add(new Produto("PROD-4", "Sopa de Legumes", 16.90, 12));
        produtosPadrao.add(new Produto("PROD-5", "Água Mineral", 4.00, 20));
        salvarProdutosNoArquivo(produtosPadrao);
        return produtosPadrao;
    }

    public static void salvarProdutosNoArquivo(ArrayList<Produto> produtos) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(produtos);
        } catch (IOException e) {
            System.err.println("Erro ao salvar os produtos: " + e.getMessage());
        }
    }

    public static void salvarProdutosAtuais() {
        salvarProdutosNoArquivo(listaProdutos);
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Produto> carregarProdutosDoArquivo() {
        File arquivoProdutos = new File(FILE_NAME);
        if (!arquivoProdutos.exists()) {
            System.out.println("Arquivo " + FILE_NAME + " não encontrado. Uma lista padrão será criada se necessário.");
            return null;
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (ArrayList<Produto>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar produtos do arquivo " + FILE_NAME + ": " + e.getMessage() + ". Usando lista padrão se aplicável.");
            return null;
        }
    }

    public void cadastrarProduto() {
        ensureScannerInitialized();
        System.out.println("\n--- Cadastro de Novo Produto ---");
        System.out.print("Digite o nome do produto: ");
        String nome = scn.nextLine();

        double valor = 0;
        while (true) {
            System.out.print("Digite o valor do produto: R$ ");
            try {
                valor = scn.nextDouble();
                if (valor < 0) {
                    System.out.println("Valor não pode ser negativo.");
                    continue;
                }
                scn.nextLine(); 
                break;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida para valor. Use números (ex: 10.50).");
                scn.nextLine(); 
            }
        }
        
        int quantidade = 0;
        while (true) {
            System.out.print("Digite a quantidade em estoque: ");
            try {
                quantidade = scn.nextInt();
                 if (quantidade < 0) {
                    System.out.println("Estoque não pode ser negativo.");
                    continue;
                }
                scn.nextLine(); 
                break;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida para quantidade. Use números inteiros.");
                scn.nextLine(); 
            }
        }

        String novoId = "PROD-" + (listaProdutos.size() + 1); 
        Produto novoProduto = new Produto(novoId, nome, valor, quantidade);
        listaProdutos.add(novoProduto);
        salvarProdutosAtuais();

        System.out.println("Produto '" + nome + "' cadastrado com sucesso! ID: " + novoId);
    }

    public void listarProdutos() {
        if (listaProdutos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        System.out.println("\n--- Lista de Produtos ---");
        for (Produto produto : listaProdutos) {
            System.out.println("ID: " + produto.id + ", Nome: " + produto.nomeproduto +
                               ", Valor: R$ " + String.format("%.2f", produto.valorproduto) +
                               ", Estoque: " + produto.estoque);
            System.out.println("-------------------------");
        }
    }

    public void atualizarProduto() {
        ensureScannerInitialized();
        listarProdutos();
        if (listaProdutos.isEmpty()) return;

        System.out.print("Digite o ID do produto que deseja atualizar (ex: PROD-1): ");
        String idProdutoAtualizar = scn.nextLine().toUpperCase();
        Produto produtoParaAtualizar = null;
        int indiceProduto = -1;

        for (int i = 0; i < listaProdutos.size(); i++) {
            if (listaProdutos.get(i).id.equals(idProdutoAtualizar)) {
                produtoParaAtualizar = listaProdutos.get(i);
                indiceProduto = i;
                break;
            }
        }

        if (produtoParaAtualizar == null) {
            System.out.println("Produto com ID '" + idProdutoAtualizar + "' não encontrado.");
            return;
        }

        System.out.println("Atualizando produto: " + produtoParaAtualizar.nomeproduto);

        System.out.print("Novo nome (atual: " + produtoParaAtualizar.nomeproduto + ", deixe em branco para não alterar): ");
        String nomeNovo = scn.nextLine();
        if (!nomeNovo.isEmpty()) {
            produtoParaAtualizar.nomeproduto = nomeNovo;
        }

        System.out.print("Novo valor (atual: " + produtoParaAtualizar.valorproduto + ", deixe em branco ou digite valor < 0 para não alterar): R$ ");
        String valorTexto = scn.nextLine();
        if (!valorTexto.isEmpty()) {
            try {
                double valorNovo = Double.parseDouble(valorTexto.replace(",", "."));
                if (valorNovo >= 0) {
                    produtoParaAtualizar.valorproduto = valorNovo;
                } else {
                    System.out.println("Valor inválido, mantendo o anterior.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Formato de valor inválido, mantendo o anterior.");
            }
        }

        System.out.print("Nova quantidade em estoque (atual: " + produtoParaAtualizar.estoque + ", deixe em branco ou digite valor < 0 para não alterar): ");
        String estoqueTexto = scn.nextLine();
         if (!estoqueTexto.isEmpty()) {
            try {
                int estoqueNovo = Integer.parseInt(estoqueTexto);
                if (estoqueNovo >= 0) {
                    produtoParaAtualizar.estoque = estoqueNovo;
                } else {
                     System.out.println("Estoque inválido, mantendo o anterior.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Formato de estoque inválido, mantendo o anterior.");
            }
        }
        
        listaProdutos.set(indiceProduto, produtoParaAtualizar);
        salvarProdutosAtuais();
        System.out.println("Produto atualizado: " + produtoParaAtualizar.nomeproduto +
                           ", Valor: R$ " + String.format("%.2f", produtoParaAtualizar.valorproduto) +
                           ", Estoque: " + produtoParaAtualizar.estoque);
    }

    public void verificarEstoque() {
        ensureScannerInitialized();
        System.out.print("Insira o ID do produto para checar no estoque (ex: PROD-1): ");
        String idProduto = scn.nextLine().toUpperCase();
        Produto encontrado = null;
        for (Produto produto : listaProdutos) {
            if (produto.id.equals(idProduto)) {
                encontrado = produto;
                break;
            }
        }
        if (encontrado != null) {
            System.out.println("\nProduto: " + encontrado.id + " - " + encontrado.nomeproduto);
            System.out.println("Estoque disponível: " + encontrado.estoque);
            System.out.println("Status: " + (encontrado.estoque > 0 ? "DISPONÍVEL" : "INDISPONÍVEL"));
        } else {
            System.out.println("Produto com ID '" + idProduto + "' não encontrado.");
        }
    }
}