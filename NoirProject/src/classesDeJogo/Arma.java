package classesDeJogo;
public enum Arma {

	CACETETE(15, 30), SOCOINGLES(10, 35), PISTOLA(35, 50), METRALHADORA(40, 60);

	public int bonus;
	public int bonusEspecial;

	Arma(int b, int be) {
		this.bonus = b;
		this.bonusEspecial = be;
	}

}
