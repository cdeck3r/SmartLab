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
	public static double unter_grenze_rot = 90;

	ArrayList<Produkt_> produktArrayList = new ArrayList<>();

	public ArrayList<Integer> integerArrayList;
	public long Tage_seit_einkauf;

	CumulativeProbability cumulativeProbability = new CumulativeProbability();
	
	
	/**
	 * berechne die Verbrauchswahrscheinlichkeit von allen Produkten eines Kunden und setze das Attribute Verbrauchwahrscheinlichkeit von Produkt_
	 * berechne die Liste durch die App und speichere Produkte mit wahrscheinlichkeht >= 90 in der temp_einkaufsliste
	 * 
	 * @param kunden_id
	 * @param putDatum : in Tage, Es is der Zeitpunt, wo ein Produkt auf die IstEinkaufsliste eingef�gt wird
	 * @return
	 * @throws ParseException 
	 */
	public ArrayList<String> get_berechneteEinkaufsliste(int kunden_id, int putDatum) throws ParseException {
		
		setAllProdukte(kunden_id);

		for (int i = 0; i < produktArrayList.size(); i++) {
			this.integerArrayList = getVerbrauchsDaten_Produkt(this.produktArrayList.get(i).getProdukt_id(), kunden_id);

			Tage_seit_einkauf = getAbgelaufeneTage_seit_Einkauf(this.produktArrayList.get(i).getProdukt_id(),
					kunden_id, putDatum);

			this.produktArrayList.get(i).setVerbrauchswarscheinlichkeit(cumulativeProbability
					.computeKumulativeWahrscheinlichkeit(this.integerArrayList, (int) Tage_seit_einkauf) * 100);

			System.out.println("Bezeichnung: " + this.produktArrayList.get(i).getBezeichnung() + "            Verbrauchswahrscheinlichkeit: "
					+ this.produktArrayList.get(i).getVerbrauchswarscheinlichkeit() + "\n" + "Tage Seit Einkauf:      "
					+ Tage_seit_einkauf);
		}
		
		
		ArrayList<String> produktliste = new ArrayList<>();

		for (int i = 0; i < this.produktArrayList.size(); i++) {
			if (this.produktArrayList.get(i).getVerbrauchswarscheinlichkeit() >= this.unter_grenze_rot) {
				produktliste.add(this.produktArrayList.get(i).getBezeichnung());
				//istService.put_Product_On_Einkaufsliste(kunden_id, this.produktArrayList.get(i).getBezeichnung(), putDatum);
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
			//System.out.println("verbindung stshet");
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
			//System.out.println("verbindung stshet");
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
				//System.out.println(produkt_id + "    " + result.getInt("verbrauch_in_tagen"));
				result.next();
			}

			result = statement
					.executeQuery("SELECT ablauf_in_tagen FROM produkt_mhd_statistik WHERE produkt_id = " + produkt_id);
			result.last();

			anzahl = result.getRow();

			result.first();

			for (int i = 0; i < anzahl; i++) {
				arrayList.add(result.getInt("ablauf_in_tagen"));
				//System.out.println(produkt_id + "    " + result.getInt("ablauf_in_tagen"));
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
	 * @param zeitPunkt : Verbrauchszeitpunkt
	 * @return
	 * @throws ParseException 
	 */
	public Long abgelaufene_Tage_Produkt(int produkt_id, int kunden_id, int verbrauchZeitPunktinTagen) throws ParseException {
		
		String verbrauchZeitPunktStr =istService.ermittleEinkaufsDatum(verbrauchZeitPunktinTagen);
		Date verbrauchsZeitPunkt = new SimpleDateFormat("yyyy-MM-dd").parse(verbrauchZeitPunktStr);
		long diff = 0;

		connection = dbConnector.getDBVerbindung();

		if (connection != null) {
			//System.out.println("verbindung steht");
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

		System.out.println("Diff Tage:    " + diff);
		return diff;
	}
	
	
	/**
	 * initialisiere die folgenden Tabelen:
	 * einkaufs_historie -- kauf_datum auf today setzen und verbrauch_datum auf null setzen.
	 * produktverbrauchsstatistik -- leeren
	 * temp_einkaufsliste -- leeren
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
		statement.executeUpdate("INSERT INTO produkt_verbrauchsstatistik (ID, kunden_id, produkt_id, verbrauch_in_tagen)"
				+ "VALUES (" + 1 + ", " + 1 + ", " + 1 + ", " + 0 + ")");
		statement.executeUpdate("INSERT INTO produkt_verbrauchsstatistik (ID, kunden_id, produkt_id, verbrauch_in_tagen)"
				+ "VALUES (" + 2 + ", " + 1 + ", " + 2 + ", " + 0 + ")");
		statement.executeUpdate("INSERT INTO produkt_verbrauchsstatistik (ID, kunden_id, produkt_id, verbrauch_in_tagen)"
				+ "VALUES (" + 3 + ", " + 1 + ", " + 3 + ", " + 0 + ")");
		statement.executeUpdate("INSERT INTO produkt_verbrauchsstatistik (ID, kunden_id, produkt_id, verbrauch_in_tagen)"
				+ "VALUES (" + 4 + ", " + 1 + ", " + 4 + ", " + 0 + ")");
		statement.executeUpdate("INSERT INTO produkt_verbrauchsstatistik (ID, kunden_id, produkt_id, verbrauch_in_tagen)"
				+ "VALUES (" + 5 + ", " + 1 + ", " + 5 + ", " + 0 + ")");
		
		statement.executeUpdate("DELETE FROM temp_einkaufsliste");	
	}
	

	/**
	 * erstelle eine Map von IstEinkaufslisten bei jedem Einkauf. kaufTag:produktNamenListe
	 * @param persona
	 * @param zeitRaum
	 * @return eine istEinkaufsListeMap
	 * @throws ParseException
	 */
	public Map<Integer, List<String>> erstelleIstEinkaufslisteMapBeimKaufV1 (Persona persona, Map<Integer, List<Produkt>> sollEinkaufsListenMap, int zeitRaum) throws ParseException{
		Map<Integer, List<String>> istEinkaufsListeMap = new HashMap<>();
		List<String> istEinkaufsListe = new ArrayList<>();
		List<String> tempIstEinkaufsListe = new ArrayList<>();
		List<Integer> sollEinkaufsListenMapKeys = sollService.sortiereSollMapKey(sollEinkaufsListenMap);
		
		int j=0;
		
		for(int i=0; i<sollEinkaufsListenMapKeys.size(); i++) {
			
			while(j<zeitRaum) {
				tempIstEinkaufsListe = this.get_berechneteEinkaufsliste(1, j);
				for(int l=0; l<tempIstEinkaufsListe.size(); l++) {
					if(this.itemAlreadyExist(istEinkaufsListe, tempIstEinkaufsListe.get(l))==false) {
						istEinkaufsListe.add(tempIstEinkaufsListe.get(l));
						istService.put_Product_On_Einkaufsliste(1, tempIstEinkaufsListe.get(l), j); //bef�llen der temp_einkaufsliste
					}
				}
				
				if(sollEinkaufsListenMapKeys.get(i)==j) {
					istEinkaufsListeMap.put(sollEinkaufsListenMapKeys.get(i), istEinkaufsListe);
					for(int k=0; k<istEinkaufsListe.size(); k++) {
						if(!istEinkaufsListe.get(k).isEmpty()) {
							istService.buy_Product_From_Einkaufsliste(istEinkaufsListe.get(k), 1, sollEinkaufsListenMapKeys.get(i)); //buy after getting the list
						}
					}
					istEinkaufsListe = new ArrayList<>(); //locale istEinkaufListe nach dem Einkauf leeren
					break;
				}
				j++;
			}
			if(sollEinkaufsListenMapKeys.get(i)>zeitRaum) {
				break;
			}
		}
		
		this.schreibeGenerierteEinkaufslistenInEinerDatei(istEinkaufsListeMap, "istEinkaufsListe.txt");
		return istEinkaufsListeMap;
	}
	
	
	
	/**
	 * zeigt IstEinkaufslisten an. kaufdatum:istEinkaufsListe
	 * @param istEinkaufsListenMap
	 */
	public void printIstEinkaufsListen(Map<Integer, List<String>> istEinkaufsListenMap) {
		List<Integer> istListeMapKey = istService.sortiereIstMapKey(istEinkaufsListenMap);

		System.out.println("");
		System.out.println("-------------------------------Isteinkaufslisten beim Einkauf---------------------------------");
		System.out.println("");
		for(int i=0; i<istListeMapKey.size(); i++) {
			System.out.println("Einkaufstag: " + istListeMapKey.get(i));
			for (String produktListe : istEinkaufsListenMap.get(istListeMapKey.get(i))) {
				System.out.println("IstListe: " + produktListe);
			}
		}
		System.out.println("");
		System.out.println("-----------------------------------------------------------------------------------------------");
		System.out.println("");
	}
	
	/**
	 * pr�fe, ob ein item in einer Liste existiert
	 * @param list
	 * @param item
	 * @return
	 */
	public boolean itemAlreadyExist(List<String> list, String item) {
		boolean b = false;
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).equals(item)) {
				b=true;
				break;
			}
			else
				b=false;
		}
		return b;
	}
	
	/**
	 * schreibe generierte Einkaufslisten in einer Datei
	 * @param einkaufslisteMap
	 * @param dateiName
	 */
	public void schreibeGenerierteEinkaufslistenInEinerDatei(Map<Integer, List<String>> einkaufslisteMap, String dateiName) {
		List<Integer> ListeMapKey = istService.sortiereIstMapKey(einkaufslisteMap);
		String path = "C:\\Users\\tchwangnwou\\Desktop\\HS REUTLINGEN\\Simulation_Dateien\\";
		File datei = new File(path + dateiName);
		try {
			datei.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(datei))) {
			bw.write("---------------------------" + "\n");
			bw.write("-----Ist-Einkaufsliste-----" + "\n");
			bw.write("---------------------------" + "\n");
			for(int i=0; i<ListeMapKey.size(); i++) {
				bw.write("Einkaufstag: " + ListeMapKey.get(i) + "\n");
				for (String produktListe : einkaufslisteMap.get(ListeMapKey.get(i))) {
					bw.write(produktListe + "\n");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
