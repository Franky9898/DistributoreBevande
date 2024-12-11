package distributore;

import java.util.Scanner;

public class Moneta
{
	double valore;

	public Moneta(double valore)
	{
		this.valore = valore;
	}

	public static double inserisciMoneta(Moneta[] moneteValide)
	{
		Scanner input = new Scanner(System.in);
		double subTotale = 0;
		int c = 0;
		System.out.println("Inserisci moneta: ");
		double valoreMoneta = input.nextDouble();
		input.close();
		for (int i = 0; i < moneteValide.length; i++)
		{
			if (valoreMoneta == moneteValide[i].valore)
			{
				c++;
				subTotale += valoreMoneta;
				break;
			}
		}
		if (c == moneteValide.length)
			return subTotale;
		else
		{
			System.out.println("Moneta non valida");
			return 0;
		}
	}
}
