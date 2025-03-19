package Java;

public class teste {
    public static void main(String[] args) {
        Usuario user = new Usuario();
        user.gerarID();
        String id = user.pegarID();
        System.out.println("ID do usuário: " + id);
    }
}
