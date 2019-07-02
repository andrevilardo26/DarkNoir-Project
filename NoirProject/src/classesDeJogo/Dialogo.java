package classesDeJogo;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//CRIAÇÃO DA OPÇÃO DO DIÁLOGO ESCOLHIDO (CAMINHO DE DIÁLOGOS), APRESENTAÇÃO DOS TEXTOS E O ARRAYLIST CONTENDO OS TEXTOS
//E CADA ARQUIVO.TXT QUE É UM CAMINHO DE DIÁLOGO

public class Dialogo {
	
	//ATRIBUTOS DE DIALOGO
	protected String valorDeOpcao;
	protected String texto;
	protected ArrayList<Dialogo> caminhos = new ArrayList<>();

	//CONSTRUTOR DE DIALOGO (IMPORTA E LE O ARQUIVO .TXT EXTERNO)
	Dialogo(String arquivo, String valorDeOpcao) {
		try {
			File file = new File(arquivo);
			Scanner leitor = new Scanner(file);
			this.texto = "";
			while (leitor.hasNextLine()) {
				
				this.texto += leitor.nextLine();
				this.texto += "\n";
			}
		} catch (FileNotFoundException e) {
			System.out.println("OCORREU UM ERRO!");
			e.printStackTrace();
		}
		this.valorDeOpcao = valorDeOpcao;
	}

	//MENSAGEM PARA INICIALIZAÇÃO DO JOGO
	public void iniciarJogo() {
		System.out.println("\n (ESSE JOGO UTILIZA CARACTERES UTF-8 EM SUA COMPOSIÇÃO, CERTIFIQUE-SE DE HABILITAR UTF-8 EM SUA JRE ANTES DE INICIAR) \n \n  \n"
				+ "                                     -- INICIAR O JOGO -- \n"
				+ "                                           APERTE (1)  ");
	}
	
	//EXECUTAA IMPREÇÃO
	public void executa() {
		imprime();
	}
	
	//IMPRIME O TEXTO
	public void imprime() {
		System.out.println(texto);
		int i = 1;
		for (Dialogo d : caminhos) {
			System.out.print("("+i+") " + d.valorDeOpcao + "\t");
			i++;
		}
	}
	
	//ADICIONA O CAMINHO SEGUINTE AO TEXTO ATUAL
	public void adicionarCaminho(Dialogo d) {
		this.caminhos.add(d);
	}
	
	//LE O CAMINHO QUE FOI ADICIONADO
	public Dialogo getCaminho(int i) {
		if (i >= this.caminhos.size() || i < 0)
			throw new IllegalArgumentException ("OPÇÃO INVÁLIDA");
		return this.caminhos.get(i);
	}
	
	//RETORNA O VALOR DE OPÇÃO DO CAMINHO
	public String getOpcao() {
		return this.valorDeOpcao;
	}

}
