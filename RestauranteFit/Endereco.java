/*Documentação
 * 
 * Esse arquivo é competente a classe Endereço, assim como seus métodos declarados no documento do projeto .
 * Versão inicial por Kaue no dia 20/03/25. 
 * 
 * Historico de alterações
 * 27/03/2025 adicionado documentação de código, assim como instruido pelo professor - PH
 */
package RestauranteFit;
import java.util.Scanner;

public class Endereco {
    public String rua;
    public int numero;
    public String bairro;
    public String cidade;
    public String estado;

    public Endereco(){
        Scanner scn = new Scanner(System.in);
        System.out.println("\nENDEREÇO");

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

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar endereço. Detalhes: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            return;
        }
    }
}