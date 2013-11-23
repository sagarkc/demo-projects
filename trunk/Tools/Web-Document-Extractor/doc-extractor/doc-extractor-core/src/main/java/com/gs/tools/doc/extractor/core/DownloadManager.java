/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gs.tools.doc.extractor.core;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.log4j.Logger;

/**
 *
 * @author Sabuj
 */
public class DownloadManager {
    
    private static DownloadManager downloadManager;
    private static final Logger logger = Logger.getLogger(DownloadManager.class);
    
    private final HttpClient httpClient;
    private final CookieStore cookieStore;
    
    private DownloadManager(){
        logger.info("Init DownloadManager");
        cookieStore = new BasicCookieStore();
        HttpClientBuilder clientBuilder = HttpClientBuilder.create();
        clientBuilder.setDefaultCookieStore(cookieStore);
        
        Collection<Header> headers = new ArrayList<Header>();
        headers.add(new BasicHeader("Accept", 
                "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8"));
        headers.add(new BasicHeader("User-Agent", 
                "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36"));
        headers.add(new BasicHeader("Accept-Encoding", 
                "gzip,deflate,sdch"));
        headers.add(new BasicHeader("Accept-Language", 
                "en-US,en;q=0.8"));
//        headers.add(new BasicHeader("Accept-Encoding", 
//                "gzip,deflate,sdch"));
        clientBuilder.setDefaultHeaders(headers);
        
        ConnectionConfig.Builder connectionConfigBuilder = ConnectionConfig.custom();
        connectionConfigBuilder.setBufferSize(10485760);
        clientBuilder.setDefaultConnectionConfig(connectionConfigBuilder.build());
        
        SocketConfig.Builder socketConfigBuilder = SocketConfig.custom();
        socketConfigBuilder.setSoKeepAlive(true);
        socketConfigBuilder.setSoTimeout(3000000);
        clientBuilder.setDefaultSocketConfig(socketConfigBuilder.build());
        logger.info("Create HTTP Client");
        httpClient = clientBuilder.build();
        
    }
    
    
    public static DownloadManager create(){
        if(null != downloadManager)
            return downloadManager;
        synchronized(DownloadManager.class){
            if(null == downloadManager)
                downloadManager = new DownloadManager();
        }
        return downloadManager;
    }
    
    public byte[] readContentFromGET(String url) throws Exception{
        byte[] result = null;
        logger.info("URL: " + url);
        HttpGet httpGet = new HttpGet(url);
        
        HttpResponse response = httpClient.execute(httpGet, new BasicHttpContext());
        if (null != response && response.getStatusLine().getStatusCode() == 200) {
			HttpEntity httpEntity = response.getEntity();
			if (null != httpEntity) {
				
				// read the response to String
				InputStream responseStream = httpEntity.getContent();
				if (null != responseStream) {
					BufferedInputStream inputStream = null;
                    ByteArrayOutputStream outputStream=null;
					try {
                        outputStream = new ByteArrayOutputStream();
						inputStream = new BufferedInputStream(responseStream);
						int count = 0;
                        long totalSize = 0L;
						int size = 1024;
						byte[] byteBuff = new byte[size];
						while ((count = inputStream.read(byteBuff, 0, size)) >= 0) {
                            totalSize += count;
							outputStream.write(byteBuff, 0, count);
						}
                        logger.info("Total Downloaded: " + totalSize + " Byte");
						result = outputStream.toByteArray();
					} catch (Exception ex) {
						ex.printStackTrace();
						throw ex;
					} finally {
						IOUtils.closeQuietly(responseStream);
						IOUtils.closeQuietly(inputStream);
						IOUtils.closeQuietly(outputStream);
					}
				}
			}
		}
        return result;
    }
}
