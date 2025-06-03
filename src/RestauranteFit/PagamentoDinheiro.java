package RestauranteFit;

import java.util.Scanner; // Necessário se scn fosse local, mas é herdado
// usa padrão de projeto Template Method
public class PagamentoDinheiro extends Pagamento {

    public PagamentoDinheiro(Pedido pedido) {
        super(pedido); 
    }

    @Override
    protected void definirInformacoesTipoPagamento() {
        this.tipoPagamento = "Dinheiro"; 
        System.out.println("Forma de pagamento selecionada: Dinheiro");
    }

    @Override
    protected void obterValorPagoPeloCliente() {
        if (scn == null) { 
            System.err.println("ERRO CRÍTICO: Scanner não inicializado na classe Pagamento!");
            super.scn = new Scanner(System.in); 
        }
        
        System.out.print("Digite o valor pago em dinheiro: R$ ");
        while (true) { 
            String input = scn.nextLine().trim().replace(",", "."); 
            try {
                this.valorPago = Double.parseDouble(input);
                if (this.valorPago < 0) {
                    System.out.println("Valor pago não pode ser negativo. Tente novamente.");
                    System.out.print("Digite o valor pago em dinheiro: R$ ");
                    continue;
                }
                break; 
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um valor numérico (ex: 50.75).");
                System.out.print("Digite o valor pago em dinheiro: R$ ");
            }
        }
    }

    @Override
    protected void processarTransacaoEspecifica() {
        System.out.println("Transação em dinheiro processada localmente (cálculo de troco já feito no template).");
    }
}