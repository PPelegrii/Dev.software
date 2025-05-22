package RestauranteFit;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;

public class Endereco {
    public String rua;
    public int numero;
    public String bairro;
    public String cidade;
    public String estado;

    // Coleção HashSet para armazenar endereços únicos
    public static Set<Endereco> setEnderecos = new HashSet<>();

    public Endereco(){
        Scanner scn = new Scanner(System.in);
        System.out.println("\n\nENDEREÇO");

        try {
            System.out.print("Informe a rua: ");
            this.rua = scn.nextLine();

            // Laço para garantir que o número seja um inteiro válido
            while (true) {
                System.out.print("Informe o número: ");
                String numeroTexto = scn.nextLine();
                try {
                    this.numero = Integer.parseInt(numeroTexto);
                    break; // sai do loop se deu certo
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

            // Adiciona o novo endereço ao HashSet
            setEnderecos.add(this);

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar endereço. Detalhes: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    // Método para listar todos os endereços cadastrados
    public void listarEnderecos() {
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
}
