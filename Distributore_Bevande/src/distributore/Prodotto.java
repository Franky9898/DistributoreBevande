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

}
