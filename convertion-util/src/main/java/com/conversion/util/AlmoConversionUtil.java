package com.conversion.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.xml.Unmarshaller;
import org.xml.sax.InputSource;

import com.conversion.mapping.AlmoCatalogData;

public class AlmoConversionUtil {

	public static void convertXml2Csv(String inputXml, String outputCsv,
			char csvSeperator) {
		if (inputXml == null || inputXml.equals("")) {
			return;
		}
		File inputXmlFile = new File(inputXml);
		File outputCsvFile = null;
		if (outputCsv == null || outputCsv.equals("")) {
			outputCsvFile = new File(inputXmlFile.getAbsolutePath() + ".csv");
		} else {
			outputCsvFile = new File(outputCsv);
		}

		Mapping mapping = new Mapping();

		try {
			String mappingFile = AlmoConversionUtil.class.getResource(
					"/mappings/almo-mapping.xml").getFile();
			mapping.loadMapping(mappingFile);
			Unmarshaller unmar = new Unmarshaller(mapping);
			AlmoCatalogData cat = (AlmoCatalogData) unmar
					.unmarshal(new InputSource(new FileReader(inputXmlFile)));
			BufferedWriter bw = null;
			try {
				bw = new BufferedWriter(new FileWriter(outputCsvFile));
				bw.write(cat.toCsv(csvSeperator));
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (bw != null) {
					bw.close();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
