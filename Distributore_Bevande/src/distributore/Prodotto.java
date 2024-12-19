package distributore;

import java.util.Scanner;

public class Prodotto
{
	private final String nome;
	private final int id;
	private double prezzo;
	private final boolean bevandaCalda;
	private int quantita;
	private int quantitaAcquistata;

	// Costruttore

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

	// Metodo che restituisce un boolean se la bevanda è presente o meno

	public static int bevandaEsaurita(Prodotto bevanda, int quantitaAcquistare, Scanner scanner)
	{
		if (bevanda.quantita == 0)
		{
			System.out.println("Il prodotto è esaurito. Non è possibile acquistarlo.");
			return 0; // Prodotto non disponibile
		}
		if (bevanda.quantita >= quantitaAcquistare)
		{
			System.out.println(String.format("Prezzo: %.2f€", bevanda.prezzo * quantitaAcquistare));
			bevanda.quantita -= quantitaAcquistare;
			return quantitaAcquistare; // Acquisto effettuato
		} else
		{
			System.out.println("Quantità insufficiente. Prodotto esaurito per la quantità richiesta.");
			System.out.println("La quantità disponibile è: " + bevanda.quantita);

			System.out.println("Procedere con la quantità disponibile? Premi 1 per sì.");
			int procedere = Main.getInt(scanner);
			if (procedere == 1)
			{
				int quantitaEffettiva = bevanda.quantita;
				System.out.println(String.format("Prezzo: %.2f€", bevanda.prezzo * bevanda.quantita));
				
				bevanda.quantita = 0;
				return quantitaEffettiva; // Acquisto effettuato
			} else
			{
				System.out.println("Operazione annullata.");
				return 0; // Nessun acquisto
			}
		}
	}

	public static boolean controlloSubTotale(Prodotto bevanda, double subTotale, int quantitaAcquistare)
	{
		if (subTotale >= (bevanda.prezzo * quantitaAcquistare))
			return true;
		else
			return false;
	}

	// Aggiornamento sulle quantita

	public static void erogazioneBevanda(Prodotto bevanda, int quantitaAcquistare)
	{
		//bevanda.quantita -= quantitaAcquistare; l'ho messo in bevandaEsaurita
		bevanda.quantitaAcquistata += quantitaAcquistare;
	}

}
