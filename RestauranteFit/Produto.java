/*Documentação
 * 
 * Esse arquivo é competente a classe Produto, assim como seus métodos declarados no documento do projeto .
 * Versão inicial por PedroH no dia 19/03/25. 
 * 
 * Historico de alterações:
 * 21/03/25 adicionado lista de produtos fixa e verificarEstoque() está funcional - PH
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
    protected String id, nomeproduto;
    public double valorproduto;
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

    static{ // Lista de produtos 
        listaProdutos.add(new Produto("PROD-1", "temp/produto_1", 25.99, 0));
        listaProdutos.add(new Produto("PROD-2", "temp/produto_2", 12.50, 15));
        listaProdutos.add(new Produto("PROD-3", "temp/produto_3", 30.41, 18));
        listaProdutos.add(new Produto("PROD-4", "temp/produto_4", 20.75, 12));
        listaProdutos.add(new Produto("PROD-5", "temp/produto_5", 18.85, 10));
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
        System.out.println("Insira 'PROD-' + o ID do produto para checar no estoque");
        String idProduto = scn.nextLine().toUpperCase(); // garante que o ID inserido sempre seja maiuscula
        for (Produto produto : listaProdutos) {
            if (produto.id.equals(idProduto)) {
                if (produto.estoque > 0) {
                    System.out.println("\nProduto:\n"+ produto.id +" " + produto.nomeproduto + "\nStatus: DISPONÍVEL\nEstoque: " + produto.estoque);
                } else {
                    System.out.println("\nProduto:\n "+ produto.id +" " + produto.nomeproduto + "\nStatus: INDISPONÍVEL :(");
                }
                return;
            }
        }
        System.out.println("Produto não encontrado.");
    }
}