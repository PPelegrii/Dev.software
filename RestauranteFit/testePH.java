package RestauranteFit;
/*
* adicionado um arquivo de teste pessoal para não interferir com os outros
*/

public class testePH{
    public static void main(String[] args) {
        // Cria um usuário
        Usuario usuario = new Usuario();
        //usuario.Cadastrar();
        usuario.gerarID();
        String id = usuario.pegarID();
        System.out.println("ID do usuário: " + id);

        Produto produto = new Produto();
        produto.listarProdutos();
        produto.verificarEstoque(); // :]

        // Declara e inicializa a variável pedido antes de pagamento
        Pedido pedido = new Pedido(usuario);
        Pagamento pagamento = new Pagamento(pedido);
        pedido.fazerPedido(); // :] :] :] :] :] :] :] :] :]  funciona :]
    }
}
