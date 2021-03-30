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

import model.Pessoa;
import model.repository.PessoaRepository;

@Path("pessoa")
public class PessoaApi {
	@GET // /pessoa
	@Produces(MediaType.APPLICATION_JSON)
	public Set<Pessoa> getAll() {
		Set<Pessoa> pessoas = PessoaRepository.recuperarPessoas();
		return pessoas;
	}

	@GET // /pessoa/{id}
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("id") int id) {
		Pessoa pessoa = PessoaRepository.recuperarPessoaPorId(id);

		if (pessoa != null) {
			return Response.ok(pessoa, MediaType.APPLICATION_JSON).build();
		}
		else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@POST // /pessoa
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(Pessoa pessoa) throws URISyntaxException {
		int idPessoa = PessoaRepository.criarPessoa(pessoa);
		URI uri = new URI("/pessoa/" + idPessoa);
		return Response.created(uri).build();
	}

	@PUT // /pessoa/{id}
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response update(@PathParam("id") int id, Pessoa pessoa) {
		pessoa.setIdPessoa(id);

		if (PessoaRepository.atualizarPessoa(pessoa)) {
			return Response.ok().build();
		}
		else {
			return Response.notModified().build();
		}
	}

	@DELETE // /pessoa/{id}
	@Path("{id}")
	public Response delete(@PathParam("id") int id) {
		if (PessoaRepository.excluirPessoaPorId(id)) {
			return Response.ok().build();
		}
		else {
			return Response.notModified().build();
		}
	}
}
