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

	//Prodotto(final String nome, final int id, double prezzo, final boolean bevandaCalda, int quantita)
	public Macchinetta(ArrayList<Prodotto> prodotti, final Moneta[] moneteValide, double resto, int zucchero, int bacchette, int bicchieri) //Costruttore macchinetta
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

	public static Prodotto selezioneIdProdotto(Macchinetta distributore, Scanner scanner) //Return null se codiceOperatore
	{
		int id = -1;
		while (id < 10) //Gli id devono iniziare da 10
		{
			System.out.println("Inserisci id prodotto: ");
			id = scanner.nextInt();
			if (id != Operatore.codiceOperatore)
			{
				Prodotto prodottoSelezionato = controlloId(distributore, id);
				if (prodottoSelezionato != null && prodottoSelezionato.getQuantita() > 0)
					return prodottoSelezionato;
				else if (prodottoSelezionato.getQuantita() == 0)
				{
					System.out.println("Prodotto non disponibile");
					id = -1;
					continue;
				} else
				{
					System.out.println("Id non valido");
					continue;
				}
			}
		}
		return null;
	}

	public static Prodotto controlloId(Macchinetta distributore, int inputId)
	{
		for (Prodotto prodotto : distributore.prodotti)
		{
			if (prodotto.getId() == inputId)
			{
				return prodotto;
			}
		}
		return null;
	}

	public static boolean controlloResto(Macchinetta distributore, Prodotto bevanda, double subTotale)
	{
		if (distributore.resto > (subTotale - bevanda.getPrezzo()))
			return true;
		else
			return false;
	}

	public static void selezioneZucchero(Macchinetta distributore, Scanner scanner) //Scanner input)
	{
		boolean conferma = true;
		int zucchero = -1;
		while (conferma)
		{
			if (zucchero > -1 && zucchero < 6 && zucchero <= distributore.zucchero)
			{
				distributore.zucchero -= zucchero;
				conferma = false;
			} else if ((zucchero <= -1 || zucchero >= 6) && zucchero != 10)
			{
				System.out.println("Selezionare quantità zucchero tra 0 e 5. Premere 10 per annullare:  ");
				zucchero = scanner.nextInt();
			} else
			{
				System.out.println("Zucchero non sufficiente, selezionare quantità minore o premere 10 per annullare.");
				zucchero = scanner.nextInt();
				if (zucchero == 10)
					System.exit(0);
			}
		}
	}

	public static void controlloBicchieri(Macchinetta distributore)
	{
		if (distributore.bicchieri > 0)
			distributore.bicchieri--;
		else
		{
			System.out.println("Non ci sono abbastanza bicchieri");
			System.exit(0);
		}
	}

	public static void erogazioneBacchetta(Macchinetta distributore)
	{
		if (distributore.bacchette > 0)
			distributore.bacchette--;
		else
			System.out.println("Gira con il dito. Occhio che scotta");
	}

	public static void erogazioneResto(Macchinetta distributore, Prodotto bevanda, double subTotale)
	{
		double restoDovuto = subTotale - bevanda.getPrezzo();
		if (distributore.resto >= restoDovuto)
		{

			distributore.resto -= (restoDovuto);
			System.out.println("Il tuo resto: " + restoDovuto);
			distributore.resto += subTotale;
		} else
		{
			System.out.println("Il tuo resto: " + distributore.resto);
			distributore.resto = subTotale;
		}
	}

	public static ArrayList<Prodotto> inizializzazioneProdotti()
	{
		//Prodotto(final String nome, final int id, double prezzo, final boolean bevandaCalda, int quantita)
		Prodotto caffe = new Prodotto("Caffe", 11, 0.5, true, 4);
		Prodotto the = new Prodotto("The caldo", 12, 0.8, true, 5);
		Prodotto acquaNaturale = new Prodotto("Acqua Ferragni", 13, 5.95, false, 6);
		ArrayList<Prodotto> listaProdotti = new ArrayList<Prodotto>();
		listaProdotti.add(the);
		listaProdotti.add(caffe);
		listaProdotti.add(acquaNaturale);
		return listaProdotti;
	}

}
