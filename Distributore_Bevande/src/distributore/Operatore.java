package distributore;

import java.util.ArrayList;
import java.util.Scanner;

public class Operatore
{
	//Tutte le cose che può fare l'operatore
	public static int codiceOperatore = 9999;

	public void aggiungereProdotto(ArrayList<Prodotto> prodotti, Prodotto prodottoDaAggiungere)
	{
		prodotti.add(prodottoDaAggiungere);
	}

	public void rimuovereProdotto(ArrayList<Prodotto> prodotti, Prodotto prodottoDaRimuovere)
	{
		prodotti.remove(prodottoDaRimuovere);
	}

	public void aggiungereQuantitaProdotto(Prodotto prodotto, int quantitaDaAggiungere)
	{
		prodotto.quantita += quantitaDaAggiungere;
	}

	public void rimuovereQuantitaProdotto(Prodotto prodotto, int quantitaDaRimuovere)
	{
		prodotto.quantita += quantitaDaRimuovere;
	}

	public void cambiarePrezzoProdotto(Prodotto prodotto, double nuovoPrezzo)
	{
		prodotto.prezzo = nuovoPrezzo;
	}

	public void printProdottiAcquistati(ArrayList<Prodotto> lista) //Print personalizzata per evidenziare la quantità acquistata di un prodotto
	{
		for (int i = 0; i < lista.size(); i++)
		{
			System.out.println("Prodotto: " + lista.get(i).nome + ", acquistato " + lista.get(i).quantitaAcquistata + " volte.");
		}
	}

	public void prodottiAcquistati(ArrayList<Prodotto> prodotti)
	{
		ArrayList<Prodotto> acquistati = new ArrayList<Prodotto>();
		for (int i = 0; i < prodotti.size(); i++)
		{
			if (prodotti.get(i).quantitaAcquistata > 0)
				acquistati.add(prodotti.get(i));
		}
		printProdottiAcquistati(acquistati);
	}

	public void totaleIncassato(ArrayList<Prodotto> prodotti)
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
		int sceltaOperatore = -1;
		while (sceltaOperatore == -1)
		{
			switch (sceltaOperatore)
			{
			case -1:
				System.out.println("Scegli 1 per aggiungere un prodotto, 2 per rimuovere un prodotto, 3 per cambiare la quantità di un prodotto, 4 per cambiare il prezzo di un prodotto, "
						+ "5 per il totale incassato, 6 per vedere quali prodotti sono stati acquistati. ");
				sceltaOperatore = inputOp.nextInt();
				break;
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
				int j = distributore.prodotti.indexOf(distributore.prodotti.get(id));
				Prodotto prodottoDaRimuovere = distributore.prodotti.get(j);
				rimuovereProdotto(distributore.prodotti, prodottoDaRimuovere);
				break;
			}
		}
		System.out.println("Premi ");
	}

}
