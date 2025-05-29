package RestauranteFit;

public class teste {
    public static void main(String[] args) {
        // Cria um usuário
        // padrão singleton
        Usuario usuario = Usuario.getInstance();
        usuario.Cadastrar();
        String id = usuario.pegarID();
        System.out.println("ID do usuário: " + id);
        usuario.exibirInformacoes();

        // Cria instância de Produto para usar os métodos
        Produto produto = new Produto();

        // Menu interativo para produtos
        boolean continuar = true;
        java.util.Scanner scn = new java.util.Scanner(System.in);

        while (continuar) {
            System.out.println("\n--- Menu Produto ---");
            System.out.println("1 - Cadastrar Produto");
            System.out.println("2 - Listar Produtos");
            System.out.println("3 - Verificar Estoque");
            System.out.println("4 - Atualizar Produto");
            System.out.println("0 - Sair do Menu Produto");
            System.out.print("Escolha uma opção: ");
            int opcao = scn.nextInt();
            scn.nextLine(); // limpar buffer

            switch (opcao) {
                case 1:
                    produto.cadastrarProduto();
                    break;
                case 2:
                    produto.listarProdutos();
                    break;
                case 3:
                    produto.verificarEstoque();
                    break;
                case 4:
                    // Para atualizar, precisaria escolher qual produto atualizar.
                    // Aqui vamos atualizar o primeiro produto como exemplo:
                    if (!Produto.listaProdutos.isEmpty()) {
                        Produto primeiroProduto = Produto.listaProdutos.get(0);
                        System.out.println("Atualizando produto: " + primeiroProduto.nomeproduto);
                        primeiroProduto.atualizarProduto();
                    } else {
                        System.out.println("Nenhum produto disponível para atualizar.");
                    }
                    break;
                case 0:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }

        // Segue o fluxo do pedido e pagamento
        Pedido pedido = new Pedido(usuario);
        pedido.fazerPedido();

        // padrão proxy
        PagamentoProxy pagamento = new PagamentoProxy(pedido);
        pagamento.realizarPagamento();

        pagamento.exibirDetalhesPagamento();
        usuario.endereco.listarEnderecos();

        // Fecha scanner do Produto para evitar warnings
        produto.closeScanner();
        scn.close();
    }
}
