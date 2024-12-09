package distributore;

import java.util.ArrayList;

public class Macchinetta
{
	public int resto;
	public int zucchero;
	public int bacchette;
	public int bicchieri;
	public ArrayList<Prodotto> prodotti;

	public Macchinetta(ArrayList<Prodotto> prodotti, int resto, int zucchero, int bacchette, int bicchieri) //Costruttore macchinetta
	{
		this.resto = resto;
		this.zucchero = zucchero;
		this.bacchette = bacchette;
		this.bicchieri = bicchieri;
		this.prodotti = prodotti;
	}

}
