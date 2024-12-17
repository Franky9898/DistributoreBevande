package distributore;

import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
	public static void funzioneMacchinetta(Macchinetta distributore, Scanner scanner) //Funzione principale, contiene le scelte dei prodotti e le azioni operatore
	{
		while (true)
		{
			Prodotto prodottoSelezionato = Macchinetta.selezioneIdProdotto(distributore, scanner); 
			if (prodottoSelezionato == null) //Il null porta dall'operatore
			{
				Operatore.funzioneOperatore(distributore, scanner);
				continue;
			}
			boolean disponibilita = Prodotto.bevandaEsaurita(prodottoSelezionato);
			if (disponibilita) //Il prodotto è esaurito, quindi si ricomincia dall'inizio
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
			boolean controlloBicchieri= Macchinetta.controlloBicchieri(distributore);
			if (!controlloBicchieri) //Se non ci sono abbastanza bicchieri riparte il loop
				continue;
			int zucchero = Macchinetta.selezioneZucchero(distributore, scanner);
			if(zucchero == 10) //Se l'utente ha premuto "annulla" riparte il loop
				continue;
			Macchinetta.erogazioneBacchetta(distributore);
			Prodotto.erogazioneBevanda(prodottoSelezionato);
			System.out.println("Ritirare la bevanda");
			Operatore.aggiornamentoIncasso(distributore, prodottoSelezionato, subTotale, restabbile);
			Macchinetta.erogazioneResto(distributore, prodottoSelezionato, subTotale);
			Macchinetta.aggiornamentoBicchieri(distributore);
			Macchinetta.aggiornamentoZucchero(distributore, zucchero);
			continue; 
		}
	}

	public static void main(String[] args)
	{ 
		Scanner scanner = new Scanner(System.in);
		Moneta[] moneteValide = Moneta.inizializzazioneMoneteValide();
		ArrayList<Prodotto> listaProdotti = Macchinetta.inizializzazioneProdotti();

		//Macchinetta(ArrayList<Prodotto> prodotti, final Moneta[] moneteValide, double resto, int zucchero, int bacchette, int bicchieri)
		Macchinetta distributore = new Macchinetta(listaProdotti, moneteValide, 5, 10, 10, 10);
		funzioneMacchinetta(distributore, scanner);
		scanner.close();
	}
}
