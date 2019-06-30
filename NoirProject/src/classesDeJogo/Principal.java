package classesDeJogo;

import java.util.Scanner;

public class Principal {

	private Dialogo corrente;
	private Dialogo fimDeJogo = new Dialogo("src/textos/fimDeJogo.txt", "MORRER");
	private Personagem jogador, inimigo;
	private ItemConsumivel remedio, superRemedio, adrenalina;

	Principal() {
		
		criandoObjetos();
		
		Dialogo introducao = new Dialogo("src/textos/intro.txt", "");
		Dialogo introOp1 = new Dialogo("src/textos/introOp1.txt", " ATENDER A CAMPAINHA");
		Dialogo introOp2 = new Dialogo("src/textos/introOp2.txt", " IGNORAR E DESCANSAR");
		Dialogo escolha0Op1 = new Dialogo("src/textos/escolha0Op1.txt", "ACEITAR CONTRATO");
		Dialogo escolha0Op2 = new Dialogo("src/textos/escolha0Op2.txt", "RECUSAR CONTRATO\n");
		Dialogo dialogo1 = new Dialogo("src/textos/dialogo1.txt", "CONTINUAR");
		Dialogo dialogo1Op1 = new Dialogo("src/textos/dialogo1Op1.txt", "ATACAR");
		Dialogo dialogo1Op2 = new Dialogo("src/textos/dialogo1Op2.txt", "DIZER A VERDADE");
		Dialogo dialogo1Op3 = new Dialogo("src/textos/dialogo1Op3.txt", "MENTIR \n");
		Dialogo dialogo1Op3Op1 = new Dialogo("src/textos/dialogo1Op3Op1.txt", "INVENTAR NOME");
		Dialogo dialogo1Op3Op2 = new Dialogo("src/textos/dialogo1Op3Op2.txt", "ENROLAR \n");
		Dialogo dialogo1Op3Op2ORecusa = new Dialogo("src/textos/dialogo1Op3Op2Recusa.txt", "RECUSAR \n \n");
		Dialogo dialogo2 = new Dialogo("src/textos/dialogo2.txt", "");
		Dialogo dialogo2Op1 = new Dialogo("src/textos/dialogo2Op1.txt", "DIALOGAR");
		Dialogo dialogo2Op2 = new Dialogo("src/textos/dialogo2Op2.txt", "ENFRENTAR");
		Dialogo dialogo2Op1Op1 = new Dialogo("src/textos/dialogo2Op1Op1.txt", "SER SAUDOSISTA");
		Dialogo dialogo2Op1Op2 = new Dialogo("src/textos/dialogo2Op1Op2.txt", "NEGOCIAR");
		Dialogo dialogo2Op1Op3 = new Dialogo("src/textos/dialogo2Op1Op3.txt", "ENGANAR");
		Batalha batalha1 = new Batalha("src/textos/HERMOBIUS.txt", "PARA INICIAR BATALHA \n", inimigo, jogador);
		
		this.corrente = introducao;
		introducao.adicionarCaminho(introOp1);
		introducao.adicionarCaminho(introOp2);		
		//introducao.adicionarCaminho(this.fimDeJogo);
		
		
		introOp1.adicionarCaminho(escolha0Op1);
		introOp1.adicionarCaminho(escolha0Op2);
		introOp2.adicionarCaminho(escolha0Op1);
		introOp2.adicionarCaminho(escolha0Op2);
		
		escolha0Op2.adicionarCaminho(this.fimDeJogo);
		
		escolha0Op1.adicionarCaminho(dialogo1);

		dialogo1.adicionarCaminho(dialogo1Op1);
		dialogo1.adicionarCaminho(dialogo1Op2);
		dialogo1.adicionarCaminho(dialogo1Op3);
		
		dialogo1Op1.adicionarCaminho(batalha1);
		dialogo1Op2.adicionarCaminho(batalha1);
		dialogo1Op3Op1.adicionarCaminho(batalha1);
		dialogo1Op3Op2ORecusa.adicionarCaminho(batalha1);
		
		dialogo1Op3.adicionarCaminho(dialogo1Op3Op1);
		dialogo1Op3.adicionarCaminho(dialogo1Op3Op2);

		dialogo1Op3Op2.adicionarCaminho(this.fimDeJogo);
		dialogo1Op3Op2.adicionarCaminho(dialogo1Op3Op2ORecusa);
		
		batalha1.adicionarCaminho(dialogo2);
		batalha1.adicionarCaminho(this.fimDeJogo);
		
		dialogo2.adicionarCaminho(dialogo2Op1);
		dialogo2.adicionarCaminho(dialogo2Op2);
		
		dialogo2Op1.adicionarCaminho(dialogo2Op1Op1);
		dialogo2Op1.adicionarCaminho(dialogo2Op1Op2);
		dialogo2Op1.adicionarCaminho(dialogo2Op1Op3);
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
		
		remedio = new ItemCura(" REMEDIO ", ItemConsumivel.valorRandomico(30, 66));
		superRemedio = new ItemCura(" SUPER REMEDIO ", ItemConsumivel.valorRandomico(70, 100));
		adrenalina = new ItemEnergizador("ADRENALINA", ItemConsumivel.valorRandomico(60, 100));
		
		jogador = new Personagem("DETETIVE'D'", 4);
		inimigo = new Personagem("PUNK", 2);
		jogador.setVida(350);
		jogador.setEnergia(50);
		jogador.setPoderAtaque(Personagem.valorRandomico(26, 46));
		jogador.setPoderEspecial(Personagem.valorRandomico(36, 66));
		jogador.setArma(Arma.PISTOLA);
		jogador.setProvocacao(jogador.getEnergia()/3);
		jogador.adicionarItem(remedio);
		jogador.adicionarItem(superRemedio);
		jogador.adicionarItem(adrenalina);

		inimigo.setVida(330);
		inimigo.setEnergia(80);
		inimigo.setPoderAtaque(Personagem.valorRandomico(10, 22));
		inimigo.setPoderEspecial(Personagem.valorRandomico(25, 40));
		inimigo.setArma(Arma.CACETETE);
		inimigo.setProvocacao(inimigo.getEnergia()/2);
	}

}
