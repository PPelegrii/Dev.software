package RestauranteFit;

import java.time.ZonedDateTime;
import java.util.Scanner;

public class Pagamento {
    private Pedido pedido;
    private double valorPago;
    private String tipoPagamento;
    private ZonedDateTime dataPagamento;
    private Boolean pagamentoConcluido;
    
    public Pagamento(Pedido pedido){
        this.pedido = pedido;
        this.dataPagamento = ZonedDateTime.now();
        this.pagamentoConcluido = false;

    }
    
    // Método para realizar o pagamento
    public void realizarPagamento() {
        Scanner scn = new Scanner(System.in);

        System.out.println("Valor do Pedido: R$ " + pedido.valorpedido);
        
        
        scn.nextLine(); // Limpa o buffer
        System.out.println("Digite o tipo de pagamento: ");
        this.tipoPagamento = scn.nextLine();

        // Verifica se o valor pago é suficiente
        while(!pagamentoConcluido){
            System.out.print("Digite o valor pago: R$ ");
            this.valorPago = scn.nextDouble();
            if (valorPago >= pedido.valorpedido) {
                
                this.pagamentoConcluido = true;
                System.out.println("Pagamento de R$ " + valorPago + " realizado com sucesso!");
                pedido.finalizarPedido(); // Finaliza o pedido após o pagamento
            } else {
                System.out.println("Erro: O valor pago é menor do que o valor do pedido!");
                System.out.println("Tente novamente.");
            }
        }
    }
    public void exibirDetalhesPagamento() {
        System.out.println("\n--- Detalhes do Pagamento ---");
        System.out.println("ID do Pedido: " + pedido.getId());
        System.out.println("Valor do Pedido: R$ " + pedido.valorpedido);
        System.out.println("Valor Pago: R$ " + valorPago);
        System.out.println("Data do Pagamento: " + dataPagamento);
        System.out.println("Tipo de Pagamento: " + tipoPagamento);
        System.out.println("Pagamento Concluído: " + (pagamentoConcluido ? "Sim" : "Não"));
    }
}
