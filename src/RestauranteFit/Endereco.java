package RestauranteFit;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Endereco {
    public String rua;
    public int numero;
    public String bairro;
    public String cidade;
    public String estado;

    public static Set<Endereco> setEnderecos = new HashSet<>();

    public Endereco() {
        Scanner scn = new Scanner(System.in); 
        System.out.println("\n\nENDEREÇO");

        try {
            System.out.print("Informe a rua: ");
            this.rua = scn.nextLine();

            while (true) {
                System.out.print("Informe o número: ");
                String numeroTexto = scn.nextLine();
                try {
                    this.numero = Integer.parseInt(numeroTexto);
                    break; 
                } catch (NumberFormatException ex) {
                    System.out.println("Entrada inválida! Digite apenas números inteiros.");
                }
            }

            System.out.print("Informe o bairro: ");
            this.bairro = scn.nextLine();

            System.out.print("Informe a cidade: ");
            this.cidade = scn.nextLine();

            System.out.print("Informe o estado: ");
            this.estado = scn.nextLine();

            setEnderecos.add(this); 

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar endereço. Detalhes: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    public static void listarEnderecosCadastrados() { 
        if (setEnderecos.isEmpty()) {
            System.out.println("Nenhum endereço cadastrado.");
        } else {
            System.out.println("\nEndereços cadastrados:");
            for (Endereco endereco : setEnderecos) {
                System.out.println("Rua: " + endereco.rua + ", Número: " + endereco.numero + ", Bairro: " + endereco.bairro +
                                   ", Cidade: " + endereco.cidade + ", Estado: " + endereco.estado);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Endereco endereco = (Endereco) o;
        return numero == endereco.numero &&
               java.util.Objects.equals(rua, endereco.rua) &&
               java.util.Objects.equals(bairro, endereco.bairro) &&
               java.util.Objects.equals(cidade, endereco.cidade) &&
               java.util.Objects.equals(estado, endereco.estado);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(rua, numero, bairro, cidade, estado);
    }
}