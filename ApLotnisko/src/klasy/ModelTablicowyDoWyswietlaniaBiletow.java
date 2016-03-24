/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klasy;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import pakiet.*;

/**
 *
 * @author Dominik
 */
public class ModelTablicowyDoWyswietlaniaBiletow extends AbstractTableModel{
    
    public static final int OBJECT_COL = -1;
    public static final int ID_COL = 0;
    public static final int IMIE_COL = 1;
    public static final int NAZWISKO_COL = 2;
    public static final int DATA_LOTU_COL = 3;
    public static final int NAZWA_MIEJSCA_DOCELOWEGO_COL = 4;
    public static final int CENA_LOTU_COL = 5;
    public static final int MODEL_SAMOLOTU = 6;
    public static final int NR_SAMOLOTU = 7;
    public static final int DATA_WYSTAWIENIA_BILETU = 8;
    
    
    private String [] nazwykolumn = {"Id", 
                                        "Imie pasażera", 
                                        "Nazwisko pasażera", 
                                        "Data lotu",
                                        "Miejsce docelowe",
                                        "Cena",
                                        "Model samolotu",
                                        "Nr samolotu",
                                        "Data wystawienia biletu"};
    
    private List<Samolot> samoloty;
    private List<Bilet> bilety;
    private List<Miasto> miasta;
    
    Functions f;

    public ModelTablicowyDoWyswietlaniaBiletow(List<Samolot> samoloty, List<Bilet> bilety, List<Miasto> miasta) throws IOException, FileNotFoundException, SQLException {
        super();
        this.samoloty = samoloty;
        this.bilety = bilety;
        this.miasta = miasta;
        f = new Functions();
    }

    @Override
    public int getRowCount() {
        return bilety.size();
    }

    @Override
    public String getColumnName(int _nr) {
		return nazwykolumn[_nr];
	}
    
    @Override
    public int getColumnCount() {
        return nazwykolumn.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Bilet temp = bilety.get(row);
        //int id_samolotu = temp.getId_samolotu();
        //int id_miejsca_docelowego = temp.getId_miejsca_docelowego();
        
        
        switch (col) {
            case ID_COL:
                return temp.getId();
                
            case IMIE_COL:
                return temp.getImie_pasazera();
                
            case NAZWISKO_COL:
                return temp.getNazwisko_pasazera();
                
            case DATA_LOTU_COL:
                return temp.getData_lotu();
                
            case NAZWA_MIEJSCA_DOCELOWEGO_COL:
               {
            try {
                return f.Informacje_miasto(temp)[0];
            } catch (SQLException ex) {
                Logger.getLogger(ModelTablicowyDoWyswietlaniaBiletow.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
                
            case CENA_LOTU_COL:
                {
            try {
                return f.Informacje_miasto(temp)[1];
            } catch (SQLException ex) {
                Logger.getLogger(ModelTablicowyDoWyswietlaniaBiletow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                
            case MODEL_SAMOLOTU:
        {
            try {
                return f.Informacje_samolot(temp)[0];
            } catch (SQLException ex) {
                Logger.getLogger(ModelTablicowyDoWyswietlaniaBiletow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                
            case NR_SAMOLOTU:    
            { 
                try {
                return f.Informacje_samolot(temp)[1];
            } catch (SQLException ex) {
                Logger.getLogger(ModelTablicowyDoWyswietlaniaBiletow.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            
            case DATA_WYSTAWIENIA_BILETU:
                return temp.getData_wystawienia_biletu();
                
            case OBJECT_COL:
			return temp;
                
            default:
                return temp.getId();
                
        }
    }
    
    
}
