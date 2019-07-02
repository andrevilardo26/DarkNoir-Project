package classesDeJogo;

import java.util.Random;
import java.util.Scanner;

public class Batalha extends Dialogo {
	
	//HÁ 2 CAMINHOS POSSÍVEIS NA BATALHA, O CAMINHO COM ÍNDICE 0 CONTINUA O JOGO NORMALMENTE
	//O CAMINHO COM ÍNDICE 1 LEVA AO FIM DO JOGO
	 
	//ATRIBUTOS DA CLASSE BATALHA
	Personagem inimigo, jogador;
	
	//CONSTRUTOR DA BATALHA
	Batalha(String arquivo, String valorDeOpcao, Personagem inimigo, Personagem jogador) {
		super(arquivo, valorDeOpcao);
		this.inimigo = inimigo;
		this.jogador = jogador;
		
	}
	
	
	
	//EXECUÇÃO DO LOOP DE BATALHA
	public void executa() {
		int rodada = 1, opcao;
		Scanner teclado = new Scanner(System.in);
		Random random = new Random();
		do {
			System.out.println("\n RODADA " + rodada);
			imprime();
			opcao = teclado.nextInt();
			try {
				acao(jogador, inimigo, opcao);
				acao(inimigo, jogador, 1+random.nextInt(3));
				rodada++;
			} catch (IllegalArgumentException ex) {
				System.out.println(ex.getMessage());
			}
		} while (!(jogador.getVida() <= 0 || inimigo.getVida() <= 0));
		imprime();
		if (jogador.getVida() <= 0)
			this.caminhos.set(0, this.caminhos.get(1));
		jogador.resetVida();
		inimigo.resetVida();
	}
	
	//IMPREÇÃO DAS BARRAS DE ATRIBUTOS DO PERSONAGEM
	public void imprime() {
		System.out.println("==========================================");
		System.out.println(jogador.getNome() + ":       VIDA:" + jogador.getVida() + " / ENERGIA:" + jogador.getEnergia() + "     ");
		System.out.println(inimigo.getNome() + ":     VIDA:" + inimigo.getVida() + " / ENERGIA:" + inimigo.getEnergia() + "     ");
		System.out.println("==========================================");
		System.out.println("DIGITE: (1) ATAQUE (2) ATAQUE ESPECIAL (3) PROVOCAR  (4) USAR ITEM \n");
	}
	
	// AÇÕES QUE PODEM SER USADAS PELO PERSONAGEM DURANTE O LOOP DE BATALHA
	public void acao(Personagem atacante, Personagem vitima, int opcao) {
		if (opcao < 1 || opcao > 4)
			throw new IllegalArgumentException ("OPÇÃO INVÁLIDA \n");
			switch(opcao) {
			case 1:
				atacante.atacar();
				vitima.tomarDano(atacante.getPoderAtaque());
				
				break;
			case 2:
				atacante.atacarEspecial();
				vitima.tomarDanoEspecial(atacante.getPoderEspecial());
				break;
			case 3:
				if (vitima.getPoderAtaque() <= 0) {
					System.out.println(" \n NÃO É POSSÍVEL PROVOCAR, " + atacante.getNome() + " ERROU ! \n");
					break;
				}
					else{
						vitima.serProvocado(atacante.getProvocacao());
					}
				break;
			case 4:
				int valor = atacante.ImprimirItem();
				if(valor>-1) {
					if(valor == Integer.MAX_VALUE) {
						System.out.println("NÃO EXISTEM MAIS ITENS");
						break;}
					atacante.escolherItem(valor);
				}
				
				break;
			}
		if  (atacante.EstaVivo()) {
			
		}
	}
	
	
	
}
