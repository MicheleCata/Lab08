package it.polito.tdp.extflightdelays.model;

public class Voli {
	
	private int id_partenza;
	private int id_arrivo;
	private double distanza;

	public Voli(int id_partenza, int id_arrivo, double distanza) {
		super();
		this.id_partenza = id_partenza;
		this.id_arrivo = id_arrivo;
		this.distanza = distanza;
	}

	public int getId_partenza() {
		return id_partenza;
	}

	public void setId_partenza(int id_partenza) {
		this.id_partenza = id_partenza;
	}

	public int getId_arrivo() {
		return id_arrivo;
	}

	public void setId_arrivo(int id_arrivo) {
		this.id_arrivo = id_arrivo;
	}

	public double getDistanza() {
		return distanza;
	}

	public void setDistanza(double distanza) {
		this.distanza = distanza;
	}
	
	@Override
	public String toString() {
		return this.id_partenza+" "+this.id_arrivo+" "+this.distanza;
	}
	
}