package RestauranteFit;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scnPrincipal = new Scanner(System.in);

        System.out.println("Bem-vindo ao Sistema RestauranteFit!");

        Usuario usuario = Usuario.getInstance();
        if (usuario.pegarID() == null) {
            usuario.Cadastrar();
        }
        usuario.exibirInformacoes();

        Produto produtoManager = new Produto();

        boolean sairDoSistema = false;
        while (!sairDoSistema) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1 - Gerenciar Produtos");
            System.out.println("2 - Fazer Pedido");
            System.out.println("3 - Ver Endereços Cadastrados (Global)");
            System.out.println("4 - Atualizar Cadastro de Usuário");
            System.out.println("0 - Sair do Sistema");
            System.out.print("Escolha uma opção: ");

            int opcaoMenuPrincipal = -1;
            try {
                String inputOpcao = scnPrincipal.nextLine().trim();
                if (inputOpcao.isEmpty()){
                    System.out.println("Opção não pode ser vazia.");
                    continue;
                }
                opcaoMenuPrincipal = Integer.parseInt(inputOpcao);
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Por favor, digite um número.");
                continue;
            }

            switch (opcaoMenuPrincipal) {
                case 1:
                    menuGerenciarProdutos(produtoManager, scnPrincipal);
                    break;
                case 2:
                    Pedido pedido = new Pedido(usuario);
                    pedido.fazerPedido();

                    if (pedido.pedidoFuncionou && pedido.valorpedido > 0) {
                        pedido.exibirInformacoes();

                        System.out.println("\nProsseguir para o pagamento? (s/n)");
                        if(scnPrincipal.nextLine().trim().equalsIgnoreCase("s")){
                            Pagamento pagamentoConcreto = new PagamentoDinheiro(pedido);
                            PagamentoProxy pagamentoProxy = new PagamentoProxy(pagamentoConcreto);

                            System.out.println("--- Iniciando Pagamento via Proxy ---");
                            pagamentoProxy.executarProcessoDePagamento();

                            System.out.println("\n--- Status Final do Pagamento (Detalhes via Proxy) ---");
                            pagamentoProxy.exibirDetalhesPagamento();

                            if(pagamentoProxy.isPagamentoConcluido()){
                                System.out.println("\nObrigado por seu pedido e pagamento!");
                            } else {
                                System.out.println("\nO pagamento não foi concluído ou foi cancelado. O pedido não foi finalizado.");
                            }
                        } else {
                            System.out.println("Pagamento não iniciado. Pedido permanece como 'Aguardando Pagamento'.");
                            pedido.statuspedido = "Aguardando Pagamento (escolha do usuário)";
                        }

                        // AQUI ESTÁ A LINHA CORRIGIDA:
                    } else if (pedido.valorpedido <= 0 && pedido.pedidoFuncionou && pedido.temItens()){
                        System.out.println("Pedido realizado, mas o valor é zero (ex: itens promocionais). Não há pagamento a ser processado.");
                        pedido.finalizarPedidoAposPagamento();
                    }
                    else {
                        System.out.println("Pedido não foi realizado com sucesso, estava vazio ou foi cancelado. Nenhum pagamento a processar.");
                    }
                    break;
                case 3:
                    Endereco.listarEnderecosCadastrados();
                    break;
                case 4:
                    usuario.atualizarCadastro();
                    usuario.exibirInformacoes();
                    break;
                case 0:
                    sairDoSistema = true;
                    System.out.println("Salvando produtos antes de sair...");
                    Produto.salvarProdutosAtuais();
                    System.out.println("Saindo do sistema RestauranteFit...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
        scnPrincipal.close();
    }

    public static void menuGerenciarProdutos(Produto produtoManager, Scanner scn) {
        boolean continuarMenuProduto = true;
        while (continuarMenuProduto) {
            System.out.println("\n--- MENU GERENCIAR PRODUTOS ---");
            System.out.println("1 - Cadastrar Novo Produto");
            System.out.println("2 - Listar Todos os Produtos");
            System.out.println("3 - Atualizar Produto Existente");
            System.out.println("4 - Verificar Estoque de Produto");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            int opcaoProduto = -1;
            try {
                String inputOpcao = scn.nextLine().trim();
                if (inputOpcao.isEmpty()){
                    System.out.println("Opção não pode ser vazia.");
                    continue;
                }
                opcaoProduto = Integer.parseInt(inputOpcao);
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Por favor, digite um número.");
                continue;
            }

            switch (opcaoProduto) {
                case 1:
                    produtoManager.cadastrarProduto();
                    break;
                case 2:
                    produtoManager.listarProdutos();
                    break;
                case 3:
                    produtoManager.atualizarProduto();
                    break;
                case 4:
                    produtoManager.verificarEstoque();
                    break;
                case 0:
                    continuarMenuProduto = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}