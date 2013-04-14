/**
 * 
 */
package net.sf.jquery.tags.ui.flexigrid.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class Flexigrid {

	private List<ColumnModel> columnModels;
	private List<SearchItem> searchItems;
	
	/**
	 * 
	 */
	public Flexigrid() {
		columnModels = new ArrayList<ColumnModel>();
		searchItems = new ArrayList<SearchItem>();
	}

	public List<ColumnModel> getColumnModels() {
		return columnModels;
	}

	public void setColumnModels(List<ColumnModel> columnModels) {
		this.columnModels = columnModels;
	}

	public List<SearchItem> getSearchItems() {
		return searchItems;
	}

	public void setSearchItems(List<SearchItem> searchItems) {
		this.searchItems = searchItems;
	}
	
	
}

class ColumnModel{
	
}

class SearchItem{
	
}