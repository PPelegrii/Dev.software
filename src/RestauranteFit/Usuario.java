package RestauranteFit;

import java.util.Scanner;
import java.util.UUID;

public class Usuario extends Pessoa {
    private String id;
    private transient final Scanner scn; 
    public String observacoes;
    public Endereco endereco; 

    private static Usuario instanciaUnica;

    private Usuario() {
        scn = new Scanner(System.in);
    }

    public static Usuario getInstance() {
        if (instanciaUnica == null) {
            instanciaUnica = new Usuario();
        }
        return instanciaUnica;
    }

    private String gerarIDUnico() { 
        return "ID-" + UUID.randomUUID().toString();
    }

    public String pegarID() {
        return this.id;
    }

    public void Cadastrar() {
        System.out.print("\n --- Cadastro de usuário --- " + "\nInforme seu nome: ");
        super.nome = scn.nextLine(); 
        Boolean emailValido = false;

        while (!emailValido) {
            System.out.print("Informe seu email: ");
            super.email = scn.nextLine(); 
            if (super.email.contains("@gmail.com") || super.email.contains("@yahoo.com") || super.email.contains("@hotmail.com")) {
                emailValido = true;
            } else {
                System.out.println("\nInsira um email válido!");
            }
        }
        this.endereco = new Endereco(); 
        System.out.println("Nome: " + super.nome + "\nE-mail: " + super.email);

        if (this.id == null) {
            this.id = gerarIDUnico();
            System.out.println("ID gerado: " + this.id);
        }
    }

    public void atualizarCadastro() {
        System.out.println("Atualização de cadastro:");
        System.out.print("Informe seu novo nome (atual: " + super.nome + "): ");
        String nomeNovo = scn.nextLine();
        if (!nomeNovo.isEmpty() && !super.nome.equals(nomeNovo)) {
            super.nome = nomeNovo;
        } else if (nomeNovo.isEmpty()){
            System.out.println("Nome mantido.");
        } else {
            System.out.println("Novo nome é igual ao anterior.");
        }

        System.out.print("Informe seu novo email (atual: " + super.email + "): ");
        String emailNovo = scn.nextLine();
        if(!emailNovo.isEmpty()){
            Boolean emailValido = false;
            while(!emailValido) {
                 if (emailNovo.contains("@gmail.com") || emailNovo.contains("@yahoo.com") || emailNovo.contains("@hotmail.com")) {
                    if(!super.email.equals(emailNovo)){
                        super.email = emailNovo;
                        emailValido = true;
                    } else {
                         System.out.println("Novo email é igual ao anterior. Email mantido.");
                         emailValido = true; 
                    }
                } else {
                    System.out.println("\nInsira um email válido para alteração!");
                    System.out.print("Informe seu novo email (atual: " + super.email + "): ");
                    emailNovo = scn.nextLine();
                    if(emailNovo.isEmpty()) { 
                        System.out.println("Email mantido.");
                        emailValido = true;
                    }
                }
            }
        } else {
            System.out.println("Email mantido.");
        }
        System.out.println("Deseja atualizar o endereço? (s/n)");
        if(scn.nextLine().trim().equalsIgnoreCase("s")){
            this.endereco = new Endereco();
        }

        System.out.println("Cadastro atualizado: Nome: " + super.nome + "\nE-mail: " + super.email);
    }

    @Override
    public void exibirInformacoes() {
        System.out.println("\n--- Informações do Usuário ---");
        System.out.println("ID do Usuário: " + (id != null ? id : "N/A"));
        System.out.println("Usuário: " + (super.nome != null ? super.nome : "N/A"));
        System.out.println("Email: " + (super.email != null ? super.email : "N/A"));
        if (endereco != null) {
            System.out.println("Endereço: Rua: " + endereco.rua + ", Nº: " + endereco.numero + 
                               " - Bairro: " + endereco.bairro + ", Cidade: " + endereco.cidade + 
                               " - Estado: " + endereco.estado);
        } else {
            System.out.println("Endereço: Não cadastrado.");
        }
    }
}