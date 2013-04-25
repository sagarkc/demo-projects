package com.gs.demo.hr.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.gs.demo.hr.dao.EmployeeDao;
import com.gs.demo.hr.impl.HRCommonDaoImpl;
import com.gs.demo.hr.model.entity.Department;
import com.gs.demo.hr.model.entity.Employee;

public class EmployeeDaoImpl extends HRCommonDaoImpl<Employee, Long> implements
		EmployeeDao {
	
	@Autowired private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public Long getRowCount() {
		return Long.valueOf(count());
	}

	@Override
	public Long getRowCount(String where) {
		String sql = "select count(*) as row_count from hrmgr_employee " 
				+ (null != where && !"".equals(where.trim()) ? where : "") ;
		List<Long> counts = jdbcTemplate.query(sql, new RowMapper<Long>(){

			@Override
			public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getLong("row_count");
			}
			
		});
		
		return (null != counts ? counts.size() : 0L);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return getAll();
	}

	@Override
	public List<Employee> getPagedEmployees(long limitFrom, long rowCount) {
		String sql = "select e from Employee e order by e.id";
		
		List<Employee> list = 
				getEntityManager().createQuery(sql)
		         .setFirstResult((int)limitFrom) // offset
		         .setMaxResults((int)rowCount) // limit
		         .getResultList();
		
		return list;
	}

	@Override
	public List<Employee> getPagedEmployees(long limitFrom, long rowCount,
			String order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> getPagedEmployees(long limitFrom, long rowCount,
			String where, String order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub

	}

}
