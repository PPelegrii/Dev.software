/*Documentação
 * 
 * Esse arquivo é competente a classe Pedido, assim como seus métodos declarados no documento do projeto .
 * Versão inicial por PedroH no dia 18/03/25. 
 * 
 * Referências:
 * https://www.w3schools.com/java/
 * https://docs.oracle.com/javase/tutorial/index.html
 * https://www.geeksforgeeks.org/java/
 * Copilot AI, ChatGPT
 * 
 * Historico de alterações:
 * 19/03/25 finalizado prototipo - PH
 * 27/03/25 adicionado a lógica de fazer pedido, anteriormente calcularTotal, unido com método fazerPedido da classe Usuario - PH
 * 01/04/25 melhorado lógica e integração com Pagamento.java - PH
 */
package RestauranteFit;
import java.time.ZonedDateTime;
import java.util.Scanner;
// import RestauranteFit.Produto;

public class Pedido extends Usuario{
    private final Usuario usuario; // Composição
    private final Scanner scn; // Scanner como variável de instância
    private final ZonedDateTime data;// Data e hora atual
    protected final String id, statuspedido;
    protected double valorpedido;
    public Boolean pedidoFuncionou;

    // Construtor que recebe um usuário
    public Pedido(Usuario usuario){
        this.usuario = usuario;
        this.scn = new Scanner(System.in); // inicia o scan
        this.data = ZonedDateTime.now(); // Pega a data e hora atual
        this.id = usuario.pegarID(); // Usa o ID do usuário associado
        this.statuspedido = "Em andamento"; // Inicializa o status do pedido
        this.valorpedido = 0.0; // Inicializa o valor do pedido
    }

    public void fazerPedido(){
        this.pedidoFuncionou = false;
        System.out.println("\n--- Fazer Pedido ---");
        System.out.println("Produtos disponíveis:");
        for (Produto produto : Produto.listaProdutos){
            System.out.println(produto.nomeproduto);
        }

        boolean continuar = true;
        while (continuar){
            System.out.print("\nDigite o nome do item: ");
            String itemPedido = scn.nextLine().toLowerCase();

            Produto produtoSelecionado = null;
            for (Produto produto : Produto.listaProdutos){ // pega todos os produtos na lista.produtos em produto.java
                if (produto.nomeproduto.equalsIgnoreCase(itemPedido)){
                    produtoSelecionado = produto;
                    break;
                }
            }

            if (produtoSelecionado == null) {
                System.out.println("Produto não encontrado. Tente novamente.");
                this.pedidoFuncionou = false;
                fazerPedido(); return;
            }

            System.out.print("Digite a quantidade desejada: ");
            int quantidade = 0; 
            boolean entradaValida = false;

            while (!entradaValida) {
                try {
                    quantidade = scn.nextInt();
                    scn.nextLine(); // Limpa o buffer do Scanner
                    entradaValida = true;
                } catch (Exception e) {
                    System.out.println("Erro: você deve digitar um número inteiro válido!");
                    scn.nextLine(); // Limpa a entrada inválida
                }
            }

            if (produtoSelecionado.estoque >= quantidade){
                produtoSelecionado.estoque -= quantidade;
                valorpedido += produtoSelecionado.valorproduto * quantidade;
                System.out.println("Produto adicionado ao pedido. Total parcial: R$ " + valorpedido);
            } else {
                System.out.println("Quantidade insuficiente no estoque. Estoque disponível: " + produtoSelecionado.estoque);
                cancelarPedido(); return;
            }
            System.out.print("Observações: ");
            this.observacoes = scn.nextLine();

            System.out.print("Deseja adicionar mais produtos ao pedido? (1 - Sim, 2 - Não): ");
            int opcao = scn.nextInt();
            scn.nextLine(); // Limpa o buffer do Scanner

            while(opcao != 1 && opcao != 2){
                System.out.println("Opção inválida. 1 - Sim | 2 - Não");
                opcao = scn.nextInt();
                scn.nextLine(); // Limpa o buffer do Scanner
            }
            if (opcao == 2){
                if(produtoSelecionado.estoque == 0) return;
                finalizarPedido();
                continuar = false;
            }
        }
        System.out.println("Valor total do pedido: R$ " + valorpedido);
        adicionarPedido();
        exibirInformacoes();
    }

        // isso é polimorfismo. Método exibirInformacoes da classe Exibir foi sobrescrita
    @Override
    public void exibirInformacoes(){
        System.out.println("\n--- Informações do Pedido ---");
        System.out.println("ID do pedido: " + id + "\nData do pedido: " + data);                         // id e data
        System.out.println("Status do pedido: " + statuspedido + "\nValor do pedido: R$ " + valorpedido); // status e valor pedido
        System.out.println("Usuário associado: " + usuario.nome + "\nObservações: " + observacoes); // nome e observações
    }
    public void adicionarPedido(){
        System.out.println("Pedido adicionado com sucesso!");
        this.pedidoFuncionou = true; // a bool pedidoFuncionou faz mais sentido aqui
    }
    public void cancelarPedido(){
        System.out.println("Pedido cancelado!");
    }
    public void finalizarPedido(){
        System.out.println("Pedido finalizado!");
    }
    @Override
    public void closeScanner(){ // fecha o scanner de entrada
        scn.close();
    }
}
