package distributore;

import java.util.ArrayList;
import java.util.Scanner;

public class Macchinetta
{
	public double resto;
	public int zucchero;
	public int bacchette;
	public int bicchieri;
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
	}

	public ArrayList<Prodotto> getProdotti()
	{
		return prodotti;
	}

	public static Prodotto selezioneIdProdotto(Macchinetta distributore) //AAAAAAAAAAAHHHHHHHHH
	{
		int inputId = -1;
		do
		{
			Scanner s = new Scanner(System.in);
			System.out.println("Inserisci l'ID del prodotto:");

			if (!s.hasNextInt())
			{
				System.out.println("Errore: Inserisci un ID numerico valido.");
				s.next();
				continue;
			}

			inputId = s.nextInt();

			Prodotto prodottoSelezionato = controlloId(distributore, inputId);

			if (prodottoSelezionato != null)
			{
				s.close();
				return prodottoSelezionato;
			} else if (inputId == Operatore.codiceOperatore)
			{
				System.out.println("Benvenuto operatore.");
				s.close();
				return null;
			} else
			{
				System.out.println("Errore: ID prodotto non valido.");
			}
			s.close();
		} while (inputId == -1);
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

	public static void selezioneZucchero(Macchinetta distributore)
	{
		Scanner input = new Scanner(System.in);
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
				zucchero = input.nextInt();
			} else
			{
				System.out.println("Zucchero non sufficiente, selezionare quantità minore o premere 10 per annullare.");
				zucchero = input.nextInt();
				if (zucchero == 10)
					System.exit(0);
			}
		}
		input.close();
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
		} else
		{
			System.out.println("Il tuo resto: " + distributore.resto);
			distributore.resto = 0;
		}
	}

}
