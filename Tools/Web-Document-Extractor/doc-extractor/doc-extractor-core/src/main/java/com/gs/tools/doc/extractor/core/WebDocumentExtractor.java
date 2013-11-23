/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gs.tools.doc.extractor.core;

import java.nio.charset.Charset;

/**
 *
 * @author sabuj
 */
public interface WebDocumentExtractor {
    Charset ENCODING_UTF_8 = Charset.forName("UTF-8");
    
    String getFileExtension();
    long extract(String sourceUrl, String targetFolderName) throws Exception;
    
}
