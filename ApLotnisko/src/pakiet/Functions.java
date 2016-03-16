/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pakiet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Key;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import klasy.*;

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
		props.load(new FileInputStream("src/pakiet/properties.properties"));
		
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
        Pracownik temp = new Pracownik(id, imie, nazwisko);
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

}
