
package com.gs.demo.hr.model.vo;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class PaginationVo {

	private int resultPerPage 	= 10;
	private int pageNumber 		= 1;
	private String sortName 	;
	private String order 		= "asc";
	private String searchType 	;
	private String searchKey	;
	
	

	/**
	 * @param pageNumber
	 * @param resultPerPage
	 */
	public PaginationVo(int pageNumber, int resultPerPage) {
		this.pageNumber = pageNumber;
		this.resultPerPage = resultPerPage;
	}
	
	

	/**
	 * @param pageNumber
	 * @param resultPerPage
	 * @param sortName
	 * @param order
	 * @param searchType
	 * @param searchKey
	 */
	public PaginationVo(int pageNumber, int resultPerPage, String sortName,
			String order, String searchType, String searchKey) {
		this.pageNumber = pageNumber;
		this.resultPerPage = resultPerPage;
		this.sortName = (null != sortName && !"".equals(sortName.trim())) ? sortName : null;
		this.order = (null != order && !"".equals(order.trim())) ? order : null;
		this.searchType = (null != searchType && !"".equals(searchType.trim())) ? searchType : null;
		this.searchKey = (null != searchKey && !"".equals(searchKey.trim())) ? "'"+searchKey+"'" : null;
	}



	public int getResultPerPage() {
		return resultPerPage;
	}

	public void setResultPerPage(int resultPerPage) {
		this.resultPerPage = resultPerPage;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	
	
	
}
