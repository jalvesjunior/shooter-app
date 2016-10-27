package br.com.shooter.model.dao;
import java.io.Serializable;

public abstract class BaseEntity<ID> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public abstract ID getId();
	public abstract void setId(ID id);
}