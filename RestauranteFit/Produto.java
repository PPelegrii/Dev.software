/*Documentação
 * 
 * Esse arquivo é competente a classe Produto, assim como seus métodos declarados no documento do projeto .
 * Versão inicial por PedroH no dia 19/03/25. 
 * 
 * Referências:
 * https://www.w3schools.com/java/
 * https://docs.oracle.com/javase/tutorial/index.html
 * https://www.geeksforgeeks.org/java/
 */
package RestauranteFit;
import java.util.ArrayList;
import java.util.Scanner;
    
public class Produto{
    public static ArrayList<Produto> listaProdutos = initializeListaProdutos(); // Lista de produtos
    private Scanner scn; // Scanner como variável de instância
    public String id, nomeproduto;
    public double valorproduto;
    public int estoque;
    
    // Construtor padrão
    public Produto(){
        this.scn = new Scanner(System.in); // inicia o scan
    }
    // Construtor
    public Produto(String id, String nomeproduto, double valorproduto, int estoque){
        this.id = id;
        this.nomeproduto = nomeproduto;
        this.valorproduto = valorproduto;
        this.estoque = estoque;
    }

    private static ArrayList<Produto> initializeListaProdutos(){ // inicializa a lista de produtos
        ArrayList<Produto> produtos = new ArrayList<>(); // ele aumenta aumoticamente o tamanho do array
        produtos.add(new Produto("PROD-1", "Pizza", 25.90, 1));
        produtos.add(new Produto("PROD-2", "Coxinha", 7.50, 15));
        produtos.add(new Produto("PROD-3", "Pastel", 10.00, 18));
        produtos.add(new Produto("PROD-4", "Espaguete", 23.90, 12));
        produtos.add(new Produto("PROD-5", "Coca", 6.00, 10));
        return produtos;
    }
        
    public void cadastrarProduto(){
        System.out.print("\nDigite o nome do produto: ");
        String nome = scn.nextLine();

        System.out.print("Digite o valor do produto: ");
        double valor = scn.nextDouble();

        System.out.print("Digite a quantidade em estoque: ");
        int quantidade = scn.nextInt();
        scn.nextLine(); // Limpa o buffer do scanner

        id = "PROD-" + (listaProdutos.size() + 1); // Gera um ID único para o produto
        Produto produto = new Produto(id, nome, valor, quantidade);
        listaProdutos.add(produto); // Adiciona o produto à lista

        System.out.println("Produto cadastrado com sucesso! ID: " + id);
        System.out.println("Deseja cadastrar outro produto?\n1 - Sim\n2 - Não");
        int opcao = scn.nextInt();
        while(opcao != 1 && opcao != 2){
            System.out.println("Opção inválida. 1 - Sim | 2 - Não");
            opcao = scn.nextInt();
            scn.nextLine(); // Limpa o buffer do Scanner
        }
        if (opcao == 1){
            cadastrarProduto();
        }
    }
    // Método para listar todos os produtos
    public void listarProdutos(){
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
    // Método para atualizar um produto
    public void atualizarProduto(){
        System.out.println("Atualização de produto:" + "\nInforme o nome do produto: ");
        String nomeprodutoNovo = scn.nextLine();
            if((nomeproduto).equals(nomeprodutoNovo)){
                System.out.println("\nCampo não pode ser o mesmo que antes!: ");
                return;
            }else{
                nomeproduto = nomeprodutoNovo;
            }

        System.out.println("\nInforme o valor do produto: ");
        double valorprodutoNovo = scn.nextDouble();
            if((valorproduto) == (valorprodutoNovo)){
                System.out.println("\nCampo não pode ser o mesmo que antes!: ");
                return;
            }else{
                valorproduto = valorprodutoNovo;
            }

        System.out.println("\nInforme a quantidade em estoque: ");
        int estoqueprodutoNovo = scn.nextInt();
            if((estoque) == (estoqueprodutoNovo)){
                System.out.println("\nCampo não pode ser o mesmo que antes!: ");
                return;
            }else{
                estoque = estoqueprodutoNovo;
            }
        System.out.println("Nome: "+ nomeproduto + "\nValor: R$ "+ valorproduto + "\nEstoque: "+ estoque);
    }
    // Método para verificar o estoque de um produto
    public void verificarEstoque(){
        System.out.println("Insira 'PROD-' + o ID do produto para checar no estoque");
        String idProduto = scn.nextLine().toUpperCase(); // garante que o ID inserido sempre seja maiuscula
        for (Produto produto : listaProdutos){
            if (produto.id.equals(idProduto)){
                if (produto.estoque > 0) {
                    System.out.println("\nProduto:\n"+ produto.id +" " + produto.nomeproduto + "\nStatus: DISPONÍVEL\nEstoque: " + produto.estoque);
                } else {
                    System.out.println("\nProduto:\n "+ produto.id +" " + produto.nomeproduto + "\nStatus: INDISPONÍVEL :(");
                }
                listarProdutos();
                return;
            }
        }
        System.out.println("Produto não encontrado.");
    }
    public void closeScanner(){ // fecha o scanner de entrada
        scn.close();
    }
}
