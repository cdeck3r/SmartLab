package simulator.main;

import java.sql.SQLException;
import java.text.ParseException;

import java.util.List;
import java.util.Map;

import simulator.isteinkaufsliste.service.IstService;
import simulator.model.Persona;
import simulator.model.Produkt;
import simulator.solleinkaufsliste.service.SollService;
import simulator.isteinkaufsliste.service.EinkaufslistenGenerator;

public class Main {

	public static void main(String[] args) throws ParseException, SQLException {
		SollService sollService = new SollService();
		IstService istService = new IstService();
		EinkaufslistenGenerator einkaufslistenGenerator = new EinkaufslistenGenerator();
		Persona sportler;
		
		sportler = sollService.erstellePersonaMitVerbrauchsdaten("Sportler", 3, 6, 1000);
		Map<Integer, List<Produkt>> sollEinkaufsListenMap = sollService.erstelleSollEinkaufslistenBeimEinkauf(sportler, 150);
		printPersonaData(sportler);
		
		
		einkaufslistenGenerator.reset();
		
		Map<Integer, List<String>> istEinkaufsListenMap = einkaufslistenGenerator.erstelleIstEinkaufslisteMapBeimKaufV1(sportler, sollEinkaufsListenMap, 150);
		einkaufslistenGenerator.printIstEinkaufsListen(istEinkaufsListenMap);
		
		

		//Map<Integer, List<Produkt>> sollListeMap = sollService.erstelleSollEinkaufslistenBeimEinkauf(sportler, 5);
		
		//sollService.printSollEinkaufsListen(sollListeMap);
		

//		List<Produkt> sportlerSollEinkaufsliste = sollService.erstelleSollEinkaufsListe(sportler, sportler.getEinkaufsAblauf().get(4));
//
//
//		System.out.println("*****SollEinkaufslite******");
//		for (Produkt produkt : sportlerSollEinkaufsliste) {
//			System.out.println(produkt.getName());
//		}

		// Einkaufsliste generator
		
		// IstService istService = new IstService();
		// istService.put_Product_On_Einkaufsliste(2, "Butter");
		// System.out.println(istService.get_einkaufsliste(1));

		
		
		//System.out.println("##### Berechnete Einkaufsliste vom Generator: " + einkaufslistenGenerator.get_berechneteEinkaufsliste(1, 150));
		//istService.put_Product_On_Einkaufsliste(1, "Joghurt", 5);
		//istService.put_Product_On_Einkaufsliste(1, "Salami", 6);
		//istService.buy_Product_From_Einkaufsliste("Joghurt", 1, 100);
		//istService.buy_Product_From_Einkaufsliste("Salami", 1, 100);
		// System.out.println("##### Einkaufsliste aus der DB: " +
		// istService.get_einkaufsliste(1));

	}
	
	
	
	
	
	
	
	
	public static void printPersonaData(Persona persona) {
		System.out.println("");
		System.out.println("-------------------------------Persona Data---------------------------------");
		System.out.println("");
		System.out.println("Einkaufsablauf in Tagenabstand (kumuliert): " + persona.getEinkaufsAblauf());
		System.out.println(
				"Verbrauchsablauf Butter kumuliert:" + persona.getProduktMap().get("Butter").getVerbrauchsablauf());
		System.out.println(
				"Verbrauchsablauf Milch kumuliert:" + persona.getProduktMap().get("Milch").getVerbrauchsablauf());
		System.out.println(
				"Verbrauchsablauf Kaese kumuliert:" + persona.getProduktMap().get("Kaese").getVerbrauchsablauf());
		System.out.println(
				"Verbrauchsablauf Salami kumuliert:" + persona.getProduktMap().get("Salami").getVerbrauchsablauf());
		System.out.println(
				"Verbrauchsablauf Joghurt kumuliert:" + persona.getProduktMap().get("Joghurt").getVerbrauchsablauf());

		System.out.println("");
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("");
	}
}
