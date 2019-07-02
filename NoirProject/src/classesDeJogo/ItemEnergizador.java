package classesDeJogo;

public class ItemEnergizador extends ItemConsumivel {

	//METODO QUE CRIA O ITEM ENERGIZADOR
	public ItemEnergizador(String itemNome, int bonus) {
		super(itemNome, bonus);
	}

	//METODO QUE DÁ O RESULTADO DO USO DE ENERGIZADOR AO PERSONAGEM
	public void resultadoUso(Personagem p) {
		p.setEnergia(p.getEnergia() + this.bonus);
	}
	
	
}
