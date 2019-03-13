import java.util.ArrayList;

public class App{
    public static void imprimeDados(String titulo,ArrayList<Cliente> clientes){
        System.out.println(titulo+":");
        for(Cliente cli:clientes){
            System.out.println(cli.toString());
        }
        System.out.println("----------\n");
    }

    public static void main(String args[]) {
        CadastroClientes cc = new CadastroClientes();

        cc.carregaDados("Clientes.csv");
        imprimeDados("Todos clientes",cc.getTodosClientes());
        imprimeDados("Clientes homens",cc.getClientesPorSexo("Male"));
        imprimeDados("Clientes mulheres",cc.getClientesPorSexo("Female"));
    }
}