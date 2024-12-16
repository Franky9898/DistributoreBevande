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
			int id = scanner.nextInt();
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

	public static boolean controlloResto(Macchinetta distributore, Prodotto bevanda, double subTotale) // Controlla se la macchinetta ha abbastanza resto
	{
		if (distributore.resto > (subTotale - bevanda.getPrezzo()))
			return true;
		else
			return false;
	}

	public static int selezioneZucchero(Macchinetta distributore, Scanner scanner) // Permette di selezionare la quantità dello zucchero
	{
		boolean conferma = true;
		int zucchero = -1;
		while (conferma)
		{
			if (zucchero > -1 && zucchero < 6 && zucchero <= distributore.zucchero)
			{
				conferma = false;
				return zucchero;
			} else if ((zucchero <= -1 || zucchero >= 6) && zucchero != 10) // Input non valido
			{
				System.out.println("Selezionare quantità zucchero tra 0 e 5. Premere 10 per annullare:  ");
				zucchero = scanner.nextInt();
				if (zucchero == 10) // Premuto 10, ricomincia
					return 10;
			} else
			{
				System.out.println("Zucchero non sufficiente, selezionare quantità minore o premere 10 per annullare.");
				zucchero = scanner.nextInt();
				if (zucchero == 10) // Premuto 10, ricomincia
					return 10;
			}
		}
		return 0;
	}

	public static void aggiornamentoZucchero(Macchinetta distributore, int zucchero)
	{
		distributore.zucchero -= zucchero; // Aggiorna lo zucchero nel distributore
	} 

	public static boolean controlloBicchieri(Macchinetta distributore) // Aggiorna numero bicchieri
	{
		if (distributore.bicchieri > 0)
			return true;
		else
		{
			System.out.println("Non ci sono abbastanza bicchieri");
			return false;
		}
	}

	public static void aggiornamentoBicchieri(Macchinetta distributore)
	{
		distributore.bicchieri--;
	}

	public static void erogazioneBacchetta(Macchinetta distributore) // Aggiorna numero bacchette
	{
		if (distributore.bacchette > 0)
			distributore.bacchette--;
		else
			System.out.println("Gira con il dito. Occhio che scotta");
	}

	public static void erogazioneResto(Macchinetta distributore, Prodotto bevanda, double subTotale)
	{
		double restoDovuto = subTotale - bevanda.getPrezzo();
		if (distributore.resto >= restoDovuto) // Aggiorna resto distributore
		{
			distributore.resto -= (restoDovuto);
			System.out.println("Il tuo resto: " + restoDovuto);
			distributore.resto += subTotale;
		} else // Dà tutto il resto e per il prossimo utilizzo ottiene l'attuale subtotale
		{
			System.out.println("Il tuo resto: " + distributore.resto);
			distributore.resto = subTotale;
		}
	}

	public static void stampaProdotti(Macchinetta distributore) // Stampa personalizzata di ID, nome e prezzo
	{
		for (Prodotto p : distributore.prodotti)
		{
			System.out.println("Id: " + p.getId() + ", prodotto: " + p.getNome() + ", prezzo: " + p.getPrezzo());
		}
	}

	public static ArrayList<Prodotto> inizializzazioneProdotti()
	{
		// Prodotto(final String nome, final int id, double prezzo, final boolean bevandaCalda, int quantita)
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
