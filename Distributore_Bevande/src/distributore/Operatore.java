package distributore;

import java.util.ArrayList;
import java.util.Scanner;

public class Operatore
{
	//Tutte le cose che può fare l'operatore
	public static final int codiceOperatore = 9999;

	private static void aggiungereProdotto(ArrayList<Prodotto> prodotti, Prodotto prodottoDaAggiungere)
	{
		int checkCounter = 0;
		for (int i = 0; i < prodotti.size(); i++)
		{
			if (prodottoDaAggiungere.getId() != prodotti.get(i).getId())
				checkCounter++; //Per il momento è unico, dunque aumento il counter
			else
				break; //Non è unico si interrompe il ciclo
		}
		if (checkCounter == prodotti.size())
		{
			prodotti.add(prodottoDaAggiungere);
			System.out.println("Prodotto aggiunto con successo.");
		} else
			System.out.println("L'id è già in uso.");
	}

	private static void rimuovereProdotto(ArrayList<Prodotto> prodotti, Prodotto prodottoDaRimuovere)
	{
		prodotti.remove(prodottoDaRimuovere);
	}

	private static void aggiungereQuantitaProdotto(Prodotto prodotto, int quantitaDaAggiungere)
	{
		if (quantitaDaAggiungere > 0)
			prodotto.setQuantita(prodotto.getQuantita() + quantitaDaAggiungere);
		else
			System.out.println("Errore");
	}

	private static void rimuovereQuantitaProdotto(Prodotto prodotto, int quantitaDaRimuovere)
	{
		if (quantitaDaRimuovere > 0 && prodotto.getQuantita() > quantitaDaRimuovere)
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

	private static void printProdottiAcquistati(ArrayList<Prodotto> lista) //Print personalizzata per evidenziare la quantità acquistata di un prodotto
	{
		for (int i = 0; i < lista.size(); i++)
		{
			System.out.println("Prodotto: " + lista.get(i).getNome() + ", acquistato " + lista.get(i).getQuantitaAcquistata() + " volte.");
		}
	}

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

	private static void totaleIncassato(ArrayList<Prodotto> prodotti)
	{
		double incasso = 0;
		for (int i = 0; i < prodotti.size(); i++)
		{
			incasso = prodotti.get(i).getQuantitaAcquistata() * prodotti.get(i).getPrezzo();
		}
		System.out.println("Totale incassato: " + incasso);
	}

	public static void funzioneOperatore(Macchinetta distributore)
	{
		Scanner inputOp = new Scanner(System.in);
		int continua = 1;
		int sceltaOperatore = -1;
		while (continua == 1)
		{
			switch (sceltaOperatore)
			{
			case 1: //Aggiunta prodotto
				System.out.println("Inserisci nome prodotto: ");
				String nome = inputOp.next();
				int id = -1;
				while (id < 0)
				{
					System.out.println("Inserisci id prodotto: ");
					id = inputOp.nextInt();
				}
				double prezzo = -1;
				while (prezzo < 0)
				{
					System.out.println("Inserisci prezzo prodotto: ");
					prezzo = inputOp.nextDouble();
				}
				System.out.println("Definisci se il prodotto è una bevanda calda: ");
				boolean bevandaCalda = inputOp.nextBoolean();
				int quantita = -1;
				while (quantita < 0)
				{
					System.out.println("Inserisci quantità iniziale prodotto: ");
					quantita = inputOp.nextInt();
				}
				Prodotto prodottoDaAggiungere = new Prodotto(nome, id, prezzo, bevandaCalda, quantita);
				aggiungereProdotto(distributore.prodotti, prodottoDaAggiungere);
				sceltaOperatore = -1;
				break;
			case 2: //Rimozione prodotto
				System.out.println("Inserisci id prodotto da rimuovere: ");
				id = inputOp.nextInt();
				int j = -1;
				for (int i = 0; i < distributore.prodotti.size(); i++)
				{
					if (distributore.prodotti.get(i).getId() == id)
					{
						j = i;
						break;
					}
				}
				if (j != -1)
				{
					Prodotto prodottoDaRimuovere = distributore.prodotti.get(j);
					rimuovereProdotto(distributore.prodotti, prodottoDaRimuovere);
					System.out.println("Prodotto rimosso");

				} else
				{
					System.out.println("Id non trovato");
				}
				sceltaOperatore = -1;
				break;
			case 3: //Aggiungere quantità prodotto
				System.out.println("Inserisci id prodotto di cui vuoi aggiungere quantita: ");
				id = inputOp.nextInt();
				j = -1;
				for (int i = 0; i < distributore.prodotti.size(); i++)
				{
					if (distributore.prodotti.get(i).getId() == id)
					{
						j = i;
						break;
					}
				}
				if (j != -1)
				{
					Prodotto prodottoDaRimpolpare = distributore.prodotti.get(j);
					System.out.println("Inserisci la quantità da aggiungere: ");
					quantita = inputOp.nextInt();
					aggiungereQuantitaProdotto(prodottoDaRimpolpare, quantita);
				} else
				{
					System.out.println("Id non trovato");
				}
				sceltaOperatore = -1;
				break;
			case 4: //Rimozione prodotto
				System.out.println("Inserisci id prodotto di cui vuoi rimuovere quantita: ");
				id = inputOp.nextInt();
				j = -1;
				for (int i = 0; i < distributore.prodotti.size(); i++)
				{
					if (distributore.prodotti.get(i).getId() == id)
					{
						j = i;
						break;
					}
				}
				if (j != -1)
				{
					Prodotto prodottoDaDecimare = distributore.prodotti.get(j);
					System.out.println("Inserisci la quantità da rimuovere: ");
					quantita = inputOp.nextInt();
					rimuovereQuantitaProdotto(prodottoDaDecimare, quantita);
				} else
				{
					System.out.println("Id non trovato");
				}
				sceltaOperatore = -1;
				break;
			case 5: //Cambiare prezzo prodotto
				System.out.println("Inserisci id prodotto di cui vuoi cambiare prezzo: ");
				id = inputOp.nextInt();
				j = -1;
				for (int i = 0; i < distributore.prodotti.size(); i++)
				{
					if (distributore.prodotti.get(i).getId() == id)
					{
						j = i;
						break;
					}
				}
				if (j != -1)
				{
					Prodotto prodottoPrezzare = distributore.prodotti.get(j);
					System.out.println("Inserisci nuovo prezzo: ");
					prezzo = inputOp.nextDouble();
					cambiarePrezzoProdotto(prodottoPrezzare, prezzo);
				} else
				{
					System.out.println("Id non trovato");
				}
				sceltaOperatore = -1;
				break;
			case 6: //Visualizza totale incassato
				totaleIncassato(distributore.prodotti);
				sceltaOperatore = -1;
				break;
			case 7: //Visualizza lista prodotti acquistati
				prodottiAcquistati(distributore.prodotti);
				sceltaOperatore = -1;
				break;
			default:
				System.out.println("Scegli 1 per aggiungere un prodotto, 2 per rimuovere un prodotto, 3 per aggiungere la quantità di un prodotto, 4 per rimuovere la quantità di un prodotto,"
						+ "\n5 per cambiare il prezzo di un prodotto, 6 per il totale incassato, 7 per vedere quali prodotti sono stati acquistati. ");
				sceltaOperatore = inputOp.nextInt();
				continue;
			}
			System.out.println("Vuoi fare altro? Premi 1 per sì.");
			continua = inputOp.nextInt();
		}
		inputOp.close();
	}

}
