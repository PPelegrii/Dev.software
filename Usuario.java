/*Documentação
 * 
 * Esse arquivo é competente a classe Usuario, assim como seus métodos declarados no documento do projeto 
 * Versão inicial por PedroH no dia 13/03/25. 
 * 
 * Historico de alterações
 * 14/03/25 adicionado ifs nas entradas de dados e otimizações no código. - PH
 * 
 */
package dev_software;
import java.util.Scanner;
import java.util.UUID;

public class Usuario{
    String nome, email;
    Scanner scn; // Scanner como variável de instância

    public Usuario(){ // Construtor
        scn = new Scanner(System.in); // inicia o scan
    }
    public void ID(){ // Isso aqi n funciona, 
        String ID = "ID-"+UUID.randomUUID().toString(); // cria um ID aleatório para o usuario
        System.out.println("ID: "+ID);
    }

    public void Cadastrar(){
        System.out.println("Cadastro de usuário:" + "\nInforme seu nome: ");
        nome = scn.nextLine();

        System.out.println("\nInforme seu email: ");
        email = scn.nextLine();
            if(email.contains("@")){
                System.out.println(email);
            }else{
                System.out.println("Insira um email válido!");
                return;
            }
        System.out.println("Nome: "+ nome + "\nE-mail: "+ email);
    }
    public void Atualizar(){
        System.out.println("Atualização de cadastro:" + "\nInforme seu nome: ");
        String nomeNovo = scn.nextLine();
            if((nome).equals(nomeNovo)){
                System.out.println("\nCampo não pode ser o mesmo que antes!: ");
                return;
            }else{
                nome = nomeNovo;
            }

        System.out.println("\nInforme seu email: ");
        String emailNovo = scn.nextLine();
            if((emailNovo).equals(email)){
                System.out.println("\nCampo não pode ser o mesmo que antes!: ");
                return;
            }else{
                email = emailNovo;
            }

        System.out.println("Nome: "+ nome + "\nE-mail: "+ email);
    }
    public void fazerPedido(){
        System.out.println("--- Fazer Pedido ---" + "Nome do cliente: ");
        String nomeCliente = scn.nextLine();

        System.out.print("Item do pedido: ");
        String itemPedido = scn.nextLine();

        System.out.print("Quantidade: ");
        int quantidade = scn.nextInt();
        scn.nextLine(); // Limpa o buffer do scanner

        System.out.print("Observações: ");
        String observacoes = scn.nextLine();

        System.out.println("\n--- Resumo do Pedido ---");
        System.out.println("Cliente: " + nomeCliente);
        System.out.println("Item: " + itemPedido);
        System.out.println("Quantidade: " + quantidade);
        System.out.println("Observações: " + observacoes);

    }
    public void closeScanner(){ // fecha o scanner de entrada
        scn.close();
    }
}
