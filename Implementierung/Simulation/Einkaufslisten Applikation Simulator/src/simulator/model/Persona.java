package simulator.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Persona implements Serializable{
	private String bezeichnung;
	private Map<String,Produkt> produktMap = new HashMap<String, Produkt>(); // "butter", butter
	private int einkaufRangeMin;
	private int einkaufRangeMax;
	private List<Integer> einkaufsAblauf;
	
	//Konstruktor
	public Persona(String bezeichnung, int einkaufRangeMin, int einkaufRangeMax) {
		super();
		this.bezeichnung = bezeichnung;
		this.einkaufRangeMin = einkaufRangeMin;
		this.einkaufRangeMax = einkaufRangeMax;
	}

	//Getters und Setters
	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public Map<String, Produkt> getProduktMap() {
		return produktMap;
	}

	public void setProduktMap(Map<String, Produkt> produktMap) {
		this.produktMap = produktMap;
	}

	public List<Integer> getEinkaufsAblauf() {
		return einkaufsAblauf;
	}

	public void setEinkaufsAblauf(List<Integer> einkaufsAblauf) {
		this.einkaufsAblauf = einkaufsAblauf;
	}

	public int getEinkaufRangeMin() {
		return einkaufRangeMin;
	}

	public void setEinkaufRangeMin(int einkaufRangeMin) {
		this.einkaufRangeMin = einkaufRangeMin;
	}

	public int getEinkaufRangeMax() {
		return einkaufRangeMax;
	}

	public void setEinkaufRangeMax(int einkaufRangeMax) {
		this.einkaufRangeMax = einkaufRangeMax;
	}
	
}
