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
    public String numero;
    public String bairro;
    public String cidade;
    public String estado;

    public Endereco(){
        Scanner scn = new Scanner(System.in);
        System.out.println("\nENDEREÇO");
        System.out.println("Informe a rua: ");
        this.rua = scn.nextLine();
        System.out.println("Informe o número: ");
        this.numero = scn.nextLine();
        System.out.println("Informe o bairro: ");
        this.bairro = scn.nextLine();
        System.out.println("Informe a cidade: ");
        this.cidade = scn.nextLine();
        System.out.println("Informe o estado: ");
        this.estado = scn.nextLine();

        
    }
}
