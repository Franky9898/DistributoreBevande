package distributore;

import java.util.ArrayList;
import java.util.Scanner;

public class Operatore
{
	// Tutte le cose che può fare l'operatore
	public static final int codiceOperatore = 9999;

	// Metodo che permette all'operatore di aggiunfere un prodotto

	private static void aggiungereProdotto(ArrayList<Prodotto> prodotti, Prodotto prodottoDaAggiungere)
	{
		int checkCounter = 0;
		for (int i = 0; i < prodotti.size(); i++)
		{
			if (prodottoDaAggiungere.getId() != prodotti.get(i).getId())
				checkCounter++; // Per il momento è unico, dunque aumento il counter
			else
				break; // Non è unico si interrompe il ciclo
		}
		if (checkCounter == prodotti.size())
		{
			prodotti.add(prodottoDaAggiungere);
			System.out.println("Prodotto aggiunto con successo.");
		} else
			System.out.println("L'id è già in uso.");
	}

	// Metodo per rimuovere un prodotto

	private static void rimuovereProdotto(ArrayList<Prodotto> prodotti, Prodotto prodottoDaRimuovere)
	{
		prodotti.remove(prodottoDaRimuovere);
	}

	// Metodo per aggiungere la quantità

	private static void aggiungereQuantitaProdotto(Prodotto prodotto, int quantitaDaAggiungere)
	{
		if (quantitaDaAggiungere > 0)
			prodotto.setQuantita(prodotto.getQuantita() + quantitaDaAggiungere);
		else
			System.out.println("Errore");
	}

	// Metodo per ridurre la quantità

	private static void rimuovereQuantitaProdotto(Prodotto prodotto, int quantitaDaRimuovere)
	{
		if (quantitaDaRimuovere > 0 && prodotto.getQuantita() >= quantitaDaRimuovere)
			prodotto.setQuantita(prodotto.getQuantita() - quantitaDaRimuovere);
		else
			System.out.println("Errore");
	}

	private static void cambiarePrezzoProdotto(Prodotto prodotto, double nuovoPrezzo)
	{
		if (nuovoPrezzo > 0)
			prodotto.setPrezzo(nuovoPrezzo);
		else
			System.out.println("Errore");
	}

	// Metodo print personalizzata per evidenziare la quantità acquistata di un prodotto

	private static void printProdottiAcquistati(ArrayList<Prodotto> lista)
	{
		for (int i = 0; i < lista.size(); i++)
		{
			System.out.println("Prodotto: " + lista.get(i).getNome() + ", acquistato " + lista.get(i).getQuantitaAcquistata() + " volte.");
		}
	}

	// Metodo che aggiunge in un array i prodotti acquistati

	private static void prodottiAcquistati(ArrayList<Prodotto> prodotti)
	{
		ArrayList<Prodotto> acquistati = new ArrayList<Prodotto>();
		for (int i = 0; i < prodotti.size(); i++)
		{
			if (prodotti.get(i).getQuantitaAcquistata() > 0)
				acquistati.add(prodotti.get(i));
		}
		printProdottiAcquistati(acquistati);
	}

	// Metodo che ritorna l'incasso del distributore

	public static double aggiornamentoIncasso(Macchinetta distributore, Prodotto prodottoSelezionato, double subTotale, boolean restabbile, int quantitaAcquistare)
	{
		if (restabbile == true)
			distributore.incasso += (prodottoSelezionato.getPrezzo() * quantitaAcquistare);
		else
			distributore.incasso += subTotale - distributore.resto;

		return distributore.incasso;
	}

	private static void prodottiEsauriti(ArrayList<Prodotto> prodotti)
	{
		ArrayList<Prodotto> esauriti = new ArrayList<Prodotto>();
		for (int i = 0; i < prodotti.size(); i++)
		{
			if (prodotti.get(i).getQuantita() == 0)
				esauriti.add(prodotti.get(i));
		}
		printProdottiAcquistati(esauriti);
	}

	private static void cambioPrezzoMassa(Macchinetta distributore, double percentuale) //Cambia il prezzo di una percentuale in base al numero inserito, con segno
	{
		for (Prodotto p : distributore.prodotti)
		{
			p.setPrezzo(p.getPrezzo() * (1 + percentuale / 100));
		}
	}

	private static Prodotto creazioneProdotto(Scanner scanner) //Input necessari per la creazione di un nuovo prodotto
	{
		scanner.nextLine();
		System.out.println("Inserisci nome prodotto: ");
		String nome = scanner.nextLine();
		int id = -1;
		while (id < 0)
		{
			System.out.println("Inserisci id prodotto: ");
			id = scanner.nextInt();
			scanner.nextLine();
		}
		double prezzo = -1;
		while (prezzo < 0)
		{
			System.out.println("Inserisci prezzo prodotto: ");
			prezzo = scanner.nextDouble();
			scanner.nextLine();
		}
		System.out.println("Definisci se il prodotto è una bevanda calda: ");
		boolean bevandaCalda = scanner.nextBoolean();
		int quantita = -1;
		while (quantita < 0)
		{
			System.out.println("Inserisci quantità iniziale prodotto: ");
			quantita = scanner.nextInt();
			scanner.nextLine();
		}
		// Istanza dell'oggetto Prodotto da aggiungere
		Prodotto prodottoDaAggiungere = new Prodotto(nome, id, prezzo, bevandaCalda, quantita);
		return prodottoDaAggiungere;
	}

	// Metodo con tutte le azioni che può fare l'operatore sulla macchinetta

	public static void funzioneOperatore(Macchinetta distributore, Scanner scanner)
	{
		int continua = 1;
		int sceltaOperatore = -1;
		while (continua == 1)
		{
			switch (sceltaOperatore)
			{

			case 1:
				Prodotto prodottoDaAggiungere = creazioneProdotto(scanner);
				aggiungereProdotto(distributore.prodotti, prodottoDaAggiungere);
				sceltaOperatore = -1;
				break;
			case 2: // Rimozione prodotto
				Prodotto prodottoDaRimuovere = Macchinetta.selezioneIdProdotto(distributore, scanner);
				rimuovereProdotto(distributore.prodotti, prodottoDaRimuovere);
				sceltaOperatore = -1;
				break;
			case 3: // Aggiungere quantità prodotto
				Prodotto prodottoDaRimpolpare = Macchinetta.selezioneIdProdotto(distributore, scanner);
				System.out.println("Inserisci la quantità da aggiungere: ");
				int quantita = scanner.nextInt();
				aggiungereQuantitaProdotto(prodottoDaRimpolpare, quantita);
				sceltaOperatore = -1;
				break;
			case 4: // Rimozione quantita prodotto
				Prodotto prodottoDaDecimare = Macchinetta.selezioneIdProdotto(distributore, scanner);
				System.out.println("Inserisci la quantità da diminuire: ");
				quantita = scanner.nextInt();
				rimuovereQuantitaProdotto(prodottoDaDecimare, quantita);
				sceltaOperatore = -1;
				break;
			case 5: // Cambiare prezzo prodotto
				Prodotto prodottoPrezzare = Macchinetta.selezioneIdProdotto(distributore, scanner);
				System.out.println("Inserisci nuovo prezzo: ");
				double prezzo = scanner.nextDouble();
				cambiarePrezzoProdotto(prodottoPrezzare, prezzo);
				sceltaOperatore = -1;
				break;
			case 6: // Visualizza totale incassato
				System.out.println("Incasso: " + distributore.getIncasso());
				sceltaOperatore = -1;
				break;
			case 7: // Visualizza lista prodotti acquistati
				prodottiAcquistati(distributore.prodotti);
				sceltaOperatore = -1;
				break;
			case 8: // Visualizza lista prodotti esauriti
				prodottiEsauriti(distributore.prodotti);
				sceltaOperatore = -1;
				break;
			case 9: //Cambiare prezzo a tutti i prodotti
				System.out.println("Inserisci la percentuale di variazione prezzo. Attenzione se vuoi diminuire il prezzo ricorda il segno -.");
				double percentuale = scanner.nextDouble();
				cambioPrezzoMassa(distributore, percentuale);
				sceltaOperatore = -1;
				break;
			case 10:
				System.exit(0);
				break;
			default:
				System.out.println("Premi: \n1) per aggiungere un prodotto \n2) per rimuovere un prodotto \n3) per aggiungere la quantità di un prodotto"
						+ "\n4) per rimuovere la quantità di un prodotto \n5) per cambiare il prezzo di un prodotto"
						+ " \n6) per il totale incassato \n7) per vedere quali prodotti sono stati acquistati \n8) per visualizzare lista prodotti esauriti"
						+ "\n9) per cambiare il prezzo a tutti i prodotti \n10) per resettare la macchinetta");
				sceltaOperatore = scanner.nextInt();
				continue;
			}
			System.out.println("Vuoi fare altro? Premi 1 per sì.");
			continua = scanner.nextInt();
		}
	}

}
