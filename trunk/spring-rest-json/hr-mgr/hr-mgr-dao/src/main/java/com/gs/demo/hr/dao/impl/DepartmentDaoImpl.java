/**
 * File :: com.gs.demo.hr.dao.impl.DepartmentDaoImpl
 * Date :: Apr 14, 2013
 */
package com.gs.demo.hr.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.gs.demo.hr.dao.DepartmentDao;
import com.gs.demo.hr.impl.HRCommonDaoImpl;
import com.gs.demo.hr.jdbc.ReflectionBasedRowMapper;
import com.gs.demo.hr.model.entity.Department;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
@Repository
public class DepartmentDaoImpl extends HRCommonDaoImpl<Department, Long> implements DepartmentDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 
	 */
	public DepartmentDaoImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/* (non-Javadoc)
	 * @see com.gs.demo.hr.dao.DepartmentDao#getRowCount()
	 */
	@Override
	public Long getRowCount() {
		return Long.valueOf(count());
	}

	@Override
	public Long getRowCount(String where) {
		String sql = "select count(*) as row_count from hrmgr_department " 
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
	public List<Department> getAllDepertments() {
		return getAll();
	}

	@Override
	public List<Department> getPagedDepertments(long limitFrom, long rowCount) {
		String sql = "select d from Department d order by d.id";
		
		List<Department> list = 
				getEntityManager().createQuery(sql)
		         .setFirstResult((int)limitFrom) // offset
		         .setMaxResults((int)rowCount) // limit
		         .getResultList();
		
		return list;
	}

	/* (non-Javadoc)
	 * @see com.gs.demo.hr.dao.DepartmentDao#getPagedDepertments(long, long, java.lang.String)
	 */
	@Override
	public List<Department> getPagedDepertments(long limitFrom, long rowCount,
			String order) {
		String sql = "select d from Department d order by d."+order;
		
		List<Department> list = 
				getEntityManager().createQuery(sql)
		         .setFirstResult((int)limitFrom) // offset
		         .setMaxResults((int)rowCount) // limit
		         .getResultList();
		
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.gs.demo.hr.dao.DepartmentDao#getPagedDepertments(long, long, java.lang.String, java.lang.String)
	 */
	@Override
	public List<Department> getPagedDepertments(long limitFrom, long rowCount,
			String where, String order) {
		String sql = "select d from Department d where d." 
			+ where + " order by d." + order;
		
		List<Department> list = 
				getEntityManager().createQuery(sql)
		         .setFirstResult((int)limitFrom) // offset
		         .setMaxResults((int)rowCount) // limit
		         .getResultList();
		
		return list;
	}
	
	@Override
	public Long saveDepartment(Department department) {
		Department department2 = merge(department);
		return department2.getId();
	}

	@Override
	public Department getById(Long id) {
		return getEntityManager().find(Department.class, id);
	}

	
	
}
