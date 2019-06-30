package classesDeJogo;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Dialogo {
	protected String valorDeOpcao;
	protected String texto;
	protected ArrayList<Dialogo> caminhos = new ArrayList<>();

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
	
	public void executa() {
		imprime();
	}

	public void iniciarJogo() {
		System.out.println("\n (ESSE JOGO UTILIZA CARACTERES UTF-8 EM SUA COMPOSIÇÃO, CERTIFIQUE-SE DE HABILITAR UTF-8 EM SUA JRE ANTES DE INICIAR) \n \n  \n"
				+ "                                     -- INICIAR O JOGO -- \n"
				+ "                                           APERTE (1)  ");
	}
	
	public void imprime() {
		System.out.println(texto);
		int i = 1;
		for (Dialogo d : caminhos) {
			System.out.print("("+i+") " + d.valorDeOpcao + "\t");
			i++;
		}
	}
	
	public void adicionarCaminho(Dialogo d) {
		this.caminhos.add(d);
	}
	
	public Dialogo getCaminho(int i) {
		if (i >= this.caminhos.size() || i < 0)
			throw new IllegalArgumentException ("OP��");
		return this.caminhos.get(i);
	}
	
	public String getOpcao() {
		return this.valorDeOpcao;
	}

}
