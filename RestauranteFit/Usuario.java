/*Documentação
 * 
 * Esse arquivo é competente a classe Usuario, assim como seus métodos declarados no documento do projeto .
 * Versão inicial por PedroH no dia 13/03/25. 
 * 
 * Referências:
 * https://www.w3schools.com/java/
 * https://docs.oracle.com/javase/tutorial/index.html
 * https://www.geeksforgeeks.org/java/
 */
package RestauranteFit; // Temporario afim de testes
import java.util.Scanner;
import java.util.UUID;

public class Usuario extends Pessoa {
    private String id; // Id privado do usuário
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
        System.out.print("\n --- Cadastro de usuário --- " + "\nInforme seu nome: ");
        nome = scn.nextLine();
        Boolean emailValido = false;

        while(!emailValido){
            System.out.print("Informe seu email: ");
            email = scn.nextLine();
                if(email.contains("@gmail.com") || email.contains("@yahoo.com")|| email.contains("@hotmail.com")){ // auto explicativo 
                    System.out.print(email);
                    emailValido = true;
                }else{
                    System.out.print("\nInsira um email válido!");
                }
        }       
        this.endereco = new Endereco();
        System.out.println("Nome: "+ nome + "\nE-mail: "+ email);

        if (this.id == null) { // Garante que o ID não seja gerado mais de uma vez
        this.id = gerarID();  // gera o ID do usuario para ser usado em outras classes | referencia linha 33
        } 

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
            }else{
                email = emailNovo;
            }

        System.out.println("Nome: "+ nome + "\nE-mail: "+ email);
    }

    public void closeScanner(){ // fecha o scanner de entrada
        scn.close();
    }

    // isso é polimorfismo. Método exibirInformacoes da classe Exibir foi sobrescrita
    @Override
    public void exibirInformacoes(){
        System.out.println("Usuário: " + nome);
        System.out.println("Email: " + email);
        if (endereco != null) {
            System.out.println("Endereço: " + endereco.rua + ", " + endereco.numero + " - " + endereco.bairro + ", " + endereco.cidade + " - " + endereco.estado);
        }
        
    }
}