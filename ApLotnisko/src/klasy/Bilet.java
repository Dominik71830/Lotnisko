/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klasy;

/**
 *
 * @author Dominik
 */
public class Bilet {
    private int id;
    private String data_lotu;
    private String data_wystawienia_biletu;
    private String imie_pasazera;
    private String nazwisko_pasazera;
    private int id_miejsca_docelowego;
    private int id_samolotu;

    public Bilet(int id, String data_lotu, String data_wyswietlenia_biletu, String imie_pasazera, String nazwisko_pasezera, int id_miejsca_docelowego, int id_samolotu) {
        this.id = id;
        this.data_lotu = data_lotu;
        this.data_wystawienia_biletu = data_wyswietlenia_biletu;
        this.imie_pasazera = imie_pasazera;
        this.nazwisko_pasazera = nazwisko_pasezera;
        this.id_miejsca_docelowego = id_miejsca_docelowego;
        this.id_samolotu = id_samolotu;
    }

    public Bilet() {
    }

    public int getId() {
        return id;
    }

    public String getData_lotu() {
        return data_lotu;
    }

    public String getData_wystawienia_biletu() {
        return data_wystawienia_biletu;
    }

    public String getImie_pasazera() {
        return imie_pasazera;
    }

    public String getNazwisko_pasazera() {
        return nazwisko_pasazera;
    }

    public int getId_miejsca_docelowego() {
        return id_miejsca_docelowego;
    }

    public int getId_samolotu() {
        return id_samolotu;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setData_lotu(String data_lotu) {
        this.data_lotu = data_lotu;
    }

    public void setData_wystawienia_biletu(String data_wystawienia_biletu) {
        this.data_wystawienia_biletu = data_wystawienia_biletu;
    }

    public void setImie_pasazera(String imie_pasazera) {
        this.imie_pasazera = imie_pasazera;
    }

    public void setNazwisko_pasazera(String nazwisko_pasazera) {
        this.nazwisko_pasazera = nazwisko_pasazera;
    }

    public void setId_miejsca_docelowego(int id_miejsca_docelowego) {
        this.id_miejsca_docelowego = id_miejsca_docelowego;
    }

    public void setId_samolotu(int id_samolotu) {
        this.id_samolotu = id_samolotu;
    }

    @Override
    public String toString() {
        return "Bilet{" + "id=" + id + ", data_lotu=" + data_lotu + ", data_wystawienia_biletu=" + data_wystawienia_biletu + ", imie_pasazera=" + imie_pasazera + ", nazwisko_pasezera=" + nazwisko_pasazera + ", id_miejsca_docelowego=" + id_miejsca_docelowego + ", id_samolotu=" + id_samolotu + '}';
    }
    
    
}
