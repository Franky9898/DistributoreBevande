package distributore;

import java.util.ArrayList;
import java.util.Scanner;

public class Macchinetta
{
	public double resto;
	public int zucchero;
	public int bacchette;
	public int bicchieri;
	public double incasso;
	public ArrayList<Prodotto> prodotti;
	public final Moneta[] moneteValide;

	// Prodotto(final String nome, final int id, double prezzo, final boolean bevandaCalda, int
	// quantita)
	public Macchinetta(ArrayList<Prodotto> prodotti, final Moneta[] moneteValide, double resto, int zucchero, int bacchette, int bicchieri) // Costruttore macchinetta
	{
		if (resto < 0)
			throw new IllegalArgumentException("Il resto non può essere negativo.");
		if (zucchero < 0)
			throw new IllegalArgumentException("Lo zucchero iniziale non può essere negativo.");
		if (bacchette < 0)
			throw new IllegalArgumentException("Il numero delle bacchette iniziali non può essere negativo.");
		if (bicchieri < 0)
			throw new IllegalArgumentException("Il numero dei bicchieri iniziali non può essere negativo.");
		this.resto = resto;
		this.zucchero = zucchero;
		this.bacchette = bacchette;
		this.bicchieri = bicchieri;
		this.prodotti = prodotti;
		this.moneteValide = moneteValide;
		this.incasso = 0;
	}

	public ArrayList<Prodotto> getProdotti()
	{
		return prodotti;
	}

	public double getIncasso()
	{
		return incasso;
	}

	public static Prodotto selezioneIdProdotto(Macchinetta distributore, Scanner scanner) // Return null se codiceOperatore
	{
		while (true) // Permette di rimanere nel ciclo fin quando non c'è un return
		{
			Macchinetta.stampaProdotti(distributore);
			System.out.println("Inserisci id prodotto: ");
			int id = Main.getInt(scanner);
			if (id == Operatore.codiceOperatore) // L'id operatore ritorna null
				return null;
			for (Prodotto p : distributore.prodotti)
			{
				if (id == p.getId()) // Se l'id corrisponde a qualcuno nell'array allora ritorna p
				{
					System.out.println("Hai selezionato: " + p.getNome());
					return p;
				}
			}
			System.out.println("Id non valido"); // id non trovato e diverso dall'operatore
		}
	}

	public static boolean controlloResto(Macchinetta distributore, Prodotto bevanda, double subTotale, int quantitaAcquistare) // Controlla se la macchinetta ha abbastanza resto
	{
		if (distributore.resto >= (subTotale - (bevanda.getPrezzo() * quantitaAcquistare)))
			return true;
		else
			return false;
	}

	public static int[] selezioneZucchero(Macchinetta distributore, int quantitaAcquistare, Scanner scanner) // Permette di selezionare la quantità dello zucchero
	{
		int zuccheroSel = 0;
		int[] listaZucchero = new int[quantitaAcquistare];
		for (int i = 0; i < listaZucchero.length; i++)
		{
			int zucchero = -1;
			while (true)
			{
				if (zucchero > -1 && zucchero < 6 && zucchero <= (distributore.zucchero - zuccheroSel))
				{
					listaZucchero[i] = zucchero;
					zuccheroSel += zucchero;
					break;
				} else if (zucchero > -1 && zucchero < 6 && zucchero > (distributore.zucchero - zuccheroSel))
				{
					System.out.println("Zucchero non sufficiente, selezionare quantità minore.");
					zucchero = -1;
				} else
				{
					System.out.println("Selezionare quantità zucchero tra 0 e 5 per la bevanda: " + (i+1) + ". Premere 10 per annullare:  ");
					zucchero = Main.getInt(scanner);
					if (zucchero == 10)
						return null;
				}
			}
		}
		return listaZucchero;
	}

	public static void aggiornamentoZucchero(Macchinetta distributore, int[] zucchero)
	{
		for (Integer z : zucchero)
			distributore.zucchero -= z; // Aggiorna lo zucchero nel distributore
	}

	public static boolean controlloBicchieri(Macchinetta distributore, int quantitaAcquistare) // Aggiorna numero bicchieri
	{
		if (distributore.bicchieri >= quantitaAcquistare)
			return true;
		else
		{
			System.out.println("Non ci sono abbastanza bicchieri");
			return false;
		}
	}

	public static void aggiornamentoBicchieri(Macchinetta distributore, int quantitaAcquistare)
	{
		distributore.bicchieri -= quantitaAcquistare;
	}

	public static void erogazioneBacchetta(Macchinetta distributore, int quantitaAcquistare) // Aggiorna numero bacchette
	{
		if (distributore.bacchette >= quantitaAcquistare)
			distributore.bacchette -= quantitaAcquistare;
		else
		{
			for (int i = (quantitaAcquistare - distributore.bacchette); i > 0; i--)
				System.out.println("Gira con il dito. Occhio che scotta");
			distributore.bacchette = 0;
		}
	}

	public static void erogazioneResto(Macchinetta distributore, Prodotto bevanda, double subTotale, int quantitaAcquistare)
	{
		double restoDovuto = subTotale - (bevanda.getPrezzo() * quantitaAcquistare);
		if (distributore.resto >= restoDovuto) // Aggiorna resto distributore
		{
			distributore.resto -= (restoDovuto);
			System.out.println(String.format("Il tuo resto: %.2f€" , restoDovuto));
			distributore.resto += subTotale;
		} else // Dà tutto il resto e per il prossimo utilizzo ottiene l'attuale subtotale
		{
			System.out.println(String.format("Il tuo resto: %.2f€" , restoDovuto));
			distributore.resto = subTotale;
		}
	}

	public static void stampaProdotti(Macchinetta distributore) // Stampa personalizzata di ID, nome e prezzo
	{
		for (Prodotto p : distributore.prodotti)
		{
			System.out.println(String.format("Id: %d, prodotto: %s, prezzo: %.2f€ ", p.getId(), p.getNome(), p.getPrezzo()));
		}
	}

	public static ArrayList<Prodotto> inizializzazioneProdotti()
	{
		// Prodotto(final String nome, final int id, double prezzo, final boolean bevandaCalda, int
		// quantita)
		Prodotto id10 = new Prodotto("Acqua calda", 10, 0.4, true, 5);
		Prodotto id11 = new Prodotto("Caffe", 11, 0.5, true, 5);
		Prodotto id12 = new Prodotto("Te caldo", 12, 0.7, true, 5);
		Prodotto id13 = new Prodotto("Cioccolata", 13, 0.8, true, 5);
		Prodotto id14 = new Prodotto("Cappuccino", 14, 0.7, true, 5);
		Prodotto id15 = new Prodotto("Ginseng", 15, 0.8, true, 5);
		Prodotto id20 = new Prodotto("Acqua Ferragni", 20, 5.90, false, 6);
		Prodotto id21 = new Prodotto("Acqua Frizzante", 21, 0.90, false, 6);
		Prodotto id22 = new Prodotto("Aranciata", 22, 1.30, false, 6);
		Prodotto id23 = new Prodotto("Cola", 23, 1.50, false, 6);
		Prodotto id24 = new Prodotto("Energy brick", 24, 2.50, false, 6);
		ArrayList<Prodotto> listaProdotti = new ArrayList<Prodotto>();
		listaProdotti.add(id10);
		listaProdotti.add(id11);
		listaProdotti.add(id12);
		listaProdotti.add(id13);
		listaProdotti.add(id14);
		listaProdotti.add(id15);
		listaProdotti.add(id20);
		listaProdotti.add(id21);
		listaProdotti.add(id22);
		listaProdotti.add(id23);
		listaProdotti.add(id24);
		return listaProdotti;
	}

}
