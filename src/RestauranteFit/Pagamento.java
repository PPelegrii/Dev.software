package RestauranteFit;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
// import java.util.InputMismatchException; // Não é usado diretamente aqui

// usa padrão de projeto Template Method nos metodos
// definirInformacoesTipoPagamento e obterValorPagoPeloCliente
// nova classe: PagamentoDinheiro
public abstract class Pagamento {
    protected Pedido pedido;
    protected double valorPago;
    protected String tipoPagamento;
    protected ZonedDateTime dataPagamento;
    protected Boolean pagamentoConcluido;
    protected double troco;
    protected transient Scanner scn; 

    public Pagamento(Pedido pedido) {
        this.pedido = pedido;
        this.pagamentoConcluido = false;
        this.scn = new Scanner(System.in); 
    }

    public void executarProcessoDePagamento() {
        if (pedido == null || !pedido.pedidoFuncionou || pedido.valorpedido <= 0) {
            System.out.println("Pagamento não pode prosseguir: pedido inválido, não funcional ou sem valor.");
            if (pedido != null) pedido.statuspedido = "Falha no Pagamento (pedido inválido)";
            return;
        }
        if (this.pagamentoConcluido) {
            System.out.println("Este pagamento já foi concluído anteriormente.");
            exibirDetalhesPagamento();
            return;
        }

        exibirValorDoPedido();
        definirInformacoesTipoPagamento(); 

        while (!pagamentoConcluido) {
            obterValorPagoPeloCliente(); 

            if (valorPago >= pedido.valorpedido) {
                this.dataPagamento = ZonedDateTime.now(); 
                this.pagamentoConcluido = true;
                processarTransacaoEspecifica(); 
                
                System.out.println("Pagamento de R$ " + String.format("%.2f", valorPago) + 
                                   " (" + this.tipoPagamento + ") realizado com sucesso em " + 
                                   this.dataPagamento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + "!");
                
                troco = valorPago - pedido.valorpedido;
                System.out.println("Troco: R$ " + String.format("%.2f", troco));
                pedido.finalizarPedidoAposPagamento(); 
            } else {
                System.out.println("Erro: O valor pago (R$ " + String.format("%.2f", valorPago) + 
                                   ") é menor do que o valor do pedido (R$ " + String.format("%.2f", pedido.valorpedido) + ")!");
                System.out.println("Tente novamente ou cancele o pagamento.");
                System.out.print("Deseja tentar pagar novamente? (s/n): ");
                if (!scn.nextLine().trim().equalsIgnoreCase("s")) {
                    System.out.println("Pagamento cancelado pelo usuário.");
                    pedido.statuspedido = "Pagamento Cancelado";
                    break; 
                }
            }
        }
    }

    protected void exibirValorDoPedido() {
        System.out.println("\n--- Processando Pagamento ---");
        System.out.println("Valor total do Pedido: R$ " + String.format("%.2f", pedido.valorpedido));
    }

    protected abstract void definirInformacoesTipoPagamento();
    protected abstract void obterValorPagoPeloCliente();
    protected void processarTransacaoEspecifica() {
    }

    public void exibirDetalhesPagamento() {
        if (!pagamentoConcluido) {
            System.out.println("\n--- Detalhes do Pagamento ---");
            System.out.println("Pagamento ainda não foi concluído ou foi cancelado.");
            if (pedido != null) {
                System.out.println("ID do Pedido: " + pedido.id);
                System.out.println("Valor do Pedido: R$ " + String.format("%.2f", pedido.valorpedido));
            }
            return;
        }
        System.out.println("\n--- Detalhes do Pagamento ---");
        System.out.println("ID do Pedido: " + pedido.id);
        System.out.println("Valor do Pedido: R$ " + String.format("%.2f", pedido.valorpedido));
        System.out.println("Valor Pago: R$ " + String.format("%.2f", valorPago));
        System.out.println("Troco: R$ " + String.format("%.2f", troco));
        if (dataPagamento != null) {
             System.out.println("Data do Pagamento: " + dataPagamento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        }
        System.out.println("Tipo de Pagamento: " + tipoPagamento);
        System.out.println("Pagamento Concluído: Sim");
    }

    public Pedido getPedido() {
        return this.pedido;
    }
    
    public boolean isPagamentoConcluido() {
        return this.pagamentoConcluido;
    }
}