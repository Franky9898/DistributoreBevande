package distributore;

public class Prodotto
{
	public String nome;
	public int id;
	public double prezzo;
	private boolean bevandaCalda;
	int quantita;
	int quantitaAcquistata;

	public Prodotto(String nome, int id, double prezzo, boolean bevandaCalda, int quantita)
	{
		this.nome = nome;
		this.id = id;
		this.prezzo = prezzo;
		this.bevandaCalda = bevandaCalda;
		this.quantita = quantita;
	}

}
