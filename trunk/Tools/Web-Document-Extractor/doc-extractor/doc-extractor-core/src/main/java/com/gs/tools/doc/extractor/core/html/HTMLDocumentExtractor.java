/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gs.tools.doc.extractor.core.html;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlHead;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gs.tools.doc.extractor.core.WebDocumentExtractor;
import com.gs.tools.doc.extractor.core.util.HttpUtility;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.log4j.Logger;

/**
 *
 * @author sabuj
 */
public class HTMLDocumentExtractor implements WebDocumentExtractor {

    private static Logger logger = Logger.getLogger(HTMLDocumentExtractor.class);
    private HttpClient httpClient;
    private HttpClient httpsClient;
    private WebClient webClient;
    private String userName;
    private String password;

    public HTMLDocumentExtractor() {
        httpClient = HttpUtility.getDefaultHttpClient();
        httpsClient = HttpUtility.getDefaultHttpsClient();
        webClient = new WebClient(BrowserVersion.FIREFOX_17);
        webClient.getOptions().setJavaScriptEnabled(false);
    }

    public HTMLDocumentExtractor(String userName, String password) {
        this.userName = userName;
        this.password = password;
        httpClient = HttpUtility.getLoginHttpClient(userName, password);
        httpsClient = HttpUtility.getLoginHttpsClient(userName, password);
    }

    @Override
    protected void finalize() throws Throwable {
        
        super.finalize(); 
    }
    
    

    public long extract(String sourceUrl, String targetFolderName) throws Exception {
        File rootDir = new File(targetFolderName);

        long fileCount = 0;

        HttpGet httpGet = new HttpGet(sourceUrl);
        String html = HttpUtility.readContentAsText(httpClient.execute(httpGet));
        if (null != html && html.length() > 0) {
            String[] cssLinks = StringUtils.substringsBetween(html, "<link", ">");
            if (null != cssLinks && cssLinks.length > 0) {
                for (String link : cssLinks) {
                    String rel = StringUtils.substringBetween(link, "rel=\"", "\"");
                    if (rel.equalsIgnoreCase("stylesheet")) {
                        String css = StringUtils.substringBetween(link, "href=\"", "\"");
                        if (css.contains("/")) {
                            String path = css.substring(css.lastIndexOf("/"));
                            //File cssFile = 
                        }
                    }
                }
            }
            String title = StringUtils.substringBetween(html, "<title>", "</title>");
            File titleFile = new File(rootDir, title);
            IOUtils.write(html, new BufferedWriter(new FileWriter(titleFile)));
            fileCount++;

        }
        return fileCount;
    }
    
    void x(String sourceUrl, String targetFolderName) throws Exception {
        long fileCount = 0;
        try {
            File rootDir = new File(targetFolderName);
            if(!rootDir.exists())
                throw new FileNotFoundException(rootDir.getAbsolutePath() + " not found");
            final HtmlPage page = webClient.getPage(sourceUrl);
            String html = page.asXml();
            HtmlElement head = page.getHead();
            if(null != head){
                DomNodeList<HtmlElement> links = head.getElementsByTagName("link");
                if(null != links && links.size() > 0){
                    for (HtmlElement link : links) {
                        String stylesheet = link.getAttribute("rel");
                        if("stylesheet".equalsIgnoreCase(stylesheet)){
                            String css = link.getAttribute("href");
                            String cssFileName = css;
                            if(css.contains("/")){
                                File cssFolder = new File(rootDir, css.substring(0, css.lastIndexOf("/")));
                                if(!cssFolder.exists()){
                                    cssFolder.mkdirs();
                                }
                                File cssFile = new File(cssFolder, css.substring(css.lastIndexOf("/")+1));
                                HttpGet get = new HttpGet(sourceUrl + "/" + css);
                                byte[] cssText = HttpUtility.getFile(sourceUrl + "/" + css);
                                IOUtils.write(cssText, new BufferedWriter(new FileWriter(cssFile)));
                                fileCount ++;
                            } else {
                                
                            }
                        }
                    }
                }
            }
        } finally {
            webClient.closeAllWindows();
        }
        //return fileCount;
    }

    public long extract(String sourceUrl, String targetFolderName, boolean https) throws Exception {
        final HttpClient client = (https) ? httpsClient : httpClient;
        File rootDir = new File(targetFolderName);

        long fileCount = 0;

        HttpGet httpGet = new HttpGet(sourceUrl);
        String html = HttpUtility.readContentAsText(client.execute(httpGet));
        if (null != html && html.length() > 0) {
            String[] cssLinks = StringUtils.substringsBetween(html, "<link", ">");
            if (null != cssLinks && cssLinks.length > 0) {
                for (String link : cssLinks) {
                    String rel = StringUtils.substringBetween(link, "rel=\"", "\"");
                    if (rel.equalsIgnoreCase("stylesheet")) {
                        String css = StringUtils.substringBetween(link, "href=\"", "\"");
                        if (css.contains("/")) {
                            String path = css.substring(css.lastIndexOf("/"));
                            //File cssFile = 
                        }
                    }
                }
            }
            String title = StringUtils.substringBetween(html, "<title>", "</title>");
            File titleFile = new File(rootDir, title);
            IOUtils.write(html, new BufferedWriter(new FileWriter(titleFile)));
            fileCount++;

        }
        return fileCount;
    }

}
