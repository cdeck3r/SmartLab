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
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import simulator.dbconnector.DBConnector;
import simulator.model.Persona;
import simulator.model.Produkt;
import simulator.model.Produkt_;
import simulator.solleinkaufsliste.service.SollService;

public class IstService {
	
	DBConnector dbConnector = new DBConnector();
	
	private Connection connection;
	private Statement statement;
	private PreparedStatement preparedStatement;
	private ResultSet result;
	
	//SollService sollService = new SollService();

	public ArrayList<String> get_einkaufsliste(int kunden_id) {

		connection = dbConnector.getDBVerbindung();
		
		ArrayList<String>arrayList = new ArrayList<>();

		if (connection != null) {
			//System.out.println("verbindung stshet");
		}

		try {
			statement = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			result = statement.executeQuery("SELECT produkt_id FROM temp_einkaufsliste WHERE kunden_id = " + kunden_id);
			result.last();
			int anzahl = result.getRow();

			result.first();

			ArrayList<Integer> integerArrayList = new ArrayList<>();
			for (int j = 0; j < anzahl; j++) {
				integerArrayList.add(result.getInt("produkt_id"));
				result.next();
			}

			for (int i = 0; i < integerArrayList.size(); i++) {
				result = statement
						.executeQuery("SELECT Bezeichnung FROM produkt WHERE produkt_id = " + integerArrayList.get(i));
				result.first();
				arrayList.add(result.getString("Bezeichnung"));
			}

			// connection.commit();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return arrayList;
	}

	/**
	 * 
	 * @param product
	 * @param kunden_id
	 * @param einkaufTag : nimmt sein Wert vom einkaufsablauf bzw. aus der Solleinkaufsliste Map einkaufTag:sollEinkaufsListe
	 * @throws ParseException
	 */
	public void buy_Product_From_Einkaufsliste(String product, int kunden_id, int einkaufTag) throws ParseException {
		
		String einkaufsDatum = this.ermittleEinkaufsDatum(einkaufTag);
		
		connection = dbConnector.getDBVerbindung();

		if (connection != null) {
			//System.out.println("verbindung stshet");
		}

		try {

			statement = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			result = statement.executeQuery("SELECT produkt_id FROM produkt WHERE Bezeichnung = '" + product + "'");
			result.first();
			int product_id = result.getInt("produkt_id");

			result = statement.executeQuery("SELECT verbrauch_datum FROM einkaufs_historie WHERE produkt_id = "
					+ product_id + " AND kunden_id = " + kunden_id);
			result.last();

			if (result.getRow() == 0) {
				result = statement.executeQuery("SELECT max(einkauf_id) AS einkauf_id FROM einkaufs_historie");
				result.first();
				int einkauf_id = result.getInt("einkauf_id");

				if (einkauf_id == 0) {
					einkauf_id = 1;
				} else {
					einkauf_id++;
				}

				statement = (Statement) connection.createStatement();

				statement.executeUpdate("INSERT INTO einkaufs_historie (einkauf_id, kunden_id, produkt_id, kauf_datum)"
						+ "VALUES (" + einkauf_id + ", " + kunden_id + ", " + product_id + ", '" + einkaufsDatum + "')");
			} else {
				statement = (Statement) connection.createStatement();

				statement.executeUpdate("UPDATE einkaufs_historie " + "SET kauf_datum = '" + einkaufsDatum + "' "
						+ "WHERE produkt_id = " + product_id + " AND kunden_id = " + kunden_id);
			}

			statement = (Statement) connection.createStatement();

			statement.executeUpdate("DELETE FROM temp_einkaufsliste " + "WHERE produkt_id = " + product_id
					+ " AND kunden_id = " + kunden_id);

			// connection.commit();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<String> get_WarenkorbListe(int kunden_id) {

		connection = dbConnector.getDBVerbindung();
		
		ArrayList<String> arrayList = new ArrayList<>();

		if (connection != null) {
			//System.out.println("verbindung stshet");
		}

		try {
			statement = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			result = statement.executeQuery("SELECT produkt_id FROM standard_warenkorb WHERE kunden_id = " + kunden_id);
			result.last();
			int anzahl = result.getRow();

			result.first();

			ArrayList<Integer> integerArrayList = new ArrayList<>();
			for (int j = 0; j < anzahl; j++) {
				integerArrayList.add(result.getInt("produkt_id"));
				result.next();
			}

			for (int i = 0; i < integerArrayList.size(); i++) {
				result = statement
						.executeQuery("SELECT Bezeichnung FROM produkt WHERE produkt_id = " + integerArrayList.get(i));
				result.first();
				arrayList.add(result.getString("Bezeichnung"));
			}

			// connection.commit();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return arrayList;
	}

	public void put_Product_On_Einkaufsliste(int kunden_id, String product, int putTag) {

		String verbrauchsDatum = this.ermittleEinkaufsDatum(putTag);

		connection = dbConnector.getDBVerbindung();

		if (connection != null) {
			//System.out.println("verbindung stshet");
		}

		try {
			statement = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			result = statement.executeQuery("SELECT produkt_id FROM produkt WHERE Bezeichnung = '" + product + "'");
			result.first();
			int product_id = result.getInt("produkt_id");

			// Einf�gen in EInkaufshistorie
			result = statement.executeQuery("SELECT * FROM einkaufs_historie WHERE produkt_id = " + product_id
					+ " AND kunden_id = " + kunden_id);
			result.last();

			if (result.getRow() == 0) {
				result = statement.executeQuery("SELECT max(einkauf_id) AS einkauf_id FROM einkaufs_historie");
				result.first();
				int einkauf_id = result.getInt("einkauf_id");

				if (einkauf_id == 0) {
					einkauf_id = 1;
				} else {
					einkauf_id++;
				}

				statement = (Statement) connection.createStatement();

				statement.executeUpdate(
						"INSERT INTO einkaufs_historie (einkauf_id, kunden_id, produkt_id, verbrauch_datum)"
								+ "VALUES (" + einkauf_id + ", " + kunden_id + ", " + product_id + ", '" + verbrauchsDatum
								+ "')");
			} else {
				statement = (Statement) connection.createStatement();

				statement.executeUpdate("UPDATE einkaufs_historie " + "SET verbrauch_datum = '" + verbrauchsDatum + "' "
						+ "WHERE produkt_id = " + product_id + " AND kunden_id = " + kunden_id);
			}

			// EInf�gen in EInkaufsliste
			result = statement.executeQuery("SELECT * FROM temp_einkaufsliste WHERE kunden_id = " + kunden_id
					+ " AND produkt_id = " + product_id);
			result.first();

			if (result.getRow() == 0) {
				statement = (Statement) connection.createStatement();

				statement.executeUpdate("INSERT INTO temp_einkaufsliste (kunden_id, produkt_id) " + "VALUES ("
						+ kunden_id + ", " + product_id + ")");

			} else {
				// connection.commit();
				//connection.close();
			}

			// Verbrauch in Tagen ausrechnen

			result = statement.executeQuery("SELECT kauf_datum FROM einkaufs_historie WHERE produkt_id = " + product_id
					+ " AND kunden_id = " + kunden_id);
			result.last();

			if (result.getRow() == 0) {
				/*
				 * result = statement.
				 * executeQuery("SELECT max(einkauf_id) AS einkauf_id FROM einkaufs_historie");
				 * result.first(); int einkauf_id = result.getInt("einkauf_id");
				 * 
				 * if(einkauf_id == 0) { einkauf_id = 1; } else { einkauf_id++; }
				 * 
				 * statement = connection.createStatement();
				 * 
				 * statement.
				 * executeUpdate("INSERT INTO einkaufs_historie (einkauf_id, kunden_id, produkt_id, verbrauch_datum)"
				 * + "VALUES (" + einkauf_id + ", " + Control_Klasse.kunden_id + ", " +
				 * product_id + ", '" + fdate + "')");
				 */

				// do nothing
			} else {
				result.first();
				//if(result.next()==true) {
					String datum = result.getString("kauf_datum");
					// String datum = "2018-05-01";
					Date kauf_date = new SimpleDateFormat("yyyy-MM-dd").parse(datum);
					long diff = new SimpleDateFormat("yyyy-MM-dd").parse(verbrauchsDatum).getTime() - kauf_date.getTime();
					//long diff = new Date().getTime() - kauf_date.getTime();
					diff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

					result = statement.executeQuery("SELECT max(ID) AS ID FROM produkt_verbrauchsstatistik");
					result.first();
					int id = result.getInt("ID") + 1;

					// INsert der Differenz der Tage
					statement = (Statement) connection.createStatement();

					statement.executeUpdate(
							"INSERT INTO produkt_verbrauchsstatistik (ID, kunden_id, produkt_id, verbrauch_in_tagen)"
									+ "VALUES (" + id + ", " + kunden_id + ", " + product_id + ", " + diff + ")");
				//}
				
			}

			// connection.commit();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * ermittle das Einkaufsdatum aus dem Parameter einkaufsTag.
	 * 
	 * ist auch g�ltig beim Einf�gen eines Produkt auf der einkaufsliste -- einkaufsTag w�re dabei putTag
	 * 
	 * @param einkaufsTag : Anzahl der Tage seit heute
	 * @return
	 */
	public String ermittleEinkaufsDatum(int einkaufsTag) {
		String einkaufsDatum;
		Calendar c = Calendar.getInstance();
		Date today = c.getTime();
		Long todayInTagen = TimeUnit.DAYS.convert(today.getTime(), TimeUnit.MILLISECONDS);
		Long einkaufsDatumInTagen = todayInTagen + einkaufsTag;
		Long einKaufsDatumInMiliSec = TimeUnit.MILLISECONDS.convert(einkaufsDatumInTagen, TimeUnit.DAYS);
		
		c.setTimeInMillis(einKaufsDatumInMiliSec);
		Date einKaufsDatumDate = c.getTime();
		einkaufsDatum = new SimpleDateFormat("yyyy-MM-dd").format(einKaufsDatumDate);
		
//		System.out.println("einkaufsDatum in Tagen: " + einkaufsDatumInTagen);
//		System.out.println("einkaufsDatum in Miliseconds: " + einKaufsDatumInMiliSec);
//		System.out.println("einkaufsDatum in String: " + einkaufsDatum);
		
		return einkaufsDatum;
	}	
	
	
	
	/**
	 * sortiere eine Liste, die keyListe von der istEinkaufslisteMap, so dass die Map-Werte �ber den Key sortiert 
	 * abgerufen werden k�nnen.
	 * @param istEinkaufsListenMap
	 * @return
	 */
	public List<Integer> sortiereIstMapKey(Map<Integer, List<String>> istEinkaufsListenMap){
		List<Integer> istListeMapKey = new ArrayList<>();
		for(int key : istEinkaufsListenMap.keySet()) {
			istListeMapKey.add(key);
		}
		Collections.sort(istListeMapKey);
		
		return istListeMapKey;
	}

}
