package distributore;

import java.util.ArrayList;

public class Main
{
	public static void funzioneMacchinetta(Macchinetta distributore)
	{

		Prodotto prodottoSelezionato = Macchinetta.selezioneIdProdotto(distributore); //CONTROLLARE SELEZIONE ID PRODOTTTO
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
			//Scanner inputUtente = new Scanner(System.in);
			System.out.println("Non c'è abbastanza resto. Vuoi continuare a costo maggiorato? Premi 1 per sì");
			int conferma = 0; //inputUtente.nextInt();
			//inputUtente.close();
			if (conferma != 1)
				System.exit(0);
		}
		if (!prodottoSelezionato.getBevandaCalda()) //Bevanda Fredda
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

	}
	//CREARE INIZIALIZZAZIONE OGGETTI

	public static void main(String[] args)
	{
		Moneta dieciCent = new Moneta(0.1);
		Moneta[] moneteValide = { dieciCent };
		Prodotto caffe = new Prodotto("Caffe", 1, 0.5, true, 4);
		Prodotto the = new Prodotto("The caldo", 2, 0.8, true, 5);
		ArrayList<Prodotto> listaProdotti = new ArrayList<Prodotto>();
		listaProdotti.add(the);
		listaProdotti.add(caffe);
		Macchinetta distributore = new Macchinetta(listaProdotti, moneteValide, 100, 10, 10, 10);
		funzioneMacchinetta(distributore);
	}
}
