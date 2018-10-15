package com.example.fabiosprotte.kassen_system;

/**
 * Created by Fabio Sprotte on 23.08.2018.
 */
public class Produkt {
    int produkt_id;
    String Bezeichnung;
    boolean warenkorb = false;

    public Produkt() {
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

    public boolean isWarenkorb() {
        return warenkorb;
    }

    public void setWarenkorb(boolean warenkorb) {
        this.warenkorb = warenkorb;
    }
}
