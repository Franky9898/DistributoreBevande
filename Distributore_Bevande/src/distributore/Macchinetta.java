package distributore;

import java.util.ArrayList;

public class Macchinetta
{
	private int resto;
	private int zucchero;
	private int bacchette;
	private int bicchieri;
	private ArrayList<Prodotto> prodotti;

	public Macchinetta(ArrayList<Prodotto> prodotti, int resto, int zucchero, int bacchette, int bicchieri) //Costruttore macchinetta
	{
		this.resto = resto;
		this.zucchero = zucchero;
		this.bacchette = bacchette;
		this.bicchieri = bicchieri;
		this.prodotti = prodotti;
	}

}
