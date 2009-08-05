package com.gs.demo.jxls.reader;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jxls.reader.ReaderBuilder;
import net.sf.jxls.reader.XLSReadStatus;
import net.sf.jxls.reader.XLSReader;

import com.gs.demo.jxls.vo.MtgBankVo;

public class DemoExcelReader {

	static String xmlConfig = "/jxls/bank-jxls-config.xml";
	static String dataXLS = "d:/files/Mortgage POS.xls";
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//new DemoExcelReader().readExcel();
		File f = new File(dataXLS);
				//DemoExcelReader.class.getResource(dataXLS).getFile());
		if(f.exists()){
			List<MtgBankVo> l = new DemoExcelReader().
			readFromExcelFile(f);
		}else{
			System.err.println("File Not found: "+f.getAbsolutePath());
		}
		
	}
	
	private static final String EXCEL_READER_CONFIG_XML = "/jxls/bank-jxls-config.xml";
	
	public List<MtgBankVo> readFromExcelFile(File inputExcelFile) {
		Map<String, List<MtgBankVo>> beans = new HashMap<String, List<MtgBankVo>>();
		List<MtgBankVo> mdmUserList = new ArrayList<MtgBankVo>();
		try {
			InputStream inputXML = new BufferedInputStream(getClass().getResourceAsStream(EXCEL_READER_CONFIG_XML));
            XLSReader mainReader = ReaderBuilder.buildFromXML( inputXML );
            InputStream inputXLS = new BufferedInputStream(new FileInputStream(inputExcelFile));
            List<MtgBankVo> mdmUsers = new ArrayList<MtgBankVo>();
            beans.put("bankVoList", mdmUsers);
            XLSReadStatus readStatus = mainReader.read(inputXLS, beans);
            if(readStatus.isStatusOK()){
            	Collection<List<MtgBankVo>> cc = beans.values();
            	
            	for (MtgBankVo userList : beans.get("bankVoList")) {
            		mdmUserList.add(userList);
				}
            }
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mdmUserList;
	}

	public void readExcel() {
		Map beans = new HashMap();
		String localVariable = new String();
		String rowNumber = new String();
		List<String> errorList = new ArrayList<String>();

		try {
			InputStream inputXML = new BufferedInputStream(getClass().getResourceAsStream(xmlConfig));
            XLSReader mainReader = ReaderBuilder.buildFromXML( inputXML );
            InputStream inputXLS = new BufferedInputStream(getClass().getResourceAsStream(dataXLS));
            MtgBankVo bankVo = new MtgBankVo();
            List bankVoList = new ArrayList();
            beans.put("bankVoList", bankVoList);
            XLSReadStatus readStatus = mainReader.read(inputXLS, beans);
            
            for(Object k : bankVoList){
            	System.out.println(k.toString());
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
