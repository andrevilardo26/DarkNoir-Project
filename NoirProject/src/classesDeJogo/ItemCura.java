package classesDeJogo;

public class ItemCura extends ItemConsumivel {

	//MÉTODO QUE CRIA O ITEM COM VALOR DE BONUS DE CURA DELE
	public ItemCura(String itemNome, int bonus) {
		super(itemNome, bonus);
	}

	//MÉTODO QUE SOMA O VALOR DO BONUS DA CURA A VIDA ATUAL DO PERSONAGEM
	public void resultadoUso(Personagem p) {
		p.setVida(p.getVida() + this.bonus);
	}
	
	
}
