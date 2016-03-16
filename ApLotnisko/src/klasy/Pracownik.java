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
public class Pracownik {
    private int id;
    private String imie;
    private String nazwisko;
    private String haslo;

    public Pracownik(int id, String imie, String nazwisko,String haslo) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.haslo = haslo;
    }

    public int getId() {
        return id;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    @Override
    public String toString() {
        return imie + ' ' + nazwisko;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }
    
    
    
    
}
