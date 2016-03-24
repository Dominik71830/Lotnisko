/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pakiet;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.Key;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import klasy.*;
import net.proteanit.sql.DbUtils;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author Dominik
 */
public class Functions {
    
    
    Connection myConn= null;
    
    private static final String ALGORITHM = "AES";
    private static final byte[] keyValue = 
    new byte[] { 'T', 'h', 'i', 's', 'I', 's', 'A', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y' };

    public Functions() throws FileNotFoundException, IOException, SQLException {
        
                Properties props = new Properties();
		props.load(new FileInputStream("properties.properties"));
		
		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String url = props.getProperty("url");
                //JOptionPane.showMessageDialog(null, user + password + url);
                
                myConn = DriverManager.getConnection(url, user, password);
		
		System.out.println("Połączono z " + url + "\n" + "Użytkownik: " + user);
    }

    
    
    
    
 public String encrypt(String valueToEnc) throws Exception {
    Key key = generateKey();
    Cipher c = Cipher.getInstance(ALGORITHM);
    c.init(Cipher.ENCRYPT_MODE, key);
    byte[] encValue = c.doFinal(valueToEnc.getBytes());
    String encryptedValue = new BASE64Encoder().encode(encValue);
    return encryptedValue;
}

public String decrypt(String encryptedValue) throws Exception {
    Key key = generateKey();
    Cipher c = Cipher.getInstance(ALGORITHM);
    c.init(Cipher.DECRYPT_MODE, key);
    byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedValue);
    byte[] decValue = c.doFinal(decordedValue);
    String decryptedValue = new String(decValue);
    return decryptedValue;
}

private static Key generateKey() throws Exception {
    Key key = new SecretKeySpec(keyValue, ALGORITHM);
    return key;
}

private Pracownik convertRowToPracownik(ResultSet _myRs) throws SQLException {
        int id = _myRs.getInt("id");
        String imie = _myRs.getString("imie");
        String nazwisko = _myRs.getString("nazwisko");
        String haslo = _myRs.getString("haslo");
        Pracownik temp = new Pracownik(id, imie, nazwisko,haslo);
        return temp;
    }

public List<Pracownik> getAllPracownicy() throws SQLException {
	List<Pracownik> lista = new ArrayList<Pracownik>();
	
	Statement myStmt = null;
	ResultSet myRs = null;
	
	try{
		myStmt = myConn.createStatement();
		myRs = myStmt.executeQuery("select * from pracownicy");
		
		while(myRs.next()){
			Pracownik temp = convertRowToPracownik(myRs);
			lista.add(temp);
		}
	return lista;
	}
	finally{
		myStmt.close();
                myRs.close();
	}
}

    void fillJComboboxWithPracownik(JComboBox jComboBoxPracownik) throws SQLException {
        List<Pracownik> listaPracownikow = new ArrayList<Pracownik>();
        listaPracownikow = getAllPracownicy();
        
        for(Pracownik p : listaPracownikow){
           jComboBoxPracownik.addItem(p);
        }
    }

    public boolean compare(String s1, String s2){
    return(s1.equals(s2) ? true : false);
}

    void fillJTableWithBilety(JTable jTableBilety) throws SQLException {

        Statement myStmt = myConn.createStatement();
        ResultSet myRs = myStmt.executeQuery("SELECT * FROM bilety");
        jTableBilety.setModel(DbUtils.resultSetToTableModel(myRs));
    }

    void changeColumnNames(JTable jTableBilety) {
        TableColumnModel tcm = jTableBilety.getColumnModel();
                //tcm.getColumn(3).setHeaderValue("Data urodzenia"); // zmiana nazwy kolumny
        
            tcm.removeColumn(tcm.getColumn(0)); // usuwanie kolumn
        
        
            tcm.getColumn(0).setHeaderValue("Numer lotu");
            tcm.getColumn(1).setHeaderValue("Cena");
            tcm.getColumn(2).setHeaderValue("Data odlotu");
            tcm.getColumn(3).setHeaderValue("Data wystawienia biletu");
            tcm.getColumn(4).setHeaderValue("Miejce podróży");
            tcm.getColumn(5).setHeaderValue("Imię pasażera");
            tcm.getColumn(6).setHeaderValue("Nazwisko pasażera");
            tcm.getColumn(7).setHeaderValue("Model samolotu");
            
            
    }
    
    
    public double distancebetween2Points (Point p1, Point p2){
return (
        Math.sqrt(
        (p1.getX() - p2.getX()) 
        * 
        (p1.getX() - p2.getX())
        + 
        (p1.getY() - p2.getY())
        *
        (p1.getY() - p2.getY())
        )
        );
    
}
    
    
    public List<Miasto> getAllMiasta() throws SQLException {
	List<Miasto> lista = new ArrayList<Miasto>();
	
	Statement myStmt = null;
	ResultSet myRs = null;
	
	try{
		myStmt = myConn.createStatement();
		myRs = myStmt.executeQuery("select * from miejsca_docelowe");
		
		while(myRs.next()){
			Miasto temp = convertRowToMiasto(myRs);
			lista.add(temp);
		}
	return lista;
	}
	finally{
		myStmt.close();
                myRs.close();
	}
}

    public Miasto convertRowToMiasto(ResultSet _myRs) throws SQLException {
        int id = _myRs.getInt("id");
        String nazwa = _myRs.getString("nazwa");
        int cena = _myRs.getInt("cena");
        Miasto temp = new Miasto(id, nazwa,cena);
        return temp;
    }
    
    public List<Samolot> getAllSamoloty() throws SQLException {
	List<Samolot> lista = new ArrayList<Samolot>();
	
	Statement myStmt = null;
	ResultSet myRs = null;
	
	try{
		myStmt = myConn.createStatement();
		myRs = myStmt.executeQuery("select * from samoloty");
		
		while(myRs.next()){
			Samolot temp = convertRowToSamolot(myRs);
			lista.add(temp);
		}
	return lista;
	}
	finally{
		myStmt.close();
                myRs.close();
	}
}

    public Samolot convertRowToSamolot(ResultSet _myRs) throws SQLException {
        int id = _myRs.getInt("id");
        String model = _myRs.getString("model");
        String nr_samolotu = _myRs.getString("nr_samolotu");
        Samolot temp = new Samolot(id, model, nr_samolotu);
        return temp;
    }

    void fillJComboboxWithSamoloty(JComboBox jComboBoxSamoloty) throws SQLException {
        List<Samolot> lista = new ArrayList<Samolot>();
        lista = getAllSamoloty();
        
        for(Samolot s : lista){
           jComboBoxSamoloty.addItem(s);
        }
    }

    void addBilet(Bilet nowy_bilet) throws SQLException {
        PreparedStatement pstm = null;
        pstm = myConn.prepareStatement("insert into bilety (id_samolotu,data_lotu,imie_pasazera,nazwisko_pasazera,id_miejsca_docelowego)" +
                                        "values (?,?,?,?,?)");
        
        
        pstm.setInt(1, nowy_bilet.getId_samolotu());
        pstm.setString(2,nowy_bilet.getData_lotu());
        pstm.setString(3, nowy_bilet.getImie_pasazera());
        pstm.setString(4, nowy_bilet.getNazwisko_pasazera());
        pstm.setInt(5, nowy_bilet.getId_miejsca_docelowego());
       //JOptionPane.showMessageDialog(null,pstm.toString());
        pstm.execute();
        
    }

    void addPracownik(Pracownik nowy_pracownik) throws SQLException {
        PreparedStatement pstm = null;
        pstm = myConn.prepareStatement("insert into pracownicy (imie, nazwisko, haslo)" +
                                        "values (?,?,?)");
        
        
        pstm.setString(1, nowy_pracownik.getImie());
        pstm.setString(2, nowy_pracownik.getNazwisko());
        pstm.setString(3, nowy_pracownik.getHaslo());
       //JOptionPane.showMessageDialog(null,pstm.toString());
        pstm.execute();
        
    }
    
    public String[] Informacje_samolot (Bilet _b) throws SQLException
    {
        String[] tablica_informacji = new String[2]; 
        List<Samolot> lista_samolotow = new ArrayList<Samolot>();
        lista_samolotow = getAllSamoloty();
        
        for (Samolot s : lista_samolotow)
        {
          if (s.getId() == _b.getId_samolotu())
          {
              tablica_informacji[0]= s.getModel();
              tablica_informacji[1]=s.getNr_samolotu();
          }
        }
        
        return tablica_informacji;
    }
    
    
     public Bilet convertRowToBilet(ResultSet _myRs) throws SQLException {
        int id = _myRs.getInt("id");
        int id_samolotu = _myRs.getInt("id_samolotu");
        String data_lotu = _myRs.getString("data_lotu");
        String data_wystawienia_biletu = _myRs.getString("data_wystawienia_biletu");
        String imie_pasazera = _myRs.getString("imie_pasazera");
        String nazwisko_pasazera = _myRs.getString("nazwisko_pasazera");
        int id_miejsca_docelowego = _myRs.getInt("id_miejsca_docelowego");
        Bilet temp = new Bilet(id, data_lotu, data_wystawienia_biletu, imie_pasazera, nazwisko_pasazera, id_miejsca_docelowego, id_samolotu);
        //JOptionPane.showMessageDialog(null, id);
        return temp;
    }
    
    
    
    public List<Bilet> getAllBilety() throws SQLException {
	List<Bilet> lista = new ArrayList<Bilet>();
	
	Statement myStmt = null;
	ResultSet myRs = null;
	
	try{
		myStmt = myConn.createStatement();
		myRs = myStmt.executeQuery("select * from bilety");
		
		while(myRs.next()){
			Bilet temp = convertRowToBilet(myRs);
			lista.add(temp);
                        
		}
                
	return lista;
	}
	finally{
		myStmt.close();
                myRs.close();
	} 
    } 
    public String[] Informacje_miasto (Bilet _b) throws SQLException
    {
        String[] tablica_informacji = new String[2]; 
        List<Miasto> lista_miast = new ArrayList<Miasto>();
        lista_miast = getAllMiasta();
        
        for (Miasto s : lista_miast)
        {
          if (s.getId() == _b.getId_miejsca_docelowego())
          {
              tablica_informacji[0]= s.getNazwa();
              
              tablica_informacji[1]=Double.toString(s.getCena());
          }
        }
        
        return tablica_informacji;
    }
    
    
    public Miasto getMiasto (Bilet _b) throws SQLException{
        Miasto miasto = null;
        List<Miasto> miasta = new ArrayList<Miasto>();
        miasta = getAllMiasta();
        for(Miasto m : miasta)
        {
            
            if(m.getId()==_b.getId_miejsca_docelowego())
            {miasto = m;
            //JOptionPane.showMessageDialog(null, miasto);
            }
            
            
        }
        
        return miasto;
    }

   
    public void updateBilet(Bilet nowy_bilet) throws SQLException {
       PreparedStatement pstm = null;
       try{
           String sql = "update bilety set data_lotu=?, imie_pasazera=?, nazwisko_pasazera=?, id_miejsca_docelowego=?, id_samolotu=? where id=?";
           pstm = myConn.prepareStatement(sql);
           pstm.setString(1, nowy_bilet.getData_lotu());
           pstm.setString(2, nowy_bilet.getImie_pasazera());
           pstm.setString(3, nowy_bilet.getNazwisko_pasazera());
           pstm.setInt(4, nowy_bilet.getId_miejsca_docelowego());
           pstm.setInt(5, nowy_bilet.getId_samolotu());
           pstm.setInt(6, nowy_bilet.getId());
           
           pstm.execute();
           
       }
       catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
       }
       finally{
           pstm.close();
       }
    }

    public void playsound() throws FileNotFoundException, IOException, LineUnavailableException, UnsupportedAudioFileException {
        
        Clip clip = AudioSystem.getClip();
        /*InputStream in = new FileInputStream("src/muzyka/muzyka.wav");
        AudioStream as = new AudioStream(in);
        AudioPlayer.player.start(as);
                */
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("src/muzyka/muzyka.wav"));         
        //  clip.open(inputStream);
        //  clip.start(); 
        clip.open(inputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
