package classesDeJogo;
import java.util.Scanner;

public class Principal {

	private Dialogo corrente;
	private Dialogo fimDeJogo = new Dialogo("src/textos/fimDeJogo.txt", "MORRER");
	private Personagem detetive, policial, misterioso, gangster;
	private ItemConsumivel aspirina, superAspirina, adrenalina;

	Principal() {
		
		criandoObjetos();
		
		Dialogo introducao = new Dialogo("src/textos/introducao.txt", "");
		Dialogo atendeuCampainha = new Dialogo("src/textos/atendeuCampainha.txt", " ATENDER A CAMPAINHA");
		Dialogo naoAtendeu = new Dialogo("src/textos/naoAtendeu.txt", " IGNORAR E DESCANSAR");
		Dialogo escolha0Op1 = new Dialogo("src/textos/escolha0Op1.txt", "ACEITAR CONTRATO");
		Dialogo escolha0Op2 = new Dialogo("src/textos/escolha0Op2.txt", "RECUSAR CONTRATO\n");
		Dialogo cenaDoCrime = new Dialogo("src/textos/cenaDoCrime.txt", "CONTINUAR");
		Dialogo falarGarota = new Dialogo("src/textos/falarGarota.txt", "FALAR COM GAROTA");
		Dialogo falarPolicial = new Dialogo("src/textos/falarPolicial.txt", "FALAR COM POLICIAL");
		Dialogo irMotel = new Dialogo("src/textos/irMotel.txt", "ACEITAR PROPOSTA");
		Dialogo contraProposta = new Dialogo("src/textos/contraProposta.txt", "ACEITAR PROPOSTA");
		Dialogo enfrentarPolicial = new Dialogo("src/textos/enfrentarPolicial.txt", "BRIGAR COM POLICIAL");
		Dialogo subornarPolicial = new Dialogo("src/textos/subornarPolicial.txt", "SUBORNAR POLICIAL");
		Dialogo acharProva = new Dialogo("src/textos/acharProva.txt", "PEGAR PROVA DO CRIME");
		
		Batalha batalha1 = new Batalha("src/textos/batalha1.txt", "PARA INICIAR BATALHA \n", policial, detetive);
		
		this.corrente = introducao;
		introducao.adicionarCaminho(atendeuCampainha);
		introducao.adicionarCaminho(naoAtendeu);		
			
		atendeuCampainha.adicionarCaminho(escolha0Op1);
		atendeuCampainha.adicionarCaminho(escolha0Op2);
		naoAtendeu.adicionarCaminho(escolha0Op1);
		naoAtendeu.adicionarCaminho(escolha0Op2);
		
		escolha0Op2.adicionarCaminho(this.fimDeJogo);
		
		escolha0Op1.adicionarCaminho(cenaDoCrime);

		cenaDoCrime.adicionarCaminho(falarGarota);
		cenaDoCrime.adicionarCaminho(falarPolicial);
		
		falarPolicial.adicionarCaminho(enfrentarPolicial);
		enfrentarPolicial.adicionarCaminho(batalha1);
		falarPolicial.adicionarCaminho(subornarPolicial);
		subornarPolicial.adicionarCaminho(acharProva);
        
		
		falarGarota.adicionarCaminho(irMotel);
		falarGarota.adicionarCaminho(contraProposta);
		
		batalha1.adicionarCaminho(acharProva);
		batalha1.adicionarCaminho(this.fimDeJogo);
		
		
	}

	public static void main(String[] args) {
		Principal principal = new Principal();
		Scanner entrada = new Scanner(System.in);
		int opcao = 0;
		principal.corrente.iniciarJogo();
		opcao = entrada.nextInt();

		do {
			principal.corrente.executa();
			if (principal.corrente instanceof Batalha) {
				principal.corrente = principal.corrente.getCaminho(0);
			} else {
				opcao = entrada.nextInt();
				try {
					principal.corrente = principal.corrente.getCaminho(opcao - 1);
				} catch (IllegalArgumentException ex) {
					System.out.println(ex.getMessage());
				}
			}
		} while (principal.corrente != principal.fimDeJogo);
		principal.corrente.executa();;
		entrada.close();
	}
	
	
	public void criandoObjetos() {
		
		aspirina = new ItemCura(" ASPIRINA ", ItemConsumivel.valorRandomico(30, 66));
		superAspirina = new ItemCura(" SUPER ASPIRINA ", ItemConsumivel.valorRandomico(70, 100));
		adrenalina = new ItemEnergizador("ADRENALINA", ItemConsumivel.valorRandomico(60, 100));
		
		detetive = new Personagem("DETETIVE'D'", 4);
		policial = new Personagem("CABO AMARAL", 2);
		gangster = new Personagem("MARRETA", 1);
		misterioso = new Personagem("BALTHAZAR", 4);
		
		detetive.setVida(400);
		detetive.setEnergia(50);
		detetive.setPoderAtaque(Personagem.valorRandomico(26, 40));
		detetive.setPoderEspecial(Personagem.valorRandomico(36, 66));
		detetive.setArma(Arma.PISTOLA);
		detetive.setProvocacao(detetive.getEnergia()/3);
		detetive.adicionarItem(superAspirina);
		detetive.adicionarItem(adrenalina);

		policial.setVida(380);
		policial.setEnergia(80);
		policial.setPoderAtaque(Personagem.valorRandomico(20, 30));
		policial.setPoderEspecial(Personagem.valorRandomico(28, 50));
		policial.setArma(Arma.CACETETE);
		policial.setProvocacao(policial.getEnergia()/2);
		
		gangster.setVida(600);
		gangster.setEnergia(50);
		gangster.setPoderAtaque(Personagem.valorRandomico(28, 40));
		gangster.setPoderEspecial(Personagem.valorRandomico(35, 55));
		gangster.setArma(Arma.SOCOINGLES);
		gangster.setProvocacao(policial.getEnergia()/2);
		
		misterioso.setVida(500);
		misterioso.setEnergia(100);
		misterioso.setPoderAtaque(Personagem.valorRandomico(20, 45));
		misterioso.setPoderEspecial(Personagem.valorRandomico(35, 70));
		misterioso.setArma(Arma.METRALHADORA);
		misterioso.setProvocacao(misterioso.getEnergia()/2);
	}

}
