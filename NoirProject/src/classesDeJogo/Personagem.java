package classesDeJogo;
import java.util.Random;

import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class Personagem {

//ATRIBUTOS

	private String nome;        		 // O NOME DO PERSONAGEM
	private int vidaBase;				// A VIDA FIXA DO PERSONAGEM(USADA PARA RESETAR)
	private int vida;                  //QUANTIDADE DE VIDA DO PERSONAGEM (AO CHEGAR A 0 ELE MORRE)
	private int energia;              //QUANTIDADE DE ENERGIA DO PERSONAGEM (AO CHEGAR A 100 PODE USAR SEU ATAQUE ESPECIAL)
	private int tamanhoMochila = 0;  //QUANTIDADE DE ITENS QUE O PERSONAGEM PODE CARREGAR
	private int poderAtaque;        // QUANTIDADE DE DANO QUE O PERSONAGEM DÁ AO USAR ATAQUE
	private int poderEspecial;     // QUANTIDADE DE DANO QUE O PERSONAGEM DÁ AO USAR ATAQUE ESPECIAL
	private Arma arma;            // ARMA QUE SERÁ USADA NAQUELA BATALHA PELO PESONAGEM
	private int provocacao;      // QUANTIDADE DE ENERGIA QUE É RETIRADA DO OPONENTE AO USAR PROVOCAR
	
	
	private ItemConsumivel[] itens;                           // ITEM CONSUMÍVEL QUE O PERSONAGEM TEM
	private String semItem = "VOCÊ NÃO POSSUI ESSE ITEM \n"; // A MENSAGEM DE NÃO TER ITEM

	//CONSTRUTOR DE PERSONAGEM
	Personagem(String nome, int quantItens) {
		this.nome = nome;
		this.itens = new ItemConsumivel[quantItens];
	}

//SETTERS AND GETTERS DOS ATRIBUTOS......................................................................................
	
//Nome.............................................

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	
//Vida ............................................

	public void setVida(int vida) {
		this.vida = vida;
		this.vidaBase = vida;
	}

	public int getVida() {
		return vida;
	}

//energia ..........................................

	public void setEnergia(int energia) {
		this.energia = energia;
	}

	public int getEnergia() {
		return energia;
	}
	
//dano ..............................................

	public void setPoderAtaque(int poderAtaque) {
		this.poderAtaque = poderAtaque;
	}

	public int getPoderAtaque() {
		return poderAtaque;
	}

//danoEspecial ......................................

	public void setPoderEspecial(int poderEspecial) {
		this.poderEspecial = poderEspecial;
	}

	public int getPoderEspecial() {
		return poderEspecial;
	}

//Arma...............................................

	public void setArma(Arma arma) {
		this.arma = arma;
	}

	public Arma getArma() {
		return arma;
	}
	
//Provocacao.........................................
	
	public void setProvocacao(int provocacao) {
		this.provocacao = provocacao;
	}
		
	public int getProvocacao() {
		return provocacao;
	}

//METODOS DO JOGO.........................................................................................................

//Ações .........................................

    //METODO DE ATAQUE NORMAL DO PERSONAGEM, ADICIONA ENERGIA AO ATACANTE E IMPRIME O ATAQUE
	public void atacar() {
		this.energia += 15;
		int num = new Random().nextInt();
		if (num < 0.3) {
			System.out.println(this.getNome() + " CAUSOU UM DANO SUPERFICIAL NO OPONENTE \n");
		}
		else if (num < 0.6) {
			System.out.println(this.getNome() + " CAUSOU UMA FRATURA INTERNA NO OPONENTE \n");
		}
		else {
			System.out.println(this.getNome() + " CAUSOU HEMORRAGIA NO OPONENTE \n");
		}
	}
	//METODO DE ATAQUE ESPECIAL DO PERSONAGEM, CONSOME A ENERGIA DO ATACANTE, IMPRIME O ATAQUE ESPECIAL
	public void atacarEspecial() {
		if (energia >= 100) {
			this.energia -= 80;
			int num = new Random().nextInt();
			if (num < 0.3) {
				System.out.println(this.getNome() + " CAUSOU UMA FRATURA EXPOSTA NO OPONENTE \n");
			}
			else if (num < 0.6) {
				System.out.println(this.getNome() + " FERIL FATALMENTE O OPONENTE \n");
			}
			else {
				System.out.println(this.getNome() + " ATINGIU A CABEÇA DO OPONENTE \n");
			}
		} else {
			System.out.println(this.getNome() + " ESTÁ COM ENERGIA BAIXA! ATAQUE FOI FRACO.");

		}
	}
	
	//MEDODO DE RECEBER O DANO, REDUZINDO A VIDA DO PERSONAGEM E ACRESCENTANDO 5 DE ENERGOA.
	public void tomarDano(int valor) {
		this.vida -= valor;
		this.energia += 5;
	}

	//MEDODO DE RECEBER O DANO ESPECIAL, REDUZINDO BASTANTE A VIDA DO PERSONAGEM E ACRESCENTANDO 10 DE ENERGOA.
	public void tomarDanoEspecial(int valor) {
		this.vida -= valor;
		this.energia += 10;
	}

	//METODO DE RECEBER PROVOCAÇÃO, CONSOME A ENERGIA DO PROVOCADO E IMPRIME A PROVOCAÇÃO
	public void serProvocado(int valor) {
		this.provocacao = valor;
		this.energia -= provocacao;
		int num = new Random().nextInt();
		if (num < 0.3) {
			System.out.println(this.getNome() + " FOI XINGADO \n");
		}
		else if (num < 0.6) {
			System.out.println(this.getNome() + " FOI PROVOCADO \n");
		}
		else {
			System.out.println(this.getNome() + " FOI HUMILHADO \n");
		}
		}

	//MEDODO DE CURA, SETANDO A VIDA DO PERSONAGEM COM O VALOR ATUAL MAIS O DA CURA.
	public void usarCura(Personagem jogador, int valor) {
		jogador.setVida(vida + valor);
		this.energia += 10;
	}

	//METODO PARA MARCAR A UTILIZAÇÃO DE UM ITEM CONSUMÍVEM, DA O EFEITO DO USO, SOMA 5 DE ENERGIA E REDUZ O ITEM DA MOCHILA(ARRAY).
	public String uso(int i) {
			ItemConsumivel temp = itens[i];
			itens[i].resultadoUso(this);
			itens[i] = null;
			this.energia += 5;
			this.tamanhoMochila--;
			return temp.getitemNome();
	}

	//ACRESCENTA UM ITEM A MOCHILA(ARRAY) DO PERSONAGEM
	public void adicionarItem(ItemConsumivel item) {
		for (int i = 0; i < this.itens.length; i++) {
			if (this.itens[i] == null) {
				itens[i] = item;
				return;
			}
		}
	}

//Situações.........................................

	//METODO PARA RESETAR A VIDA DO PERSONAGEM, 
	public void resetVida() {
		this.vida = vidaBase;
	}

    //GERA UM VALOR RANDOMICO ENTRE 2 VALORES.
	public static int valorRandomico(int min, int max) {
		Random nRandomico = new Random();
		int randomNum = nRandomico.nextInt((max - min) + 1) + min;
		return randomNum;
	}
	
	//IMPRIME A MOCHILA COM AS OPÇÕES DE ITENS A SEREM UTILIZADOS 
	public int ImprimirItem() {
			System.out.println("ESCOLHA UM ITEM:");
			for (int i = 0; i < itens.length; i++) {
				if(itens[i] != null) {
					System.out.println(i+1 + "-" + itens[i].getitemNome());
				}
				}
			
			Scanner selector = new Scanner(System.in);
			int  valor = selector.nextInt()-1;
			if(valor>itens.length-1  || valor<0) {
				System.out.println("ESCOLHA INVÁLIDA. DIGITE UM VALOR VÁLIDO");
				return ImprimirItem();
			}
			if(itens[valor] == null) {
				return Integer.MAX_VALUE;
			}
			return valor;
	}
	
	//METODO PARA O A SELEÇÃO E USO DO ITEM EM SÍ, UTILIZA MECANISMOS DE EXCEÇÃO PARA O CASO DE NÃO HAVER O ITEM
	public void escolherItem(int itemEscolha) {
		switch (itemEscolha) {
		case 0:
			try {
				String itemUsado = this.uso(0);
				System.out.println(this.getNome() + " USOU " + itemUsado + "\n");
				System.out.println("ITEM "+ itemUsado + " ACABOU \n");
			} catch (IllegalArgumentException ex) {
				throw new NoSuchElementException(semItem);
			}
			break;
		case 1:
			try {
				String itemUsado = this.uso(1);
				System.out.println(this.getNome() + " USOU " + itemUsado + "\n");
				System.out.println("ITEM "+ itemUsado + " ACABOU \n");
			} catch (IllegalArgumentException ex) {
				throw new NoSuchElementException(semItem);
			}
			break;
		case 2:
			try {
				String itemUsado = this.uso(2);
				System.out.println(this.getNome() + " USOU " + itemUsado + "\n");
				System.out.println("ITEM "+ itemUsado + " ACABOU \n");
			} catch (IllegalArgumentException ex) {
				throw new NoSuchElementException(semItem);
			}
			break;
		}
	}
	public boolean EstaVivo() {
		return (this.vida > 0);
	}

}
