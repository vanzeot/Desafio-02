import java.util.Scanner;

public class Mensageria {

    public static boolean apresentarMenuEColetarAcao(){

        Scanner scanner = new Scanner(System.in);
        boolean estaEmServico = true;

        String opcaoSelecionada = scanner.nextLine();


        switch (opcaoSelecionada){

            case "1":
                AdministradorDePessoas.imprimirTabela();
                break;
            case "2":
                AdministradorDePessoas.adicionarPessoa();
                break;
            case "3":
                AdministradorDePessoas.editarPessoa();
                break;
            case "4":
                AdministradorDePessoas.excluirPessoa();
                break;
            case "5":
                estaEmServico = false;
                break;
            default:
                System.out.println("Opção não reconhecida. Por favor, tente de novo...");
        }

        return estaEmServico;
    }

    public static void bemVindo(){
        System.out.println("\n==================================");
        System.out.println("=== DESAFIO 02 - CADASTRO JAVA ===");
        System.out.println("==================================");

    }

    public static void mostrarAcoesDoMenu(){
        System.out.println("\n" +
                "\n1 - Listar pessoas" +
                "\n2 - Incluir pessoa" +
                "\n3 - Editar pessoa" +
                "\n4 - Excluir pessoa" +
                "\n5 - Finalizar" +
        "\n\nInsira o número correspondente a ação desejada:");
    }

    public static void mostrarAcoesDeEdicao(){
        System.out.println(
                "\n1 - Nome" +
                "\n2 - Telefone" +
                "\n3 - Email" +
                "\n4 - Endereço" +
                "\n5 - CPF" +
                "\n6 - Data de nascimento" +
                "\n7 - Contatos" +
                "\n8 - Finalizar" +
                "\n\nInsira o número correspondente a ação desejada:");
    }

    public static void mostrarAcoesDeContato(){
        System.out.println(
                "\n1 - Incluir contato" +
                "\n2 - Editar contato" +
                "\n3 - Excluir contato" +
                "\n4 - Finalizar" +
                "\n\nInsira o número correspondente a ação desejada:");
    }

}
