package distributore;

import java.util.ArrayList;
import java.util.Scanner;

public class Operatore
{
	//Tutte le cose che può fare l'operatore
	public static int codiceOperatore = 9999;

	public Operatore()
	{
	}

	private static void aggiungereProdotto(ArrayList<Prodotto> prodotti, Prodotto prodottoDaAggiungere)
	{
		prodotti.add(prodottoDaAggiungere);
	}

	private static void rimuovereProdotto(ArrayList<Prodotto> prodotti, Prodotto prodottoDaRimuovere)
	{
		prodotti.remove(prodottoDaRimuovere);
	}

	private static void aggiungereQuantitaProdotto(Prodotto prodotto, int quantitaDaAggiungere)
	{
		if (quantitaDaAggiungere > 0)
			prodotto.quantita += quantitaDaAggiungere;
		else
			System.out.println("Errore");
	}

	private static void rimuovereQuantitaProdotto(Prodotto prodotto, int quantitaDaRimuovere)
	{
		if (quantitaDaRimuovere > 0 && prodotto.quantita > quantitaDaRimuovere)
			prodotto.quantita -= quantitaDaRimuovere;
		else
			System.out.println("Errore");
	}

	private static void cambiarePrezzoProdotto(Prodotto prodotto, double nuovoPrezzo)
	{
		if (nuovoPrezzo > 0)
			prodotto.prezzo = nuovoPrezzo;
		else
			System.out.println("Errore");
	}

	private static void printProdottiAcquistati(ArrayList<Prodotto> lista) //Print personalizzata per evidenziare la quantità acquistata di un prodotto
	{
		for (int i = 0; i < lista.size(); i++)
		{
			System.out.println("Prodotto: " + lista.get(i).nome + ", acquistato " + lista.get(i).quantitaAcquistata + " volte.");
		}
	}

	private static void prodottiAcquistati(ArrayList<Prodotto> prodotti)
	{
		ArrayList<Prodotto> acquistati = new ArrayList<Prodotto>();
		for (int i = 0; i < prodotti.size(); i++)
		{
			if (prodotti.get(i).quantitaAcquistata > 0)
				acquistati.add(prodotti.get(i));
		}
		printProdottiAcquistati(acquistati);
	}

	private static void totaleIncassato(ArrayList<Prodotto> prodotti)
	{
		double incasso = 0;
		for (int i = 0; i < prodotti.size(); i++)
		{
			incasso = prodotti.get(i).quantitaAcquistata * prodotti.get(i).prezzo;
		}
		System.out.println("Totale incassato: " + incasso);
	}

	public void funzioneOperatore(Macchinetta distributore)
	{
		Scanner inputOp = new Scanner(System.in);
		int continua = 1;
		int sceltaOperatore = -1;
		while (continua == 1)
		{
			//while (sceltaOperatore < 1 || sceltaOperatore > 6)
			//{
			System.out.println("Scegli 1 per aggiungere un prodotto, 2 per rimuovere un prodotto, 3 per aggiungere la quantità di un prodotto, 4 per aggiungere la quantità di un prodotto, "
					+ "5 per cambiare il prezzo di un prodotto, 6 per il totale incassato, 7 per vedere quali prodotti sono stati acquistati. ");
			sceltaOperatore = inputOp.nextInt();
			switch (sceltaOperatore)
			{
			case 1:
				System.out.println("Inserisci nome prodotto: ");
				String nome = inputOp.nextLine();
				System.out.println("Inserisci id prodotto: ");
				int id = inputOp.nextInt();
				System.out.println("Inserisci prezzo prodotto: ");
				double prezzo = inputOp.nextDouble();
				System.out.println("Definisci se il prodotto è una bevanda calda: ");
				boolean bevandaCalda = inputOp.nextBoolean();
				System.out.println("Inserisci quantità iniziale prodotto: ");
				int quantita = inputOp.nextInt();
				Prodotto prodottoDaAggiungere = new Prodotto(nome, id, prezzo, bevandaCalda, quantita);
				aggiungereProdotto(distributore.prodotti, prodottoDaAggiungere);
				break;
			case 2:
				System.out.println("Inserisci id prodotto da rimuovere: ");
				id = inputOp.nextInt();
				int j = -1;
				for (int i = 0; i < distributore.prodotti.size(); i++)
				{
					if (distributore.prodotti.get(i).id == id)
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
				break;
			case 3:
				System.out.println("Inserisci id prodotto di cui vuoi aggiungere quantita: ");
				id = inputOp.nextInt();
				j = distributore.prodotti.indexOf(distributore.prodotti.get(id)); //Sostituire con il ciclo for in case 2.
				Prodotto prodottoDaRimpolpare = distributore.prodotti.get(j);
				System.out.println("Inserisci la quantità da aggiungere: ");
				quantita = inputOp.nextInt();
				aggiungereQuantitaProdotto(prodottoDaRimpolpare, quantita);
				break;
			case 4:
				System.out.println("Inserisci id prodotto di cui vuoi aggiungere quantita: ");
				id = inputOp.nextInt();
				j = distributore.prodotti.indexOf(distributore.prodotti.get(id));
				Prodotto prodottoDaDecimare = distributore.prodotti.get(j);
				System.out.println("Inserisci la quantità da aggiungere: ");
				quantita = inputOp.nextInt();
				rimuovereQuantitaProdotto(prodottoDaDecimare, quantita);
				break;
			case 5:
				System.out.println("Inserisci id prodotto di cui vuoi cambiare prezzo: ");
				id = inputOp.nextInt();
				j = distributore.prodotti.indexOf(distributore.prodotti.get(id));
				Prodotto prodottoPrezzare = distributore.prodotti.get(j);
				System.out.println("Inserisci nuovo prezzo: ");
				prezzo = inputOp.nextDouble();
				cambiarePrezzoProdotto(prodottoPrezzare, prezzo);
				break;
			case 6:
				totaleIncassato(distributore.prodotti);
				break;
			case 7:
				prodottiAcquistati(distributore.prodotti);
				break;
			default:
				System.out.println("Scegli 1 per aggiungere un prodotto, 2 per rimuovere un prodotto, 3 per aggiungere la quantità di un prodotto, 4 per aggiungere la quantità di un prodotto, "
						+ "5 per cambiare il prezzo di un prodotto, 6 per il totale incassato, 7 per vedere quali prodotti sono stati acquistati. ");
				sceltaOperatore = inputOp.nextInt();
				break;
			}

			//}
			System.out.println("Vuoi fare altro? Premi 1 per sì.");
			continua = inputOp.nextInt();
		}
		//inputOp.close();
	}

}
