package webapis.SistemaControleBancarioAPI;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.ContaComum;
import model.repository.ContaComumRepository;

@Path("contacomum")
public class ContaComumApi {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Set<ContaComum> getAll() {
		Set<ContaComum> contas = ContaComumRepository.recuperarContasComuns();
		return contas;
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("id") long id) {
		ContaComum conta = ContaComumRepository.recuperarContaComumPorNumeroConta(id);
		
		if (conta != null) {
			return Response.ok(conta, MediaType.APPLICATION_JSON).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(ContaComum contaComum) throws URISyntaxException {
		long numeroConta = ContaComumRepository.criarContaComum(contaComum);
		URI uri = new URI("/contacomum/" + numeroConta);
		return Response.created(uri).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response update(@PathParam("id") long id, ContaComum contaComum) {
		contaComum.setNumeroConta(id);

		if (ContaComumRepository.atualizarContaComum(contaComum)) {
			return Response.ok().build();
		} else {
			return Response.notModified().build();
		}
	}

	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") long id) {
		if (ContaComumRepository.excluirContaComumPorNumeroConta(id)) {
			return Response.ok().build();
		} else {
			return Response.notModified().build();
		}
	}
}
