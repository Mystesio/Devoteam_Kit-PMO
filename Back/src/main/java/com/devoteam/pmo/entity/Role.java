package com.devoteam.pmo.entity;


import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.Id;



@Data

@Entity

public class Role {
    @Id

    private String roleName;

    private String roleDescription;

	public Role(String string, String string2) {
	
	}

	public Role() {
		
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public String getRoleName() {
		
		return roleName;
	}

	public void setRoleName(String roleName) {
	this.roleName= roleName;
	}
}
