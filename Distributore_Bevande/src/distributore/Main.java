package distributore;

import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
	public static int getInt(Scanner scanner) // Gestisce il typo da parte dell'utente
	{
		while (true)
		{
			try
			{
				return scanner.nextInt();
			} catch (Exception e)
			{
				System.out.println("Perfavore inserisci un numero intero.");
				scanner.next();
			}
		}
	}

	public static double getDouble(Scanner scanner) // Gestisce il typo da parte dell'utente
	{
		while (true)
		{
			try
			{
				return scanner.nextDouble();
			} catch (Exception e)
			{
				System.out.println("Perfavore inserisci un numero.");
				scanner.next();
			}
		}
	}

	public static boolean getBoolean(Scanner scanner) // Gestisce il typo da parte dell'utente
	{
		while (true)
		{
			try
			{
				return scanner.nextBoolean();
			} catch (Exception e)
			{
				System.out.println("Perfavore inserisci true o false.");
				scanner.next();
			}
		}
	}

	public static void funzioneMacchinetta(Macchinetta distributore, Scanner scanner) // Funzione principale, contiene le scelte dei prodotti e le azioni operatore
	{
		while (true)
		{
			Prodotto prodottoSelezionato = Macchinetta.selezioneIdProdotto(distributore, scanner);
			if (prodottoSelezionato == null) // Il null porta dall'operatore
			{
				Operatore.funzioneOperatore(distributore, scanner);
				continue;
			}
			int quantitaAcquistare = Prodotto.verificaDisponibilitaBevanda(prodottoSelezionato, scanner);
			if (quantitaAcquistare == 0) // Il prodotto è esaurito o l'utente ha annullato, ricomincia dall' inizio
				continue;
			boolean acquistabile = false;
			double subTotale = 0;
			do // Gestisce l'inserimento delle monete fino a raggiungere il subtotale richiesto
			{
				subTotale = Moneta.inserisciMoneta(distributore.moneteValide, subTotale, scanner);
				acquistabile = Prodotto.controlloSubTotale(prodottoSelezionato, subTotale, quantitaAcquistare);
			} while (!acquistabile);
			boolean restabbile = Macchinetta.controlloResto(distributore, prodottoSelezionato, subTotale, quantitaAcquistare);
			if (!restabbile)
			{
				System.out.println("Non c'è abbastanza resto. Vuoi continuare a costo maggiorato? Premi 1 per sì");
				int conferma = 0;
				conferma = getInt(scanner);
				if (conferma != 1)
					continue;
			}
			if (!prodottoSelezionato.getBevandaCalda()) // Bevanda Fredda
			{
				Prodotto.erogazioneBevanda(prodottoSelezionato, quantitaAcquistare);
				String output = (quantitaAcquistare == 1) ? "Ritirare la bevanda" : "Ritirare le bevande";
				System.out.println(output);
				Operatore.aggiornamentoIncasso(distributore, prodottoSelezionato, subTotale, restabbile, quantitaAcquistare);
				Macchinetta.erogazioneResto(distributore, prodottoSelezionato, subTotale, quantitaAcquistare);
				continue;
			}
			boolean controlloBicchieri = Macchinetta.controlloBicchieri(distributore, quantitaAcquistare);
			if (!controlloBicchieri) // Se non ci sono abbastanza bicchieri riparte il loop
				continue;
			int[] listaZucchero = Macchinetta.selezioneZucchero(distributore, quantitaAcquistare, scanner);
			if (listaZucchero == null) // Se l'utente ha premuto "annulla" riparte il loop
				continue;
			Macchinetta.erogazioneBacchetta(distributore, quantitaAcquistare);
			Prodotto.erogazioneBevanda(prodottoSelezionato, quantitaAcquistare);
			String output = (quantitaAcquistare == 1) ? "Ritirare la bevanda" : "Ritirare le bevande";
			System.out.println(output);
			Operatore.aggiornamentoIncasso(distributore, prodottoSelezionato, subTotale, restabbile, quantitaAcquistare);
			Macchinetta.erogazioneResto(distributore, prodottoSelezionato, subTotale, quantitaAcquistare);
			Macchinetta.aggiornamentoBicchieri(distributore, quantitaAcquistare);
			Macchinetta.aggiornamentoZucchero(distributore, listaZucchero);
			continue;
		}
	}

	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		Moneta[] moneteValide = Moneta.inizializzazioneMoneteValide();
		ArrayList<Prodotto> listaProdotti = Macchinetta.inizializzazioneProdotti();
		// Macchinetta(ArrayList<Prodotto> prodotti, final Moneta[] moneteValide, double resto, int zucchero, int bacchette, int bicchieri)
		Macchinetta distributore = new Macchinetta(listaProdotti, moneteValide, 5, 10, 10, 10);
		funzioneMacchinetta(distributore, scanner);
		scanner.close();
	}
}
