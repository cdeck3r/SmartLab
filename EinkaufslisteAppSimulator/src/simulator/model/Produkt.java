package simulator.model;

import java.util.List;

/**
 * Produkt für sollEinkaufsliste
 * @author tchwangnwou
 *
 */
public class Produkt {
	
	private String name;
	private int rangeMin;
	private int rangeMax;
	private List<Integer> verbrauchsablauf;
	
	//Konstruktor
	public Produkt(String name, int rangeMin, int rangeMax) {
		super();
		this.name = name;
		this.rangeMin = rangeMin;
		this.rangeMax = rangeMax;
	}

	//Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getRangeMin() {
		return rangeMin;
	}

	public void setRangeMin(int rangeMin) {
		this.rangeMin = rangeMin;
	}

	public int getRangeMax() {
		return rangeMax;
	}

	public void setRangeMax(int rangeMax) {
		this.rangeMax = rangeMax;
	}

	public List<Integer> getVerbrauchsablauf() {
		return verbrauchsablauf;
	}

	public void setVerbrauchsablauf(List<Integer> verbrauchsablauf) {
		this.verbrauchsablauf = verbrauchsablauf;
	}
	
	
}
