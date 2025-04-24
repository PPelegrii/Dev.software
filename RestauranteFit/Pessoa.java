package RestauranteFit;

public abstract class Pessoa {
    protected String nome, email;
    // isso é polimorfismo. método genérico que pode ter várias formas
    public abstract void exibirInformacoes();
}
