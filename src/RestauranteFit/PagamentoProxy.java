package RestauranteFit;

public class PagamentoProxy extends Pagamento { 

    private Pagamento pagamentoRealConcreto;

    public PagamentoProxy(Pagamento pagamentoConcreto) {
        super(pagamentoConcreto.getPedido()); 
        this.pagamentoRealConcreto = pagamentoConcreto;
        sincronizarEstadoComReal(); 
    }

    private void sincronizarEstadoComReal() {
        if (this.pagamentoRealConcreto != null) {
            this.pagamentoConcluido = this.pagamentoRealConcreto.pagamentoConcluido;
            this.valorPago = this.pagamentoRealConcreto.valorPago;
            this.troco = this.pagamentoRealConcreto.troco;
            this.tipoPagamento = this.pagamentoRealConcreto.tipoPagamento;
            this.dataPagamento = this.pagamentoRealConcreto.dataPagamento;
        }
    }

    @Override
    public void executarProcessoDePagamento() { 
        System.out.println("PROXY: Verificando permissões e estado antes de processar pagamento...");
        
        if (this.pagamentoConcluido) { 
            System.out.println("PROXY: Pagamento (conforme registro do proxy) já foi concluído anteriormente.");
            if (pagamentoRealConcreto.isPagamentoConcluido()) { 
                 pagamentoRealConcreto.exibirDetalhesPagamento();
            }
            return;
        }
        
        if (pagamentoRealConcreto == null) {
            System.out.println("PROXY: Erro - Objeto de pagamento real não configurado.");
            return;
        }

        System.out.println("PROXY: Delegando o processo de pagamento para o objeto real (" + pagamentoRealConcreto.getClass().getSimpleName() + ").");
        
        pagamentoRealConcreto.executarProcessoDePagamento();

        sincronizarEstadoComReal();

        if (this.pagamentoConcluido) {
            System.out.println("PROXY: Processo de pagamento concluído pelo objeto real. Estado do proxy atualizado.");
        } else {
            System.out.println("PROXY: Processo de pagamento não foi concluído pelo objeto real ou foi cancelado.");
        }
    }

    @Override
    public void exibirDetalhesPagamento() {
        System.out.println("PROXY: Verificando permissão para exibir detalhes do pagamento...");
        if (pagamentoRealConcreto == null) {
            System.out.println("PROXY: Objeto de pagamento real não está definido.");
            return;
        }
        pagamentoRealConcreto.exibirDetalhesPagamento();
    }

    @Override
    protected void definirInformacoesTipoPagamento() {
        // A lógica real é do pagamentoRealConcreto.
        // O proxy pode logar ou adicionar comportamento se o seu próprio template fosse executado.
        if (pagamentoRealConcreto != null && this.tipoPagamento == null) { // Evita sobrescrever se já sincronizado
             // this.tipoPagamento = pagamentoRealConcreto.tipoPagamento; // O estado é sincronizado pós execução
        }
    }

    @Override
    protected void obterValorPagoPeloCliente() {
        // A lógica real é do pagamentoRealConcreto.
    }

    @Override
    protected void processarTransacaoEspecifica() {
        // A lógica real é do pagamentoRealConcreto.
    }
}