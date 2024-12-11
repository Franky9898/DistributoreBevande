package distributore;

public class Prodotto
{
	public final String nome;
	public final int id;
	public double prezzo;
	public final boolean bevandaCalda;
	public int quantita;
	public int quantitaAcquistata;

	public Prodotto(final String nome, final int id, double prezzo, final boolean bevandaCalda, int quantita)
	{
		this.nome = nome;
		this.id = id;
		this.prezzo = prezzo;
		this.bevandaCalda = bevandaCalda;
		this.quantita = quantita;
	}

	public int getId()
	{
		return id;
	}

	public static boolean bevandaEsaurita(Prodotto bevanda)
	{
		if (bevanda.quantita > 0)
		{
			System.out.println("Prezzo: " + bevanda.prezzo);
			return false;
		} else
		{
			System.out.println("Prodotto esaurito.");
			return true;
		}
	}

	public static boolean controlloSubTotale(Prodotto bevanda, double subTotale)
	{
		if (subTotale >= bevanda.prezzo)
			return true;
		else
			return false;
	}

	public static void erogazioneBevanda(Prodotto bevanda)
	{
		bevanda.quantita--;
	}

}
