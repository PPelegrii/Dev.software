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
        System.out.println("ENDEREÇO");
        System.out.println("Informe a rua: ");
        this.rua = scn.nextLine();
        System.out.println("Informe o número: ");
        this.numero = scn.nextLine();
        System.out.println("Informe o bairro: ");
        this.bairro = scn.nextLine();
        System.out.println("Informe a cidade: ");
        this.cidade = scn.nextLine();
        System.out.println("Informe o estado: ");
        estado = scn.nextLine();
    }
}
