/*Documentação
 * 
 * Esse arquivo é competente a classe Pedido, assim como seus métodos declarados no documento do projeto 
 * Versão inicial por PedroH no dia 18/03/25. 
 * 
 * Historico de alterações:
 * 19/03/25 finalizado prototipo - PH
 */
package RestauranteFit;
import java.time.ZonedDateTime;
import java.util.Scanner;
// import RestauranteFit.Produto;

public class Pedido extends Usuario{
    private Usuario usuario; // Composição
    //private Pedido pedido; // Composição
    private Scanner scn; // Scanner como variável de instância
    private ZonedDateTime data;// Data e hora atual
    private String id, statuspedido;
    private double valorpedido;

    // Construtor que recebe um usuário
    public Pedido(Usuario usuario){
        this.usuario = usuario;
        this.scn = new Scanner(System.in); // inicia o scan
        this.data = ZonedDateTime.now(); // Pega a data e hora atual
        this.id = usuario.pegarID(); // Usa o ID do usuário associado
        this.statuspedido = "Em andamento"; // Inicializa o status do pedido
        this.valorpedido = 0.0; // Inicializa o valor do pedido
        this.observacoes = usuario.observacoes; // Acessa as observações do usuário 
    }

    // Atualmente o valor do pedido é inserido manualmente, precisa implementar a lógica para somar o valor dos produtos individualmente
    public void calcularTotal(){
        boolean valorValido = false; // check para verificar se o valor é válido
        while (!valorValido) {
            try {
                System.out.print("Digite o valor do pedido: ");
                this.valorpedido = scn.nextDouble(); // Lê o valor do pedido
                scn.nextLine(); // Limpa o buffer do Scanner
                valorValido = true; // Define como válido se não houver exceção
            } catch (java.util.InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, insira um número válido.");
                scn.nextLine(); // Limpa o buffer do Scanner para evitar loop infinito
            }
        }
        System.out.println("Valor total do pedido: R$ " + valorpedido);
        adicionarPedido();
        informacoesPedido();
    }
    
    public void informacoesPedido(){
        System.out.println("\n--- Informações do Pedido ---");
        System.out.println("ID do pedido: " + id);
        System.out.println("Data do pedido: " + data);
        System.out.println("Status do pedido: " + statuspedido);
        System.out.println("Valor do pedido: R$ " + valorpedido);
        System.out.println("Usuário associado: " + usuario.nome); // Acessa o nome do usuário
        System.out.println("Observações: " + observacoes);
        System.out.println("\nDeseja adicionar mais produtos ao pedido?\n1 - Sim\n2 - Não");
        int opcao = scn.nextInt();
        if (opcao == 1) {
            calcularTotal();
        } else {
            finalizarPedido();
        }
    }
    public void adicionarPedido(){
        System.out.println("Pedido adicionado com sucesso!");
    }
    public void cancelarPedido(){
        System.out.println("Pedido cancelado!");
    }
    public void finalizarPedido(){
        System.out.println("Pedido finalizado!");
    }
}
