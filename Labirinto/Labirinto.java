package Labirinto;

import java.io.IOException;
import Arquivo.Arquivo;

/**
 * Labirinto
 * 
 * @author Henrique J Berto <henrijb@gmail.com>
 *
 */
public class Labirinto {

    private char[][] labirinto;
    private char parede;
    private char fim;
    private char caminho;
    private char visitado;
    private int caminhoH;
    private int caminhoV;

    /**
     * Labirinto
     * 
     * @author Henrique J Berto <henrijb@gmail.com>
     */
    public Labirinto() {
	this.setCaminho(' ');
	this.setFim('D');
	this.setParede('X');
	this.setVisitado('#');
    }

    /**
     * getLabirinto retorna o labirinto
     * 
     * @author Henrique J Berto <henrijb@gmail.com>
     * @return char labirinto
     */
    public char[][] getLabirinto() {
	return labirinto;
    }

    /**
     * setLabirinto define o labirinto
     * 
     * @author Henrique J Berto <henrijb@gmail.com>
     * @param char labirinto
     */
    public void setLabirinto(char[][] labirinto) {
	this.labirinto = labirinto;
    }

    /**
     * carregaLabirinto grava o labirinto na memória
     * 
     * @author Henrique J Berto <henrijb@gmail.com>
     * 
     * @param String fileName
     * @return Charset labirinto
     * @throws IOException
     */
    public char[][] carregaLabirinto(String fileName) throws IOException {

	char[][] mapa;
	Arquivo arquivo = new Arquivo();
	arquivo.leArquivo(fileName);
	mapa = arquivo.montaDadosArquivo();
	arquivo.fechaLeitura();

	if (mapa == null)
	    throw new IOException("Arquivo não encontrado");

	return mapa;
    }

    /**
     * saidaLabirinto retorna no arquivo se existe ou não saída para o labirinto
     * 
     * @author Henrique J Berto <henrijb@gmail.com>
     * 
     * @param boolean resultado
     * @return void
     * @throws IOException
     */
    public void saidaLabirinto(boolean resultado) throws IOException {

	String retorno = "Existe um caminho para o labirinto";

	if (!resultado) {
	    retorno = "Não existe um caminho para o labirinto";
	}

	Arquivo arquivo = new Arquivo();
	arquivo.escreveArquivo("saidaLabirinto.txt", retorno);
    }

    /**
     * labirinto valida e inicializa o labirinto na posição (0,0)
     * 
     * @author Henrique J Berto <henrijb@gmail.com>
     * 
     * @param String labirinto
     * @return Boolean
     * @throws IOException
     */
    public Boolean labirinto(String labirinto) throws IOException {

	if (labirinto == null)
	    throw new IllegalArgumentException("Erro, labirinto está vazio.");

	return this.labirinto(this.carregaLabirinto(labirinto), 0, 0);
    }

    /**
     * labirinto percorre o labirinto
     * 
     * @author Henrique J Berto <henrijb@gmail.com>
     * 
     * @param char[][] labirintoMapa
     * @param int      linha
     * @param int      coluna
     * @return boolean
     */
    private Boolean labirinto(char[][] labirintoMapa, int linha, int coluna) {

	boolean retorno = false;

	this.visitado(labirintoMapa, linha, coluna);
	this.mostraMapa(labirintoMapa);

	retorno = this.isFim(labirintoMapa, linha, coluna);

	if (labirintoMapa.length <= linha)
	    retorno = false;

	if (labirintoMapa[linha].length <= coluna)
	    retorno = false;

	if (labirintoMapa.length - 1 == linha)
	    this.caminhoH = -1;
	else if (linha == 0)
	    this.caminhoH = 1;

	if (labirintoMapa[linha].length - 1 == coluna)
	    this.caminhoV = -1;
	else if (coluna == 0)
	    this.caminhoV = 1;

	if (this.movimentoValido(labirintoMapa, linha, coluna + this.caminhoV))
	    retorno = this.labirinto(labirintoMapa, linha, coluna + this.caminhoV);

	else if (this.movimentoValido(labirintoMapa, linha + this.caminhoH, coluna))
	    retorno = this.labirinto(labirintoMapa, linha + this.caminhoH, coluna);

	return retorno;
    }

    /**
     * movimentoValido verifica se o movimento é permitido para a posição informada
     * 
     * @author Henrique J Berto <henrijb@gmail.com>
     * 
     * @param char[][] labirintoMapa
     * @param int      linha
     * @param coluna   coluna
     * @return booelan
     */
    private boolean movimentoValido(char[][] labirintoMapa, int linha, int coluna) {

	if (labirintoMapa.length > linha && labirintoMapa[linha].length > coluna) {

	    if (isParede(labirintoMapa[linha][coluna]))
		return false;

	    else if (isCaminho(labirintoMapa[linha][coluna]))
		return true;
	}

	return false;
    }

    /**
     * setParede define qual o caractere que será usado para representar a parede
     * 
     * @author Henrique J Berto <henrijb@gmail.com>
     * 
     * @param char parede
     */
    private void setParede(char parede) {
	this.parede = parede;
    }

    /**
     * getParede retorna o campo referente a parede
     * 
     * @author Henrique J Berto <henrijb@gmail.com>
     * 
     * @return char parede
     */
    private char getParede() {
	return this.parede;
    }

    /**
     * isParede Verifica se a posição é uma parede
     * 
     * @author Henrique J Berto <henrijb@gmail.com>
     * 
     * @param char caractere
     * @return boolean
     */
    private boolean isParede(char caractere) {
	return Character.toLowerCase(this.getParede()) == Character.toLowerCase(caractere);
    }

    /**
     * setCaminho define qual o caracter irá ser usado para definir o caminho
     * 
     * @author Henrique J Berto <henrijb@gmail.com>
     * 
     * @param char caminho
     */
    private void setCaminho(char caminho) {
	this.caminho = caminho;
    }

    /**
     * getCaminho retorna o caracter referente ao caminho
     * 
     * @author Henrique J Berto <henrijb@gmail.com>
     * 
     * @return char caminho
     */
    private char getCaminho() {
	return this.caminho;
    }

    /**
     * isCaminho verifica se é um caminho válido
     * 
     * @author Henrique J Berto <henrijb@gmail.com>
     * 
     * @param char caractere
     * @return boolean
     */
    private boolean isCaminho(char caractere) {
	return Character.toLowerCase(this.getCaminho()) == Character.toLowerCase(caractere);
    }

    /**
     * setFim define qual o caracter irá ser o fim do labirinto
     * 
     * @author Henrique J Berto <henrijb@gmail.com>
     * 
     * @param char fim
     */
    private void setFim(char fim) {
	this.fim = fim;
    }

    /**
     * getFim retorna o caracter definido como o fim
     * 
     * @author Henrique J Berto <henrijb@gmail.com>
     * 
     * @return char fim
     */
    private char getFim() {
	return this.fim;
    }

    /**
     * isFim Verifica se em alguma das posições se encontra o caracter definido como
     * fim
     * 
     * @author Henrique J Berto <henrijb@gmail.com>
     * 
     * @param char labirintoMapa
     * @param int  linha
     * @param int  coluna
     * 
     * @return bollean
     */
    private boolean isFim(char[][] labirintoMapa, int linha, int coluna) {

	if (this.isFim(labirintoMapa[linha][coluna]))
	    return true;

	if (linha + 1 < labirintoMapa.length)
	    if (this.isFim(labirintoMapa[linha + 1][coluna]))
		return true;

	if (coluna + 1 < labirintoMapa[linha].length)
	    if (this.isFim(labirintoMapa[linha][coluna + 1]))
		return true;

	if (linha > 0 && linha - 1 < labirintoMapa.length)
	    if (this.isFim(labirintoMapa[linha - 1][coluna]))
		return true;

	if (coluna > 0 && coluna - 1 < labirintoMapa[linha].length)
	    if (this.isFim(labirintoMapa[linha][coluna - 1]))
		return true;

	return false;
    }

    /**
     * isFim verifica se achou o caracter correspondente o fim
     * 
     * @author Henrique J Berto <henrijb@gmail.com>
     * 
     * @param char caractere
     * @return boolean
     */
    private boolean isFim(char caractere) {
	return Character.toLowerCase(this.getFim()) == Character.toLowerCase(caractere);
    }

    /**
     * setVisitado define qual o caracter vai ser utilizado para marcar o campo como
     * visitado
     * 
     * @author Henrique J Berto <henrijb@gmail.com>
     * 
     * @param char visitado
     */
    private void setVisitado(char visitado) {
	this.visitado = visitado;
    }

    /**
     * getVisitado retorna qual o caracter escolhido para mostrar qual o campo foi
     * visitado
     * 
     * @author Henrique J Berto <henrijb@gmail.com>
     * 
     * @return char visitado
     */
    private char getVisitado() {
	return this.visitado;
    }

    /**
     * visitado marca onde foi visitado
     * 
     * @author Henrique J Berto <henrijb@gmail.com>
     * 
     * @param char[][] mapa
     * @param int      linha
     * @param int      coluna
     * @return char[][] mapa
     */
    private char[][] visitado(char[][] mapa, int linha, int coluna) throws NullPointerException {

	mapa[linha][coluna] = this.getVisitado();

	return mapa;
    }

    /**
     * mostrMapa Constroi o mapa na tela com a posição onde está
     * 
     * @author Henrique J Berto <henrijb@gmail.com>
     * 
     * @param char[][] mapa
     */
    private void mostraMapa(char[][] mapa) {

	for (char[] linha : mapa) {
	    for (char coluna : linha)
		System.out.printf("%s", coluna);

	    System.out.printf("%n");
	}
	System.out.printf("%n");
	System.out.printf("%n");

    }
}
