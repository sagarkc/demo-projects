

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class AlmoCatalogData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8781572456286925725L;
	
	private Collection<AlmoProductRecord> productRecordList;
	
	public AlmoCatalogData() {
		productRecordList = new ArrayList<AlmoProductRecord>();
	}

	public Collection<AlmoProductRecord> getProductRecordList() {
		return productRecordList;
	}

	public void setProductRecordList(Collection<AlmoProductRecord> productRecordList) {
		this.productRecordList = productRecordList;
	}
	

	public String toCsv(char delem) {
		StringBuffer buffer = new StringBuffer();
		for (AlmoProductRecord record : productRecordList) {
			buffer.append(record.toCsv(delem)).append("\n");
		}
		return buffer.toString();
	}

}
