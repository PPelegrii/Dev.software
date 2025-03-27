package RestauranteFit;
/*
* adicionado um arquivo de teste pessoal para não interferir com os outros
*/

public class testePH{
    public static void main(String[] args) {
        // Cria um usuário
        Usuario usuario = new Usuario();
        usuario.Cadastrar();
        usuario.gerarID();
        String id = usuario.pegarID();
        System.out.println("ID do usuário: " + id);

        Produto produto = new Produto(); // ta com erro
        produto.cadastrarProduto();
        produto.listarProdutos();
        produto.verificarEstoque(); // :]

        Pedido pedido = new Pedido(usuario);
        pedido.fazerPedido(); // :] :] :] :] :] :] :] :] :]  funciona :] 
    }
}
