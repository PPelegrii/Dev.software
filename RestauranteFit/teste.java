package RestauranteFit;
/* 
 * blah blah blah teste teste não compila blah
 */

public class teste {
    public static void main(String[] args) {
        // Cria um usuário
        Usuario usuario = new Usuario();
        //usuario.Cadastrar();
        //usuario.gerarID();
        //String id = usuario.pegarID();
        //System.out.println("ID do usuário: " + id);

        //Produto produto = new Produto();
        //produto.cadastrarProduto();
        //produto.listarProdutos();
        //produto.verificarEstoque(); // :]

        Pedido pedido = new Pedido(usuario);
        usuario.fazerPedido();
        pedido.calcularTotal();
    }
}
