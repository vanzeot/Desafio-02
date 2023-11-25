import java.util.ArrayList;
import java.util.Scanner;

public class AdministradorDeContatos {

    static ArrayList<Contato> contatos;

    public AdministradorDeContatos(){
        contatos = new ArrayList<>();
    }

    public static int selecionarContato(ArrayList<Contato> contatos){

        boolean loop = true;
        int indice = 0;

        while (loop){

            for (int i = 0; i < contatos.size(); i++) {
                System.out.println((i+1) + " - " + contatos.get(i).getNome());
            }

            System.out.println("Insira o número correspondente ao contato desejado:");
            indice = Integer.parseInt(
                    new Scanner(System.in).nextLine()
            ) - 1 ; // Remove 1 pois array começa na posição 0

            // Busca contato a partir do valor inserido
            try {
                contatos.get(indice);
                loop = false;
            } catch (Exception e){
                System.out.println("Valor inválido. ");
                indice = -1;
            }
        }

        return indice;
    }
}
