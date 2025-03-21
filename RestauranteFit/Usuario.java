/*Documentação
 * 
 * Esse arquivo é competente a classe Usuario, assim como seus métodos declarados no documento do projeto .
 * Versão inicial por PedroH no dia 13/03/25. 
 * 
 * Historico de alterações
 * 14/03/25 adicionado ifs nas entradas de dados e otimizações no código. - PH
 * 18/03/25 adicionado método para gerar ID e pegar ID. - PH
 * 19/03/25 adicionado método para fazer pedido usando o ID . - PH
 * 
 */
package RestauranteFit; // Temporario afim de testes
import java.util.Scanner;
import java.util.UUID;

public class Usuario{
    private String id; // Id privado do usuário
    protected String nome, email;
    final Scanner scn; // Scanner como variável de instância
    public String observacoes;
    public Endereco endereco;

    public Usuario(){ // Construtor
        scn = new Scanner(System.in); // inicia o scan
    }
    public String gerarID(){ // Método para gerar um ID
        this.id = "ID-"+ UUID.randomUUID().toString(); // cria um ID aleatório e armazena em this.id
        System.out.println("ID gerado: " + this.id);
        return this.id;
    }
    public String pegarID() { // Método para acessar o ID
        return this.id; // usado em outras classes para pegar o ID do usuário
    }

    public void Cadastrar(){
        System.out.println("Cadastro de usuário:" + "\nInforme seu nome: ");
        nome = scn.nextLine();

        System.out.println("\nInforme seu email: ");
        email = scn.nextLine();
            if(email.contains("@gmail.com")){ // auto explicativo 
                System.out.println(email);
            }else{
                System.out.println("Insira um email válido!");
                return;
            }
        this.endereco = new Endereco();
        System.out.println("Nome: "+ nome + "\nE-mail: "+ email);
        gerarID(); // gera o ID do usuario para ser usado em outras classes | referencia linha 23
    }
    public void atualizarCadastro(){
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
        System.out.println("\n--- Fazer Pedido ---" + "\nNome do cliente: ");
        String nomeCliente = nome;

        System.out.print("Item do pedido: ");
        String itemPedido = scn.nextLine().toLowerCase();
        
        Produto produtoEscolhido = null;
        for (Produto produto : Produto.listaProdutos) {
        if (produto.nomeproduto.equalsIgnoreCase(itemPedido)) { // Compara sem diferenciar maiúsculas e minúsculas
            produtoEscolhido = produto;
            break;
        }
    }
    
        if (produtoEscolhido == null) {
            System.out.println("Erro: Produto não encontrado! Tente novamente.");
            return;
        }

        System.out.print("Quantidade: ");
        int quantidade = scn.nextInt();
        scn.nextLine(); // Limpa o buffer do scanner

        if (quantidade > produtoEscolhido.estoque){ // Verifica se há estoque suficiente
            System.out.println("Erro: Estoque insuficiente! Apenas " + produtoEscolhido.estoque + " unidades disponíveis.");
            return;
        }

        System.out.print("Observações: ");
        this.observacoes = scn.nextLine();

        // Atualiza o estoque após o pedido
        produtoEscolhido.estoque -= quantidade;
        
        System.out.println("\n--- Resumo do Pedido ---");
        System.out.println("Cliente: " + nomeCliente);
        System.out.println("Item: " + itemPedido);
        System.out.println("Quantidade: " + quantidade);
        System.out.println("Observações: " + this.observacoes);

    }
    public void closeScanner(){ // fecha o scanner de entrada
        scn.close();
    }
}