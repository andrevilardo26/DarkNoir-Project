package classesDeJogo;
public enum Arma {

	CACETETE(15, 33), ESPADA(25, 55), PISTOLA(30, 40), FUZIL(40, 65);

	public int bonus;
	public int bonusEspecial;

	Arma(int b, int be) {
		this.bonus = b;
		this.bonusEspecial = be;
	}

}
