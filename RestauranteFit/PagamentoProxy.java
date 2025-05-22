package RestauranteFit;

import java.time.ZonedDateTime;

// padrão proxy
public class PagamentoProxy extends Pagamento {
    private Pagamento pagamentoReal; // Referência à instância real de Pagamento
    private boolean isPagamentoConcluido;

    // Construtor
    public PagamentoProxy(Pedido pedido) {
        super(pedido);
        this.isPagamentoConcluido = false;
    }

    @Override
    public void realizarPagamento() {
        // Controla o acesso ao pagamento real
        if (isPagamentoConcluido) {
            System.out.println("Pagamento já foi concluído!");
            return;
        }

        if (pagamentoReal == null) {
            // Se o pagamento real não foi criado, cria uma instância do pagamento real
            pagamentoReal = new Pagamento(getPedido());
        }

        // Chama o método realizarPagamento do pagamento real
        pagamentoReal.realizarPagamento();

        // Marca que o pagamento foi concluído
        isPagamentoConcluido = true;
    }

    @Override
    public void exibirDetalhesPagamento() {
        // Controla quando os detalhes do pagamento podem ser exibidos
        if (!isPagamentoConcluido) {
            System.out.println("Pagamento ainda não foi concluído.");
            return;
        }

        // Chama o método de exibição do pagamento real
        pagamentoReal.exibirDetalhesPagamento();
    }

    public Pedido getPedido() {
        return super.pedido;
    }
}
