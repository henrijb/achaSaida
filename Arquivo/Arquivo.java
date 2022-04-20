package Arquivo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Arquivo
 * 
 * @author Henrique J Berto <henrijb@gmail.com>
 *
 */
public class Arquivo {

    private BufferedReader arquivoLeitura;

    /**
     * Arquivo
     */
    public Arquivo() {

    }

    /**
     * escreveArquivo cria e escreve em um arquivo
     * 
     * @author Henrique J Berto <henrijb@gmail.com>
     * 
     * @param String nomeArquivo
     * @param String texto
     * @throws IOException
     */
    public void escreveArquivo(String nomeArquivo, String texto) throws IOException {
	try {
	    File f = new File(nomeArquivo);
	    FileWriter fr = new FileWriter(f);
	    PrintWriter out = new PrintWriter(fr);
	    out.write(texto);
	    out.close();
	} catch (IOException e) {
	    throw new IOException("Erro ao escrever arquivo.");
	}
    }

    /**
     * montaDadosArquivo abre e le o arquivo e armazena na memória
     * 
     * @author Henrique J Berto <henrijb@gmail.com>
     * 
     * @return char[][] array
     * @throws IOException
     */
    public char[][] montaDadosArquivo() throws IOException {
	int linha = 0;
	int coluna = 0;
	int i = 0;
	char[][] array = null;

	try {

	    String line;
	    linha = Integer.parseInt(this.arquivoLeitura.readLine().trim());
	    coluna = Integer.parseInt(this.arquivoLeitura.readLine().trim());

	    array = new char[linha][coluna];

	    line = this.arquivoLeitura.readLine();

	    while (line != null) {
		for (int j = 0; j < array[i].length; j++) {
		    array[i][j] = line.charAt(j);
		}
		line = this.arquivoLeitura.readLine();
		i++;
	    }
	} catch (IOException | NullPointerException e) {
	    throw new IOException("Erro ao ler arquivo.");
	}

	return array;
    }

    /**
     * leArquivo armazena no buffer o conteudo do arquivo
     * 
     * @author Henrique J Berto <henrijb@gmail.com>
     * 
     * @param String nomeArquivo
     * @throws FileNotFoundException
     */
    public void leArquivo(String nomeArquivo) throws FileNotFoundException {

	try {
	    this.arquivoLeitura = new BufferedReader(new FileReader(new File(nomeArquivo)));

	} catch (FileNotFoundException e) {
	    throw new FileNotFoundException("Arquivo " + nomeArquivo + " não existe.\n");
	}
    }

    /**
     * fechaLeitura fecha o arquivo que estava sendo lido
     * 
     * @author Henrique J Berto <henrijb@gmail.com>
     */
    public void fechaLeitura() {
	try {
	    this.arquivoLeitura.close();
	} catch (IOException | NullPointerException e) {
	    throw new NullPointerException("Erro ao fechar, arquivo não está aberto.\n");
	}
    }
}
