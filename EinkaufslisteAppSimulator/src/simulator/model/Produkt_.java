package simulator.model;

/**
 * Produkt für IsEinkaufsliste
 *
 */
public class Produkt_ {
	
	 int produkt_id;
	  String Bezeichnung;
	  double verbrauchswarscheinlichkeit;
	  
	  public Produkt_() {
	  }
	  
	  public int getProdukt_id() {
	    return produkt_id;
	  }
	  
	  public void setProdukt_id(int produkt_id) {
	    this.produkt_id = produkt_id;
	  }
	  
	  public String getBezeichnung() {
	    return Bezeichnung;
	  }
	  
	  public void setBezeichnung(String bezeichnung) {
	    Bezeichnung = bezeichnung;
	  }
	  
	  public double getVerbrauchswarscheinlichkeit() {
	    return verbrauchswarscheinlichkeit;
	  }
	  
	  public void setVerbrauchswarscheinlichkeit(double verbrauchswarscheinlichkeit) {
	    this.verbrauchswarscheinlichkeit = verbrauchswarscheinlichkeit;
	  }
}
