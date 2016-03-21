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
public class Samolot {
    
    private int id;
    private String model;
    private String nr_samolotu;

    public Samolot(int id, String model, String nr_samolotu) {
        this.id = id;
        this.model = model;
        this.nr_samolotu = nr_samolotu;
    }

    public Samolot() {
    }

    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public String getNr_samolotu() {
        return nr_samolotu;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setNr_samolotu(String nr_samolotu) {
        this.nr_samolotu = nr_samolotu;
    }

    @Override
    public String toString() {
        return model;
    }
    
    
    
    }
