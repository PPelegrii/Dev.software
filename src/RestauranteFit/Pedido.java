package RestauranteFit;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

// Classe para armazenar um item do pedido com sua quantidade
// (ItemPedido continua a mesma, incluída aqui para contexto se necessário, mas não é modificada)
class ItemPedido {
    Produto produto;
    int quantidade;

    public ItemPedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }
}

public class Pedido extends Exibir {
    private final Usuario usuario;
    private transient final Scanner scn; 
    private final ZonedDateTime data;
    protected final String id;
    protected String statuspedido; 
    protected double valorpedido;
    public Boolean pedidoFuncionou;
    public String observacoes;
    private List<ItemPedido> itensDoPedido; // Lista privada

    public Pedido(Usuario usuario) {
        this.usuario = usuario;
        this.scn = new Scanner(System.in);
        this.data = ZonedDateTime.now();
        this.id = (usuario != null && usuario.pegarID() != null ? usuario.pegarID() : "USER_TEMP") + "-" + data.toEpochSecond(); 
        this.statuspedido = "Em rascunho";
        this.valorpedido = 0.0;
        this.pedidoFuncionou = false; 
        this.itensDoPedido = new ArrayList<>();
    }

    public void fazerPedido() {
        System.out.println("\n--- Fazer Pedido ---");
        Produto pTemp = new Produto(); 
        pTemp.listarProdutos(); 

        if (Produto.listaProdutos.isEmpty()) {
            System.out.println("Desculpe, não há produtos disponíveis para pedido no momento.");
            this.statuspedido = "Falhou (sem produtos)";
            this.pedidoFuncionou = false;
            return;
        }
        this.statuspedido = "Montando";
        boolean continuarAdicionando = true;
        while (continuarAdicionando) {
            System.out.print("\nDigite o NOME do item que deseja adicionar ao pedido (ou 'fim' para encerrar): ");
            String nomeItemPedido = scn.nextLine().trim();

            if (nomeItemPedido.equalsIgnoreCase("fim")) {
                continuarAdicionando = false;
                continue;
            }

            Produto produtoSelecionado = null;
            for (Produto produto : Produto.listaProdutos) {
                if (produto.nomeproduto.equalsIgnoreCase(nomeItemPedido)) {
                    produtoSelecionado = produto;
                    break;
                }
            }

            if (produtoSelecionado == null) {
                System.out.println("Produto '" + nomeItemPedido + "' não encontrado. Tente novamente.");
                continue; 
            }

            int quantidade = 0;
            boolean entradaValida = false;
            while (!entradaValida) {
                System.out.print("Digite a quantidade desejada de " + produtoSelecionado.nomeproduto + ": ");
                try {
                    quantidade = scn.nextInt();
                    scn.nextLine(); 
                    if (quantidade <= 0) {
                        System.out.println("Quantidade deve ser maior que zero.");
                        continue;
                    }
                    entradaValida = true;
                } catch (InputMismatchException e) {
                    System.out.println("Erro: você deve digitar um número inteiro válido para quantidade!");
                    scn.nextLine(); 
                }
            }

            if (produtoSelecionado.estoque >= quantidade) {
                itensDoPedido.add(new ItemPedido(produtoSelecionado, quantidade)); 
                valorpedido += produtoSelecionado.valorproduto * quantidade;
                System.out.println(quantidade + "x " + produtoSelecionado.nomeproduto + " adicionado(s). Total parcial: R$ " + String.format("%.2f", valorpedido));
                this.pedidoFuncionou = true; 
            } else {
                System.out.println("Quantidade insuficiente de '" + produtoSelecionado.nomeproduto + "' no estoque. Disponível: " + produtoSelecionado.estoque);
            }

            System.out.print("Deseja adicionar mais produtos ao pedido? (s/n): ");
            String opcao = scn.nextLine().trim();
            if (!opcao.equalsIgnoreCase("s")) {
                continuarAdicionando = false;
            }
        }
        
        System.out.print("Observações para este pedido (opcional): ");
        this.observacoes = scn.nextLine();

        if (this.pedidoFuncionou && !itensDoPedido.isEmpty()) { // Acesso interno à itensDoPedido é OK
            System.out.println("Pedido montado. Valor total do pedido: R$ " + String.format("%.2f", valorpedido));
            adicionarPedido(); 
        } else {
            System.out.println("Nenhum item adicionado ao pedido ou pedido vazio.");
            cancelarPedido();
        }
    }
    
    public void confirmarDebitoEstoqueAposPagamento() {
        if (!this.statuspedido.equals("Finalizado")) {
            System.out.println("Débito de estoque só pode ocorrer para pedidos finalizados.");
            return;
        }
        System.out.println("Debitando itens do estoque...");
        for (ItemPedido item : itensDoPedido) {
            Produto produtoNoEstoque = null;
            for (Produto p : Produto.listaProdutos) {
                if (p.id.equals(item.produto.id)) {
                    produtoNoEstoque = p;
                    break;
                }
            }
            if (produtoNoEstoque != null) {
                if (produtoNoEstoque.estoque >= item.quantidade) {
                    produtoNoEstoque.estoque -= item.quantidade;
                    System.out.println("Estoque de '" + produtoNoEstoque.nomeproduto + "' atualizado para: " + produtoNoEstoque.estoque);
                } else {
                    System.err.println("ALERTA: Estoque de '" + produtoNoEstoque.nomeproduto + 
                                       "' ficou insuficiente (" + produtoNoEstoque.estoque + 
                                       ") para a quantidade pedida (" + item.quantidade + ") no momento do débito!");
                }
            }
        }
        Produto.salvarProdutosAtuais(); 
    }

    /**
     * Verifica se o pedido contém algum item.
     * @return true se o pedido tiver um ou mais itens, false caso contrário.
     */
    public boolean temItens() {
        return this.itensDoPedido != null && !this.itensDoPedido.isEmpty();
    }

    @Override
    public void exibirInformacoes() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        System.out.println("\n--- Informações do Pedido ---");
        System.out.println("ID do pedido: " + id);
        System.out.println("Data do pedido: " + data.format(formatter));
        System.out.println("Status do pedido: " + statuspedido);
        if (temItens()) { // Usando o novo método aqui também para consistência
            System.out.println("Itens do Pedido:");
            for (ItemPedido item : itensDoPedido) {
                System.out.println("- " + item.quantidade + "x " + item.produto.nomeproduto + 
                                   " (R$ " + String.format("%.2f", item.produto.valorproduto) + " cada)");
            }
        }
        System.out.println("Valor total do pedido: R$ " + String.format("%.2f", valorpedido));
        if (usuario != null && usuario.nome != null) {
            System.out.println("Usuário associado: " + usuario.nome);
        } else {
            System.out.println("Usuário associado: Não disponível");
        }
        System.out.println("Observações: " + (observacoes != null && !observacoes.isEmpty() ? observacoes : "Nenhuma"));
    }

    public void adicionarPedido() {
        this.statuspedido = "Aguardando Pagamento";
    }

    public void cancelarPedido() {
        System.out.println("Pedido " + id + " cancelado!");
        this.statuspedido = "Cancelado";
        this.pedidoFuncionou = false; 
    }

    public void finalizarPedidoAposPagamento() { 
        this.statuspedido = "Finalizado";
        System.out.println("Pedido " + id + " finalizado com sucesso após pagamento!");
        confirmarDebitoEstoqueAposPagamento(); 
    }
}