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

	public static int verificaDisponibilitaBevanda(Prodotto bevanda, Scanner scanner) //Verifica disponibilità prodotto in base a quantità che si vuole acquistare
	{
		while (true)
		{
			System.out.print("Seleziona quantità da acquistare (0 per annullare): "); //Scelto 0 perché qualsiasi altra quantità potrebbe essere disponibile e i negativi sono soggetti a typo
			int quantitaAcquistare = Main.getInt(scanner);
			if (quantitaAcquistare == 0)
			{
				System.out.println("Operazione annullata.");
				return 0;
			}
			if (quantitaAcquistare < 0) //Input non valido ricomincia a inserimento quantità
			{
				System.err.println("Input non valido. Inserire un valore maggiore o uguale a 0.");
				continue;
			}
			if (bevanda.quantita < quantitaAcquistare) 
			{
				System.out.println(String.format("Il prodotto non è disponibile nella quantità selezionata. Disponibili: %d. Riprovare.", bevanda.quantita));
				continue;
			}
			// Quantità valida, calcola il prezzo
			System.out.println(String.format("Prezzo totale: %.2f€", bevanda.prezzo * quantitaAcquistare));
			return quantitaAcquistare;
		}
	}

	public static boolean controlloSubTotale(Prodotto bevanda, double subTotale, int quantitaAcquistare)
	{
		if (subTotale >= (bevanda.prezzo * quantitaAcquistare))
			return true;
		return false;
	}

	// Aggiornamento sulle quantita

	public static void erogazioneBevanda(Prodotto bevanda, int quantitaAcquistare)
	{
		bevanda.quantita -= quantitaAcquistare;
		bevanda.quantitaAcquistata += quantitaAcquistare;
		System.out.println((quantitaAcquistare == 1) ? "Ritirare la bevanda." : "Ritirare le bevande.");
	}

}
