/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.model.vo.PermissionVo
 * Date:	Aug 20, 2013  5:47:45 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.model.vo;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public class PermissionVo implements IsSerializable, Serializable {

	private Long permissionId;
	private String permissionName;
	private String permissionDescription;
	private RoleVo roleVo;
	
	/**
	 * 
	 */
	public PermissionVo() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the permissionId
	 */
	public Long getPermissionId() {
		return permissionId;
	}

	/**
	 * @param permissionId the permissionId to set
	 */
	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}

	/**
	 * @return the permissionName
	 */
	public String getPermissionName() {
		return permissionName;
	}

	/**
	 * @param permissionName the permissionName to set
	 */
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	/**
	 * @return the permissionDescription
	 */
	public String getPermissionDescription() {
		return permissionDescription;
	}

	/**
	 * @param permissionDescription the permissionDescription to set
	 */
	public void setPermissionDescription(String permissionDescription) {
		this.permissionDescription = permissionDescription;
	}

	/**
	 * @return the roleVo
	 */
	public RoleVo getRoleVo() {
		return roleVo;
	}

	/**
	 * @param roleVo the roleVo to set
	 */
	public void setRoleVo(RoleVo roleVo) {
		this.roleVo = roleVo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((permissionId == null) ? 0 : permissionId.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof PermissionVo)) {
			return false;
		}
		PermissionVo other = (PermissionVo) obj;
		if (permissionId == null) {
			if (other.permissionId != null) {
				return false;
			}
		} else if (!permissionId.equals(other.permissionId)) {
			return false;
		}
		return true;
	}
	
}
