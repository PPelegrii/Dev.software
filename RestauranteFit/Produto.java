/*Documentação
 * 
 * Esse arquivo é competente a classe Produto, assim como seus métodos declarados no documento do projeto 
 * Versão inicial por PedroH no dia 19/03/25. 
 * 
 * Historico de alterações:
 * 
 */
package RestauranteFit;
import java.util.ArrayList;
import java.util.Scanner;
// import RestauranteFit.Usuario;
// import RestauranteFit.Pedido;
    
public class Produto{
    private static ArrayList<Produto> listaProdutos = new ArrayList<>(); // Lista de produtos
    // private Pedido pedido; // Composição
    private Scanner scn; // Scanner como variável de instância
    private String id, nomeproduto;
    private double valorproduto;
    private int estoque;
    
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
    // Método para cadastrar um produto
    public void cadastrarProduto() {
        System.out.print("Digite o nome do produto: ");
        String nome = scn.nextLine();

        System.out.print("Digite o valor do produto: ");
        double valor = scn.nextDouble();

        System.out.print("Digite a quantidade em estoque: ");
        int quantidade = scn.nextInt();
        scn.nextLine(); // Limpa o buffer do scanner

        String id = "PROD-" + (listaProdutos.size() + 1); // Gera um ID único para o produto
        Produto produto = new Produto(id, nome, valor, quantidade);
        listaProdutos.add(produto); // Adiciona o produto à lista

        System.out.println("Produto cadastrado com sucesso! ID: " + id);
        System.out.println("Deseja cadastrar outro produto?\n1 - Sim\n2 - Não");
        int opcao = scn.nextInt();
        if (opcao == 1) {
            cadastrarProduto();
        } else {
            return;
        }
    }
    // Método para listar todos os produtos
    public void listarProdutos() {
        if (listaProdutos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        System.out.println("\n--- Lista de Produtos ---");
        for (Produto produto : listaProdutos) {
            System.out.println("ID: " + produto.id);
            System.out.println("Nome: " + produto.nomeproduto);
            System.out.println("Valor: R$ " + produto.valorproduto);
            System.out.println("Estoque: " + produto.estoque);
            System.out.println("-------------------------");
        }
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
        System.out.println("Insira o ID do produto para checar no estoque");
        String idProduto = scn.nextLine();
        for (Produto produto : listaProdutos) {
            if (produto.id.equals(idProduto)) {
                if (produto.estoque > 0) {
                    System.out.println("Produto disponível. Estoque: " + produto.estoque);
                } else {
                    System.out.println("Produto indisponível no momento.");
                }
                return;
            }
        }
        //System.out.println("Produto não encontrado.");
    }
}