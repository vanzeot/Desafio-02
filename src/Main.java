import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        // Instanciação de objetos/variáveis
        boolean estaEmServico = true;
        new AdministradorDePessoas();

        popularListagem(AdministradorDePessoas.pessoas);


        Mensageria.bemVindo();


        while ( estaEmServico ){

            Mensageria.mostrarAcoesDoMenu();

            estaEmServico = Mensageria.apresentarMenuEColetarAcao();

        }

    }


    /*
    Método abaixo criado apenas para a listagem não aparecer vazia no início. Mesmo assim,
    está prevista esta situação de lista vazia, em que é mostrada a mensagem "Listagem vazia".
     */

    public static void popularListagem(ArrayList<Pessoa> pessoas){

        Contato contato1otavio = new Contato(
                "Elis",
                "elis@gmail.com",
                "3234-4243"
        );

        Contato contato2otavio = new Contato(
                "Antonio",
                "antonio@gmail.com",
                "3234-4243"
        );

        ArrayList<Contato> contatosOtavio = new ArrayList<>();
        ArrayList<Contato> contatosDanielle = new ArrayList<>();
        contatosOtavio.add(contato1otavio);
        contatosOtavio.add(contato2otavio);
        contatosDanielle.add(contato1otavio);
        contatosDanielle.add(contato2otavio);

        Pessoa otavio = new Pessoa(
                "Otávio dos Santos",
                "98888-0141",
                "otavio1@gmail.com",
                "Rua das Pedras, 217, Goiânia",
                "954.706.010-48",
                "11/08/1994",
                contatosOtavio
        );
        Pessoa danielle = new Pessoa(
                "Danielle Ribeiro",
                "99999-0141",
                "danielle@gmail.com",
                "Rua 14, 123, Centro, Goiânia",
                "751.773.540-32",
                "27/12/1993",
                contatosDanielle);

        pessoas.add(otavio);
        pessoas.add(danielle);

    }
}