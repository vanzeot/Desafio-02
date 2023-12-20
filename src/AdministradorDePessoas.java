import Auxiliares.AuxiliarCpf;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class AdministradorDePessoas {

    /*
    Esta classe serve como a interface entre os dados inputados pelo usuário e o conjunto de pessoas
    representado pelo ArrayList<Pessoa>.
    Aqui existem métodos para capturar informações via terminal e, a partir destas, realizar ações CRUD.
     */

    static ArrayList<Pessoa> pessoas = new ArrayList<>();

    // MÉTODOS DE AÇÕES DO MENU

    public static void imprimirTabela(){

        String formatacaoDasColunasDaTabela = "| %-25s | %-13s | %-25s | %-35s | %-14s | %-12s | %-160s |\n";

        System.out.println();
        System.out.printf(formatacaoDasColunasDaTabela,"NOME", "TELEFONE", "EMAIL", "ENDEREÇO", "CPF", "DATA NASC.", "CONTATOS");

        if (!pessoas.isEmpty()){
            for(int i = 0; i < pessoas.size(); i++){
                Pessoa tempPessoa = pessoas.get(i);
                System.out.printf(formatacaoDasColunasDaTabela, // formatação das colunas
                        tempPessoa.getNome(), //nome
                        tempPessoa.getTelefone(), //telefone
                        tempPessoa.getEmail(), //email
                        tempPessoa.getEndereco(), //endereço
                        tempPessoa.getCpf(), //cpf
                        tempPessoa.getDataDeNascimento(), //data de nascimento
                        tempPessoa.imprimirContatos()
                );
            }
        } else {
            System.out.println("\nNenhuma pessoa foi adicionada à lista.");
        }

    }

    public static void adicionarPessoa(){

        Pessoa novaPessoa = new Pessoa();

        inserirDados(novaPessoa);

        pessoas.add(novaPessoa);

        System.out.println("\nDados incluídos com sucesso.\n");
    }

    public static Pessoa inserirDados(Pessoa pessoa){
        pessoa.selecionarNome()
                .selecionarTelefone()
                .selecionarEmail()
                .selecionarEndereco()
                .selecionarCpf()
                .selecionarDataDeNascimento()
                .adicionarContatos();

        return pessoa;
    }

    public static void editarPessoa(){

        int indice = solicitarCpfParaEncontrarPessoa();

        Pessoa pessoa = pessoas.get(indice);

        System.out.println("A pessoa é o(a): " + pessoa.getNome() +
                "\nCaso naõ deseje alterar algum dado, apenas tecle 'Enter' para pular.");

        pessoa.acoesDeEdicao();

    }

    public static void excluirPessoa(){

        Scanner scanner = new Scanner(System.in);
        int indice = solicitarCpfParaEncontrarPessoa();
        Pessoa pessoa = pessoas.get(indice);

        System.out.println("A pessoa é o(a): " + pessoa.getNome() +
                "\nCaso deseje excluir a pessoa, favor digitar 'confirma': ");
        String confirmacao = scanner.nextLine();

        if (confirmacao.equals("confirma")) {
            pessoas.remove(indice);
            System.out.println("\nDados alterados com sucesso.\n");
        } else {
            System.out.println("\nOperação abortada.\n");
        }

    }


    //=====================
    // MÉTODOS AUXILIARES
    //=====================


    // Lida com a interface entre o usuário e o método de busca por cpf

    private static int solicitarCpfParaEncontrarPessoa() {
        Scanner scanner = new Scanner(System.in);
        boolean buscaFinalizada = false;
        Pessoa resultadoDaBusca = null;
        String cpfFormatado = "";
        int indice = -1;

        while (!buscaFinalizada){
            System.out.println("Digite o CPF da pessoa:");
            String cpfDigitado = scanner.nextLine();

            boolean ehValido = AuxiliarCpf.cpfEhValido(cpfDigitado);

            if (ehValido){
                cpfFormatado = AuxiliarCpf.formataCpf(cpfDigitado);

                indice = getIndicePorCpf(cpfFormatado);
            }

            try {
                resultadoDaBusca = pessoas.get(indice);
                buscaFinalizada = true;
            } catch (Exception e){
                System.out.print("Pessoa não encontrada. Essa é a lista de pessoas:");
                imprimirTabela();
            }

        }

        return indice;
    }

    // Realiza uma varredura no Array até encontrar o que possui o CPF

    public static int getIndicePorCpf(String cpf) {

        boolean naoAchou = true;
        int indice = 0;

        while(naoAchou && indice < pessoas.size()){

            if (Objects.equals( pessoas.get(indice).getCpf(), cpf)){
                naoAchou = false;
            } else {
                indice++;
            }
        }
        if (indice == pessoas.size() || pessoas.isEmpty()) indice = -1;

        System.out.println("indice = " + indice);
        return indice;

    }

    /*
    Método abaixo criado apenas para a listagem não aparecer vazia no início. Mesmo assim,
    está prevista esta situação de lista vazia, em que é mostrada a mensagem "Listagem vazia".
     */

    public static void popularListagem(){

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
        Contato contato1danielle = new Contato(
                "Lúcia",
                "lucia@gmail.com",
                "3455-4265"
        );

        Contato contato2danielle = new Contato(
                "Fátima",
                "fatima@gmail.com",
                "3234-4899"
        );

        ArrayList<Contato> contatosOtavio = new ArrayList<>();
        ArrayList<Contato> contatosDanielle = new ArrayList<>();
        contatosOtavio.add(contato1otavio);
        contatosOtavio.add(contato2otavio);
        contatosDanielle.add(contato1danielle);
        contatosDanielle.add(contato2danielle);

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
