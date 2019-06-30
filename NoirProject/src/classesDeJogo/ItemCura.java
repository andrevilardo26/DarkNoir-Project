package classesDeJogo;

public class ItemCura extends ItemConsumivel {

	
	public ItemCura(String itemNome, int bonus) {
		super(itemNome, bonus);
	}

	public void resultadoUso(Personagem p) {
		p.setVida(p.getVida() + this.bonus);
	}
	
	
}
