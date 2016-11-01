package br.com.shooter.model.service.rs;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.shooter.model.dao.BodyPartDao;
import br.com.shooter.model.domain.BodyPart;

@Path("/bodyPart")
@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public class BodyPartService {

	@Inject
	private BodyPartDao bodyPartDao;

	@POST
	@Transactional
	public void save(BodyPart entity) {
		if(entity.getId() == null) {
			bodyPartDao.save(entity);
		} else {
			bodyPartDao.update(entity);
		}
	}

	@PUT
	@Transactional
	public void update(BodyPart entity) {
		bodyPartDao.update(entity);
	}

	@DELETE
	@Path("/{id}")
	@Transactional
	public void delete(@PathParam("id") Integer id) {
		bodyPartDao.delete(new BodyPart(id));
	}

	@GET
	@Path("/{id}")
	public BodyPart findById(@PathParam("id") Integer id) {
		return bodyPartDao.find(new BodyPart(id));
	}

	@GET
	public List<BodyPart> findAll() {
		return bodyPartDao.findAll();
	}
}