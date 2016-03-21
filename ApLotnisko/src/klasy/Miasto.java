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
public class Miasto {
    private int id;
    private String nazwa;
    private int Cena;

    public Miasto() {
    }
    
    public Miasto(int id, String nazwa, int Cena) {
        this.id = id;
        this.nazwa = nazwa;
        this.Cena = Cena;
    }

    public int getId() {
        return id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public int getCena() {
        return Cena;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setCena(int Cena) {
        this.Cena = Cena;
    }

    @Override
    public String toString() {
        return "Miasto{" + "id=" + id + ", nazwa=" + nazwa + ", Cena=" + Cena + '}';
    }
    
    
    
}
