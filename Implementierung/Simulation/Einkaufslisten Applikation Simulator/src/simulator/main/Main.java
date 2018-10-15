package simulator.main;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import simulator.isteinkaufsliste.service.IstService;
import simulator.isteinkaufsliste.service.Simulator;
import simulator.model.Persona;
import simulator.model.Produkt;
import simulator.solleinkaufsliste.service.SollService;
import simulator.isteinkaufsliste.service.EinkaufslistenGenerator;

public class Main {

	public static void main(String[] args) throws ParseException, SQLException {
		SollService sollService = new SollService();
		IstService istService = new IstService();
		Simulator simulator = new Simulator();
		EinkaufslistenGenerator einkaufslistenGenerator = new EinkaufslistenGenerator();
		Persona persona = sollService.deserializierePersona();
		
		einkaufslistenGenerator.reset();
		
//		for(int i=2; i<4; i++) {
//			einkaufslistenGenerator.reset();
//			for(int j=0; j<4; j++) {
//				simulator.simuliereAblauf(persona, sollEinkaufsListenMap, 150, true, true, i, j, "istEinkaufsliste_11"+ i + "" + j +".csv");
//			}
//			
//		}
		
		simulator.simuliereAblauf(persona, 150, true, true, 0, 0, "istEinkaufsliste_1100.csv");

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
