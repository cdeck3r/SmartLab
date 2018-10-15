package simulator.isteinkaufsliste.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import simulator.dbconnector.DBConnector;
import simulator.model.Persona;
import simulator.model.Produkt;
import simulator.model.Produkt_;
import simulator.solleinkaufsliste.service.SollService;

public class EinkaufslistenGenerator {
	private Connection connection = null;
	private Statement statement;
	private ResultSet result;
	DBConnector dbConnector = new DBConnector();

	IstService istService = new IstService();
	SollService sollService = new SollService();

	public static int kunden_id;

	public static double unter_grenze_gelb = 50;
	public static double unter_grenze_rot = 95;

	ArrayList<Produkt_> produktArrayList = new ArrayList<>();

	public ArrayList<Integer> integerArrayList;
	public long Tage_seit_einkauf;

	CumulativeProbability cumulativeProbability = new CumulativeProbability();

	/**
	 * berechne die Verbrauchswahrscheinlichkeit von allen Produkten eines Kunden
	 * und setze das Attribute Verbrauchwahrscheinlichkeit von Produkt_ berechne die
	 * Liste durch die App und speichere Produkte mit wahrscheinlichkeht >= 90 in
	 * der temp_einkaufsliste
	 * 
	 * @param kunden_id
	 * @param putDatum
	 *            : in Tage, Es is der Zeitpunt, wo ein Produkt auf die
	 *            IstEinkaufsliste eingef�gt wird
	 * @return
	 * @throws ParseException
	 */
	public ArrayList<String> get_berechneteEinkaufsliste(int kunden_id, int putDatum) throws ParseException {

		setAllProdukte(kunden_id);

		for (int i = 0; i < produktArrayList.size(); i++) {
			this.integerArrayList = getVerbrauchsDaten_Produkt(this.produktArrayList.get(i).getProdukt_id(), kunden_id);

			Tage_seit_einkauf = getAbgelaufeneTage_seit_Einkauf(this.produktArrayList.get(i).getProdukt_id(), kunden_id,
					putDatum);

			this.produktArrayList.get(i).setVerbrauchswarscheinlichkeit(cumulativeProbability
					.computeKumulativeWahrscheinlichkeit(this.integerArrayList, (int) Tage_seit_einkauf) * 100);

			/*System.out.println("Bezeichnung: " + this.produktArrayList.get(i).getBezeichnung()
					+ "            Verbrauchswahrscheinlichkeit: "
					+ this.produktArrayList.get(i).getVerbrauchswarscheinlichkeit() + "\n" + "Tage Seit Einkauf:      "
					+ Tage_seit_einkauf);
			*/
		}

		ArrayList<String> produktliste = new ArrayList<>();

		for (int i = 0; i < this.produktArrayList.size(); i++) {
			if (this.produktArrayList.get(i).getVerbrauchswarscheinlichkeit() >= this.unter_grenze_rot) {
				if(this.itemAlreadyExist(istService.get_einkaufsliste(1), this.produktArrayList.get(i).getBezeichnung())==false) {
					produktliste.add(this.produktArrayList.get(i).getBezeichnung());
					istService.put_Product_On_Einkaufsliste(kunden_id, this.produktArrayList.get(i).getBezeichnung(), putDatum);
				}
			}
		}

		return produktliste;
	}

	// Methoden

	public void setAllProdukte(int kunden_id) {
		produktArrayList = get_all_Produkte(kunden_id);
	}

	public ArrayList<Integer> getVerbrauchsDaten_Produkt(int produkt_id, int kunden_id) {
		ArrayList<Integer> arrayList = new ArrayList<>();

		arrayList = verbrauch_in_Tagen(produkt_id, kunden_id);

		return arrayList;
	}

	public Long getAbgelaufeneTage_seit_Einkauf(int produkt_id, int kunden_id, int zeitPunkt) throws ParseException {
		Long abgelaufenTage;

		abgelaufenTage = (Long) abgelaufene_Tage_Produkt(produkt_id, kunden_id, zeitPunkt);

		return abgelaufenTage;
	}

	public ArrayList<Produkt_> get_all_Produkte(int kunden_id) {

		ArrayList<Produkt_> arrayList = new ArrayList<>();

		connection = dbConnector.getDBVerbindung();

		if (connection != null) {
			// System.out.println("verbindung stshet");
		}

		try {
			arrayList = new ArrayList<>();

			statement = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			result = statement.executeQuery("SELECT produkt_id FROM standard_warenkorb WHERE kunden_id = " + kunden_id);
			result.last();

			int anzahl = result.getRow();

			result.first();

			for (int i = 0; i < anzahl; i++) {
				Produkt_ produkt = new Produkt_();
				produkt.setProdukt_id(result.getInt("produkt_id"));
				arrayList.add(produkt);
				result.next();
			}

			for (int i = 0; i < arrayList.size(); i++) {
				result = statement.executeQuery(
						"SELECT Bezeichnung FROM produkt WHERE produkt_id = " + arrayList.get(i).getProdukt_id());
				result.first();

				arrayList.get(i).setBezeichnung(result.getString("Bezeichnung"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return arrayList;
	}

	public ArrayList<Integer> verbrauch_in_Tagen(int produkt_id, int kunden_id) {
		ArrayList<Integer> arrayList = new ArrayList<>();

		connection = dbConnector.getDBVerbindung();

		if (connection != null) {
			// System.out.println("verbindung stshet");
		}

		try {
			arrayList = new ArrayList<>();

			statement = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			result = statement
					.executeQuery("SELECT verbrauch_in_tagen FROM produkt_verbrauchsstatistik WHERE produkt_id = "
							+ produkt_id + " AND kunden_id = " + kunden_id);
			result.last();

			int anzahl = result.getRow();

			result.first();

			for (int i = 0; i < anzahl; i++) {
				arrayList.add(result.getInt("verbrauch_in_tagen"));
				// System.out.println(produkt_id + " " + result.getInt("verbrauch_in_tagen"));
				result.next();
			}

			result = statement
					.executeQuery("SELECT ablauf_in_tagen FROM produkt_mhd_statistik WHERE produkt_id = " + produkt_id);
			result.last();

			anzahl = result.getRow();

			result.first();

			for (int i = 0; i < anzahl; i++) {
				arrayList.add(result.getInt("ablauf_in_tagen"));
				// System.out.println(produkt_id + " " + result.getInt("ablauf_in_tagen"));
				result.next();
			}

			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return arrayList;

	}

	/**
	 * 
	 * @param produkt_id
	 * @param kunden_id
	 * @param zeitPunkt
	 *            : Verbrauchszeitpunkt
	 * @return
	 * @throws ParseException
	 */
	public Long abgelaufene_Tage_Produkt(int produkt_id, int kunden_id, int verbrauchZeitPunktinTagen)
			throws ParseException {

		String verbrauchZeitPunktStr = istService.ermittleEinkaufsDatum(verbrauchZeitPunktinTagen);
		Date verbrauchsZeitPunkt = new SimpleDateFormat("yyyy-MM-dd").parse(verbrauchZeitPunktStr);
		long diff = 0;

		connection = dbConnector.getDBVerbindung();

		if (connection != null) {
			// System.out.println("verbindung steht");
		}

		try {
			statement = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			result = statement.executeQuery("SELECT kauf_datum FROM einkaufs_historie WHERE produkt_id = " + produkt_id
					+ " AND kunden_id = " + kunden_id);
			result.first();

			String datum = result.getString("kauf_datum");
			Date kauf_date = new SimpleDateFormat("yyyy-MM-dd").parse(datum);
			diff = verbrauchsZeitPunkt.getTime() - kauf_date.getTime();
			diff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		//System.out.println("Diff Tage:    " + diff);
		return diff;
	}

	/**
	 * initialisiere die folgenden Tabelen: einkaufs_historie -- kauf_datum auf
	 * today setzen und verbrauch_datum auf null setzen. produktverbrauchsstatistik
	 * -- leeren temp_einkaufsliste -- leeren
	 * 
	 * @throws SQLException
	 * @throws ParseException
	 */
	public void reset() throws SQLException, ParseException {
		connection = dbConnector.getDBVerbindung();
		statement = (Statement) connection.createStatement();

		Calendar c = Calendar.getInstance();
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2018-07-03");
		Date today = c.getTime();
		String kaufDatum = new SimpleDateFormat("yyyy-MM-dd").format(today);

		statement.executeUpdate("DELETE FROM einkaufs_historie");
		statement.executeUpdate("INSERT INTO einkaufs_historie (einkauf_id, kunden_id, produkt_id, kauf_datum)"
				+ "VALUES (" + 1 + ", " + 1 + ", " + 1 + ", '" + kaufDatum + "')");
		statement.executeUpdate("INSERT INTO einkaufs_historie (einkauf_id, kunden_id, produkt_id, kauf_datum)"
				+ "VALUES (" + 2 + ", " + 1 + ", " + 2 + ", '" + kaufDatum + "')");
		statement.executeUpdate("INSERT INTO einkaufs_historie (einkauf_id, kunden_id, produkt_id, kauf_datum)"
				+ "VALUES (" + 3 + ", " + 1 + ", " + 3 + ", '" + kaufDatum + "')");
		statement.executeUpdate("INSERT INTO einkaufs_historie (einkauf_id, kunden_id, produkt_id, kauf_datum)"
				+ "VALUES (" + 4 + ", " + 1 + ", " + 4 + ", '" + kaufDatum + "')");
		statement.executeUpdate("INSERT INTO einkaufs_historie (einkauf_id, kunden_id, produkt_id, kauf_datum)"
				+ "VALUES (" + 5 + ", " + 1 + ", " + 5 + ", '" + kaufDatum + "')");

		statement.executeUpdate("DELETE FROM produkt_verbrauchsstatistik");

		statement.executeUpdate("DELETE FROM temp_einkaufsliste");
	}

	/**
	 * 
	 * @param persona
	 * @param sollEinkaufsListenMap
	 * @param zeitRaum
	 * @param appTouchPoint
	 * @param homeTouchPoint
	 * @param Qapp
	 * @param Qhome
	 * @param matrixCombinationDateiName
	 * @return
	 * @throws ParseException
	 */
	public Map<Integer, List<String>> erstelleIstEinkaufslisteMapBeimKauf(Persona persona,
			Map<Integer, List<Produkt>> sollEinkaufsListenMap, int zeitRaum, boolean appTouchPoint,
			boolean homeTouchPoint, int Qapp, int Qhome, String matrixCombinationDateiName) throws ParseException {
		Map<Integer, List<String>> istEinkaufsListeMap = new HashMap<>();
		// List<String> istEinkaufsListe = new ArrayList<>();
		List<String> tempIstEinkaufsListeProTag = new ArrayList<>();
		List<Produkt> tempSollEinkaufsListeProTag = new ArrayList<>();
		List<Integer> sollEinkaufsListenMapKeys = sollService.sortiereSollMapKey(sollEinkaufsListenMap);

		int j = 1;

		for (int i = 0; i < sollEinkaufsListenMapKeys.size(); i++) {

			while (j < zeitRaum) {
				// tempIstEinkaufsListeProTag = this.get_berechneteEinkaufsliste(1, j);
				tempSollEinkaufsListeProTag = sollService.erstelleSollEinkaufsListe(persona, j);

				// Qualitätsstufe wird berücksichtig
				if (appTouchPoint && Qapp != 0) {
					// List<String> produktAusManuellemInput =
					// this.getProduktAusManuellemInput(istService.get_einkaufsliste(1),
					// tempSollEinkaufsListeProTag, Qapp);

					List<String> aktuelleDBeinkaufsliste = istService.get_einkaufsliste(1);

					if (Math.random() <= 1) {
						for (int r = 0; r < tempSollEinkaufsListeProTag.size(); r++) {
							if (this.itemAlreadyExist(aktuelleDBeinkaufsliste,
									tempSollEinkaufsListeProTag.get(r).getName()) == false) {
								istService.put_Product_On_Einkaufsliste(1, tempSollEinkaufsListeProTag.get(r).getName(), j);
							}
						}
					}
					

					// if (produktAusManuellemInput.isEmpty() == false) {
					// for(String produkt : produktAusManuellemInput) {
					// istService.put_Product_On_Einkaufsliste(1, produkt, j);
					// //test
					// //System.out.println("DB am Tag: " + j + " " + produkt);
					// }
					// }
				}

				// else if (homeTouchPoint && Qhome!=0) {
				// List<String> produktAusManuellemInput =
				// this.getProduktAusManuellemInput(istService.get_einkaufsliste(1),
				// tempSollEinkaufsListeProTag, Qhome);
				// if (produktAusManuellemInput.isEmpty() == false) {
				// for(int p=0; p<produktAusManuellemInput.size(); p++) {
				// //istEinkaufsListe.add(produktAusManuellemInput);
				// istService.put_Product_On_Einkaufsliste(1, produktAusManuellemInput.get(p),
				// j);
				// }
				// }
				// }
				// for (int l = 0; l < tempIstEinkaufsListeProTag.size(); l++) {
				// if (this.itemAlreadyExist(istService.get_einkaufsliste(1),
				// tempIstEinkaufsListeProTag.get(l)) == false) {
				// //istEinkaufsListe.add(tempIstEinkaufsListeProTag.get(l));
				// istService.put_Product_On_Einkaufsliste(1, tempIstEinkaufsListeProTag.get(l),
				// j); // befüllen der
				// // temp_einkaufsliste
				// }
				// }

				if (sollEinkaufsListenMapKeys.get(i) == j) {
				 istEinkaufsListeMap.put(sollEinkaufsListenMapKeys.get(i),
				 /*istEinkaufsListe*/ istService.get_einkaufsliste(1));
				 //test
				// for(String produkt : istService.get_einkaufsliste(1)) {
				// System.out.println("DB vor buy: " + j + " " + produkt);
				// }
				 for (int k = 0; k < istService.get_einkaufsliste(1).size(); k++) {
				 if (istService.get_einkaufsliste(1).get(k).isEmpty() == false /*||
				 istEinkaufsListe.get(k) == null*/) {
				 istService.buy_Product_From_Einkaufsliste(istService.get_einkaufsliste(1).get(k),
				 1,
				 sollEinkaufsListenMapKeys.get(i)); // buy after getting the list
				 }
				 }
				 //test
				// for(String produkt : istService.get_einkaufsliste(1)) {
				// System.out.println("DB after buy: " + j + " " + produkt);
				// }
				 //istEinkaufsListe = new ArrayList<>(); // locale istEinkaufListe nach dem Einkauf leeren
				 //j++;
				 break;
				 }
				j++;
			}
			if (sollEinkaufsListenMapKeys.get(i) > zeitRaum) {
				break;
			}
		}

		this.schreibeGenerierteEinkaufslistenInEinerDatei(istEinkaufsListeMap, matrixCombinationDateiName);
		return istEinkaufsListeMap;
	}

	/**
	 * zeigt IstEinkaufslisten an. kaufdatum:istEinkaufsListe
	 * 
	 * @param istEinkaufsListenMap
	 */
	public void printIstEinkaufsListen(Map<Integer, List<String>> istEinkaufsListenMap) {
		List<Integer> istListeMapKey = istService.sortiereIstMapKey(istEinkaufsListenMap);

		System.out.println("");
		System.out.println(
				"-------------------------------Isteinkaufslisten beim Einkauf---------------------------------");
		System.out.println("");
		for (int i = 0; i < istListeMapKey.size(); i++) {
			System.out.println("Einkaufstag: " + istListeMapKey.get(i));
			for (String produktListe : istEinkaufsListenMap.get(istListeMapKey.get(i))) {
				System.out.println("IstListe: " + produktListe);
			}
		}
		System.out.println("");
		System.out.println(
				"-----------------------------------------------------------------------------------------------");
		System.out.println("");
	}

	/**
	 * pr�fe, ob ein item in einer Liste existiert
	 * 
	 * @param list
	 * @param item
	 * @return
	 */
	public boolean itemAlreadyExist(List<String> list, String item) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equals(item)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * schreibe generierte Einkaufslisten in einer Datei
	 * 
	 * @param einkaufslisteMap
	 * @param dateiName
	 */
	public void schreibeGenerierteEinkaufslistenInEinerDatei(Map<Integer, List<String>> einkaufslisteMap,
			String dateiName) {
		List<Integer> ListeMapKey = istService.sortiereIstMapKey(einkaufslisteMap);
		String path = "C:\\Users\\tchwangnwou\\Desktop\\HS REUTLINGEN\\Simulation_Dateien\\";
		File datei = new File(path + dateiName);
		try {
			datei.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(datei))) {
			bw.write("---------------------------" + "\n");
			bw.write("-----Ist-Einkaufsliste-----" + "\n");
			bw.write("---------------------------" + "\n");
			for (int i = 0; i < ListeMapKey.size(); i++) {
				// Einkaufstag;Produkt1;Produkt2;Produktx
				bw.write(ListeMapKey.get(i) + ";");
				for (String produktListe : einkaufslisteMap.get(ListeMapKey.get(i))) {
					bw.write(produktListe + ";");
				}
				bw.write("\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gibt ein oder kein Produkt zurück, das ein User über App oder über Home
	 * selber in die Einkaufsliste eingetragen hat. Der Produkt muss aus der Liste
	 * der generierten Produkte, die die Grenze(untere_grenze_rot = 90%
	 * Wahrscheinlichkeit) nicht erreicht haben. Dies ist durch die Qualitätsstufe
	 * des entsprechenden Komponenten bedingt.
	 * 
	 * ‚0‘ = keine Verwendung 0% dass Produkt X auf Einkaufsliste landet ‚1‘ =
	 * schwache/lockere Verwendung 33% dass Produkt X auf Einkaufsliste landet ‚2‘ =
	 * mittelmäßige Verwendung 66% dass Produkt X auf Einkaufsliste landet ‚3‘ =
	 * starke/sorgfältige Verwendung 99% dass Produkt X auf Einkaufsliste landet
	 * 
	 * @param Qkomponent
	 *            ist die Qualitätsstufe des genutzten Komponenten
	 * @param tempSollEinkaufsListeProTag
	 * @param istEinkaufsListe
	 * @return Produktname
	 */
	public List<String> getProduktAusManuellemInput(List<String> istEinkaufsListe,
			List<Produkt> tempSollEinkaufsListeProTag, int Qkomponent) {
		List<String> produkt = new ArrayList<>();

		List<String> tempSollEinkaufsListeProTagWithName = new ArrayList<>();
		for (int e = 0; e < tempSollEinkaufsListeProTag.size(); e++) {
			tempSollEinkaufsListeProTagWithName.add(tempSollEinkaufsListeProTag.get(e).getName());
		}

		if (Qkomponent == 1) {
			if (Math.random() <= 0.33) {
				for (int i = 0; i < tempSollEinkaufsListeProTagWithName.size(); i++) {
					if (this.itemAlreadyExist(istEinkaufsListe, tempSollEinkaufsListeProTagWithName.get(i)) == false) {
						produkt.add(tempSollEinkaufsListeProTagWithName.get(i));
					}
				}
			}
		} else if (Qkomponent == 2) {
			if (Math.random() <= 0.66) {
				for (int i = 0; i < tempSollEinkaufsListeProTagWithName.size(); i++) {
					if (this.itemAlreadyExist(istEinkaufsListe, tempSollEinkaufsListeProTagWithName.get(i)) == false) {
						produkt.add(tempSollEinkaufsListeProTagWithName.get(i));
					}
				}
			}
		} else if (Qkomponent == 3) {
			if (Math.random() <= 1) {
				for (int i = 0; i < tempSollEinkaufsListeProTagWithName.size(); i++) {
					if (this.itemAlreadyExist(istEinkaufsListe, tempSollEinkaufsListeProTagWithName.get(i)) == false) {
						produkt.add(tempSollEinkaufsListeProTagWithName.get(i));
					}
				}
			}
		} else {

		}
		return produkt;
	}
}
