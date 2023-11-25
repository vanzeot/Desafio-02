import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contato {

    private String nome;
    private String email;
    private String telefone;

    public Contato(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public Contato() {

    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    Scanner scanner = new Scanner(System.in);

    public Contato selecionarNome(){
        System.out.println("Digite o nome da pessoa: ");
        String nome = scanner.nextLine().trim();

        if (nome.isBlank()){
            while (this.nome == null){
                System.out.println("Nome em branco. Digite o nome da pessoa: ");
                nome = scanner.nextLine().trim();
            }
        } else {
            this.nome = nome;
        }

        return this;
    }

    public Contato selecionarEmail(){
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

    public Contato selecionarTelefone(){
        System.out.println("Digite o telefone da pessoa: ");
        String telefone = scanner.nextLine().trim();
        // TODO: ADICIONAR TRATAMENTO/FORMATAÇÃO

        if (telefone.isBlank()){
            while (this.telefone == null){
                System.out.println("Telefone em branco. Digite o telefone da pessoa: ");
                telefone = scanner.nextLine().trim();
            }
        } else {
            this.telefone = telefone;
        }

        return this;
    }

    public Contato inserirDados() {
        this.selecionarNome().selecionarEmail().selecionarTelefone();
        return this;
    }

    private boolean emailNaoEhValido(String email) {
        email.trim();
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return !matcher.matches();
    }

}
