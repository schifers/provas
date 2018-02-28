package br.com.schifers.provas.resource;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.schifers.provas.entity.Person;
import br.com.schifers.provas.service.PersonService;

@Path("person")
public class PersonResource {

	@Inject
	private PersonService personService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Person> getPeople() {
		return personService.findAll();
	}

}
