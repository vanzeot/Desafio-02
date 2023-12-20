import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        // Instanciação de objetos/variáveis
        boolean estaEmServico = true;

        Mensageria.bemVindo();
        AdministradorDePessoas.popularListagem();

        while ( estaEmServico ){

            Mensageria.mostrarAcoesDoMenu();
            estaEmServico = Mensageria.apresentarMenuEColetarAcao();

        }
    }
}