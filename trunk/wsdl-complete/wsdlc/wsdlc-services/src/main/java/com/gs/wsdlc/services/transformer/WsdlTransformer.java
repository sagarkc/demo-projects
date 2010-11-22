/**
 * 		WSDL - Complete
 *
 *	sabuj.das@gmail.com
 */
package com.gs.wsdlc.services.transformer;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;

import org.xmlsoap.schemas.wsdl.DefinitionsDocument;
import org.xmlsoap.schemas.wsdl.TDefinitions;
import org.xmlsoap.schemas.wsdl.TService;
import org.xmlsoap.schemas.wsdl.impl.DefinitionsDocumentImpl;



/**
 * @author Sabuj Das
 *
 */
public class WsdlTransformer {

	private static Logger logger = Logger.getLogger(WsdlTransformer.class);
	
	
	public static void main(String[] args) {
		XmlOptions xmloptions = new XmlOptions();
		xmloptions.setDocumentType(XmlBeans.typeForClass(DefinitionsDocumentImpl.class));
		try {
			DefinitionsDocument definitionsDocument = DefinitionsDocument.Factory.parse(new File("d:\\temp\\test.xml"), xmloptions);
			if(null != definitionsDocument){
				TDefinitions tDefinitions = definitionsDocument.getDefinitions();
				TService tService = tDefinitions.getServiceArray(0);
				System.out.println(tService.getName());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
