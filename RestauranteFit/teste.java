package RestauranteFit;
import RestauranteFit.Usuario;
import RestauranteFit.Produto;
import RestauranteFit.Pedido;

public class teste {
    public static void main(String[] args) {
        // Cria um usuário
        Usuario usuario = new Usuario();
        usuario.Cadastrar();
        String id = usuario.pegarID();
        System.out.println("ID do usuário: " + id);

        Produto produto = new Produto();
        produto.cadastrarProduto();
        produto.listarProdutos();
        produto.verificarEstoque();        
    }
}
