package distributore;

import java.util.Scanner;

public class Moneta
{
	double valore;

	public Moneta(double valore)
	{
		this.valore = valore;
	}

	public static double inserisciMoneta(Moneta[] moneteValide, double subTotale, Scanner scanner) // Inserisci monete accettate dal distributore
	{
		double valoreMoneta;
		do
		{
			System.out.println("Inserisci moneta (valori accettati: 0.1, 0.2, 0.5, 1.0, 2.0): ");
			valoreMoneta = Main.getDouble(scanner);
		} while (valoreMoneta < 0.1 || valoreMoneta > 2.0);
		for (Moneta moneta : moneteValide)
		{
			if (valoreMoneta == moneta.valore)
			{
				subTotale += valoreMoneta;
				System.out.printf("Subtotale: %.2f€%n", subTotale);
				return subTotale;
			}
		}
		// Se nessuna moneta valida è stata trovata
		System.out.println("Moneta non valida.");
		return subTotale;
	}

	public static Moneta[] inizializzazioneMoneteValide()
	{
		return new Moneta[] { new Moneta(0.1), new Moneta(0.2), new Moneta(0.5), new Moneta(1.0), new Moneta(2.0) };
	}
}
