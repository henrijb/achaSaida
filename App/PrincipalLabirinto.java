package App;

import java.io.IOException;
import java.util.Scanner;
import Labirinto.Labirinto;

/**
 * PrincipalLabirinto
 * 
 * @author Henrique J Berto <henrijb@gmail.com>
 *
 */
public class PrincipalLabirinto {

    public PrincipalLabirinto() {
	String nomeArquivo;
	Labirinto labirinto = new Labirinto();
	Scanner entrada = new Scanner(System.in);

	try {
	    System.out.println("Insira o nome do arquivo:");
	    nomeArquivo = entrada.nextLine();
	    entrada.close();
	    System.out.printf("Nome do arquivo selecionado: %s%n%n", nomeArquivo);

	    labirinto.saidaLabirinto(labirinto.labirinto(nomeArquivo));

	} catch (IOException | NullPointerException | IllegalArgumentException e) {
	    System.out.printf("%s%n%n", e.getMessage());
	}
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
	new PrincipalLabirinto();

    }
}
