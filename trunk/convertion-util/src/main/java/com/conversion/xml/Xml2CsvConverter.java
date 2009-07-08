/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.conversion.xml;

/**
 *
 * @author sabuj.das
 */
public class Xml2CsvConverter {

    public static boolean convertXml2Csv(String inputXmlFile,
            String xsltfFile){
        
        return true;
    }

    public static boolean convertXml2Csv(String inputXmlFile, 
            String xsltfFile, String outputCsvFile){
        String[] args = new String[]
        {
            "-IN", inputXmlFile,
            "-XSL", xsltfFile,
            "-OUT", outputCsvFile
        };
        try{
        	xalanConverter(args);
        }catch(Exception ex){
        	return false;
        }
        return true;
    }

    private static void xalanConverter(String[] args){
        if(args != null){
            org.apache.xalan.xslt.Process.main(args);
        }
    }

    public static void main(String[] a){
        //con
    }

}
