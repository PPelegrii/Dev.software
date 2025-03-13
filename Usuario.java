package dev_software;
import java.util.Scanner;

public class Usuario{
    int ID;
    String nome, email;
    Scanner scn; // Scanner como variável de instância

    public Usuario(){ // Construtor
        scn = new Scanner(System.in); // inicia o scan
    }

    public void Cadastrar(){
        System.out.print("\nCadastro de usuário:");

        System.out.println("\nInforme seu nome: ");
        nome = scn.nextLine();

        System.out.println("\nInforme seu email: ");
        email = scn.nextLine();

        System.out.println(nome);
        System.out.println(email);
            
    }
    public void Atualizar(){
        System.out.print("Atualização de cadastro:");

        System.out.println("\nInforme seu nome: ");
        nome = scn.nextLine();

        System.out.println("\nInforme seu email: ");
        email = scn.nextLine();

        System.out.println(nome);
        System.out.println(email);
    }
    public void fazerPedido(){
            
        System.out.println("--- Fazer Pedido ---");
    
        System.out.print("Nome do cliente: ");
        String nomeCliente = scn.nextLine();

        System.out.print("Item do pedido: ");
        String itemPedido = scn.nextLine();

        System.out.print("Quantidade: ");
        int quantidade = scn.nextInt();
        scn.nextLine(); // Limpar o buffer do scanner

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