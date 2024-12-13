package distributore;

public class Prodotto
{
	private final String nome;
	private final int id;
	private double prezzo;
	private final boolean bevandaCalda;
	private int quantita;
	private int quantitaAcquistata;

	public Prodotto(final String nome, final int id, double prezzo, final boolean bevandaCalda, int quantita)
	{
		if (id < 10)
			throw new IllegalArgumentException("L'id prodotto deve iniziare da 10");
		if (quantita < 1)
			throw new IllegalArgumentException("La quantita iniziale del prodotto non può essere non positiva.");
		if (prezzo < 0)
			throw new IllegalArgumentException("Il prezzo prodotto non può essere negativo.");
		this.nome = nome;
		this.id = id;
		this.prezzo = prezzo;
		this.bevandaCalda = bevandaCalda;
		this.quantita = quantita;
	}

	public String getNome()
	{
		return nome;
	}

	public int getId()
	{
		return id;
	}

	public double getPrezzo()
	{
		return prezzo;
	}

	public boolean getBevandaCalda()
	{
		return bevandaCalda;
	}

	public int getQuantita()
	{
		return quantita;
	}

	public int getQuantitaAcquistata()
	{
		return quantitaAcquistata;
	}

	public void setPrezzo(double nuovoPrezzo)
	{
		this.prezzo = nuovoPrezzo;
	}

	public void setQuantita(int nuovaQuantita)
	{
		this.quantita = nuovaQuantita;
	}

	public void setQuantitaAcquistata(int nuovaQuantitaAcquistata)
	{
		this.quantitaAcquistata = nuovaQuantitaAcquistata;
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
		bevanda.quantitaAcquistata++;
	}

}
