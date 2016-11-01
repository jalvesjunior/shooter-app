package br.com.shooter.model.dao;

import javax.ejb.Stateless;

import br.com.shooter.model.domain.BodyPart;

@Stateless
public class BodyPartDao extends GenericDao<BodyPart, Integer>{

	public BodyPartDao() {
		super(BodyPart.class);
	}

}
