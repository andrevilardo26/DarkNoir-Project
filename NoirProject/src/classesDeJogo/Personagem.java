package classesDeJogo;

import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class Personagem {

//ATRIBUTOS

	private int vida; // Quantidade de Vida do personagem (Ao chegar a 0 o personagem morre)
	private int energia; // Quantidade de energia do personagem (Ao chegar a 100 o personagem pode usar
	private int tamanhoMochila = 0;// sua habilidade especial)
	private int poderAtaque; // Quantidade de dano causado ao atacar o inimigo
	private int poderEspecial; // Quantidade de dano causado ao usar a habilidade especial no inimigo
	private String nome; // O nome do personagem
	private ItemConsumivel[] itens; // ITEM CONSUMIVEL
	private Arma arma; // ARMAS
	private int provocacao;
	
	
	private String semItem = "VOCÊ NÃO POSSUI ESSE ITEM \n";

	Personagem(String nome, int quantItens) {
		this.nome = nome;
		this.itens = new ItemConsumivel[quantItens];
	}

//SETTERS AND GETTERS DOS ATRIBUTOS......................................................................................

//provocacao
	
	public void setProvocacao(int provocacao) {
		this.provocacao = provocacao;
	}
	
	public int getProvocacao() {
		return provocacao;
	}
	
//nome.............................................

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

//vida ............................................

	public void setVida(int vida) {
		this.vida = vida;
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

//arma.............................................

	public void setArma(Arma arma) {
		this.arma = arma;
	}

	public Arma getArma() {
		return arma;
	}

//METODOS DO JOGO.........................................................................................................

//Ações .........................................

	public void atacar() {
		this.energia += 15;
		System.out.println(this.getNome() + " USOU ATAQUE \n");
	}

	public void atacarEspecial() {
		if (energia >= 100) {
			this.energia -= 80;
			System.out.println(this.getNome() + " USOU ATAQUE ESPECIAL \n");
		} else {
			System.out.println(this.getNome() + " ESTÁ COM ENERGIA BAIXA! ATAQUE FOI FRACO.");

		}
	}

	public void serProvocado(int valor) {
		this.provocacao = valor;
		this.energia -= provocacao;
		System.out.println(this.getNome() + " FOI PROVOCADO \n");
	}

	public void usarCura(Personagem jogador, int valor) {
		jogador.setVida(vida + valor);
		this.energia += 10;
	}

	public String uso(int i) {
			ItemConsumivel temp = itens[i];
			itens[i].resultadoUso(this);
			itens[i] = null;
			this.energia += 5;
			this.tamanhoMochila--;
			return temp.getitemNome();
	}

	public void adicionarItem(ItemConsumivel item) {
		for (int i = 0; i < this.itens.length; i++) {
			if (this.itens[i] == null) {
				itens[i] = item;
				return;
			}
		}
	}

//Situações.........................................

	public void tomarDano(int valor) {
		this.vida -= valor;
		this.energia += 5;
	}

	public void tomarDanoEspecial(int valor) {
		this.vida -= valor;
		this.energia += 10;
	}

	public static int valorRandomico(int min, int max) {
		Random nRandomico = new Random();
		int randomNum = nRandomico.nextInt((max - min) + 1) + min;
		return randomNum;
	}
	
//METODOS DA BATALHA.............................
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
