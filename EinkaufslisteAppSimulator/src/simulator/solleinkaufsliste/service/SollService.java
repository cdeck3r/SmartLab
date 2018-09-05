package simulator.solleinkaufsliste.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import simulator.model.Persona;
import simulator.model.Produkt;

public class SollService {
	
	//Personas Definition
	//Sportler
	Map<String,Produkt> sportlerProduktMap = new HashMap<String,Produkt>();
	public void setSportlerProduktMap() {
		sportlerProduktMap.put("Butter", new Produkt("Butter", 17, 22));
		sportlerProduktMap.put("Milch", new Produkt("Milch", 3, 7));
		sportlerProduktMap.put("Kaese", new Produkt("Kaese", 7, 10));
		sportlerProduktMap.put("Salami", new Produkt("Salami", 5, 7));
		sportlerProduktMap.put("Joghurt", new Produkt("Joghurt", 20, 25));
	}
	/*hier weitere Personas*/
	
	
	
	
	//erstellt verbrauchs- sowie Einkaufsablauf
	public List<Integer> erstelleVerbrauchsOderEinkaufsablauf(int rangeMin, int rangeMax, int anzahlDatensaetze){
		ArrayList<Integer> verbrauchsablauf = new ArrayList<Integer>();
		int result=0;
		for(int i=0; i<anzahlDatensaetze; i++) {
			result = (int)(Math.random()*(rangeMax-rangeMin))+rangeMin;
			verbrauchsablauf.add(result);
		}
		return verbrauchsablauf;
	}
	
	//kumuliere eine Liste wie den Einkaufsablauf
	public List<Integer> kumuliereEinkaufsablauf(List<Integer> einkaufsAblauf){
		List<Integer> listeKumuliert = new ArrayList<Integer>();
		int result=0;
		for(int i=0; i<einkaufsAblauf.size(); i++) {
			result = result + einkaufsAblauf.get(i);
			listeKumuliert.add(result);
		}
		return listeKumuliert;
	}
	
	//kumuliere Verbrauchsablauf in Abhängigkeit zum einkaufsZeitpunkt
	public List<Integer> kumuliereVerbrauchsablauf(List<Integer> verbrauchsAblauf, List<Integer> einkaufsAblauf ){
		List<Integer> kumulierterVerbrauchsablauf = new ArrayList<Integer>();
		int lastBuy=0;
		
		for(int i=0; i<verbrauchsAblauf.size(); i++) {
			if(lastBuy != Integer.MAX_VALUE) {
				kumulierterVerbrauchsablauf.add(lastBuy+verbrauchsAblauf.get(i));
				lastBuy = this.findeKleinsteGroesserOderGleich(einkaufsAblauf, kumulierterVerbrauchsablauf.get(i));
			}
			else{
				kumulierterVerbrauchsablauf.add(lastBuy);
			}
		}
		return kumulierterVerbrauchsablauf;
	}
	
	//finde den größten Zahleintrag einer Liste (Verbrauchsablauf), der <= einer angegebenen Zahl (Einkaufzeitpunkt).
	public int findeGroesteZahlKleinerGleichKaufzeitpunkt(List<Integer> liste, int einkaufZeitpunkt) {
		int result = 0;
		for(int i : liste) {
			if(i <= einkaufZeitpunkt) {
				result = i;
			}
		}
		return result;
	}
	
	//kleinstes Element was größer oder gleich einen Wert ist
	public int findeKleinsteGroesserOderGleich(List<Integer> einkaufsablauf, int wert) {
		int result = 0;
		for(int i : einkaufsablauf) {
			if(i>=wert) {
				result = i;
				break;
			}
		}
		if(result == 0) {
			result = Integer.MAX_VALUE;
		}
		return result;
	}
	
	//finde den index eines Werta (Einkaufzeitpunkt) aus einer Liste (Einkaufverbrauch)
	public int findeIndexVonEinkaufzeitpunktAusListe(List<Integer> liste, int einkaufZeitpunkt) {
		int index=0;
		for(int i : liste) {
			if(i!=einkaufZeitpunkt) {
				index++;
			}
			if(i==einkaufZeitpunkt) {
				break;
			}
		}
		return index;
	}
	
	//erstelle eine Persona mit verbrauchsdaten (Einkaufsablauf und Verbrauchsablauf sind kummuliert)
	/**************** WENN ALLEN PERSONAS IMMER DIE GLEICHEN PRODUKTE ZUGEWIESEN SIND BRAUCHT MAN KEINE IF BEDINGUNG*****************/ 
	public Persona erstellePersonaMitVerbrauchsdaten(String personaBezeichnung, int einkaufRangeMin, int einkaufRangeMax, int anzahlDatensaetze){
		Persona persona = new Persona(personaBezeichnung, einkaufRangeMin, einkaufRangeMax);
		this.setSportlerProduktMap();
		/*hier weitere Personadefinitionen setzen*/
		persona.setProduktMap(sportlerProduktMap);
		persona.setEinkaufsAblauf(this.kumuliereEinkaufsablauf(this.erstelleVerbrauchsOderEinkaufsablauf(einkaufRangeMin, einkaufRangeMax, anzahlDatensaetze)));
		Produkt butter = persona.getProduktMap().get("Butter");
		Produkt milch = persona.getProduktMap().get("Milch");
		Produkt kaese = persona.getProduktMap().get("Kaese");
		Produkt salami = persona.getProduktMap().get("Salami");
		Produkt Joghurt = persona.getProduktMap().get("Joghurt");
		List<Integer> butterNormalVerbrauchsverlauf = this.erstelleVerbrauchsOderEinkaufsablauf(butter.getRangeMin(), butter.getRangeMax(), anzahlDatensaetze);
		List<Integer> milchNormalVerbrauchsverlauf = this.erstelleVerbrauchsOderEinkaufsablauf(milch.getRangeMin(), milch.getRangeMax(), anzahlDatensaetze);
		List<Integer> kaeseNormalVerbrauchsverlauf = this.erstelleVerbrauchsOderEinkaufsablauf(kaese.getRangeMin(), kaese.getRangeMax(), anzahlDatensaetze);
		List<Integer> salamiNormalVerbrauchsverlauf = this.erstelleVerbrauchsOderEinkaufsablauf(salami.getRangeMin(), salami.getRangeMax(), anzahlDatensaetze);
		List<Integer> JoghurtNormalVerbrauchsverlauf = this.erstelleVerbrauchsOderEinkaufsablauf(Joghurt.getRangeMin(), Joghurt.getRangeMax(), anzahlDatensaetze);
		
		System.out.println("Verbrauchsablauf Butter normal:" + butterNormalVerbrauchsverlauf);
		System.out.println("Verbrauchsablauf Milch normal:" + milchNormalVerbrauchsverlauf);
		System.out.println("Verbrauchsablauf Kaese normal:" + kaeseNormalVerbrauchsverlauf);
		System.out.println("Verbrauchsablauf Salami normal:" + salamiNormalVerbrauchsverlauf);
		System.out.println("Verbrauchsablauf Joghurt normal:" + JoghurtNormalVerbrauchsverlauf);
		
		butter.setVerbrauchsablauf(this.kumuliereVerbrauchsablauf(butterNormalVerbrauchsverlauf, persona.getEinkaufsAblauf()));
		milch.setVerbrauchsablauf(this.kumuliereVerbrauchsablauf(milchNormalVerbrauchsverlauf, persona.getEinkaufsAblauf()));	
		kaese.setVerbrauchsablauf(this.kumuliereVerbrauchsablauf(kaeseNormalVerbrauchsverlauf, persona.getEinkaufsAblauf()));
		salami.setVerbrauchsablauf(this.kumuliereVerbrauchsablauf(salamiNormalVerbrauchsverlauf, persona.getEinkaufsAblauf()));	
		Joghurt.setVerbrauchsablauf(this.kumuliereVerbrauchsablauf(JoghurtNormalVerbrauchsverlauf, persona.getEinkaufsAblauf()));
	
		return persona;
	}
	
	//erstelle eine Solleinkaufsliste zu einem Kaufzeitpunkt
	//einkaufZeitpunkt muss einen Eintrag im kumulierten einkaufsAblauf entsprechen
	/**************** WENN ALLEN PERSONAS IMMER DIE GLEICHEN PRODUKTE ZUGEWIESEN SIND BRAUCHT MAN KEINE IF BEDINGUNG*****************/ 
	public List<Produkt>  erstelleSollEinkaufsListe(Persona persona, int einkaufZeitpunkt) {
		List<Produkt> sollEinkaufsliste = new ArrayList<>();
		List<Integer> einkaufsAblauf = persona.getEinkaufsAblauf();
		List<Integer> butterVerbrauchsablauf = persona.getProduktMap().get("Butter").getVerbrauchsablauf();
		List<Integer> milchVerbrauchsablauf = persona.getProduktMap().get("Milch").getVerbrauchsablauf();
		List<Integer> kaeseVerbrauchsablauf = persona.getProduktMap().get("Kaese").getVerbrauchsablauf();
		List<Integer> salamiVerbrauchsablauf = persona.getProduktMap().get("Salami").getVerbrauchsablauf();
		List<Integer> JoghurtVerbrauchsablauf = persona.getProduktMap().get("Joghurt").getVerbrauchsablauf();
		
		int aktuellerEinkaufsablaufIndex = this.findeIndexVonEinkaufzeitpunktAusListe(einkaufsAblauf, einkaufZeitpunkt);
		int aktuellerButterVerbrauchsablauf = this.findeGroesteZahlKleinerGleichKaufzeitpunkt(butterVerbrauchsablauf, einkaufZeitpunkt);
		int aktuellerMilchVerbrauchsablauf = this.findeGroesteZahlKleinerGleichKaufzeitpunkt(milchVerbrauchsablauf, einkaufZeitpunkt);
		int aktuellerKaeseVerbrauchsablauf = this.findeGroesteZahlKleinerGleichKaufzeitpunkt(kaeseVerbrauchsablauf, einkaufZeitpunkt);
		int aktuellerSalamiVerbrauchsablauf = this.findeGroesteZahlKleinerGleichKaufzeitpunkt(salamiVerbrauchsablauf, einkaufZeitpunkt);
		int aktuellerJoghurtVerbrauchsablauf = this.findeGroesteZahlKleinerGleichKaufzeitpunkt(JoghurtVerbrauchsablauf, einkaufZeitpunkt);
		
		//Logging von 
		System.out.println("aktuellerEinkaufsablaufIndex: " + aktuellerEinkaufsablaufIndex);
		System.out.println("aktuellerButterVerbrauchsablauf: " + aktuellerButterVerbrauchsablauf);
		System.out.println("aktuellerMilchVerbrauchsablauf: " + aktuellerMilchVerbrauchsablauf);
		System.out.println("aktuellerKaeseVerbrauchsablauf: " + aktuellerKaeseVerbrauchsablauf);
		System.out.println("aktuellerSalamiVerbrauchsablauf: " + aktuellerSalamiVerbrauchsablauf);
		System.out.println("aktuellerJoghurtVerbrauchsablauf: " + aktuellerJoghurtVerbrauchsablauf);
		
		//Butter
		if(aktuellerButterVerbrauchsablauf < einkaufZeitpunkt) {
			int einkaufZeitpunktDavor = 0;
			if(aktuellerEinkaufsablaufIndex!=0) {
				einkaufZeitpunktDavor = einkaufsAblauf.get(aktuellerEinkaufsablaufIndex-1);
			}
			System.out.println("einkaufZeitpunktDavor: " + einkaufZeitpunktDavor);
			if(aktuellerButterVerbrauchsablauf > einkaufZeitpunktDavor) {
				//Butter auf SollEinkaufsliste setzen
				sollEinkaufsliste.add(persona.getProduktMap().get("Butter"));
				System.out.println("Butter wurde auf die Solleinkaufsliste gesetzt : von davor");
			}
			if(aktuellerButterVerbrauchsablauf <= einkaufZeitpunktDavor) {
				//Butter auf SollEinkaufsliste nicht setzen, da beim letzten Kauf gekauft	
			}
		}
		if(aktuellerButterVerbrauchsablauf == einkaufZeitpunkt) {
			//Butter auf SollEinkaufsliste setzen
			sollEinkaufsliste.add(persona.getProduktMap().get("Butter"));
			System.out.println("Butter wurde auf die Solleinkaufsliste gesetzt : actuel");
		}
		
		//Milch
		if(aktuellerMilchVerbrauchsablauf < einkaufZeitpunkt) {
			int einkaufZeitpunktDavor = 0;
			if(aktuellerEinkaufsablaufIndex!=0) {
				einkaufZeitpunktDavor = einkaufsAblauf.get(aktuellerEinkaufsablaufIndex-1);
			}
			System.out.println("einkaufZeitpunktDavor: " + einkaufZeitpunktDavor);
			if(aktuellerMilchVerbrauchsablauf > einkaufZeitpunktDavor) {
				//Milch auf SollEinkaufsliste setzen
				sollEinkaufsliste.add(persona.getProduktMap().get("Milch"));
				System.out.println("Milch wurde auf die Solleinkaufsliste gesetzt : von davor");
			}
			if(aktuellerMilchVerbrauchsablauf <= einkaufZeitpunktDavor) {
				//Milch auf SollEinkaufsliste nicht setzen, da beim letzten Kauf gekauft	
			}
		}
		if(aktuellerMilchVerbrauchsablauf == einkaufZeitpunkt) {
			//Milch auf SollEinkaufsliste setzen
			sollEinkaufsliste.add(persona.getProduktMap().get("Milch"));
			System.out.println("Milch wurde auf die Solleinkaufsliste gesetzt : actuel");
		}
		
		//Kaese
		if(aktuellerKaeseVerbrauchsablauf < einkaufZeitpunkt) {
			int einkaufZeitpunktDavor = 0;
			if(aktuellerEinkaufsablaufIndex!=0) {
				einkaufZeitpunktDavor = einkaufsAblauf.get(aktuellerEinkaufsablaufIndex-1);
			}
			System.out.println("einkaufZeitpunktDavor: " + einkaufZeitpunktDavor);
			if(aktuellerKaeseVerbrauchsablauf > einkaufZeitpunktDavor) {
				//Kaese auf SollEinkaufsliste setzen
				sollEinkaufsliste.add(persona.getProduktMap().get("Kaese"));
				System.out.println("Kaese wurde auf die Solleinkaufsliste gesetzt : von davor");
			}
			if(aktuellerKaeseVerbrauchsablauf <= einkaufZeitpunktDavor) {
				//Kaese auf SollEinkaufsliste nicht setzen, da beim letzten Kauf gekauft	
			}
		}
		if(aktuellerKaeseVerbrauchsablauf == einkaufZeitpunkt) {
			//Kaese auf SollEinkaufsliste setzen
			sollEinkaufsliste.add(persona.getProduktMap().get("Kaese"));
			System.out.println("Kaese wurde auf die Solleinkaufsliste gesetzt : actuel");
		}
		
		//Salami
		if(aktuellerSalamiVerbrauchsablauf < einkaufZeitpunkt) {
			int einkaufZeitpunktDavor = 0;
			if(aktuellerEinkaufsablaufIndex!=0) {
				einkaufZeitpunktDavor = einkaufsAblauf.get(aktuellerEinkaufsablaufIndex-1);
			}
			System.out.println("einkaufZeitpunktDavor: " + einkaufZeitpunktDavor);
			if(aktuellerSalamiVerbrauchsablauf > einkaufZeitpunktDavor) {
				//Salami auf SollEinkaufsliste setzen
				sollEinkaufsliste.add(persona.getProduktMap().get("Salami"));
				System.out.println("Salami wurde auf die Solleinkaufsliste gesetzt : von davor");
			}
			if(aktuellerSalamiVerbrauchsablauf <= einkaufZeitpunktDavor) {
				//Salami auf SollEinkaufsliste nicht setzen, da beim letzten Kauf gekauft	
			}
		}
		if(aktuellerSalamiVerbrauchsablauf == einkaufZeitpunkt) {
			//Salami auf SollEinkaufsliste setzen
			sollEinkaufsliste.add(persona.getProduktMap().get("Salami"));
			System.out.println("Salami wurde auf die Solleinkaufsliste gesetzt : actuel");
		}
		
		//Joghurt
		if(aktuellerJoghurtVerbrauchsablauf < einkaufZeitpunkt) {
			int einkaufZeitpunktDavor = 0;
			if(aktuellerEinkaufsablaufIndex!=0) {
				einkaufZeitpunktDavor = einkaufsAblauf.get(aktuellerEinkaufsablaufIndex-1);
			}
			System.out.println("einkaufZeitpunktDavor: " + einkaufZeitpunktDavor);
			if(aktuellerJoghurtVerbrauchsablauf > einkaufZeitpunktDavor) {
				//Joghurt auf SollEinkaufsliste setzen
				sollEinkaufsliste.add(persona.getProduktMap().get("Joghurt"));
				System.out.println("Joghurt wurde auf die Solleinkaufsliste gesetzt : von davor");
			}
			if(aktuellerJoghurtVerbrauchsablauf <= einkaufZeitpunktDavor) {
				//Joghurt auf SollEinkaufsliste nicht setzen, da beim letzten Kauf gekauft	
			}
		}
		if(aktuellerJoghurtVerbrauchsablauf == einkaufZeitpunkt) {
			//Joghurt auf SollEinkaufsliste setzen
			sollEinkaufsliste.add(persona.getProduktMap().get("Salami"));
			System.out.println("Joghurt wurde auf die Solleinkaufsliste gesetzt : actuel");
		}
		
		return sollEinkaufsliste;
	}
	
	/**
	 * erstelle SollEinkaufslisten vor jedem Einkauf über ein Zeitraum in Tagen.
	 * Da es verkommen kann, das zum Zeitpunkt eines Einkaufs die Solleinkaufsliste leer ist, gibt diese Methode nur ausgefüllte sollEinkaufslisten
	 * @param persona
	 * @param zeitRaumInTagen
	 * @return eine Map, key:value = Kauftag:SollEinkaufsliste
	 */
	public Map<Integer, List<Produkt>> erstelleSollEinkaufslistenBeimEinkauf(Persona persona, int zeitRaumInTagen){
		Map<Integer, List<Produkt>> sollEinkaufslistenMap = new HashMap<>();
		List<Produkt> produktListe = new ArrayList<>();
		int j=0;
		for(int i=0; i<persona.getEinkaufsAblauf().size(); i++) {
			while(j<zeitRaumInTagen) {
				if(persona.getEinkaufsAblauf().get(i)==j) {
					produktListe = this.erstelleSollEinkaufsListe(persona, j);
					sollEinkaufslistenMap.put(j, produktListe);
					break;
				}
				j++;
			}
			if(persona.getEinkaufsAblauf().get(i)>zeitRaumInTagen) {
				break;
			}
		}
		
		sollEinkaufslistenMap = this.findeNurAusgefuellteSollEinkaufslisten(sollEinkaufslistenMap);
		
		return sollEinkaufslistenMap;
	}
	
	
	
	/**
	 * 
	 * @param sollEinkaufsListenMap
	 * @return gibt Kauftagen und deren entsprechende SollEinkaufsliste zurück, wenn die SollEinkaufsliste nicht leer ist.
	 */
	public Map<Integer, List<Produkt>> findeNurAusgefuellteSollEinkaufslisten(Map<Integer, List<Produkt>> sollEinkaufsListenMap){
		Map<Integer, List<Produkt>> ausgefuellteSollEinkaufslistenMap = new HashMap<>();
		
		List<Integer> sollListeMapKey = this.sortiereMapKey(sollEinkaufsListenMap);
		List<Produkt> sollListeMapValue = new ArrayList<>();
		
		for(int i=0; i<sollListeMapKey.size(); i++) {
			if(!sollEinkaufsListenMap.get(sollListeMapKey.get(i)).isEmpty()) {
				sollListeMapValue = sollEinkaufsListenMap.get(sollListeMapKey.get(i));
				ausgefuellteSollEinkaufslistenMap.put(sollListeMapKey.get(i), sollListeMapValue);
			}
		}
		
		return ausgefuellteSollEinkaufslistenMap;
	}
	
	
	/**
	 * sortiere eine Liste, die keyListe von der solleinkaufslisteMap, so dass die Map-Werte über den Key sortiert 
	 * abgerufen werden können.
	 * @param sollEinkaufsListenMap
	 * @return
	 */
	public List<Integer> sortiereMapKey(Map<Integer, List<Produkt>> sollEinkaufsListenMap){
		List<Integer> sollListeMapKey = new ArrayList<>();
		for(int key : sollEinkaufsListenMap.keySet()) {
			sollListeMapKey.add(key);
		}
		Collections.sort(sollListeMapKey);
		
		return sollListeMapKey;
	}
	
	/**
	 * zeit Solleinkaufslisten an
	 * @param sollEinkaufsListenMap
	 */
	public void printSollEinkaufsListen(Map<Integer, List<Produkt>> sollEinkaufsListenMap) {
		List<Integer> sollListeMapKey = this.sortiereMapKey(sollEinkaufsListenMap);

		System.out.println("");
		System.out.println("-------------------------------Solleinkaufslisten beim Einkauf---------------------------------");
		System.out.println("");
		for(int i=0; i<sollListeMapKey.size(); i++) {
			System.out.println("Einkaufstag: " + sollListeMapKey.get(i));
			for (Produkt produktListe : sollEinkaufsListenMap.get(sollListeMapKey.get(i))) {
				System.out.println("SollListe: " + produktListe.getName());
			}
		}
		System.out.println("");
		System.out.println("-----------------------------------------------------------------------------------------------");
		System.out.println("");
	}
	
}
