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

import br.com.shooter.model.dao.PlayerDao;
import br.com.shooter.model.domain.Player;

@Path("/player")
@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public class PlayerService {

	@Inject
	private PlayerDao playerDao;

	@POST
	@Transactional
	public void save(Player player) {
		if(player.getId() == null) {
			playerDao.save(player);
		} else {
			this.update(player);
		}
	}

	@PUT
	@Transactional
	public void update(Player player) {
		playerDao.update(player);
	}

	@DELETE
	@Path("/{id}")
	@Transactional
	public void excluir(@PathParam("id") Integer id) {
		playerDao.delete(new Player(id));
	}

	@GET
	@Path("/{id}")
	public Player findById(@PathParam("id") Integer id) {
		return playerDao.find(new Player(id));
	}

	@GET
	public List<Player> findAll() {
		return playerDao.findAll(Player.class);
	}
}