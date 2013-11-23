/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gs.tools.doc.extractor.core.html;

import com.gs.tools.doc.extractor.core.DownloadManager;
import com.gs.tools.doc.extractor.core.WebDocumentExtractor;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author sabuj
 */
public  class HTMLDocumentExtractor implements WebDocumentExtractor {

    private static final Logger logger = Logger.getLogger(HTMLDocumentExtractor.class);
    private static final DownloadManager downloadManager
            = DownloadManager.create();
    
    private final Set<String> sourceUrlCache = new HashSet<String>();
    
    public HTMLDocumentExtractor() {
        
    }

    public String getFileExtension() {
        return ".html";
    }

    @Override
    protected void finalize() throws Throwable {

        super.finalize();
    }

//    public static void main(String[] args) {
//        try {
//            long l = new HTMLDocumentExtractor().extract(
//                    "http://docs.spring.io/spring/docs/3.2.x/spring-framework-reference/html/index.html",
//                    "d:\\temp\\x1\\Spring3.2.x");
//            System.out.println("FIle created: "  + l);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("done");
//    }

    public long extract(String sourceUrl, String targetFolderName) throws Exception {
        logger.info("Init extraction from: " + sourceUrl);
        logger.info("Disk location: " + targetFolderName);
        File rootDir = new File(targetFolderName);
        String rootLocation = sourceUrl.substring(0, sourceUrl.lastIndexOf("/"));
        String fileName = sourceUrl.substring(sourceUrl.lastIndexOf("/") + 1);
        long fileCount = 0;
        
        // download content
        byte[] contentByte = downloadManager.readContentFromGET(sourceUrl);
        sourceUrlCache.add(sourceUrl);
        if (null != contentByte && contentByte.length > 0) {
            // save the content
            File targetFile = new File(rootDir, fileName);
            logger.info("Save to: " + targetFile.getAbsolutePath());
            writeTo(contentByte,
                    new BufferedOutputStream(new FileOutputStream(targetFile)));
            fileCount++;
            logger.info("File count: " + fileCount);
            // get as text content
            String homePageContent = new String(contentByte, ENCODING_UTF_8);
            String headSection = StringUtils.substringBetween(homePageContent, "<head", "</head>");
            // download css from root page
            fileCount += downloadCss(rootDir, rootLocation, headSection);

            String bodySection = StringUtils.substringBetween(homePageContent, "<body", "</body>");
            // download other links
            
            fileCount += processBody(rootDir, rootLocation, bodySection);
        }
        return fileCount;
    }

    private long processBody(final File rootDir, final String rootLocation, 
            final String bodyContent) throws Exception{
        long fileCount = 0;
        String imgLinks[] = StringUtils.substringsBetween(bodyContent, "<img", ">");
        if(null != imgLinks && imgLinks.length > 0){
            for (String imgLink : imgLinks) {
                String img = StringUtils.substringBetween(imgLink, "src=\"", "\"");
                if(null != img && img.length() > 0){
                    File imgFile = new File(rootDir, img);
                    File imgFolder = new File(rootDir.getAbsolutePath());
                    String hrefLocation = rootLocation;
                    if (img.contains("/")) {
                        String path = img.substring(0, img.lastIndexOf("/"));
                        hrefLocation += "/" + path;
                        String name = img.substring(img.lastIndexOf("/") + 1);
                        imgFolder = new File(rootDir, path);
                        if (!imgFolder.exists()) {
                            imgFolder.mkdirs();
                        }
                        imgFile = new File(imgFolder, name);
                    }
                    if(!sourceUrlCache.contains(rootLocation + "/" + img)){
                        sourceUrlCache.add(rootLocation + "/" + img);
                        byte[] cssContentByte = downloadManager.readContentFromGET(rootLocation + "/" + img);
                        if (null != cssContentByte && cssContentByte.length > 0) {
                            logger.info("Save to: " + imgFile.getAbsolutePath());
                            writeTo(cssContentByte, new BufferedOutputStream(new FileOutputStream(imgFile)));
                            fileCount ++;
                        }
                    }
                }
            }
        }
        String[] anchors = StringUtils.substringsBetween(bodyContent, "<a", "</a>");
        if(null != anchors && anchors.length > 0){
            for (String anchor : anchors) {
                String href = StringUtils.substringBetween(anchor, "href=\"", "\"");
                if(null != href && href.length() > 0
                        && href.endsWith(getFileExtension())){
                    File hrefFile = new File(rootDir, href);
                    File hrefFolder = new File(rootDir.getAbsolutePath());
                    String hrefLocation = rootLocation;
                    if (href.contains("/")) {
                        String path = href.substring(0, href.lastIndexOf("/"));
                        hrefLocation += "/" + path;
                        String name = href.substring(href.lastIndexOf("/") + 1);
                        hrefFolder = new File(rootDir, path);
                        if (!hrefFolder.exists()) {
                            hrefFolder.mkdirs();
                        }
                        hrefFile = new File(hrefFolder, name);
                    }
                    if(!sourceUrlCache.contains(rootLocation + "/" + href)){
                        sourceUrlCache.add(rootLocation + "/" + href);
                        byte[] cssContentByte = downloadManager.readContentFromGET(rootLocation + "/" + href);
                        if (null != cssContentByte && cssContentByte.length > 0) {
                            logger.info("Save to: " + hrefFile.getAbsolutePath());
                            writeTo(cssContentByte, new BufferedOutputStream(new FileOutputStream(hrefFile)));
                            fileCount++;
                            logger.info("File count: " + fileCount);
                            String hrefContent = new String(cssContentByte, Charset.forName("UTF-8"));
                            String bodySection = StringUtils.substringBetween(hrefContent, "<body", "</body>");
                            fileCount += processBody(hrefFolder, hrefLocation, bodySection);
                        }
                    }
                }
            }
        }
        
        return fileCount;
    }
    
    private long downloadCss(final File rootDir, final String rootLocation,
            final String pageContent) throws Exception{
        long fileCount = 0;
        
        String[] cssLinks = StringUtils.substringsBetween(pageContent, "<link", ">");
        if (null != cssLinks && cssLinks.length > 0) {
            for (String link : cssLinks) {
                String rel = StringUtils.substringBetween(link, "rel=\"", "\"");
                if (rel.equalsIgnoreCase("stylesheet")) {
                    String css = StringUtils.substringBetween(link, "href=\"", "\"");
                    File cssFile = new File(rootDir, css);
                    File cssFolder = new File(rootDir.getAbsolutePath());
                    String cssLocation = rootLocation;
                    if (css.contains("/")) {
                        String path = css.substring(0, css.lastIndexOf("/"));
                        cssLocation += "/" + path;
                        String name = css.substring(css.lastIndexOf("/") + 1);
                        cssFolder = new File(rootDir, path);
                        if (!cssFolder.exists()) {
                            cssFolder.mkdirs();
                        }
                        cssFile = new File(cssFolder, name);
                    }
                    if(!sourceUrlCache.contains(rootLocation + "/" + css)){
                        sourceUrlCache.add(rootLocation + "/" + css);
                        byte[] cssContentByte = downloadManager.readContentFromGET(rootLocation + "/" + css);
                        if (null != cssContentByte && cssContentByte.length > 0) {
                            logger.info("Save to: " + cssFile.getAbsolutePath());
                            writeTo(cssContentByte, new BufferedOutputStream(new FileOutputStream(cssFile)));
                            fileCount++;
                            logger.info("File count: " + fileCount);
                            String cssContent = new String(cssContentByte, Charset.forName("UTF-8"));
                            fileCount += processCSSLinks(cssFolder, cssLocation, cssContent);
                        }
                    }
                }
            }
        }
        
        return fileCount;
    }
    
    private long processCSSLinks(final File rootDir, final String rootLocation, 
            final String cssContent) throws Exception{
        long fileCount = 0;
        String[] urlLinks = StringUtils.substringsBetween(
                cssContent, "url(\"", "\")");
        if(null != urlLinks && urlLinks.length > 0){
            for (String link : urlLinks) {
//                if(link.contains("..")){
//                    link = link.replaceAll("../", "/");
//                }
                String cssLocation = rootLocation;
                File targetFolder = new File(rootDir.getAbsolutePath());
                File targetFile = new File(rootDir, link);
                if (link.contains("/")) {
                    String path = link.substring(0, link.lastIndexOf("/"));
                    cssLocation += "/" + path;
                    String name = link.substring(link.lastIndexOf("/") + 1);
                    targetFolder = new File(rootDir, path);
                    if (!targetFolder.exists()) {
                        targetFolder.mkdirs();
                    }
                    targetFile = new File(targetFolder, name);
                }
                
                if(!sourceUrlCache.contains(rootLocation + "/" + link)){
                        sourceUrlCache.add(rootLocation + "/" + link);
                    byte[] targetContent = downloadManager.readContentFromGET(
                                rootLocation + "/" + link);
                    if(null != targetContent && targetContent.length > 0){
                        logger.info("Save to: " + targetFile.getAbsolutePath());
                        writeTo(targetContent, 
                                new BufferedOutputStream(new FileOutputStream(targetFile)));
                        fileCount++;
                            logger.info("File count: " + fileCount);
                        if(link.toLowerCase().endsWith(".css")){
                            fileCount += processCSSLinks(targetFolder, cssLocation, 
                                    new String(targetContent, ENCODING_UTF_8));
                        }
                    }
                }
                
            }
            
        }
        return fileCount;
    }
    
    private void saveTo(final File rootDir, final String fileName, 
            final byte[] contentByte) throws Exception{
        File targetFile = new File(rootDir, fileName);
        logger.info("Save to: " + targetFile.getAbsolutePath());
        writeTo(contentByte,
                new BufferedOutputStream(new FileOutputStream(targetFile)));
    }
    
    private void writeTo(byte[] data, OutputStream outputStream) throws IOException {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(data);
            byte[] buffer = new byte[1024 * 1024];
            int count = 0;
            while ((count = bais.read(buffer, 0, 1024 * 1024)) > 0) {
                outputStream.write(buffer, 0, count);
            }
            bais.close();
        } finally {
            outputStream.close();
        }

    }


}
