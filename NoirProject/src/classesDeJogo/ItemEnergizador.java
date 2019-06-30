package classesDeJogo;

public class ItemEnergizador extends ItemConsumivel {

	public ItemEnergizador(String itemNome, int bonus) {
		super(itemNome, bonus);
	}


	public void resultadoUso(Personagem p) {
		p.setEnergia(p.getEnergia() + this.bonus);
	}
	
	
}
