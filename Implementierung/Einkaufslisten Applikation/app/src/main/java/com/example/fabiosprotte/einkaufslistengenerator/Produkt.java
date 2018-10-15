package com.example.fabiosprotte.einkaufslistengenerator;

/**
 * Created by Fabio Sprotte on 15.05.2018.
 */
public class Produkt {

    int produkt_id;
    String Bezeichnung;
    double verbrauchswarscheinlichkeit;
    boolean in_warenkorb = false;

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

    public double getVerbrauchswarscheinlichkeit() {
        return verbrauchswarscheinlichkeit;
    }

    public void setVerbrauchswarscheinlichkeit(double verbrauchswarscheinlichkeit) {
        this.verbrauchswarscheinlichkeit = verbrauchswarscheinlichkeit;
    }

    public boolean isIn_warenkorb() {
        return in_warenkorb;
    }

    public void setIn_warenkorb(boolean in_warenkorb) {
        this.in_warenkorb = in_warenkorb;
    }
}
