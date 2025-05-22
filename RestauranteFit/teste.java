package RestauranteFit;

 public class teste{
     public static void main(String[] args) {
         // Cria um usuário

         // padrão singleton
         Usuario usuario = Usuario.getInstance();
         usuario.Cadastrar();
         String id = usuario.pegarID();
         System.out.println("ID do usuário: " + id);
         usuario.exibirInformacoes();
         
         Produto produto = new Produto();
         produto.cadastrarProduto();
         produto.listarProdutos();
         produto.verificarEstoque(); // :]
         Pedido pedido = new Pedido(usuario);
         pedido.fazerPedido();
        
         // padrão proxy
         PagamentoProxy pagamento = new PagamentoProxy(pedido);
         pagamento.realizarPagamento();
 
         pagamento.exibirDetalhesPagamento();
         usuario.endereco.listarEnderecos();
     }
 }
