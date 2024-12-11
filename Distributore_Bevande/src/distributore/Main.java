package distributore;

import java.util.ArrayList;

public class Main
{
	public static void main(String[] args)
	{
		Prodotto caffe = new Prodotto("Caffe", 1, 0.5, true, 4);
		Prodotto the = new Prodotto("The caldo", 2, 0.8, true, 5);
		ArrayList<Prodotto> listaProdotti = new ArrayList<Prodotto>();
		listaProdotti.add(the);
		listaProdotti.add(caffe);
		Macchinetta distributore = new Macchinetta(listaProdotti, 100, 10, 10, 10);
		Operatore.funzioneOperatore(distributore);
	}
}
