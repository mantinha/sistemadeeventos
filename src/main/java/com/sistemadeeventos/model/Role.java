package com.sistemadeeventos.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

@Entity
//@Table(name="TB_ROLE")
public class Role implements GrantedAuthority{
	private static final long serialVersionUID = 1L;
	@Id
	private String nomeRole;
	
	@ManyToMany(mappedBy = "roles")
	private List<Usuario> usuarios;

	public String getNameRole() {
		return nomeRole;
	}

	public void setNameRole(String nomeRole) {
		this.nomeRole = nomeRole;
	}

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.nomeRole;
	}	

}
