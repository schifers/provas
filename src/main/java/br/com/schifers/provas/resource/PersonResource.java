package br.com.schifers.provas.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.schifers.provas.entity.Person;

@Path("person")
public class PersonResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getPeople() {
        List<Person> people = new ArrayList<Person>();
        people.add(new Person());
        people.get(0).setId(1L);
        people.get(0).setUsername("Bruno");
        return people;
    }

}
