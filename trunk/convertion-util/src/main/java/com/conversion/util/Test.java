package com.conversion.util;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;



public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		XMLInputFactory f = XMLInputFactory.newInstance();
		XMLStreamReader r = f.createXMLStreamReader(new FileInputStream(new File("F:\\Downloads\\allbooks.xml")));
		int count = 0;
        while (r.hasNext()) {
            count ++;
            r.next();
        }
        System.out.println("REC : " + count);
	}

}
