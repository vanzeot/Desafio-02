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

    static ArrayList<Pessoa> pessoas;

    public AdministradorDePessoas(){
        pessoas = new ArrayList<>();
    }

    // MÉTODOS DE AÇÕES DO MENU

    public static void imprimirTabela(){
        System.out.println();
        System.out.printf("| %-25s | %-13s | %-25s | %-35s | %-14s | %-12s | %-120s |\n",
                "NOME", "TELEFONE", "EMAIL", "ENDEREÇO", "CPF", "DATA NASC.", "CONTATOS");

        if (!pessoas.isEmpty()){
            for(int i = 0; i < pessoas.size(); i++){
                pessoas.get(i).imprimirDados();
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
        int indice = 0;

        while (!buscaFinalizada){
            System.out.println("Digite o CPF da pessoa:");
            String cpfDigitado = scanner.nextLine();

            boolean ehValido = AuxiliarCpf.cpfEhValido(cpfDigitado);

            if (ehValido){
                cpfFormatado = AuxiliarCpf.formataCpf(cpfDigitado);
                System.out.println("cpfFormatado:" + cpfFormatado);

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

        System.out.println("indice = "+indice);
        return indice;

    }
}
