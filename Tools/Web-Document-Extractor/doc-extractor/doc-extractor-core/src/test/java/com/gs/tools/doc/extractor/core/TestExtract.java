/**
 * 
 */
package com.gs.tools.doc.extractor.core;

import org.junit.Before;
import org.junit.Test;

import com.gs.tools.doc.extractor.core.html.HTMLDocumentExtractor;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class TestExtract {

	private WebDocumentExtractor documentExtractor;
	
	@Before public void init(){
		documentExtractor = new HTMLDocumentExtractor();
	}
	
	
	@Test
	public void test1(){
		try {
			documentExtractor.extract(
                    "http://docs.spring.io/spring/docs/3.1.x/spring-framework-reference/html/index.html", 
                    "d:\\temp\\x1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("done");
	}
}
