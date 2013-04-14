/**
 * File :: net.sf.jquery.tags.ui.flexigrid.model.PaginationResult
 * Date :: Apr 14, 2013
 */
package com.gs.demo.hr.model.vo;

import java.util.ArrayList;
import java.util.List;

import com.gs.demo.hr.model.vo.PaginationVo;

import net.sf.jsonizer.core.Jsonizable;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class PaginationResultVo<T extends Jsonizable> {

	private long totalRecords;
	private PaginationVo paginationVo;
	private List<T> result;
	
	/**
	 * 
	 */
	public PaginationResultVo() {
		paginationVo = new PaginationVo(0, 0);
		result = new ArrayList<T>();
	}

	public long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}

	public PaginationVo getPaginationVo() {
		return paginationVo;
	}

	public void setPaginationVo(PaginationVo paginationVo) {
		this.paginationVo = paginationVo;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}
	
}
