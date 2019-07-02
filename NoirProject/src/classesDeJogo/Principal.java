package classesDeJogo;
import java.util.Scanner;

public class Principal {
	
	//ATRIBUTOS DE AGREGAÇÃO DA CLASSE PRINCIPAL
	private Dialogo corrente;
	private Dialogo fimDeJogo = new Dialogo("src/textos/gameOver.txt", "SAIR DO JOGO");
	private Personagem detetive, policial, demonio, gangster;
	private ItemConsumivel aspirina, superAspirina, adrenalina;

	Principal() {
		
		criandoObjetos();
		
		// DECLRAÇÃO/IMPORTAÇÃO DOS DIÁLOGOS COM OS ARQUIVOS EXTERNOS .TXT
		Dialogo introducao = new Dialogo("src/textos/introducao.txt", "TENTAR DE NOVO \n");
		Dialogo voceMorreu = new Dialogo("src/textos/fimDeJogo.txt", "DESISTIR");
		Dialogo atendeuCampainha = new Dialogo("src/textos/atendeuCampainha.txt", " ATENDER A CAMPAINHA");
		Dialogo naoAtendeu = new Dialogo("src/textos/naoAtendeu.txt", " IGNORAR E DESCANSAR");
		Dialogo escolha0Op1 = new Dialogo("src/textos/escolha0Op1.txt", "ACEITAR CONTRATO");
		Dialogo escolha0Op2 = new Dialogo("src/textos/escolha0Op2.txt", "RECUSAR CONTRATO\n");
		Dialogo cenaDoCrime = new Dialogo("src/textos/cenaDoCrime.txt", "CONTINUAR");
		Dialogo falarGarota = new Dialogo("src/textos/falarGarota.txt", "FALAR COM GAROTA");
		Dialogo falarPolicial = new Dialogo("src/textos/falarPolicial.txt", "FALAR COM POLICIAL");
		Dialogo irMotel = new Dialogo("src/textos/irMotel.txt", "ACEITAR PROPOSTA");
		Dialogo gangsterMorto = new Dialogo("src/textos/gangsterMorto.txt", "ACEITAR PROPOSTA");
		Dialogo enfrentarPolicial = new Dialogo("src/textos/enfrentarPolicial.txt", "BRIGAR COM POLICIAL");
		Dialogo policialMorto = new Dialogo("src/textos/policialMorto.txt", "");
		Dialogo subornarPolicial = new Dialogo("src/textos/subornarPolicial.txt", "SUBORNAR POLICIAL");
		Dialogo acharProva = new Dialogo("src/textos/acharProva.txt", "ENTRAR NA CENA DO CRIME");
		Dialogo verPegada = new Dialogo("src/textos/verPegadas.txt", "SEGUIR AS PEGADAS");
		Dialogo verPc = new Dialogo("src/textos/verPc.txt", "VER O COMPUTADOR");
		Dialogo morrer = new Dialogo("src/textos/morrer.txt", "MORRER");
		Dialogo creditos = new Dialogo("src/textos/creditos.txt", "CONTINUAR");
		
		Batalha batalha1 = new Batalha("src/textos/batalha1.txt", "PARA INICIAR BATALHA \n", policial, detetive);
		Batalha batalha2 = new Batalha("src/textos/batalha2.txt", "PARA INICIAR BATALHA \n", demonio, detetive);
		Batalha batalha3 = new Batalha("src/textos/batalha3.txt", "PARA INICIAR BATALHA \n", gangster, detetive);
		
		//ADICIONANDO OS CAMINHOS POSSÍVEIS PARA CADA DIÁLOGO EM QUESTÃO
		this.corrente = introducao;
		introducao.adicionarCaminho(atendeuCampainha);
		introducao.adicionarCaminho(naoAtendeu);	
		
		voceMorreu.adicionarCaminho(fimDeJogo);
		voceMorreu.adicionarCaminho(introducao);
			
		atendeuCampainha.adicionarCaminho(escolha0Op1);
		atendeuCampainha.adicionarCaminho(escolha0Op2);
		naoAtendeu.adicionarCaminho(escolha0Op1);
		naoAtendeu.adicionarCaminho(escolha0Op2);
		
		escolha0Op2.adicionarCaminho(voceMorreu);
		
		escolha0Op1.adicionarCaminho(cenaDoCrime);

		cenaDoCrime.adicionarCaminho(falarPolicial);
		cenaDoCrime.adicionarCaminho(falarGarota);
		
		falarPolicial.adicionarCaminho(subornarPolicial);
		subornarPolicial.adicionarCaminho(acharProva);
		falarPolicial.adicionarCaminho(enfrentarPolicial);
		enfrentarPolicial.adicionarCaminho(batalha1);		
		
		batalha1.adicionarCaminho(policialMorto);
		batalha1.adicionarCaminho(voceMorreu);
		
		policialMorto.adicionarCaminho(acharProva);
		
		falarGarota.adicionarCaminho(irMotel);
		irMotel.adicionarCaminho(batalha3);
		
		batalha3.adicionarCaminho(gangsterMorto);
		batalha3.adicionarCaminho(voceMorreu);
		
		gangsterMorto.adicionarCaminho(acharProva);
		
		acharProva.adicionarCaminho(verPegada);
		acharProva.adicionarCaminho(verPc);
		
		verPegada.adicionarCaminho(batalha2);
		verPc.adicionarCaminho(creditos);
		
		batalha2.adicionarCaminho(morrer);
		batalha2.adicionarCaminho(voceMorreu);
		morrer.adicionarCaminho(creditos);
		
	}

	public static void main(String[] args) {
		Principal principal = new Principal();
		Scanner entrada = new Scanner(System.in);
		int opcao = 0;
		
		//INICIALIZAÇÃO DO JOGO
		principal.corrente.iniciarJogo();
		opcao = entrada.nextInt();
		
		//LOOP ONDE OCORRE O JOGO
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
		principal.corrente.executa();
		entrada.close();
	}
	
	//METODO QUE CRIA OS OBJETOS A SEREM UTILIZADOS NA EXECUÇÃO DO JOGO
	public void criandoObjetos() {
		
		//DECLARAÇÃO DOS ITENS CONSUMÍVEIS
		aspirina = new ItemCura(" ASPIRINA ", ItemConsumivel.valorRandomico(30, 66));
		superAspirina = new ItemCura(" SUPER ASPIRINA ", ItemConsumivel.valorRandomico(70, 100));
		adrenalina = new ItemEnergizador("ADRENALINA", ItemConsumivel.valorRandomico(60, 100));
		
		//DECLARÇÃO DOS PERSONAGENS
		detetive = new Personagem("DETETIVE'D'", 4);
		policial = new Personagem("CABO AMARAL", 2);
		gangster = new Personagem("MARRETA", 1);
		demonio = new Personagem("DEMONIO", 4);
		
		//SETTING DOS ATRIBUTOS DOS PERSONAGENS:
		
		//DETETIVE D
		detetive.setVida(360);
		detetive.setEnergia(50);
		detetive.setPoderAtaque(Personagem.valorRandomico(26, 40));
		detetive.setPoderEspecial(Personagem.valorRandomico(36, 66));
		detetive.setArma(Arma.PISTOLA);
		detetive.setProvocacao(detetive.getEnergia()/3);
		detetive.adicionarItem(superAspirina);
		detetive.adicionarItem(adrenalina);
		
		//CABO AMARAL
		policial.setVida(300);
		policial.setEnergia(80);
		policial.setPoderAtaque(Personagem.valorRandomico(20, 30));
		policial.setPoderEspecial(Personagem.valorRandomico(28, 50));
		policial.setArma(Arma.CACETETE);
		policial.setProvocacao(policial.getEnergia()/2);
		
		//MARRETA
		gangster.setVida(550);
		gangster.setEnergia(50);
		gangster.setPoderAtaque(Personagem.valorRandomico(20, 40));
		gangster.setPoderEspecial(Personagem.valorRandomico(20, 55));
		gangster.setArma(Arma.SOCOINGLES);
		gangster.setProvocacao(policial.getEnergia()/2);
		
		//DEMONIO
		demonio.setVida(500);
		demonio.setEnergia(100);
		demonio.setPoderAtaque(Personagem.valorRandomico(28, 55));
		demonio.setPoderEspecial(Personagem.valorRandomico(38, 77));
		demonio.setArma(Arma.GARRAS);
		demonio.setProvocacao(demonio.getEnergia()/2);
	}

}
