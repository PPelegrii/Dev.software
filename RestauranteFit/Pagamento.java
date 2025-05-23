package RestauranteFit;

import java.time.ZonedDateTime;
import java.util.Scanner;

public class Pagamento {
    protected Pedido pedido; // Isso é composição
    protected double valorPago;
    protected String tipoPagamento;
    protected ZonedDateTime dataPagamento;
    protected Boolean pagamentoConcluido;
    protected double troco;

    // Construtor
    public Pagamento(Pedido pedido) {
        this.pedido = pedido;
        this.dataPagamento = ZonedDateTime.now();
        this.pagamentoConcluido = false;
    }

    // Método para realizar o pagamento
    public void realizarPagamento() {
        if (pedido.pedidoFuncionou == false) return;
        Scanner scn = new Scanner(System.in);

        System.out.println("\nValor do Pedido: R$ " + pedido.valorpedido);

        System.out.print("Digite o tipo de pagamento: ");
        this.tipoPagamento = scn.nextLine();

        // Verifica se o valor pago é suficiente
        while (!pagamentoConcluido) {
            System.out.print("Digite o valor pago: R$ ");
            this.valorPago = scn.nextDouble();

            if (valorPago >= pedido.valorpedido) { // Verifica se o valor pago é igual ou maior que o valor do pedido
                this.pagamentoConcluido = true;
                System.out.println("Pagamento de R$ " + valorPago + " realizado com sucesso!");
                troco = valorPago - pedido.valorpedido;
                System.out.println("Troco: " + troco);
                pedido.finalizarPedido(); // Finaliza o pedido após o pagamento
            } else if (valorPago < pedido.valorpedido) {
                System.out.println("Erro: O valor pago é menor do que o valor do pedido!");
                System.out.println("Tente novamente.");
            }
        }
    }

    public void exibirDetalhesPagamento() { // Imprime os detalhes do pagamento
        if (pedido.pedidoFuncionou == false) return;
        System.out.println("\n--- Detalhes do Pagamento ---");
        System.out.println("ID do Pedido: " + pedido.id);
        System.out.println("Valor do Pedido: R$ " + pedido.valorpedido);
        System.out.println("Valor Pago: R$ " + valorPago);
        System.out.println("Troco: " + troco);
        System.out.println("Data do Pagamento: " + dataPagamento);
        System.out.println("Tipo de Pagamento: " + tipoPagamento);
        System.out.println("Pagamento Concluído: " + (pagamentoConcluido ? "Sim" : "Não"));
    }
}
