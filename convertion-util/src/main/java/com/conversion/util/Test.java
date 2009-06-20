package com.conversion.util;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import com.sun.xml.stream.XMLReaderImpl;



public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		XMLInputFactory f = XMLInputFactory.newInstance();
		XMLStreamReader r = f.createXMLStreamReader(new FileInputStream(new File("F:\\Downloads\\allbooks.xml")));
		
	}

}
