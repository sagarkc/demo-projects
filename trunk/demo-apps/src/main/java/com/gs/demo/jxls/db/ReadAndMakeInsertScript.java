/**
 * 
 */
package com.gs.demo.jxls.db;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jxls.reader.ReaderBuilder;
import net.sf.jxls.reader.XLSReadStatus;
import net.sf.jxls.reader.XLSReader;

import com.gs.demo.jxls.vo.DDValsVO;
import com.gs.demo.jxls.vo.DropDownVO;

/**
 * @author sabuj.das
 *
 */
public class ReadAndMakeInsertScript {

	
	private static final List<String> WORK_SHEET_NAMES = new ArrayList<String>();
	
	static{
		WORK_SHEET_NAMES.add("Flow Type");
		WORK_SHEET_NAMES.add("Suffix");
		WORK_SHEET_NAMES.add("Prefix");
		//WORK_SHEET_NAMES.add("APO FPO State");
		WORK_SHEET_NAMES.add("APO FPO");
		WORK_SHEET_NAMES.add("Source");
		WORK_SHEET_NAMES.add("Type");
		WORK_SHEET_NAMES.add("Document Type");
		WORK_SHEET_NAMES.add("Remit Code");
		WORK_SHEET_NAMES.add("Building Status");
		WORK_SHEET_NAMES.add("Improvements");
		WORK_SHEET_NAMES.add("Occupancy ORE");
		WORK_SHEET_NAMES.add("Occupancy Type");
		WORK_SHEET_NAMES.add("Type ORE");
		WORK_SHEET_NAMES.add("FNMA Project Class");
		WORK_SHEET_NAMES.add("Project Type");
		WORK_SHEET_NAMES.add("FHLMC Project Class");
		WORK_SHEET_NAMES.add("MI Company Name");
		WORK_SHEET_NAMES.add("Owner Association");
		WORK_SHEET_NAMES.add("Previous Refi");
		WORK_SHEET_NAMES.add("Occupancy");
		WORK_SHEET_NAMES.add("Type subjprop");
		WORK_SHEET_NAMES.add("Investor Prev Loan");
		WORK_SHEET_NAMES.add("Waive Escrow");
		WORK_SHEET_NAMES.add("Mortgage Insurance");
		WORK_SHEET_NAMES.add("Rate Lock Extension");
		WORK_SHEET_NAMES.add("Spcl Employer Relsp");
		WORK_SHEET_NAMES.add("Rate Lock");
		//WORK_SHEET_NAMES.add("Program Feature");
		WORK_SHEET_NAMES.add("Requested Product");
		WORK_SHEET_NAMES.add("Secondary Financing");
		WORK_SHEET_NAMES.add("Employee Loan");
		WORK_SHEET_NAMES.add("Lien Position");
		WORK_SHEET_NAMES.add("Addl Loan Purpose");
		WORK_SHEET_NAMES.add("Loan Purpose");
		WORK_SHEET_NAMES.add("District Name");
		WORK_SHEET_NAMES.add("Affiliate Name");
		WORK_SHEET_NAMES.add("Appl Taken By");
		WORK_SHEET_NAMES.add("Desired Loan Type");
		WORK_SHEET_NAMES.add("Down Payment src 2");
		WORK_SHEET_NAMES.add("Ownership type");
		WORK_SHEET_NAMES.add("Down Payment src 1");
		WORK_SHEET_NAMES.add("Employment Type");
		WORK_SHEET_NAMES.add("Borr No Selection");
		WORK_SHEET_NAMES.add("Rent or Own");
		WORK_SHEET_NAMES.add("Married To");
		WORK_SHEET_NAMES.add("Payoff Option");
		//WORK_SHEET_NAMES.add("Lien ");
		WORK_SHEET_NAMES.add("Debt Type");
		WORK_SHEET_NAMES.add("Payoff Opt AddLiab");
		WORK_SHEET_NAMES.add("Lien Type");
		WORK_SHEET_NAMES.add("Is this a Lien");
		WORK_SHEET_NAMES.add("OpenLiab Type");
		//WORK_SHEET_NAMES.add("ID State");
		//WORK_SHEET_NAMES.add("ID Country");
		WORK_SHEET_NAMES.add("ID Type");
		WORK_SHEET_NAMES.add("Credit Provider");
		WORK_SHEET_NAMES.add("Owner Association");
		//WORK_SHEET_NAMES.add("Country Name");
		WORK_SHEET_NAMES.add("Martial Status");
		WORK_SHEET_NAMES.add("Best way to reach");
		WORK_SHEET_NAMES.add("Citizenship Type");
		//WORK_SHEET_NAMES.add("Borrower Brwr Dec");
		WORK_SHEET_NAMES.add("BK Type");
		WORK_SHEET_NAMES.add("Who Filed");
		WORK_SHEET_NAMES.add("NOD Resolution");
		WORK_SHEET_NAMES.add("Desired Uw Sys");
		WORK_SHEET_NAMES.add("Section No");
		WORK_SHEET_NAMES.add("VA Fund Fee Paid By");
		WORK_SHEET_NAMES.add("VA title Vested In");
		WORK_SHEET_NAMES.add("Addl Loan Purpose");
		WORK_SHEET_NAMES.add("Status");
		WORK_SHEET_NAMES.add("Closing Agent Type");
		WORK_SHEET_NAMES.add("Pay By");
		WORK_SHEET_NAMES.add("Reason Code");
		WORK_SHEET_NAMES.add("Approval");
		WORK_SHEET_NAMES.add("PreQual Type");
		WORK_SHEET_NAMES.add("Action");
		WORK_SHEET_NAMES.add("Alert Priority Type");
		WORK_SHEET_NAMES.add("Alert Disp Type");
		WORK_SHEET_NAMES.add("jiont type");
		};
	
	private static final String INSERT_DDVAL_PART_1 = "INSERT INTO TM_ADM_MSTR_DROP_DOWN_VALS " +
			"(CODE, DISPLAY_VALUE, DROP_DOWN_TYPE, ACTIVE_IND) VALUES ( ";
	private static final String INSERT_DDVAL_PART_2 = " ) ;";
	
	private static final String INSERT_DD_PART_1 = "INSERT INTO TM_ADM_MSTR_DROP_DOWNS " +
	"(DROP_DOWN_TYPE, DESCRIPTION, ACTIVE_IND, ADMIN_MANAGED_IND) VALUES ( ";
	private static final String INSERT_DD_PART_2 = " ) ;";
	
	private static final String WORK_SHEET_NAME = "WORK_SHEET_NAME";

	private static final String DROP_DOWN_SELECT = "DROP_DOWN_SELECT";
	
	private static final File JXLS_CFG_FILE = new File("D:\\POS_RnD\\ddv-jxls-cfg.xml");
	
	private static final File JXLS_CFG_FILE_FOR_DD = new File("D:\\POS_RnD\\dropdown-jxls-cfg.xml");
	
	private static final File JXLS_CFG_FILE_TO_READ = new File("D:\\POS_RnD\\temp-ddv-jxls-cfg.xml");
	
	private static final File EXCEL_FILE_TO_READ = new File("D:\\POS_RnD\\DropDownValues.xls");
	
	private static final StringBuffer QUERY_BUFFER = new StringBuffer();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(
					ReadAndMakeInsertScript.class.getResource("/files/sheets.txt").getFile()));
			String line = "";
			while((line = br.readLine()) != null){
				System.out.println("WORK_SHEET_NAMES.add(\""+ line +"\");");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}*/
		
		try{
			
			// read for DropDown
			List<DropDownVO> ddList = readFromExcelFileDD(EXCEL_FILE_TO_READ, JXLS_CFG_FILE_FOR_DD);
			formInsertScriptForDDs(ddList);
			// read for DropDownValues
			for (String sheetName : WORK_SHEET_NAMES) {
				writeCfg(readCfg(sheetName));
				System.out.println("CFG File : "+readCfg(sheetName));
				List<DDValsVO> list = readFromExcelFile(EXCEL_FILE_TO_READ, JXLS_CFG_FILE);
				formInsertScriptForDDVals(list, formatToColumnName(sheetName));
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("FINISHED");
		}
		writeScript(QUERY_BUFFER.toString());
	}
	
	public static void formInsertScriptForDDs(List<DropDownVO> list) {
		for (DropDownVO valsVO : list) {
			/*String q = "'" + formatToColumnName(valsVO.getDROP_DOWN_TYPE()) 
			+ "', '" + valsVO.getDESCRIPTION() + "', '"
			+ valsVO.getACTIVE_IND() + "', '" + valsVO.getADMIN_MANAGED_IND() + "'";*/
			QUERY_BUFFER.append("\n" + INSERT_DD_PART_1 + valsVO.toString() + INSERT_DD_PART_2);
		}
	}
	
	public static String formatToColumnName(String txt){
		return txt.toUpperCase().replaceAll(" ", "_").replaceAll("-", "");
	}
	
	public static void formInsertScriptForDDVals(List<DDValsVO> list, String sheetName) {
		for (DDValsVO valsVO : list) {
			QUERY_BUFFER.append("\n" + INSERT_DDVAL_PART_1 + valsVO.toString() 
					+ ", \"" + formatToColumnName(sheetName) + "\", \"Y\" " 
					+ INSERT_DDVAL_PART_2);
		}
	}

	public static List<DDValsVO> readFromExcelFile(File inputFile, File cfgFile) {
		Map<String, List<DDValsVO>> beans = new HashMap<String, List<DDValsVO>>();
		List<DDValsVO> mdmUserList = new ArrayList<DDValsVO>();
		XLSReader mainReader = null;
		try {
			mainReader = ReaderBuilder.buildFromXML( cfgFile );
            InputStream inputXLS = new BufferedInputStream(new FileInputStream(inputFile));
            List<DDValsVO> mdmUsers = new ArrayList<DDValsVO>();
            beans.put("ddvVoList", mdmUsers);
            XLSReadStatus readStatus = mainReader.read(inputXLS, beans);
            if(readStatus.isStatusOK()){
            	Collection<List<DDValsVO>> cc = beans.values();
            	for (DDValsVO userList : beans.get("ddvVoList")) {
            		mdmUserList.add(userList);
				}
            }
            
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(mainReader != null){
				mainReader = null;
			}
		}
		return mdmUserList;
	}
	
	public static List<DropDownVO> readFromExcelFileDD(File inputFile, File cfgFile) {
		Map<String, List<DropDownVO>> beans = new HashMap<String, List<DropDownVO>>();
		List<DropDownVO> mdmUserList = new ArrayList<DropDownVO>();
		try {
            XLSReader mainReader = ReaderBuilder.buildFromXML( cfgFile );
            InputStream inputXLS = new BufferedInputStream(new FileInputStream(inputFile));
            List<DropDownVO> mdmUsers = new ArrayList<DropDownVO>();
            beans.put("dropDownVOList", mdmUsers);
            XLSReadStatus readStatus = mainReader.read(inputXLS, beans);
            if(readStatus.isStatusOK()){
            	Collection<List<DropDownVO>> cc = beans.values();
            	for (DropDownVO userList : beans.get("dropDownVOList")) {
            		mdmUserList.add(userList);
				}
            }
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mdmUserList;
	}
	
	
	public static <T> List<T> readFromExcelFile1(InputStream is, File cfgFile, String beanKey) {
		Map<String, List<T>> beans = new HashMap<String, List<T>>();
		List<T> mdmUserList = new ArrayList<T>();
		try {
            XLSReader mainReader = ReaderBuilder.buildFromXML( cfgFile );
            InputStream inputXLS = new BufferedInputStream(is);
            List<T> mdmUsers = new ArrayList<T>();
            beans.put(beanKey, mdmUsers);
            XLSReadStatus readStatus = mainReader.read(inputXLS, beans);
            if(readStatus.isStatusOK()){
            	Collection<List<T>> cc = beans.values();
            	for (T userList : beans.get(beanKey)) {
            		mdmUserList.add(userList);
				}
            }
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mdmUserList;
	}
	
	
	public static String readCfg(String sheetName){
		StringBuffer buffer = new StringBuffer();
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(JXLS_CFG_FILE_TO_READ));
			String line = "";
			while((line = br.readLine()) != null){
				if(line.contains(WORK_SHEET_NAME)){
					line = line.replace(WORK_SHEET_NAME, sheetName);
				}
				buffer.append(line);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return buffer.toString();
	}
	
	public static void writeCfg(String txt){
		BufferedWriter br = null;
		try{
			br = new BufferedWriter(new FileWriter(JXLS_CFG_FILE));
			br.write(txt);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void writeScript(String txt){
		BufferedWriter br = null;
		try{
			br = new BufferedWriter(new FileWriter("D:\\POS_RnD\\scripts.sql"));
			br.write(txt);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
