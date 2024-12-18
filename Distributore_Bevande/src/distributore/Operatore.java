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
		for (int i = 0; i < prodotti.size(); i++)
		{
			if (prodottoDaAggiungere.getId() == prodotti.get(i).getId())
			{
				System.out.println("L'id è già in uso.");
				return;
			}
		}
		prodotti.add(prodottoDaAggiungere);
		System.out.println("Prodotto aggiunto con successo.");
	}

	// Metodo per rimuovere un prodotto

	private static void rimuovereProdotto(ArrayList<Prodotto> prodotti, Prodotto prodottoDaRimuovere)
	{
		if (prodottoDaRimuovere == null)
		{
			System.out.println("Hai provato a rimuovere il codice operatore... Bravo non è successo niente.");
			return;
		}
		prodotti.remove(prodottoDaRimuovere);
	}

	// Metodo per aggiungere la quantità

	private static void aggiungereQuantitaProdotto(Prodotto prodotto, Scanner scanner)
	{
		int quantita = 0;
		do
		{
			System.out.println("Inserisci la quantità da aggiungere: ");
			quantita = Main.getInt(scanner);
		} while (quantita < 1);
		prodotto.setQuantita(prodotto.getQuantita() + quantita);
	}

	// Metodo per ridurre la quantità

	private static void rimuovereQuantitaProdotto(Prodotto prodotto, Scanner scanner)
	{
		if (prodotto.getQuantita() == 0)
		{
			System.out.println("Il prodotto è esaurito.");
			return;
		}
		int quantita = 0;
		do
		{
			System.out.println("Inserisci la quantità da rimuovere: ");
			quantita = Main.getInt(scanner);
		} while (quantita <= prodotto.getQuantita()); // Il controllo su quantita>0 non serve perché "quantitaProdotto" non può scendere sotto lo 0.
		prodotto.setQuantita(prodotto.getQuantita() - quantita);
	}

	private static void cambiarePrezzoProdotto(Prodotto prodotto, Scanner scanner)
	{
		double nuovoPrezzo = 0;
		do
		{
			System.out.println("Inserisci nuovo prezzo: ");
			nuovoPrezzo = Main.getDouble(scanner);
		} while (nuovoPrezzo < 0);
		prodotto.setPrezzo((double)Math.round(nuovoPrezzo*10)/10);
	}

	// Metodo che aggiunge in un array i prodotti acquistati

	private static void prodottiAcquistati(ArrayList<Prodotto> prodotti)
	{
		for (int i = 0; i < prodotti.size(); i++)
		{
			if (prodotti.get(i).getQuantitaAcquistata() > 0)
				System.out.println("Prodotto: " + prodotti.get(i).getNome() + ", acquistato " + prodotti.get(i).getQuantitaAcquistata() + " volte.");
		}
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
		for (int i = 0; i < prodotti.size(); i++)
		{
			if (prodotti.get(i).getQuantita() == 0)
				System.out.println("Prodotto esaurito: " + prodotti.get(i).getNome());
		}
	}

	private static void cambioPrezzoMassa(Macchinetta distributore, Scanner scanner) // Cambia il prezzo di una percentuale in base al numero inserito, con segno
	{
		double percentuale = 0;
		do
		{
			System.out.println("Inserisci la percentuale di variazione prezzo. Attenzione se vuoi diminuire il prezzo ricorda il segno -.");
			percentuale = Main.getDouble(scanner);
		} while (percentuale == 0);
		for (Prodotto p : distributore.prodotti)
		{
			p.setPrezzo((double)Math.round(p.getPrezzo() * (1 + percentuale / 100)*10)/10);
		}
	}

	private static Prodotto creazioneProdotto(Scanner scanner) // Input necessari per la creazione di un nuovo prodotto
	{
		scanner.nextLine();
		System.out.println("Inserisci nome prodotto: ");
		String nome = scanner.nextLine();
		int id = -1;
		while (id < 0 || id == 9999)
		{
			System.out.println("Inserisci id prodotto: ");
			id = Main.getInt(scanner);
		}
		double prezzo = -1;
		while (prezzo < 0)
		{
			System.out.println("Inserisci prezzo prodotto: ");
			prezzo = Main.getDouble(scanner);
		}
		System.out.println("Definisci se il prodotto è una bevanda calda: ");
		boolean bevandaCalda = Main.getBoolean(scanner);
		int quantita = -1;
		while (quantita < 0)
		{
			System.out.println("Inserisci quantità iniziale prodotto: ");
			quantita = Main.getInt(scanner);
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
				aggiungereQuantitaProdotto(prodottoDaRimpolpare, scanner);
				sceltaOperatore = -1;
				break;
			case 4: // Rimozione quantita prodotto
				Prodotto prodottoDaDecimare = Macchinetta.selezioneIdProdotto(distributore, scanner);
				rimuovereQuantitaProdotto(prodottoDaDecimare, scanner);
				sceltaOperatore = -1;
				break;
			case 5: // Cambiare prezzo prodotto
				Prodotto prodottoDaPrezzare = Macchinetta.selezioneIdProdotto(distributore, scanner);
				cambiarePrezzoProdotto(prodottoDaPrezzare, scanner);
				sceltaOperatore = -1;
				break;
			case 6: // Visualizza totale incassato
				System.out.println(String.format("Incasso: %.2f€", distributore.getIncasso()));
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
			case 9: // Cambiare prezzo a tutti i prodotti
				cambioPrezzoMassa(distributore, scanner);
				sceltaOperatore = -1;
				break;
			case 10:
				System.exit(0); // Commento per il futuro: return non basta perché il metodo è dentro il metodo chiamato nel main.
				break;
			default:
				System.out.println("Premi: \n1) per aggiungere un prodotto \n2) per rimuovere un prodotto \n3) per aggiungere la quantità di un prodotto"
						+ "\n4) per rimuovere la quantità di un prodotto \n5) per cambiare il prezzo di un prodotto"
						+ " \n6) per il totale incassato \n7) per vedere quali prodotti sono stati acquistati \n8) per visualizzare lista prodotti esauriti"
						+ "\n9) per cambiare il prezzo a tutti i prodotti \n10) per resettare la macchinetta");
				sceltaOperatore = Main.getInt(scanner);
				continue;
			}
			System.out.println("Vuoi fare altro? Premi 1 per sì.");
			continua = Main.getInt(scanner);
		}
	}

}
