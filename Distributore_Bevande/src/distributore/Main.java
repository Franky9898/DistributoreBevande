package distributore;

import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
	public static void funzioneMacchinetta(Macchinetta distributore, Scanner scanner) //Non abbiamo modo di salvare il restoDovuto ma non ridato.
	{
		while (true)
		{
			Prodotto prodottoSelezionato = Macchinetta.selezioneIdProdotto(distributore, scanner);
			if (prodottoSelezionato == null)
			{
				Operatore.funzioneOperatore(distributore, scanner);
				continue;
			}
			boolean disponibilita = Prodotto.bevandaEsaurita(prodottoSelezionato);
			if (disponibilita)
				continue;
			boolean acquistabile = false;
			double subTotale = 0;
			do
			{
				subTotale = Moneta.inserisciMoneta(distributore.moneteValide, subTotale, scanner);
				acquistabile = Prodotto.controlloSubTotale(prodottoSelezionato, subTotale);
			} while (!acquistabile);
			boolean restabbile = Macchinetta.controlloResto(distributore, prodottoSelezionato, subTotale);
			if (!restabbile)
			{
				System.out.println("Non c'è abbastanza resto. Vuoi continuare a costo maggiorato? Premi 1 per sì");
				int conferma = 0;
				conferma = scanner.nextInt();
				if (conferma != 1)
					continue;
			}
			if (!prodottoSelezionato.getBevandaCalda()) //Bevanda Fredda
			{
				Prodotto.erogazioneBevanda(prodottoSelezionato);
				System.out.println("Ritirare la bevanda");
				Operatore.aggiornamentoIncasso(distributore, prodottoSelezionato, subTotale, restabbile);
				Macchinetta.erogazioneResto(distributore, prodottoSelezionato, subTotale);
				continue;
			}
			Macchinetta.controlloBicchieri(distributore);
			Macchinetta.selezioneZucchero(distributore, scanner);
			Macchinetta.erogazioneBacchetta(distributore);
			Prodotto.erogazioneBevanda(prodottoSelezionato);
			System.out.println("Ritirare la bevanda");
			Operatore.aggiornamentoIncasso(distributore, prodottoSelezionato, subTotale, restabbile);
			Macchinetta.erogazioneResto(distributore, prodottoSelezionato, subTotale);
			continue;
		}
	}

	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		Moneta[] moneteValide = Moneta.inizializzazioneMoneteValide();
		ArrayList<Prodotto> listaProdotti = Macchinetta.inizializzazioneProdotti();

		//Macchinetta(ArrayList<Prodotto> prodotti, final Moneta[] moneteValide, double resto, int zucchero, int bacchette, int bicchieri)
		Macchinetta distributore = new Macchinetta(listaProdotti, moneteValide, 0.04, 10, 10, 10);
		funzioneMacchinetta(distributore, scanner);
		scanner.close();
	}
}
