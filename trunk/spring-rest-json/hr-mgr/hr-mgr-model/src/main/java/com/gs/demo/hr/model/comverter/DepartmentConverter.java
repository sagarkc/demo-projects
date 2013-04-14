/**
 * File :: com.gs.demo.hr.model.comverter.DepartmentConverter
 * Date :: Apr 15, 2013
 */
package com.gs.demo.hr.model.comverter;

import java.util.HashSet;

import com.gs.demo.hr.model.comverter.conversion.GenericConverter;
import com.gs.demo.hr.model.entity.Department;
import com.gs.demo.hr.model.vo.DepartmentVo;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class DepartmentConverter {

	public static DepartmentVo convertToVo(Department source){
		if (null == source)
			return null;
		DepartmentVo deptVo = null;
		try {
			deptVo = GenericConverter.<DepartmentVo, Department>convert(source, new HashSet<Integer>());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return deptVo;
	}
	
	public static Department convertToModel(DepartmentVo source){
		if (null == source)
			return null;
		Department deptModel = null;
		try {
			deptModel = GenericConverter.<Department, DepartmentVo>convert(source, new HashSet<Integer>());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return deptModel;
	}
	
}
