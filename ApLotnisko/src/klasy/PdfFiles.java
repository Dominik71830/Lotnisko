/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klasy;

import java.io.FileOutputStream;
import java.util.Date;

import pakiet.*;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;



public class PdfFiles {
  private static String FILE = "C:/Users/Dominik/Lotnisko/Pliki PDF/BiletPdf.pdf";
  private static String FILE2 = "C:/Users/Dominik/Lotnisko/Pliki PDF/TablePdf.pdf";
  private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
      Font.BOLD);
  private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
      Font.NORMAL, BaseColor.RED);
  private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
      Font.BOLD);
  private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
      Font.BOLD);
  private static Bilet b;
  private static Miasto m;
  private static Samolot s;
  
  private static ArrayList<Bilet> bilety;
  private static ArrayList<Miasto> miasta;
  private static ArrayList<Samolot> samoloty;

  
  
  
  
  public static void createPdf() {
    try {
      Document document = new Document();
      PdfWriter.getInstance(document, new FileOutputStream(FILE));
      document.open();
      addTitlePage(document);
      //addContent(document);
      document.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }



  public static void addTitlePage(Document document)
      throws DocumentException {
    Paragraph preface = new Paragraph();
    // We add one empty line
    //addEmptyLine(preface, 1);
    // Lets write a big header
    preface.add(new Paragraph("Bilet nr " + b.getId(), catFont));

   
    //addEmptyLine(preface, 3);
    preface.add(new Paragraph("Imie i nazwisko: " + b.getImie_pasazera() + ' ' + b.getNazwisko_pasazera(),
        smallBold));
    preface.add(new Paragraph("Data lotu: " + b.getData_lotu(),
        smallBold));
    preface.add(new Paragraph("Data wystawienia biletu: " + b.getData_wystawienia_biletu(),
        smallBold));
    //preface.add(new Paragraph("Data lotu: " + b.getData_lotu(),
       // smallBold));
    preface.add(new Paragraph("Cel podróży: " + m.getNazwa(),
        smallBold));
    preface.add(new Paragraph("Cena: " + m.getCena(),
        smallBold));
    preface.add(new Paragraph("Model samolotu: " + s.getModel(),
        smallBold));
    preface.add(new Paragraph("Nr lotu: " + s.getNr_samolotu(),
        smallBold));
    
    
    addEmptyLine(preface, 30);
    preface.add(new Paragraph("Wygenerowano: "   + new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        smallBold));
    
    document.add(preface);
    
    
  }

  public static void addContent(Document document) throws DocumentException {
    Anchor anchor = new Anchor("First Chapter", catFont);
    anchor.setName("First Chapter");

    // Second parameter is the number of the chapter
    Chapter catPart = new Chapter(new Paragraph(anchor), 1);

    Paragraph subPara = new Paragraph("Subcategory 1", subFont);
    Section subCatPart = catPart.addSection(subPara);
    subCatPart.add(new Paragraph("Hello"));

    subPara = new Paragraph("Subcategory 2", subFont);
    subCatPart = catPart.addSection(subPara);
    subCatPart.add(new Paragraph("Paragraph 1"));
    subCatPart.add(new Paragraph("Paragraph 2"));
    subCatPart.add(new Paragraph("Paragraph 3"));

    // add a list
    createList(subCatPart);
    Paragraph paragraph = new Paragraph();
    addEmptyLine(paragraph, 5);
    subCatPart.add(paragraph);

    // add a table
    //createTable(subCatPart);

    // now add all this to the document
    document.add(catPart);

    // Next section
    anchor = new Anchor("Second Chapter", catFont);
    anchor.setName("Second Chapter");

    // Second parameter is the number of the chapter
    catPart = new Chapter(new Paragraph(anchor), 1);

    subPara = new Paragraph("Subcategory", subFont);
    subCatPart = catPart.addSection(subPara);
    subCatPart.add(new Paragraph("This is a very important message"));

    // now add all this to the document
    document.add(catPart);

  }

  public static void createTable(){
      try{
    Document document = new Document();
    PdfWriter.getInstance(document, new FileOutputStream(FILE2));
    document.open();
    PdfPTable table = new PdfPTable(8);
    
   

    
    PdfPCell c1 = new PdfPCell(new Phrase("Imie pasazera"));
    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(c1);

    c1 = new PdfPCell(new Phrase("Nazwisko pasazera"));
    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(c1);

    c1 = new PdfPCell(new Phrase("Data lotu"));
    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(c1);
    
    c1 = new PdfPCell(new Phrase("Data wystawienia biletu"));
    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(c1);
    
    c1 = new PdfPCell(new Phrase("Cel podrozy: "));
    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(c1);
    
    c1 = new PdfPCell(new Phrase("Cena: "));
    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(c1);
    
    c1 = new PdfPCell(new Phrase("Model samolotu: "));
    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(c1);
    
    c1 = new PdfPCell(new Phrase("Nr lotu: "));
    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(c1);
    
    Functions f = new Functions();
     
    
    for(Bilet b : bilety){
        Miasto m = f.getMiasto(b);
        Samolot s = f.getSamolot(b);
        
        table.addCell(b.getImie_pasazera());
        table.addCell(b.getNazwisko_pasazera());
        table.addCell(b.getData_lotu());
        table.addCell(b.getData_wystawienia_biletu());
        table.addCell(m.getNazwa());
        table.addCell(Double.toString(m.getCena()));
        table.addCell(s.getModel());
        table.addCell(s.getNr_samolotu());
        //JOptionPane.showMessageDialog(null, b);
        
    }
     Paragraph preface = new Paragraph();
    preface.add(table);
    
     
          document.add(preface);
          document.close();
  
    
    
    
    
    
      addEmptyLine(preface, 30);
    preface.add(new Paragraph("Wygenerowano: "   + new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        smallBold));

      }
      catch(Exception e){
          JOptionPane.showMessageDialog(null, e);
      }

  }

  private static void createList(Section subCatPart) {
    List list = new List(true, false, 10);
    list.add(new ListItem("First point"));
    list.add(new ListItem("Second point"));
    list.add(new ListItem("Third point"));
    subCatPart.add(list);
  }

  private static void addEmptyLine(Paragraph paragraph, int number) {
    for (int i = 0; i < number; i++) {
      paragraph.add(new Paragraph(" "));
    }
  }

    public PdfFiles(Bilet b,Miasto m,Samolot s) {
        this.b = b;
        this.m = m;
        this.s = s; 
   }

    public PdfFiles(ArrayList<Bilet> bilety,ArrayList<Miasto> miasta, ArrayList<Samolot> samoloty) {
      this.bilety = bilety;
      this.miasta = miasta;
      this.samoloty = samoloty;
      
    }
    
    
} 
