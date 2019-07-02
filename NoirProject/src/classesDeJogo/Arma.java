package classesDeJogo;
public enum Arma {

	// CRIANDO E DEFININDO O DANO DE CADA ARMA EQUIPÁVEL 
	CACETETE(15, 30), SOCOINGLES(10, 35), PISTOLA(35, 50), GARRAS(40, 60);

	public int bonus;
	public int bonusEspecial;
   
	// CONSTRUTOR DE ARMA
	Arma(int b, int be) {
		this.bonus = b;
		this.bonusEspecial = be;
	}

}
