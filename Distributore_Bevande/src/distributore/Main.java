package distributore;

import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
	public static void funzioneMacchinetta(Macchinetta distributore)
	{
		Scanner inputUtente = new Scanner(System.in);
		Prodotto prodottoSelezionato = Macchinetta.selezioneIdProdotto(distributore);
		if (prodottoSelezionato == null)
		{
			Operatore.funzioneOperatore(distributore);
		}
		boolean disponibilita = Prodotto.bevandaEsaurita(prodottoSelezionato);
		if (disponibilita)
			System.exit(0);
		boolean acquistabile = false;
		double subTotale = 0;
		do
		{
			subTotale = Moneta.inserisciMoneta(distributore.moneteValide);
			acquistabile = Prodotto.controlloSubTotale(prodottoSelezionato, subTotale);
		} while (!acquistabile);
		boolean restabbile = Macchinetta.controlloResto(distributore, prodottoSelezionato, subTotale);
		if (!restabbile)
		{
			System.out.println("Non c'è abbastanza resto. Vuoi continuare a costo maggiorato? Premi 1 per sì");
			int conferma = inputUtente.nextInt();
			if (conferma != 1)
				System.exit(0);
		}
		if (!prodottoSelezionato.bevandaCalda) //Bevanda Fredda
		{
			Prodotto.erogazioneBevanda(prodottoSelezionato);
			System.out.println("Ritirare la bevanda");
			Macchinetta.erogazioneResto(distributore, prodottoSelezionato, subTotale);
			System.exit(0);
		}
		Macchinetta.controlloBicchieri(distributore);
		Macchinetta.selezioneZucchero(distributore);
		Macchinetta.erogazioneBacchetta(distributore);
		Prodotto.erogazioneBevanda(prodottoSelezionato);
		System.out.println("Ritirare la bevanda");
		Macchinetta.erogazioneResto(distributore, prodottoSelezionato, subTotale);
		System.exit(0);
		inputUtente.close();
	}

	public static void main(String[] args)
	{
		Moneta dieciCent = new Moneta(0.1);
		Prodotto caffe = new Prodotto("Caffe", 1, 0.5, true, 4);
		Prodotto the = new Prodotto("The caldo", 2, 0.8, true, 5);
		ArrayList<Prodotto> listaProdotti = new ArrayList<Prodotto>();
		listaProdotti.add(the);
		listaProdotti.add(caffe);
		//Macchinetta distributore = new Macchinetta(listaProdotti, 100, 10, 10, 10);
		//Operatore.funzioneOperatore(distributore);
	}
}
