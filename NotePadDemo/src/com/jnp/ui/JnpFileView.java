/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jnp.ui;

import com.jnp.JnpConstants;
import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileView;

/**
 *
 * @author sabuj.das
 */
public class JnpFileView extends FileView {
    public static final String CSV_FILE_EXTENSION = ".csv";
    public static final String EXCEL_FILE_EXTENSION = ".xls";
    public static final String PDF_FILE_EXTENSION = ".pdf";
    public static final String XML_FILE_EXTENSION = ".xml";
    public static final String XSL_FILE_EXTENSION = ".xsl";
  public static Icon xmlIcon = new ImageIcon(JnpFileView.class.getResource(
          JnpConstants.IMAGE_PATH + "xml_1.png"));
  public static Icon xlsIcon = new ImageIcon(JnpFileView.class.getResource(
          JnpConstants.IMAGE_PATH + "xls_1.png"));
  public static Icon csvIcon = new ImageIcon(JnpFileView.class.getResource(
          JnpConstants.IMAGE_PATH + "csv_1.png"));
  public static Icon xsltIcon = new ImageIcon(JnpFileView.class.getResource(
          JnpConstants.IMAGE_PATH + "xml_1.png"));
  public static Icon pdfIcon = new ImageIcon(JnpFileView.class.getResource(
          JnpConstants.IMAGE_PATH + "pdf_1.png"));
  public static Icon otherIcon = new ImageIcon(JnpFileView.class.getResource(
          JnpConstants.IMAGE_PATH + "other_1.png"));

  
  public String getName(File file) {
    String filename = file.getName();
//    String name = filename;
//    if(!file.isDirectory()){
//        name = filename + " : " + file.length();
//    }
//    if (filename.endsWith(XML_FILE_EXTENSION)) {
//      typeDescription = "Xml Document";
//    } else if (filename.endsWith(XSL_FILE_EXTENSION)) {
//      typeDescription = "Xml Stylesheet";
//    } else if (filename.endsWith(CSV_FILE_EXTENSION)) {
//      typeDescription = "CSV File";
//    } else if (filename.endsWith(EXCEL_FILE_EXTENSION)) {
//      typeDescription = "Excel Document";
//    } else if (filename.endsWith(PDF_FILE_EXTENSION)) {
//      typeDescription = "PDF Document";
//    }
//    if (filename.endsWith(".java")) {
//
//      return name;
//    }
    return filename;
  }

  public String getTypeDescription(File file) {
    String typeDescription = "Files";
    String filename = file.getName().toLowerCase();

    if (filename.endsWith(XML_FILE_EXTENSION)) {
      typeDescription = "Xml Document";
    } else if (filename.endsWith(XSL_FILE_EXTENSION)) {
      typeDescription = "Xml Stylesheet";
    } else if (filename.endsWith(CSV_FILE_EXTENSION)) {
      typeDescription = "CSV File";
    } else if (filename.endsWith(EXCEL_FILE_EXTENSION)) {
      typeDescription = "Excel Document";
    } else if (filename.endsWith(PDF_FILE_EXTENSION)) {
      typeDescription = "PDF Document";
    }
    return typeDescription;
  }

  public Icon getIcon(File file) {
    if (file.isDirectory()) {
      return null;
    }
    Icon icon = null;
    String filename = file.getName().toLowerCase();
    if (filename.endsWith(XML_FILE_EXTENSION)) {
      icon = xmlIcon;
    } else if (filename.endsWith(XSL_FILE_EXTENSION)) {
      icon = xsltIcon;
    } else if (filename.endsWith(EXCEL_FILE_EXTENSION)) {
      icon = xlsIcon;
    } else if (filename.endsWith(CSV_FILE_EXTENSION)) {
      icon = csvIcon;
    } else if (filename.endsWith(PDF_FILE_EXTENSION)) {
      icon = pdfIcon;
    } else{
        icon = otherIcon;
    }
    return icon;
  }

}
