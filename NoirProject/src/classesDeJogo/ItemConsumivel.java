package classesDeJogo;

import java.util.Random;

public abstract class ItemConsumivel {

	private String itemNome;
	protected int bonus;

	// MÉTODO ABSTRATO PARA O CRIAÇÃO DE ITEM CONSUMIVEL E O SEU VALOR DE BONUS AO PERSONAGEM
	public ItemConsumivel(String itemNome, int bonus) {
		super();
		this.itemNome = itemNome;
		this.bonus = bonus;
	}

	//METODO QUE RETORNA O NOME DO ITEM
	public String getitemNome() {
		return itemNome;
	}

	//METODOS QUE DA O RESULTADO DO USO AO PERSONAGEM
	public abstract void resultadoUso(Personagem p);
	
	//METODO QUE GERA UM VALOR RANDOMICO ENTRE DOIS NUMEROS 
	public static int valorRandomico(int min, int max) {
		Random nRandomico = new Random();
		int randomNum = nRandomico.nextInt((max - min) + 1) + min;
		return randomNum;
	}
}
