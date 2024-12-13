package distributore;

import java.util.Scanner;

public class Moneta
{
	double valore;

	public Moneta(double valore)
	{
		this.valore = valore;
	}

	public static double inserisciMoneta(Moneta[] moneteValide, double subTotale, Scanner scanner)
	{
		int c = 0;
		double valoreMoneta = -1.0;
		while (valoreMoneta < 0.1 || valoreMoneta > 2.0)
		{
			System.out.println("Inserisci moneta: ");
			valoreMoneta = scanner.nextDouble();
		}
		for (int i = 0; i < moneteValide.length; i++)
		{
			if (valoreMoneta == moneteValide[i].valore)
			{
				subTotale += valoreMoneta;
				System.out.println("Subtotale: " + subTotale);
				break;
			} else
				c++;
		}
		if (c != moneteValide.length)
			return subTotale;
		else
		{
			System.out.println("Moneta non valida");
			return 0;
		}
	}

	public static Moneta[] inizializzazioneMoneteValide()
	{
		Moneta dieciCent = new Moneta(0.1);
		Moneta ventiCent = new Moneta(0.2);
		Moneta cinquantaCent = new Moneta(0.5);
		Moneta unoEuro = new Moneta(1.0);
		Moneta dueEuro = new Moneta(2.0);
		Moneta[] moneteValide = { dieciCent, ventiCent, cinquantaCent, unoEuro, dueEuro };
		return moneteValide;
	}
}
