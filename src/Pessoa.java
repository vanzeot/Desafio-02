import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Auxiliares.AuxiliarCpf.cpfEhValido;
import static Auxiliares.AuxiliarCpf.formataCpf;
import static Auxiliares.AuxiliarData.dataNaoEhValida;

public class Pessoa {

    /*
    Esta classe serve para representar as pessoas que serão incluídas na lista.

    Melhorias possíveis:
    - Herança/polimorfismo para retirar repetição da estrutura de definição/validação de atributos
     */

    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private String cpf;
    private String dataDeNascimento;
    private ArrayList<Contato> contatos = new ArrayList<>();
    
    Scanner scanner = new Scanner(System.in);
    //contatos;

    public Pessoa(String nome, String telefone, String email, String endereco, String cpf, String dataDeNascimento, ArrayList<Contato> contatos) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.cpf = cpf;
        this.dataDeNascimento = dataDeNascimento;
        this.contatos = contatos;
    }

    public Pessoa() {
    }


    //=====================
    // MÉTODOS DE SELEÇÃO
    //=====================

    // CAMPOS SEM VALIDAÇÃO

    public Pessoa selecionarNome(){
        System.out.println("Digite o nome da pessoa: ");
        String input = scanner.nextLine().trim();
        this.nome = lidarComInputEmBranco(input);

        return this;
    }

    public Pessoa selecionarTelefone(){
        System.out.println("Digite o telefone da pessoa: ");
        String input = scanner.nextLine().trim();
        // TODO: ADICIONAR TRATAMENTO/FORMATAÇÃO
        this.telefone = lidarComInputEmBranco(input);

        return this;
    }

    public Pessoa selecionarEndereco(){
        System.out.println("Digite o endereço da pessoa: ");
        String input = scanner.nextLine().trim();
        this.endereco = lidarComInputEmBranco(input);

        return this;
    }

    // CAMPOS COM VALIDAÇÃO

    public Pessoa selecionarEmail(){
        System.out.println("Digite o email da pessoa: ");
        String email = scanner.nextLine().trim();

        if (!(email.isBlank() && this.email != null)){
            while (this.email == null && emailNaoEhValido(email)){
                System.out.println("O email não é válido. Tente novamente: ");
                email = scanner.nextLine().trim();
            }
            this.email = email;
        }

        return this;
    }

    public Pessoa selecionarCpf(){
        System.out.println("Digite o CPF da pessoa: ");
        String cpf = scanner.nextLine().trim();

        while (!cpfEhValido(cpf)){
                System.out.println("O CPF não é válido. Tente novamente: ");
                cpf = scanner.nextLine().trim();

            this.cpf = formataCpf(cpf);
        }

        return this;
    }

    public Pessoa selecionarDataDeNascimento(){

        System.out.println("Digite a data de nascimento (dd/mm/aaaa) da pessoa: ");
        String dataDeNascimento = scanner.nextLine()
                .trim();

        if (!(dataDeNascimento.isBlank() && this.dataDeNascimento != null)){
            while (this.dataDeNascimento == null && dataNaoEhValida(dataDeNascimento)){
                System.out.println("Digite a data de nascimento (dd/mm/aaaa) da pessoa: ");
                dataDeNascimento = scanner.nextLine().trim();
            }
            this.dataDeNascimento = dataDeNascimento;
        }

        return this;
    }

    public void acoesDeEdicao(){

        boolean emServico = true;

        while (emServico){

            Mensageria.mostrarAcoesDeEdicao();
            String acao = scanner.nextLine();

            switch(acao){

                case "1":
                    selecionarNome();
                    break;

                case "2":
                    selecionarTelefone();
                    break;

                case "3":
                    selecionarEmail();
                    break;

                case "4":
                    selecionarEndereco();
                    break;

                case "5":
                    selecionarCpf();
                    break;

                case "6":
                    selecionarDataDeNascimento();
                    break;

                case "7":
                    acoesDeContatos();
                    break;

                case "8":
                    emServico = false;
                    break;

                default:
                    System.out.println("Opção inválida.");

            }
        }
    }

    public void acoesDeContatos(){

        boolean emServico = true;
        int indice;

        while (emServico){

            Mensageria.mostrarAcoesDeContato();
            String acao = scanner.nextLine();

            switch(acao){

                case "1":
                    contatos.add(new Contato().inserirDados());
                    break;

                case "2":
                    indice = AdministradorDeContatos.selecionarContato(contatos);
                    if (indice != -1) {
                        contatos.set(indice, new Contato().inserirDados());
                    }
                    break;

                case "3":
                    if (this.contatos.size() > 2) {
                        indice = AdministradorDeContatos.selecionarContato(contatos);
                        if (indice != -1) {
                            contatos.remove(indice);
                        }
                    }
                    else {
                        System.out.println("Não há como remover, pois deve haver no mínimo 2.");
                    }
                    break;

                case "4":
                    emServico = false;
                    break;

                default:
                    System.out.println("Opção inválida.");

            }
        }
    }

    public Pessoa adicionarContatos(){
        boolean emEdicao = true;
        int i = 0;

        while (emEdicao){

            if (this.contatos.size() >= 2) {
                System.out.println("Digite 'fim' caso não deseje adicionar mais contatos: ");
                String resposta = scanner.nextLine();

                if (resposta.equals("fim") || resposta.equals("Fim") || resposta.equals("FIM")) {
                    break;
                }
            }

            System.out.println("# Contato nº " + (i+1));
            contatos.add(new Contato().inserirDados());
            i++;

        }

        return this;
    }

    public void imprimirDados(){

        // UMA LINHA APENAS VAI FICAR AQUI
        System.out.printf("| %-25s | %-13s | %-25s | %-35s | %-14s | %-12s | %-120s |\n",
                this.nome, this.telefone, this.email, this.endereco, this.cpf, this.dataDeNascimento, imprimirContatos());

    }

    //=====================
    // MÉTODOS AUXILIARES
    //=====================

    private String lidarComInputEmBranco(String input){

        while (input.trim().isBlank()){
            System.out.println("Valor em branco. Digite novamente: ");
            input = scanner.nextLine().trim();
        }

        return input;
    }

    private boolean emailNaoEhValido(String email) {
        email.trim();
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return !matcher.matches();
    }

    public String imprimirContatos(){

        String dados = "";
         for (Contato contato : contatos) {
             dados = dados + contato.getNome() + ", " + contato.getEmail() + ", " + contato.getTelefone() + " - ";
         }
         return dados.substring(0,dados.length()-4);

    }



    //=====================
    //       GETTERS
    //=====================

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public String getDataDeNascimento() {
        return dataDeNascimento;
    }

}