package classesDeJogo;
import java.util.Random;

public abstract class ItemConsumivel {

	private String itemNome;
	protected int bonus;

	
public ItemConsumivel(String itemNome, int bonus) {
		super();
		this.itemNome = itemNome;
		this.bonus = bonus;
	}



public String getitemNome() {
	return itemNome;
}

//METODOS.................................................................................................................
	public abstract void resultadoUso(Personagem p);

	
	public static int valorRandomico(int min, int max){
	    Random nRandomico = new Random();
	    int randomNum = nRandomico.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
}
