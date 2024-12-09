package distributore;

import java.util.ArrayList;

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

}
