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
	public void salvar(Player jogador) {
		playerDao.salvar(jogador);
	}

	@PUT
	@Transactional
	public void atualizar(Player jogador) {
		playerDao.atualizar(jogador);
	}

	@DELETE
	@Path("/{id}")
	@Transactional
	public void excluir(@PathParam("id") Integer codigo) {
		playerDao.excluir(codigo);
	}
	
	@GET
	@Path("/{id}")
	public Player buscarPorId(@PathParam("id") Integer codigo) {
		return playerDao.buscarPorId(codigo);
	}

	@GET
	public List<Player> buscarTodos() {
		return playerDao.buscarTodos();
	}
}