/*Documentação
 * 
 * Esse arquivo é competente a classe Pedido, assim como seus métodos declarados no documento do projeto 
 * Versão inicial por PedroH no dia 18/03/25. 
 * 
 * Historico de alterações:
 * 
 */
package Java; // Temporario afim de testes
import java.util.Scanner;

public class Pedido{
    Usuario user = new Usuario();
    Scanner scn; // Scanner como variável de instância
    java.time.ZonedDateTime data = java.time.ZonedDateTime.now(); // Data e hora atual
    String id, statuspedido;
    double valorpedido;

    public Pedido() { // Construtor
        scn = new Scanner(System.in); // inicia o scan
        user.gerarID(); // Chama o método gerarID
        id = user.pegarID(); // Inicializa o ID
    }

    public void calcularTotal(){

        //System.out.println("Valor do pedido: " + valorpedido);
    }
    
    public void informacoesPedido(){

        //System.out.println("ID do pedido: " + id);
        //System.out.println("Data do pedido: " + data);
        //System.out.println("Status do pedido: " + statuspedido);
        //System.out.println("Valor do pedido: " + valorpedido);
    }
    public void adicionarPedido(){
    }
    public void cancelarPedido(){
        System.out.println("Pedido cancelado!");
    }
    public void finalizarPedido(){
        System.out.println("Pedido finalizado!");
    }










}
