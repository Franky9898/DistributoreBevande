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

	//Prodotto(final String nome, final int id, double prezzo, final boolean bevandaCalda, int quantita)
	public Macchinetta(ArrayList<Prodotto> prodotti, double resto, int zucchero, int bacchette, int bicchieri) //Costruttore macchinetta
	{
		this.resto = resto;
		this.zucchero = zucchero;
		this.bacchette = bacchette;
		this.bicchieri = bicchieri;
		this.prodotti = prodotti;
	}

	public static boolean controlloResto(Macchinetta distributore, Prodotto bevanda, double subTotale)
	{
		if (distributore.resto > (subTotale - bevanda.prezzo))
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
					conferma = false;
			}
		}
		input.close();
	}

	public static void controlloBicchieri(Macchinetta distributore)
	{
		if (distributore.bicchieri > 0)
			distributore.bicchieri--;
		else
			System.out.println("Non ci sono abbastanza bicchieri");
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
		double restoDovuto = subTotale - bevanda.prezzo;
		distributore.resto -= (restoDovuto);
		System.out.println("Il tuo resto: " + restoDovuto);
	}

}
