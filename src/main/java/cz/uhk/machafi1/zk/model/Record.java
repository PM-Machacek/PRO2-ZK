package cz.uhk.machafi1.zk.model;

import com.opencsv.bean.CsvBindByName;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Record {

    @CsvBindByName(column = "datum")
    public String datum;

    @CsvBindByName(column = "pocet_PCR_testy")
    public int pocetPCRtestu;

    @CsvBindByName(column = "incidence_pozitivni")
    public int pocetPozitivnich;

    @CsvBindByName(column = "PCR_pozit_sympt")
    public int pocetPCRPozitivnich;


    public String getDatum() {
        return datum;
    }

    public Date getParsedDatum() {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(datum);
        } catch (Exception ex) {
            return null;
        }
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public int getPocetPCRtestu() {
        return pocetPCRtestu;
    }

    public void setPocetPCRtestu(int pocetPCRtestu) {
        this.pocetPCRtestu = pocetPCRtestu;
    }

    public int getPocetPozitivnich() {
        return pocetPozitivnich;
    }

    public void setPocetPozitivnich(int pocetPozitivnich) {
        this.pocetPozitivnich = pocetPozitivnich;
    }

    public int getPocetPCRPozitivnich() {
        return pocetPCRPozitivnich;
    }

    public void setPocetPCRPozitivnich(int pocetPCRPozitivnich) {
        this.pocetPCRPozitivnich = pocetPCRPozitivnich;
    }
}
